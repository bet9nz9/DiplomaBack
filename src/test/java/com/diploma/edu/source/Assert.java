package com.diploma.edu.source;

public class Assert extends org.springframework.util.Assert {

    public static void isEquals(Object obj1, Object obj2, String message){
         if (!obj1.equals(obj2)){
             throw new IllegalStateException(message);
         }
    }

    public static void assertNull(Object obj, String message){
        if (obj == null){
            throw new NullPointerException(message);
        }
    }

}
