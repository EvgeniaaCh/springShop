package ru.kpfu.itis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.form.add.AddStorageForm;
import ru.kpfu.itis.model.Storage;
import ru.kpfu.itis.repository.StorageRepository;
import ru.kpfu.itis.service.StorageService;
import ru.kpfu.itis.util.transform.AddStorageFormTransform;

import java.util.List;

@Service
public class StorageServiceImpl implements StorageService {

    @Autowired
    private StorageRepository storageRepository;

    @Override
    public List<Storage> getAll() {
        return storageRepository.findAll();
    }

    @Override
    public Storage getById(long id) {
        return storageRepository.findById(id);
    }

    @Override
    public void save(AddStorageForm form) {
        Storage storage = AddStorageFormTransform.transform(form);
        storageRepository.save(storage);
    }

    @Override
    public void delete(long id) {
        storageRepository.delete(id);
    }
}
