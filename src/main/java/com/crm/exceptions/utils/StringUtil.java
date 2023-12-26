package com.crm.exceptions.utils;
public class StringUtil {
    public static boolean validarString(String input){
        if(input == null || input == ""){
            return true;
        }
        return false;
    }
}
