package com.thor.storage.repository;

import com.thor.storage.document.StorageFileDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageRepository extends MongoRepository<StorageFileDocument, String> {
}
