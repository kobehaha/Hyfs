package hyfs.core.client;

import hyfs.proto.MsgProto;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ProtoBufClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        MsgProto.Msg cMsg = (MsgProto.Msg) msg;

        System.out.println("client receive msg head is : "+cMsg.getMsghead());

        System.out.println("client receive msg body is : "+cMsg.getMsgbody());

    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println("======client send msg start===");
        MsgProto.Msg.Builder builder = MsgProto.Msg.newBuilder();

        builder.setMsghead("head $");
        builder.setMsgbody("hello proto");

        ctx.writeAndFlush(builder.build());
        System.out.println("======client send msg over===");
    }
}
