package cn.myhydt.app.lookeureka.listener;

import com.netflix.eureka.EurekaServerConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.eureka.server.event.*;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 监听eureka事件 {@link org.springframework.cloud.netflix.eureka.server.event}  <br/>
 * <br/>
 * spring boot 内部监听方式有3种： <br/>
 * 1. @ {@link org.springframework.context.event.EventListener}注解  <br/>
 * 2. 实现{@link  org.springframework.context.ApplicationListener}泛型接口  <br/>
 * 3. 实现{@link  org.springframework.context.event.SmartApplicationListener}接口 <br/>
 * @author hy9902
 * @create 2018-09-27 9:50
 */
@Component
@Slf4j
public class EurekaListener {

    @EventListener
    public void logStartedEvent(EurekaServerStartedEvent event) {
        log.info("{} eureka started", event.getSource().toString());
        if(event.getSource() instanceof EurekaServerConfig){
            EurekaServerConfig config = (EurekaServerConfig)event.getSource();
        }
    }

    @EventListener
    public void logRegisteredEvent(EurekaInstanceRegisteredEvent event){
        log.info("{} eureka registered", event.getSource().toString());
    }

    @EventListener
    public void logRenewedEvent(EurekaInstanceRenewedEvent event){
        log.info("{} eureka renewed", event.getSource().toString());
    }

    @EventListener
    public void logCanceledEvent(EurekaInstanceCanceledEvent event){
        log.info("{} eureka canceled", event.getSource().toString());
    }

    @EventListener
    public void logAvailableEvent(EurekaRegistryAvailableEvent event){
        log.info("{} eureka available", event.getSource().toString());
    }
}
