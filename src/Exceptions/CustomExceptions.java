package Exceptions;

import java.util.Collection;
import java.util.Map;

public class CustomExceptions {
    public static boolean checkIfEmpty(Object input) {
        if (input == null) {
            System.out.println("Input is empty (null)!");
        }
        else if (input instanceof String) {
            if (((String) input).isEmpty()) {
                System.out.println("Input is empty (String)!");
            }
            return false;
        }
        else if (input instanceof Collection<?>) {
            if (((Collection<?>) input).isEmpty()) {
                System.out.println("Input is empty (Collection<?>)!");
            }
            return false;
        }
        else if (input instanceof Map<?, ?>) {
            if (((Map<?, ?>) input).isEmpty()) {
                System.out.println("Input is empty (Map<?, ?>)!");
            }
            return false;
        }
        else if (input.getClass().isArray()) {
            if (((Object[]) input).length == 0) {
                System.out.println("Input is empty (Object[])!");
            }
            return false;
        }
        return true;
    }

    public static boolean idCheck(Integer id) {
        if (id < 0) {
            System.out.println("Id is lower than 0!");
            return false;
        }
        return true;
    }
}
