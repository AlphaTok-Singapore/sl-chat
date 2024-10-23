package sl.chat.dev.common.publics.sensitive.common.annotation;

import java.lang.annotation.*;

/**
 * @author muxiaohui
 */
@Documented
@Inherited
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ThreadSafe {

}
