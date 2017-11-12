package hyfs.core.chunkserver.tpcfile;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author zhangzhiyuan
 * @date 12/11/2017
 * @time 4:39 PM
 * @email zhangzhiyuan@218.gmail
 */
public class FileUploadServerHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		super.channelRead(ctx, msg);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		super.exceptionCaught(ctx, cause);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {

	}
}
