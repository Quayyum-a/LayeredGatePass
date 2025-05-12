package data.repository;

import data.models.AccessCode;

public interface AccessCodeRepository extends CrudRepository<AccessCode, Integer> {
    AccessCode findByCode(String code);
}