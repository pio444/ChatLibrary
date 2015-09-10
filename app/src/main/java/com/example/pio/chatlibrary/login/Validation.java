package com.example.pio.chatlibrary.login;

import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by bober on 09.09.15.
 */
public class Validation {

    public static final String TAG = Validation.class.getSimpleName();

    public static final String regexName = "[a-zA-Z][a-zA-Z_0-9]+";
    public static final String regexE_mail = "[a-zA-Z_0-9]+@[a-zA-Z_0-9]+\\.[a-zA-Z_0-9]+";
    public static final String regexFirstName = "[a-zA-Z]+";
    public static final String regexLastName = "[a-zA-Z]+";
    public static final String regexCity = "([a-zA-Z]+([ \\t\\n\\x0B\\f\\r])?)+";
    public static final String regexPostalCity = "[0-9]{2}-[0-9]{3}";
    public static final String regexStreet = "([a-zA-Z0-9]([ \\t\\n\\x0B\\f\\r])?)+";
    public static final String[] exclusionName = {"admin", "superuser", "administrator"};

    public static boolean validationName(String name) {
        if (name.length() >= 6 && name.length() <= 32 && validationStringName(name)) {
            Pattern pattern = Pattern.compile(regexName);
            Matcher matcher = pattern.matcher(name);
            boolean match = matcher.matches();
            return match;
        }
        return false;
    }

    private static boolean validationStringName(String name) {
        for (int i = 0; i < exclusionName.length; i++) {
            if (exclusionName[i].equals(name)) {
                return false;
            }
        }
        return true;
    }

    public static boolean validationE_mail(String e_mail) {
        if (e_mail.length() >= 6 && e_mail.length() <= 40) {
            Pattern pattern = Pattern.compile(regexE_mail);
            Matcher matcher = pattern.matcher(e_mail);
            boolean match = matcher.matches();
            return match;
        }
        return false;
    }

    public static boolean validationPassword(String password, String confirmPassword) {
        if (password.equals(confirmPassword) && validationStringPassword(password)) {
            return true;
        }
        return false;
    }

    private static boolean validationStringPassword(String password) {
        if (password.length() >= 8 && password.length() <= 50) {
            boolean cyfra = password.matches(".*[0-9].*");
            boolean duzaLitera = password.matches(".*[A-Z].*");
            boolean malaLitera = password.matches(".*[a-z].*");
            boolean cokolwiek = password.matches(".*[^[a-zA-Z_0-9]].*");
            boolean bialyZnak = password.matches(".*[ \\t\\n\\x0B\\f\\r].*");
            Log.d(TAG, password);
            Log.d(TAG, String.valueOf(cyfra));
            Log.d(TAG, String.valueOf(duzaLitera));
            Log.d(TAG, String.valueOf(malaLitera));
            Log.d(TAG, String.valueOf(cokolwiek));
            Log.d(TAG, String.valueOf(bialyZnak));
            if (cyfra && duzaLitera && malaLitera && cokolwiek && !bialyZnak) {
                return true;
            }
            return false;
        }
        return false;
    }

    public static boolean validationFirstName(String firstName) {
        if (firstName.length() >= 2 && firstName.length() <= 30) {
            Pattern pattern = Pattern.compile(regexFirstName);
            Matcher matcher = pattern.matcher(firstName);
            boolean match = matcher.matches();
            return match;
        }
        return false;
    }

    public static boolean validationLastName(String lastName) {
        if (lastName.length() >= 2 && lastName.length() <= 30) {
            Pattern pattern = Pattern.compile(regexLastName);
            Matcher matcher = pattern.matcher(lastName);
            boolean match = matcher.matches();
            return match;
        }
        return false;
    }

    public static boolean validationPesel(String pesel) {
        if (pesel.length() == 11) {
            return true;
        }
        return true;
    }

    public static boolean validationCity(String city) {
        if (city.length() >= 3 && city.length() <= 60) {
            Pattern pattern = Pattern.compile(regexCity);
            Matcher matcher = pattern.matcher(city);
            boolean match = matcher.matches();
            return match;
        }
        return false;
    }

    public static boolean validationPostalCity(String postalCity) {
        if (postalCity.length() == 6) {
            Pattern pattern = Pattern.compile(regexPostalCity);
            Matcher matcher = pattern.matcher(postalCity);
            boolean match = matcher.matches();
            return match;
        }
        return false;
    }

    public static boolean validationStreet(String street) {
        Pattern pattern = Pattern.compile(regexStreet);
        Matcher matcher = pattern.matcher(street);
        boolean match = matcher.matches();
        return match;
    }

    public static boolean validationHomeNumber(String homeNumber) {
        Log.d(TAG, homeNumber);
        if (homeNumber.length() >= 1 && homeNumber.length() <= 8) {
            return true;
        }
        return false;
    }

}
