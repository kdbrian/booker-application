package io.github.junrdev.booker.repo.mongo;

import io.github.junrdev.booker.domain.model.Company;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends MongoRepository<Company, String> {
}
