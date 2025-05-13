package data.repository;

import data.model.Visitor;

public interface VisitorRepository extends CrudRepository<Visitor, Integer> {
    Visitor findByPhoneNumber(String phoneNumber);

    Visitor findByFullName(String fullName);

    Visitor findById(int id);

    Visitor findByWhomToVisit(String whomToVisit);

    Visitor findByAccessCode(String accessCode);
}
