package io.github.junrdev.booker.repo.mongo;

import io.github.junrdev.booker.domain.model.AppUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<AppUser, String> {

    Optional<AppUser> findByEmail(String email);

    Optional<AppUser> findByName(String name);

}
