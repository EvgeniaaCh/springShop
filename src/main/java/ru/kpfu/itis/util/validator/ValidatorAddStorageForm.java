package ru.kpfu.itis.util.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.kpfu.itis.form.add.AddStorageForm;

public class ValidatorAddStorageForm implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Override
    public void validate(Object o, Errors errors) {
        AddStorageForm addstorageForm = (AddStorageForm) o;
        if (addstorageForm.getCity() == null || addstorageForm.getCity().isEmpty()) {
            errors.rejectValue("city", "", "Поле не может быть пустым");
        }
        if (addstorageForm.getStreet() == null || addstorageForm.getStreet().isEmpty()) {
            errors.rejectValue("street", "", "Поле не может быть пустым");
        }
    }
}
