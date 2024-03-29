package com.github.rimuruchan.rush.command;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Cmd {

    String value();

    boolean IgnoreCase() default true;

    String permission() default "rush.default";

    CmdSender cmdSender() default CmdSender.BOTH;

    enum CmdSender {
        CONSOLE, PLAYER, BOTH
    }
}
