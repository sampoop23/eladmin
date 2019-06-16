package me.zhengjie.nio.netty.udp.server0;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;
import lombok.extern.slf4j.Slf4j;
import me.zhengjie.nio.netty.udp.server1.UdpServerInitializer1;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UdpServer0 {

    private static int PORT = 9000;

    public static void start() {

        Bootstrap b = new Bootstrap();
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            b.group(group)
                    .channel(NioDatagramChannel.class)
                    .handler(new UdpServerInitializer1())//初始化处理器
                    .handler(new UdpSeverHandler0())
                    .option(ChannelOption.SO_BROADCAST, true)// 支持广播
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .option(ChannelOption.SO_RCVBUF, 1024 * 1024)// 设置UDP读缓冲区为1M
                    .option(ChannelOption.SO_SNDBUF, 1024 * 1024);// 设置UDP写缓冲区为1M
            ;

            log.info("【UdpServer】>>>>>> UdpServer STARTED");
            // 服务端监听在9999端口
            b.bind(PORT).sync().channel().closeFuture().await();
        } catch (Exception e) {
            log.error("【UdpServer】>>>>>> UdpServer ERROR", e);
            e.printStackTrace();
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        UdpServer0.start();
    }


}
