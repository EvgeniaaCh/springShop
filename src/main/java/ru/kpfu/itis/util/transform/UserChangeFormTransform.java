package ru.kpfu.itis.util.transform;

import ru.kpfu.itis.form.change.UserChangeForm;
import ru.kpfu.itis.model.User;

public class UserChangeFormTransform {

    public static User transform(UserChangeForm form) {
        User userOut = new User();
        User user = form.getUser();
        userOut.setId(user.getId());
        userOut.setUsername(user.getUsername());
        userOut.setEmail(user.getEmail());
        userOut.setPassword(user.getPassword());
        userOut.setRole(form.getRole());
        userOut.setis_confirm(form.isis_confirm());
        return userOut;
    }
}
