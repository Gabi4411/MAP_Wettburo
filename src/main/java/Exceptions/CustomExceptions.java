package Exceptions;

import java.util.Collection;
import java.util.Map;

public class CustomExceptions {
    public static boolean checkIfEmpty(Object input) {
        if (input == null) {
            return true;
        } else if (input instanceof String) {
            return ((String) input).isEmpty();
        } else if (input instanceof Collection<?>) {
            return ((Collection<?>) input).isEmpty();
        } else if (input instanceof Map<?, ?>) {
            return ((Map<?, ?>) input).isEmpty();
        } else if (input.getClass().isArray()) {
            return ((Object[]) input).length == 0;
        }
        return false;
    }



    public static boolean idCheck(Integer id) {
        if (id < 0) {
            System.out.println("Id is lower than 0!");
            return false;
        }
        return true;
    }
}
