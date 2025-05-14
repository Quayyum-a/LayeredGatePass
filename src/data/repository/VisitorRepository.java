package data.repository;

import data.model.AccessCode;
import data.model.Visitor;

public interface VisitorRepository extends CrudRepository<Visitor, Integer> {
    Visitor findByPhoneNumber(String phoneNumber);

    Visitor findByFullName(String fullName);

    Visitor findById(int id);

    Visitor findByWhomToVisit(String whomToVisit);

    Visitor findByWhomToVisit(int whomToVisitId);

    Visitor findByAccessCode(AccessCode accessCode);

}
