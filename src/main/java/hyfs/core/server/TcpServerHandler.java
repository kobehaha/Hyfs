package hyfs.core.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.log4j.Logger;

public class TcpServerHandler extends ChannelInboundHandlerAdapter {

    private Logger logger = Logger.getLogger(TcpServerHandler.class);
    
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {

        logger.info("rpc server receive message:" + msg);

        ctx.channel().writeAndFlush("server already accept your message " + msg);
        ctx.close();
    }
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
	    logger.info("rpc server channel active");
    }
      @Override
   public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
	    logger.info("rpc server exception caught");
    }
}