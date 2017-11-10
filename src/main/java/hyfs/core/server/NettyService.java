package hyfs.core.server;

import hyfs.proto.MsgProto;
import hyfs.util.DateUtil;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class NettyService {
	private static final String IP = "127.0.0.1";
	private static final int PORT = 8090;

	private static final EventLoopGroup bossGroup = new NioEventLoopGroup();
	private static final EventLoopGroup workerGroup = new NioEventLoopGroup();

	private NettyService() {
	}

	public static NettyService getInstance() {
		return SingletonHolder.instance;
	}

	public void start() throws Exception {

		System.out.println("game start : " + DateUtil.dateFormat(DateUtil.getCurrentUtilDate()));

		try {
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).option(ChannelOption.SO_BACKLOG, 100)
					.handler(new LoggingHandler(LogLevel.INFO))
					.childHandler(new ChannelInitializer<Channel>() {

						@Override
						public void initChannel(Channel ch) throws Exception {
							ChannelPipeline pipeline = ch.pipeline();
							pipeline.addLast(new ProtobufVarint32FrameDecoder());
							pipeline.addLast(new ProtobufDecoder(MsgProto.Msg.getDefaultInstance()));
							pipeline.addLast(new ProtobufVarint32LengthFieldPrepender());
							pipeline.addLast(new ProtobufEncoder());
							pipeline.addLast(new ProtoBufServerHandler());
						}

					});

			ChannelFuture cf = bootstrap.bind(IP, PORT).sync();

			System.out.println("game start complete : " + DateUtil.dateFormat(DateUtil.getCurrentUtilDate()));

			cf.channel().closeFuture().sync();
		} finally {
			workerGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();
		}
	}

	protected static void shutdown() {
		if (workerGroup != null) {
			workerGroup.shutdownGracefully();
		}
		if (bossGroup != null) {
			bossGroup.shutdownGracefully();
		}
	}

	private static final class SingletonHolder {
		private static final NettyService instance = new NettyService();
	}
}