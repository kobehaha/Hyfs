package hyfs.core.chunkserver.tpcfile;

import hyfs.core.beans.ChunkServerConfBean;
import hyfs.core.beans.ConfigBean;
import hyfs.core.chunkserver.ChunkServer;
import hyfs.core.chunkserver.rpc.ChunkServerRpcServer;
import hyfs.core.proto.RequestMsg;
import hyfs.core.server.ProtoBufServerHandler;
import hyfs.core.server.RpcService;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.serialization.ClassResolver;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import org.apache.log4j.Logger;

/**
 * @author zhangzhiyuan
 * @date 12/11/2017
 * @time 4:22 PM
 * @email zhangzhiyuan@218.gmail
 */
public class ChunkServerFileService {


	private static final EventLoopGroup bossGroup = new NioEventLoopGroup();
	private static final EventLoopGroup workerGroup = new NioEventLoopGroup();

	private Logger logger = Logger.getLogger(RpcService.class);

	private ChunkServerFileService() {
	}

	public static ChunkServerFileService getInstance() {
		return ChunkServerFileService.SingletonHolder.instance;
	}

	public void start(ConfigBean configBean) throws Exception {

		try {
			logger.info("chunkserver tcp service prepare ----> start ");

			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).option(ChannelOption.SO_BACKLOG, 1024).childHandler(new ChannelInitializer<Channel>() {

				@Override
				protected void initChannel(Channel ch) throws Exception {
					ch.pipeline().addLast(new ObjectEncoder());
					ch.pipeline().addLast(new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.weakCachingConcurrentResolver(null)));
					ch.pipeline().addLast(new FileUploadServerHandler());
				}
			});
			ChannelFuture f = b.bind(((ChunkServerConfBean) configBean).getFilePort()).sync();
			f.channel().closeFuture().sync();
			logger.info("chunkserver tcp server start complete");

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
		private static final ChunkServerFileService instance = new ChunkServerFileService();
	}
}
