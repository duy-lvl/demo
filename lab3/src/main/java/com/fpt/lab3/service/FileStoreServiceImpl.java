package com.fpt.lab3.service;

import com.fpt.lab3.model.FileStore;
import com.fpt.lab3.repository.FileStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class FileStoreServiceImpl implements FileStoreService{
    @Autowired
    private FileStoreRepository fileStoreRepository;
    @Override
    public FileStore addFile(FileStore fileStore) {
        return fileStoreRepository.saveAndFlush(fileStore);
    }

    @Override
    public FileStore detail(int id) {
        return fileStoreRepository.getById(id);
    }
}
