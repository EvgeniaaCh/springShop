package ru.kpfu.itis.service;

import org.springframework.stereotype.Service;
import ru.kpfu.itis.form.add.AddStorageForm;
import ru.kpfu.itis.model.Storage;

import java.util.List;

@Service
public interface StorageService {

    List<Storage> getAll();

    Storage getById(long id);

    void save(AddStorageForm form);

    void delete(long id);
}
