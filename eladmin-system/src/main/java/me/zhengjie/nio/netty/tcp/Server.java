package me.zhengjie.nio.netty.tcp;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
//import io.netty.handler.codec.http.HttpRequestDecoder;
//import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

// 测试coder 和 handler 的混合使用
public class Server  implements ApplicationRunner {
    public void start(int port) throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            // 都属于ChannelOutboundHandler，逆序执行
//                            ch.pipeline().addLast(new HttpResponseEncoder());
                            ch.pipeline().addLast(new StringEncoder());

                            // 都属于ChannelIntboundHandler，按照顺序执行
//                            ch.pipeline().addLast(new HttpRequestDecoder());
                            ch.pipeline().addLast(new StringDecoder());
                            ch.pipeline().addLast(new BusinessHandler());
                        }
                    }).option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            ChannelFuture f = b.bind(port).sync();

            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        Server server = new Server();
        server.start(9000);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Server server = new Server();
        server.start(9000);
    }
}