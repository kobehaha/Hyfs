package hyfs.core.server;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import hyfs.proto.MsgProto;

public class ProtoBufServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        System.out.println("======server read msg start===");

        MsgProto.Msg cMsg = (MsgProto.Msg) msg;

        System.out.println("msg head is : "+cMsg.getMsghead());

        System.out.println("msg body is : "+cMsg.getMsgbody());

        System.out.println("======server read msg over===");

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
