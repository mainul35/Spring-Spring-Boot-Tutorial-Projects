package com.springtutorials.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class Validator {
    public static boolean isValidEmailAddress(String inputString) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(inputString);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }

    public static boolean isValidName(CharSequence inputString) {
        Pattern pattern = Pattern.compile(new String("^[a-zA-Z\\s]*$"));
        Matcher matcher = pattern.matcher(inputString);
        return matcher.matches()?true:false;
    }
    
    public static boolean isValidUsername(CharSequence inputString) {
        Pattern pattern = Pattern.compile(new String("[A-Za-z0-9_]+"));
        Matcher matcher = pattern.matcher(inputString);
        return matcher.matches()?true:false;
    }
    public static boolean isValidPassword(CharSequence inputString) {
        Pattern pattern = Pattern.compile(new String("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*[ \b\t\n\f\r\"\'\\ ]).{6,20})"));
        Matcher matcher = pattern.matcher(inputString);
        return matcher.matches()?true:false;
    }
    
}
