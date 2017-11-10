package hyfs.core.server;


import com.sun.deploy.util.SessionState;
import hyfs.proto.MsgProto;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.log4j.Logger;

public class ProtoBufServerHandler extends ChannelInboundHandlerAdapter {

    private Logger logger = Logger.getLogger(SessionState.Client.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        logger.info("======server read msg start===");


        MsgProto.Msg cMsg = (MsgProto.Msg) msg;

        logger.info("msg head is : "+cMsg.getMsghead());

        logger.info("msg body is : "+cMsg.getMsgbody());

        logger.info("======server read msg over===");

        MsgProto.Msg.Builder sBuilder = MsgProto.Msg.newBuilder();

        sBuilder.setMsghead("head $");
        sBuilder.setMsgbody("msg is receive");
        ctx.channel().writeAndFlush(sBuilder.build());


    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
