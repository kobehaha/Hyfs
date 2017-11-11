package hyfs.core.server;

import hyfs.core.beans.ConfigBean;
import hyfs.core.proto.RequestMsg;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import org.apache.log4j.Logger;

public class RpcService {


	private static final EventLoopGroup bossGroup = new NioEventLoopGroup();
	private static final EventLoopGroup workerGroup = new NioEventLoopGroup();

	private Logger logger = Logger.getLogger(RpcService.class);

	private RpcService() {
	}

	public static RpcService getInstance() {
		return SingletonHolder.instance;
	}

	public void start(ConfigBean configBean) throws Exception {

		try {
			logger.info("rpc server prepare ----> start ");

			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
					.childHandler(new ChannelInitializer<Channel>() {

						@Override
						protected void initChannel(Channel ch) throws Exception {
							ChannelPipeline pipeline = ch.pipeline();
							pipeline.addLast(new ProtobufVarint32FrameDecoder());
							pipeline.addLast(new ProtobufDecoder(RequestMsg.Request.getDefaultInstance()));
							pipeline.addLast(new ProtoBufServerHandler());
						}

					})
					.option(ChannelOption.SO_BACKLOG,1024)
					.option(ChannelOption.SO_RCVBUF,32*1024)
					.option(ChannelOption.SO_SNDBUF,32*1034)
					.option(ChannelOption.SO_KEEPALIVE,true);


			ChannelFuture cf = bootstrap.bind(configBean.getIp(), configBean.getPort()).sync();

			logger.info("rpc server start complete -----> complete");

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
		private static final RpcService instance = new RpcService();
	}
}