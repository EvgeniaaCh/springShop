package ru.kpfu.itis.form;

public class UserForm {

    private long user_id;
    private  String role;
    private boolean is_confirm;

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean getIs_confirm() {
        return is_confirm;
    }

    public void setIs_confirm(boolean is_confirm) {
        this.is_confirm = is_confirm;
    }
}
