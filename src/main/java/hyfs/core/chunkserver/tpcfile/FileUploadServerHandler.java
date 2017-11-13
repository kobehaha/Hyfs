package hyfs.core.chunkserver.tpcfile;

import hyfs.core.beans.ChunkServerConfBean;
import hyfs.core.config.ChunkServerConfigLoader;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.internal.SystemPropertyUtil;

import java.io.File;
import java.io.RandomAccessFile;

/**
 * @author zhangzhiyuan
 * @date 12/11/2017
 * @time 4:39 PM
 * @email zhangzhiyuan@218.gmail
 */
public class FileUploadServerHandler extends ChannelInboundHandlerAdapter {

	private ChunkServerConfBean configBean = (ChunkServerConfBean)(new ChunkServerConfigLoader().load());
	private volatile int start =0;
	private int byteRead;


	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("active?????");
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		//int start = 0;

		System.out.println("rexxxxxxxxxxxxx");
		if (msg instanceof FileUploadFile) {
			FileUploadFile ef = (FileUploadFile) msg;
			byte[] bytes = ef.getBytes();
			byteRead = ef.getEndPos();
			String md5 = ef.getFile_md5();//
			String path = configBean.getDataDir() + File.separator + md5;
			File file = new File(path);
			RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
			randomAccessFile.seek(start);
			randomAccessFile.write(bytes);
			start = start + byteRead;
			if (byteRead > 0) {
				ctx.writeAndFlush(start);
			} else {
				randomAccessFile.close();
				ctx.close();
			}
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {

	}
}
