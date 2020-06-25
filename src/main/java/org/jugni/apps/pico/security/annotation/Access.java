package org.jugni.apps.pico.security.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import org.jugni.apps.pico.security.Rol;

@Target(TYPE)
@Retention(RUNTIME)
public @interface Access {
  String name() default "";

  Rol rol();
}
