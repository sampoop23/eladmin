package me.zhengjie.nio.netty.udp.sever2.handler;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import me.zhengjie.modules.performance.domain.PerformanceDataTrashcan;
import me.zhengjie.modules.performance.service.PerformanceDataTrashcanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class UdpServerHandlerBak extends SimpleChannelInboundHandler<DatagramPacket> {

    public static UdpServerHandlerBak nettyUdpServerHandler;
    //
//    @Autowired
//    private JmsMessagingTemplate jmsMessagingTemplate;
    @Autowired
    private PerformanceDataTrashcanService performanceDataTrashcanService;

    public UdpServerHandlerBak() {
    }

    @PostConstruct
    public void init() {
        nettyUdpServerHandler = this;
        nettyUdpServerHandler.performanceDataTrashcanService = this.performanceDataTrashcanService;
//        nettyUdpServerHandler.jmsMessagingTemplate = this.jmsMessagingTemplate;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg) {
        log.info("开始接收数据");
        String msgString = msg.content().toString(CharsetUtil.UTF_8);
        // 将接收到数据放入ActiveMQ队列中
//        nettyUdpServerHandler.jmsMessagingTemplate.convertAndSend("mq", msgString);
        PerformanceDataTrashcan resources = new PerformanceDataTrashcan();
        resources.setGpsId("xxxx");
        resources.setStatus(1);
        resources.setWtdG(2);
        resources.setWtnG(3);
        resources.setErrInfo("dfsd");
        nettyUdpServerHandler.performanceDataTrashcanService.create(resources);
    }

}