package hyfs.core.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import org.apache.log4j.Logger;

public class HyfsClient {

	private static Logger logger = Logger.getLogger(HyfsClient.class);

	public static void main(String[] args) throws Exception {

		send();
	}

	public static void send() {
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(group);
			b.channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true);
			b.handler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ChannelPipeline pipeline = ch.pipeline();
					pipeline.addLast(new ProtobufVarint32LengthFieldPrepender());
					pipeline.addLast(new ProtobufEncoder());
//                    pipeline.addLast(new ProtobufVarint32FrameDecoder());
//                    pipeline.addLast(new ProtobufDecoder(RequestMsg.Request.getDefaultInstance()));
					pipeline.addLast(new ProtoBufClientHandler());
				}
			});

			ChannelFuture f = b.connect("127.0.0.1", 8090).sync();
			f.channel().closeFuture().sync();

		} catch (Exception e) {
			logger.error("hyfs error" + e);
		} finally {
			group.shutdownGracefully();
		}
	}

}