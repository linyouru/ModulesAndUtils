package lyr.activemq.config;

import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerEndpoint;
import org.springframework.jms.config.JmsListenerEndpointRegistry;
import org.springframework.jms.config.MethodJmsListenerEndpoint;

/**
 * 设置JmsListener注解监听.给destination名称加上前缀
 * @ClassName CustomJmsListenerEndpointRegistry
 * @Description TODO
 * @Author LYR
 * @Date 2019/10/9 11:09
 * @Version 1.0
 **/
public class CustomJmsListenerEndpointRegistry extends JmsListenerEndpointRegistry {
    /**
     * 前缀
     */
    private String prefix;

    /**
     * 后缀
     */
    private String suffix;

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    @Override
    public void registerListenerContainer(JmsListenerEndpoint endpoint, JmsListenerContainerFactory<?> factory, boolean startImmediately) {
        MethodJmsListenerEndpoint methodEndpoint = (MethodJmsListenerEndpoint) endpoint;
        methodEndpoint.setDestination(prefix + methodEndpoint.getDestination());
        super.registerListenerContainer(endpoint, factory, startImmediately);
    }
}
