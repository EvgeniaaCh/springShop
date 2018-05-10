package ru.kpfu.itis.util.transform;

import ru.kpfu.itis.form.add.AddStorageForm;
import ru.kpfu.itis.model.Storage;

public class AddStorageFormTransform {

    public static Storage transform(AddStorageForm form) {
        Storage storage = new Storage();
        storage.setCity(form.getCity());
        storage.setStreet(form.getStreet());
        return storage;
    }
}
