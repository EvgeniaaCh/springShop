package ru.kpfu.itis.util.validator;


import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.kpfu.itis.form.UserRegistrationForm;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidator implements Validator {

    private Pattern username = Pattern.compile("[A-Z, a-z, _, -]{3,15}");
    private Pattern password = Pattern.compile("[A-Z, a-z, 0-9]{6,20}");
    private Pattern email = Pattern.compile("[A-Z, a-z, 0-9, -, _, .]+@[a-z]+.[a-z]+");

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserRegistrationForm userForm = (UserRegistrationForm) o;
        if (userForm.getUsername() == null || userForm.getUsername().isEmpty()) {
            errors.rejectValue("username", "", "Поле не может быть пустым");
        }
        if (userForm.getEmail() == null || userForm.getEmail().isEmpty()) {
            errors.rejectValue("email", "", "Поле не может быть пустым");
        }
        if (userForm.getPassword() == null || userForm.getPassword().isEmpty()) {
            errors.rejectValue("password", "", "Поле не может быть пустым");
        }
        if (userForm.getRepassword() == null || userForm.getRepassword().isEmpty()) {
            errors.rejectValue("repassword", "", "Поле не может быть пустым");
        }

        Matcher usernameMatcher = username.matcher(userForm.getUsername());
        Matcher passwordMatcher = password.matcher(userForm.getPassword());
        Matcher repasswordMatcher = password.matcher(userForm.getRepassword());
        Matcher emailMatcher = email.matcher(userForm.getEmail());

        if (!usernameMatcher.matches()) {
            errors.rejectValue("username", "", "Имя не корректно");
        }

        if (!passwordMatcher.matches()) {
            errors.rejectValue("password", "", "Не корректный ввод пароля");
        }

        if (!repasswordMatcher.matches() || !Objects.equals(userForm.getPassword(), userForm.getRepassword())) {
            errors.rejectValue("repassword", "", "Не корректный ввод пароля");
        }

        if (!emailMatcher.matches()) {
            errors.rejectValue("email", "", "Не корректный ввод email");
        }
    }

}
