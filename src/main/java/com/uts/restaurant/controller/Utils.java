package com.uts.restaurant.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    private static String emailPattern = "^[^@]+@[^@]+\\.[^@]+$";      
    private static String namePattern = "[a-zA-Z]*";       
    private static String phonePattern = "04[0-9]{8}$";   

    public Utils(){}    

    private static boolean validate(String pattern, String input) {       
        Pattern regEx = Pattern.compile(pattern);       
        Matcher match = regEx.matcher(input);       
        return match.matches(); 
    }       

    public static boolean validateEmail(String email) {                       
        return validate(emailPattern,email);   
    }

    public static boolean validateName(String name) {
        return validate(namePattern,name); 
    }  

    public static boolean validatePhoneNo(String phoneNo) {
        return validate(phonePattern, phoneNo);
    }
}
