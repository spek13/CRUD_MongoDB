package com.bitware_test.crudmong.repositorys;

import com.bitware_test.crudmong.models.Owner;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OwnerRepository extends MongoRepository<Owner, String> {
}
