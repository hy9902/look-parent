package cn.myhydt.app.commonservice.webSocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.SelfSignedCertificate;

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


    public WebSocketServer(){
        this.port = 8080;
    }

    public WebSocketServer(int port){
        this.port = port;
    }

    public void init() {
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
            bossGroup = new NioEventLoopGroup();
            workerGroup = new NioEventLoopGroup();
            serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new WebSocketServerInitializer(sslCtx));
        } catch (CertificateException ce){
            ce.printStackTrace();
        } catch (SSLException ssle){
            ssle.printStackTrace();
        }


    }
}
