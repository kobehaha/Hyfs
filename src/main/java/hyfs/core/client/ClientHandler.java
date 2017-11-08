package hyfs.core.client;

import com.sun.deploy.util.SessionState;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.log4j.Logger;

public class ClientHandler extends ChannelInboundHandlerAdapter {

    private Logger logger = Logger.getLogger(SessionState.Client.class);
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        logger.info("client read message " + msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.info("client exception is general");
    }
    
}