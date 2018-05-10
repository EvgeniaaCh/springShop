package ru.kpfu.itis.form.add;

import ru.kpfu.itis.model.Accounting;

public class AddCountForm {

    private Accounting accounting;
    private int count;

    public Accounting getAccounting() {
        return accounting;
    }

    public void setAccounting(Accounting accounting) {
        this.accounting = accounting;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
