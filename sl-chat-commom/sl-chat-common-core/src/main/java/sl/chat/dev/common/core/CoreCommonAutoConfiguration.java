package sl.chat.dev.common.core;

import sl.chat.dev.common.core.utils.SpringContextHolder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author: muxiaohui
 * @date: 2023/6/12 16:01
 */
@Configuration
public class CoreCommonAutoConfiguration {

  @Bean
  public SpringContextHolder springContextHolder() {
    return new SpringContextHolder();
  }

}
