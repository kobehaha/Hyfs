package hyfs.core.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;
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
					pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
					pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));
					pipeline.addLast("decoder", new StringDecoder(CharsetUtil.UTF_8));
					pipeline.addLast("encoder", new StringEncoder(CharsetUtil.UTF_8));

					pipeline.addLast("handler", new ClientHandler());
				}
			});

			ChannelFuture f = b.connect("127.0.0.1", 8090).sync();
			f.channel().writeAndFlush("hello---->rpc server!");
			f.channel().closeFuture().sync();

		} catch (Exception e) {
			logger.error("hyfs error" + e);
		} finally {
			group.shutdownGracefully();
		}
	}

}