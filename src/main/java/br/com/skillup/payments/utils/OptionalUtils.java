package br.com.skillup.payments.utils;

import java.util.function.Consumer;
import java.util.function.UnaryOperator;

public final class OptionalUtils {

    public static <T> UnaryOperator<T> peek(Consumer<T> consumer){
        return instance -> {
            consumer.accept(instance);
            return instance;
        };
    }
}
