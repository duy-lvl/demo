package com.fpt.lab3.repository;

import com.fpt.lab3.model.FileStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileStoreRepository extends JpaRepository<FileStore, Integer> {
}
