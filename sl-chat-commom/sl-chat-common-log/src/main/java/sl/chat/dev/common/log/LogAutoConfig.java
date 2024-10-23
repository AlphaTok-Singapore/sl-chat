package sl.chat.dev.common.log;

import sl.chat.dev.common.log.aop.ApiLogAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author muxiaohui
 * @date 2023-07-01 11:23
 */
@Configuration
public class LogAutoConfig {

  @Bean
  public ApiLogAspect apiLogAspect() {
    return new ApiLogAspect();
  }
}
