package utilities.web;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface TestRunInfo {

    public enum Priority {
        LOW, MEDIUM, HIGH
    }

    public Priority priority() default Priority.MEDIUM;

    public String[] tags() default "";
    public String testMethodName() default "";
}
