package data.repository;

import data.model.AccessCode;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class InMemoryAccessCodeRepository implements AccessCodeRepository {
    private final List<AccessCode> accessCodes = new ArrayList<>();
    private int nextId = 1;

    @Override
    public AccessCode save(AccessCode accessCode) {
        if (accessCode.getId() == 0) {
            accessCode.setId(nextId++);
            accessCodes.add(accessCode);
        } else {
            accessCodes.removeIf(ac -> ac.getId() == ac.getId());
            accessCodes.add(accessCode);
        }
        return accessCode;
    }

    @Override
    public AccessCode findById(Integer id) {
        return accessCodes.stream()
                .filter(ac -> ac.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void deleteById(Integer id) {
        accessCodes.removeIf(ac -> ac.getId() == id);
    }

    @Override
    public void delete(AccessCode accessCode) {
        accessCodes.remove(accessCode);
    }

    @Override
    public Iterable<AccessCode> findAll() {
        return new ArrayList<>(accessCodes);
    }

    @Override
    public long count() {
        return accessCodes.size();
    }

    @Override
    public AccessCode findByCode(String code) {
        return accessCodes.stream()
                .filter(ac -> ac.getCode().equals(code))
                .findFirst()
                .orElse(null);
    }

    @Override
    public AccessCode findByStatus(String status) {
        return accessCodes.stream()
                .filter(ac -> ac.getStatus().equals(status))
                .findFirst()
                .orElse(null);
    }

    @Override
    public AccessCode findByVisitorId(int visitorId) {
        return accessCodes.stream()
                .filter(ac -> ac.getVisitor().getId() == visitorId)
                .findFirst()
                .orElse(null);
    }

    @Override
    public AccessCode findByResidentId(int residentId) {
        return accessCodes.stream()
                .filter(ac -> ac.getResident().getId() == residentId)
                .findFirst()
                .orElse(null);
    }

    @Override
    public AccessCode findByCreatedTime(LocalDateTime createdTime) {
        return accessCodes.stream()
                .filter(ac -> ac.getCreatedTime().equals(createdTime))
                .findFirst()
                .orElse(null);
    }
}