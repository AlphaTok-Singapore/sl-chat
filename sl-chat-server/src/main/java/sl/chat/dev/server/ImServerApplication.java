package sl.chat.dev.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy(exposeProxy = true)
@MapperScan(basePackages = {"sl.chat.dev.server.mapper"})
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class ImServerApplication {

  public static void main(String[] args) {
    SpringApplication.run(ImServerApplication.class, args);

  }
}
