package hyfs.core.server;


import com.sun.deploy.util.SessionState;
import hyfs.core.proto.RequestMsg;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.log4j.Logger;

public class ProtoBufServerHandler extends ChannelInboundHandlerAdapter {

    private Logger logger = Logger.getLogger(SessionState.Client.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        logger.info("======server read msg start===");

        RequestMsg.Request cMsg = (RequestMsg.Request) msg;

        logger.info("msg head is : "+cMsg.getId());

        logger.info("msg body is : "+cMsg.getMsgbody());

        logger.info("======server read msg over===");

    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
