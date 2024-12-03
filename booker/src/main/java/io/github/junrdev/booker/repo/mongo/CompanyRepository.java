package io.github.junrdev.booker.repo.mongo;

import io.github.junrdev.booker.domain.model.Company;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends MongoRepository<Company, String> {

    Optional<Company> findByName(String name);
    List<Company> findByNameContaining(String name);

    Optional<Company> findByLocation(String location);
    List<Company> findByLocationContaining(String location);

    List<Company> findByNameAndLocationContaining(String name,String location);
    List<Company> findByNameContainingAndLocationContaining(String name,String location);

}
