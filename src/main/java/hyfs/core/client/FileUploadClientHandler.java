package hyfs.core.client;

import hyfs.core.chunkserver.tpcfile.FileUploadFile;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @author zhangzhiyuan
 * @date 12/11/2017
 * @time 8:43 PM
 * @email zhangzhiyuan@218.gmail
 */
public class FileUploadClientHandler extends ChannelInboundHandlerAdapter{
	private int byteRead;
	private volatile int start = 0;
	private volatile int lastLength = 0;
	public RandomAccessFile randomAccessFile;
	private FileUploadFile fileUploadFile;

	public FileUploadClientHandler(FileUploadFile ef) {
		if (ef.getFile().exists()) {
			if (!ef.getFile().isFile()) {
				System.out.println("Not a file :" + ef.getFile());
				return;
			}
		}
		this.fileUploadFile = ef;
	}

	public void channelActive(ChannelHandlerContext ctx) {
		System.out.println("????");
		try {
			randomAccessFile = new RandomAccessFile(fileUploadFile.getFile(), "r");
			randomAccessFile.seek(fileUploadFile.getStarPos());
			lastLength = (int) randomAccessFile.length() / 10;
			System.out.println("??? last lenth=" + lastLength);
			byte[] bytes = new byte[lastLength];
			if ((byteRead = randomAccessFile.read(bytes)) != -1) {
				fileUploadFile.setEndPos(byteRead);
				fileUploadFile.setBytes(bytes);
				ctx.writeAndFlush(fileUploadFile);
				System.out.println("send-------");
			} else {
				System.out.println("文件已经读完");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException i) {
			i.printStackTrace();
		}
	}
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println("read ......");
		if (msg instanceof Integer) {
			start = (Integer) msg;
			if (start != -1) {
				randomAccessFile = new RandomAccessFile(fileUploadFile.getFile(), "r");
				randomAccessFile.seek(start);
				System.out.println("块儿长度：" + (randomAccessFile.length() / 10));
				System.out.println("长度：" + (randomAccessFile.length() - start));
				int a = (int) (randomAccessFile.length() - start);
				int b = (int) (randomAccessFile.length() / 10);
				if (a < b) {
					lastLength = a;
				}
				byte[] bytes = new byte[lastLength];
				System.out.println("-----------------------------" + bytes.length);
				if ((byteRead = randomAccessFile.read(bytes)) != -1 && (randomAccessFile.length() - start) > 0) {
					System.out.println("byte 长度：" + bytes.length);
					fileUploadFile.setEndPos(byteRead);
					fileUploadFile.setBytes(bytes);
					try {
						ctx.writeAndFlush(fileUploadFile);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					randomAccessFile.close();
					ctx.close();
					System.out.println("文件已经读完--------" + byteRead);
				}
			}
		}
	}
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		ctx.close();
	}

}
