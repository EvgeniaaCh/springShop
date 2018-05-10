package ru.kpfu.itis.form.change;

import ru.kpfu.itis.model.User;
import ru.kpfu.itis.model.enums.UserRole;

public class UserChangeForm {

    private User user;
    private UserRole role;
    private boolean is_confirm;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public boolean isis_confirm() {
        return is_confirm;
    }

    public void setis_confirm(boolean is_confirm) {
        this.is_confirm = is_confirm;
    }
}
