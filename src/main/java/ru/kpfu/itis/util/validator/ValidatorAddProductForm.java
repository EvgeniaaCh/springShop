package ru.kpfu.itis.util.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.kpfu.itis.form.add.AddProductForm;

public class ValidatorAddProductForm implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Override
    public void validate(Object o, Errors errors) {
        AddProductForm addProductForm = (AddProductForm) o;

        if (addProductForm.getName() == null || addProductForm.getName().isEmpty()) {
            errors.rejectValue("name", "", "Поле не может быть пустым");
        }
        if (addProductForm.getDescription() == null || addProductForm.getDescription().isEmpty()) {
            errors.rejectValue("description", "", "Поле не может быть пустым");
        }
    }
}
