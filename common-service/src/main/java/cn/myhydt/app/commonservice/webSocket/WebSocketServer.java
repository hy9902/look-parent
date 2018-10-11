package cn.myhydt.app.commonservice.webSocket;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.SelfSignedCertificate;
import io.netty.util.concurrent.ImmediateEventExecutor;
import io.netty.util.internal.PlatformDependent;

import javax.net.ssl.SSLException;
import java.security.cert.CertificateException;

/**
 * netty版 webSocket启动类
 *
 * @author hy9902
 * @create 2018-09-26 11:53
 */
public final class WebSocketServer {

    /** 是否启用 ssl */
    final static boolean SSL = System.getProperty("ssl") != null;
    /** 端口号，默认8080 */
    private int port;
    /** 服务启动对象 */
    private ServerBootstrap serverBootstrap;
    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;

    /** 服务器启动channel */
    private Channel serverChannel;
    /** 管理所有websocket channel */
    private final ChannelGroup channelGroup = new DefaultChannelGroup(ImmediateEventExecutor.INSTANCE);


    public WebSocketServer(){
        this.port = 8080;
    }

    public WebSocketServer(int port){
        this.port = port;
    }

    public WebSocketServer init() {
        // 配置ssl
        try {
            final SslContext sslCtx;
            if(SSL){
                SelfSignedCertificate ssc = new SelfSignedCertificate();
                sslCtx = SslContextBuilder.forServer(ssc.certificate(), ssc.privateKey()).build();
            } else {
                sslCtx = null;
            }

            //配置启动类
            Class channelClass;
            if(PlatformDependent.isWindows()){
                bossGroup = new NioEventLoopGroup();
                workerGroup = new NioEventLoopGroup();
                channelClass = NioServerSocketChannel.class;
            } else {
                bossGroup = new EpollEventLoopGroup();
                workerGroup = new EpollEventLoopGroup();
                channelClass = EpollServerSocketChannel.class;
            }

            serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(channelClass)
                    .childOption(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new WebSocketServerInitializer(sslCtx, channelGroup));


        } catch (CertificateException ce){
            ce.printStackTrace();
        } catch (SSLException ssle) {
            ssle.printStackTrace();
        }
        return this;
    }

    public void start() {
        try {
            ChannelFuture channelFuture = serverBootstrap.bind(port);
            channelFuture.syncUninterruptibly();
            serverChannel = channelFuture.channel();
            serverBootstrap.bind(port).sync().channel().closeFuture().sync();
        } catch (InterruptedException ie){
            ie.printStackTrace();
        } finally {
            shutdown();
        }

    }

    public void shutdown(){
        if(serverChannel != null){
            serverChannel.close();
        }
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }


    public static void main(String[] args) {
        WebSocketServer server = new WebSocketServer(8080);
        server.init().start();
    }

}
