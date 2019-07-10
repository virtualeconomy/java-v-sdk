package v.systems.type;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Base58Field {
    boolean isFixedLength() default true;
}
