package com.spring.boot.autoConfiguration.example.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import spring.boot.common.zookeeper.ZookeeperService;
import spring.boot.common.zookeeper.config.ZookeeperConfiguration;

/**
 * @author : yanwenhui
 * @description :
 * @date : 2020/8/25
 */
@Configuration
@ConditionalOnClass({ZookeeperService.class})
public class ZookeeperAutoConfiguration {

    @Configuration
    @Import({ZookeeperConfiguration.class})
    public static class ZookeeperServiceConfiguration {

        private CuratorFramework curatorTemplate;

        public ZookeeperServiceConfiguration(CuratorFramework curatorTemplate) {
            this.curatorTemplate = curatorTemplate;
        }

        @Bean
        public ZookeeperService zookeeperService() {
            return new ZookeeperService(this.curatorTemplate);
        }

    }

}
