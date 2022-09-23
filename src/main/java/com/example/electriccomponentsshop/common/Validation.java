package com.example.electriccomponentsshop.common;

import org.springframework.security.core.parameters.P;

public class Validation {
    public static final String NUMBER_FORMAT = "^[1,9][0,9]+$";
    public static int getInt(String number){
        try{
            int value = Integer.parseInt(number);
            return value;
        }catch (NumberFormatException e){
            if(number.matches(NUMBER_FORMAT)){
                return -1;
            }
            else {
                return -2;
            }
        }
    }

}
