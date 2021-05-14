package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)


public @interface Repeat {

    /**
     * Custom annotation. Will repeat Runnable run() method as many times
     * as given in annotation argument.
     * @see Runnable
     */
    int value();
}
