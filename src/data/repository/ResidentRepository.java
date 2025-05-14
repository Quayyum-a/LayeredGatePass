package data.repository;

import data.model.Resident;

public interface ResidentRepository extends CrudRepository<Resident, Integer> {
    Resident findByPhoneNumber(String phoneNumber);

    Resident findByFullName(String fullName);

    Resident findById(int id);

    Resident findByAddress(String address);

    Resident findByEmail(String email);

    // Note: generateAccessCode should be in ResidentService, not here
    Resident generateAccessCode(int residentId);
}
