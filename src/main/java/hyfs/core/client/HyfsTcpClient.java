package hyfs.core.client;

import hyfs.core.chunkserver.tpcfile.FileUploadFile;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.util.internal.SystemPropertyUtil;

import java.io.File;

/**
 * @author zhangzhiyuan
 * @date 12/11/2017
 * @time 8:35 PM
 * @email zhangzhiyuan@218.gmail
 */
public class HyfsTcpClient {



	public void connect(int port, String host, final FileUploadFile fileUploadFile) throws Exception {
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true).handler(new ChannelInitializer<Channel>() {

				@Override
				protected void initChannel(Channel ch) throws Exception {
					ch.pipeline().addLast(new ObjectEncoder());
					ch.pipeline().addLast(new ObjectDecoder(ClassResolvers.weakCachingConcurrentResolver(null)));
					ch.pipeline().addLast(new FileUploadClientHandler(fileUploadFile));

					System.out.println("xxx");
				}
			});

			ChannelFuture f = b.connect(host, port).sync();
			f.channel().closeFuture().sync();

			System.out.println("xxdsfasfx");
		} catch (Exception e){
			e.printStackTrace();
		}finally {
			group.shutdownGracefully();
		}
	}

	public static void main(String[] args) {
		int port = 9001;
		try {
			FileUploadFile uploadFile = new FileUploadFile();
			File file = new File("/Users/kobe/Desktop/Concurrent_programming.pdf");
			System.out.println("???/");
			String fileMd5 = file.getName();// 文件名
			uploadFile.setFile(file);
			uploadFile.setFile_md5(fileMd5);
			uploadFile.setStarPos(0);// 文件开始位置
			new HyfsTcpClient().connect(port, "127.0.0.1", uploadFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
