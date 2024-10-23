package sl.chat.dev.server.config;

import sl.chat.dev.server.core.algorithm.RouteHandle;
import sl.chat.dev.server.core.algorithm.consistenthash.ConsistentHashHandle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author muxiaohui
 * @date 2023-06-12 20:43
 */
@Slf4j
@Configuration
public class AppConfig {

  @Bean
  public RouteHandle routeHandle() {
    return new ConsistentHashHandle();
  }

}
