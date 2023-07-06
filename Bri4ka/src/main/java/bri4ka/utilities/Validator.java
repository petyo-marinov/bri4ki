package bri4ka.utilities;

import bri4ka.exceptions.BadRequestException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    public static void validateNames(String firstName, String lastName) {
        String lettersBulg = "[А-Я][a-я]+";
        String lettersEng = "[A-Z][a-z]+";
        if((!firstName.matches(lettersBulg) || !lastName.matches(lettersBulg)) &&
                (!firstName.matches(lettersEng) || !lastName.matches(lettersEng))){
            throw new BadRequestException("Names must start with a capital letter and contain only " +
                    "letters from the same alphabet.");
        }
        if(firstName.length() < 1 || firstName.length() > 20) {
            throw new BadRequestException("First name must be between one and twenty characters.");
        }
        if(lastName.length() < 1 || lastName.length() > 20) {
            throw new BadRequestException("Last name must be between one and twenty characters.");
        }
    }

    public static void validateUsername(String username) {
        String characterRegex = "^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$";
        if(!username.matches(characterRegex)){
            throw new BadRequestException("Your username must be between 5 and 18 characters."+"\n"
                    +"It may contain letters, numbers, and in the middle - a single dot, hyphen, and/or underscore"+
                    "\n"+"Please try another.");
        }
    }

    public static void validatePassword(String password) {
        String capitalLetterRegex = "(.*[A-Z].*)";
        String digitRegex = "(.*\\d.*)";
        String lowerCaseRegex = "(.*[a-z].*)";
        if(!password.matches(capitalLetterRegex)){
            throw new BadRequestException("Your password must contain at least one uppercase letter. Please try another.");
        }
        if(!password.matches(digitRegex)){
            throw new BadRequestException("Your password must contain at least one digit. Please try another.");
        }
        if(!password.matches(lowerCaseRegex)){
            throw new BadRequestException("Your password must contain at least one lowercase letter. Please try another.");
        }
        {
            Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
            Matcher m = p.matcher(password);
            boolean containsASpecialCharacter = m.find();
            if(!containsASpecialCharacter){
                throw new BadRequestException("Your password must contain at least one special character." +
                        " Please try another. Accepted special characters: ` ~ ! @ # $ % ^ & * ( ) _ - + = ] } [ { ; : ' < > ? *");
            }
        }
    }

    public static void validateEmail(String email) {
        String regex = "^(.+)@(.+)$";
        String pattern =  "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        if(!email.matches(regex)){
            throw new BadRequestException("Please enter a valid email address.");
        }
    }

    public static void validateAge(int age) {
        if(age < 16 || age > 100){
            throw new BadRequestException("Invalid age.");
        }
    }
}
