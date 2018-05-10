package ru.kpfu.itis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.model.Storage;

import java.util.List;

@Repository
public interface StorageRepository extends JpaRepository<Storage, Long> {

    List<Storage> findAll();

    Storage findById(long id);

}
