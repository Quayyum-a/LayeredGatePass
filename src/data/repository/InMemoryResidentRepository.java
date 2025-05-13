package data.repository;

import data.model.Resident;

import java.util.ArrayList;
import java.util.List;

public abstract class InMemoryResidentRepository implements ResidentRepository{
    private final List<Resident> residents = new ArrayList<>();
    private int nextId = 1;

    @Override
    public Resident save(Resident resident) {
        if (resident.getId() == 0) {
            resident.setId(nextId++);
            residents.add(resident);
        } else {
            residents.removeIf(r -> r.getId() == resident.getId());
            residents.add(resident);
        }
        return resident;
    }

    @Override
    public Resident findById(Integer id) {
        return residents.stream()
                .filter(r -> r.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void deleteById(Integer id) {
        residents.removeIf(r -> r.getId() == id);
    }

    @Override
    public void delete(Resident resident) {
        residents.remove(resident);
    }

    @Override
    public Iterable<Resident> findAll() {
        return new ArrayList<>(residents);
    }

    @Override
    public long count() {
        return residents.size();
    }

    @Override
    public Resident findByPhoneNumber(String phoneNumber) {
        return residents.stream()
                .filter(r -> r.getPhoneNumber().equals(phoneNumber))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Resident findByFullName(String fullName) {
        return residents.stream()
                .filter(r -> r.getFullName().equals(fullName))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Resident findByAddress(String address) {
        return residents.stream()
                .filter(r -> r.getAddress().equals(address))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Resident findByEmail(String email) {
        return residents.stream()
                .filter(r -> r.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    // Note: generateAccessCode should be in ResidentService, not here
    @Override
    public Resident generateAccessCode(int residentId) {
        throw new UnsupportedOperationException("generateAccessCode not implemented in repository");
    }
}
