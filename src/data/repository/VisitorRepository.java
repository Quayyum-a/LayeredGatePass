package data.repository;

import data.model.Visitor;

public interface VisitorRepository extends CrudRepository<Visitor, String> {
    Visitor findByPhoneNumber(String phoneNumber);

    Visitor findByFullName(String fullName);

    Visitor findById(int id);

    Visitor findByWhomToVisit(String whomToVisit);

    Visitor findByAccessCode(String accessCode);
}
