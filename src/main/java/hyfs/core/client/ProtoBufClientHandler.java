package hyfs.core.client;

import com.sun.deploy.util.SessionState;
import hyfs.core.proto.RequestMsg;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.log4j.Logger;

public class ProtoBufClientHandler extends ChannelInboundHandlerAdapter {

    private Logger logger = Logger.getLogger(SessionState.Client.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
      super.channelRead(ctx,msg);
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        logger.info("======client send msg start===");
       RequestMsg.Request.Builder builder =  RequestMsg.Request.newBuilder();

        builder.setId(20);
        builder.setBody("hello proto");

        ctx.writeAndFlush(builder.build());
        logger.info("======client send msg over===");
    }
}
