package com.example.parfumeria2.Model;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * @author : Tivadar Maria Simona
 *          gr 30223
 * @since : Apr 17 2022
 * Usage: strong example of using Reflection
 *          allows us to access al the fields needed, no matter the type of the object
 *          works hand in hand with the AbstractDAO
 * @see AbstractDAO
 *
 */

public class Reflection {

    public static void retrieveProperties(Object object) {

        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Object value;
            try {
                value = field.get(object);
                System.out.println(field.getName() + "=" + value);

            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
    }
    public static String[] retrieveColumn(Object object) {
        int size = object.getClass().getDeclaredFields().length;
        String columns[] = new String[size];
        int index = 0;
        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            columns[index] = field.getName() + " ";
            index++;
        }
        return columns;
    }
    public static Object[][] retrieveData(ArrayList<?> arrayList) {
        Object[][] obj = new Object[arrayList.size()][arrayList.getClass().getDeclaredFields().length];
        int jndex = 0;
        for (Object o : arrayList) {
            int index = 0;
            for (Field field : o.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                try {
                    obj[jndex][index] = field.get(o);
                    index++;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            jndex ++;
        }
        return obj;
    }
}