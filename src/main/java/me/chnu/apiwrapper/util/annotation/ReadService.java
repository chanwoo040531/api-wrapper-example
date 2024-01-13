package me.chnu.apiwrapper.util.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
@Transactional(readOnly = true)
public @interface ReadService {

    /**
     * Alias for {@link Component#value}.
     */
    @AliasFor(annotation = Component.class)
    String value() default "";

}