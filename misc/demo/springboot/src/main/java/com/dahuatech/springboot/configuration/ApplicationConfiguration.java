package com.dahuatech.springboot.configuration;

import com.dahuatech.springboot.bean.Person;
import com.dahuatech.springboot.bean.Student;
import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.configuration</p>
 * <p>className: ApplicationConfiguration</p>
 * <p>date: 2023/3/19</p>
 *
 * @author qinjiawei(alan)
 * @version 1.0.0
 * @since JDK8.0
 */
@Configuration
// @EnableConfigurationProperties(Human.class)
// @Import({Human.class}) // 使用Import自动向IOC容器中注入对应类型的对象
public class ApplicationConfiguration {
    // 1.@Bean不设置value值则对象在IOC容器的name就是方法名
    // 2.方法参数Person对象会从IOC容器找查找并自动注入
    @Bean("student")
    public Student student(Person person) {
        return new Student("jack", 23);
    }


    @Bean
    public TomcatServletWebServerFactory tomcatServletWebServerFactory() {
        TomcatServletWebServerFactory tomcatServletWebServerFactory = new TomcatServletWebServerFactory() {
            @Override
            protected void postProcessContext(Context context) {
                SecurityConstraint constraint = new SecurityConstraint();
                constraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection collection = new SecurityCollection();
                collection.addPattern("/*");
                constraint.addCollection(collection);
                context.addConstraint(constraint);
            }
        };

        tomcatServletWebServerFactory.addAdditionalTomcatConnectors(createTomcatConnector());
        return tomcatServletWebServerFactory;
    }

    private Connector createTomcatConnector() {
        Connector connector = new
                Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        connector.setPort(8080);
        connector.setSecure(false);
        connector.setRedirectPort(8443);
        return connector;
    }
}