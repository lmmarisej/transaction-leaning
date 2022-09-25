package org.lmmarise.transaction.springdtx.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.connection.JmsTransactionManager;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.transaction.PlatformTransactionManager;

import javax.jms.ConnectionFactory;


@EnableJms
@Configuration
public class JmsConfig {
    private static final Logger LOG = LoggerFactory.getLogger(CustomerService.class);

    @Bean
    public JmsTemplate initJmsTemplate(ConnectionFactory connectionFactory) {
        LOG.debug("init jms template with converter.");
        JmsTemplate template = new JmsTemplate();
        template.setConnectionFactory(connectionFactory); // JmsTemplate使用的connectionFactory跟JmsTransactionManager使用的必须是同一个，不能在这里封装成caching之类的。
        return template;
    }
    
    @Bean
    public PlatformTransactionManager transactionManager(ConnectionFactory connectionFactory) {
        return new JmsTransactionManager(connectionFactory);
    }
    
    // 这个用于设置 @JmsListener 使用的 containerFactory
    @Bean
    public JmsListenerContainerFactory<?> msgFactory(ConnectionFactory connectionFactory,
                                                     DefaultJmsListenerContainerFactoryConfigurer configurer,
                                                     PlatformTransactionManager transactionManager) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setTransactionManager(transactionManager);
        factory.setCacheLevelName("CACHE_CONNECTION");      // 避免 JmsListener 调用完毕把其他 JmsListener 共用的链接关闭
        factory.setReceiveTimeout(10000L);
        configurer.configure(factory, connectionFactory);
        return factory;
    }

}
