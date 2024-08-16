package com.fpt.lab3.service;

import com.fpt.lab3.model.FileStore;

public interface FileStoreService {
    FileStore addFile(FileStore fileStore);
    FileStore detail(int id);
}
