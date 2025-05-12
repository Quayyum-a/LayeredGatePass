package data.repository;

import data.model.AccessCode;

public interface AccessCodeRepository extends CrudRepository<AccessCode, Integer>{
    AccessCode findByCode(String code);
    AccessCode findByStatus(String status);
    AccessCode findByVisitorId(int visitorId);
    AccessCode findByResidentId(int residentId);
    AccessCode findByCreatedTime(String createdTime);
}