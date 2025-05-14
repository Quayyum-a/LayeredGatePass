package data.repository;

import data.model.AccessCode;
import data.model.Visitor;

import java.util.ArrayList;
import java.util.List;

public abstract class InMemoryVisitorRepository implements VisitorRepository{
    private final List<Visitor> visitors = new ArrayList<>();
    private int nextId = 1;

    @Override
    public Visitor save(Visitor visitor) {
        if (visitor.getId() == 0) {
            visitor.setId(nextId++);
            visitors.add(visitor);
        } else {
            visitors.removeIf(v -> v.getId() == visitor.getId());
            visitors.add(visitor);
        }
        return visitor;
    }

    @Override
    public Visitor findById(Integer id) {
        return visitors.stream()
                .filter(v -> v.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void deleteById(Integer id) {
        visitors.removeIf(v -> v.getId() == id);
    }

    @Override
    public void delete(Visitor visitor) {
        visitors.remove(visitor);
    }

    @Override
    public Iterable<Visitor> findAll() {
        return new ArrayList<>(visitors);
    }

    @Override
    public long count() {
        return visitors.size();
    }

    @Override
    public Visitor findByPhoneNumber(String phoneNumber) {
        return visitors.stream()
                .filter(v -> v.getPhoneNumber().equals(phoneNumber))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Visitor findByFullName(String fullName) {
        return visitors.stream()
                .filter(v -> v.getFullName().equals(fullName))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Visitor findByWhomToVisit(int whomToVisitId) {
        return visitors.stream()
                .filter(v -> v.getWhomToVisit().getId() == whomToVisitId)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Visitor findByAccessCode(AccessCode accessCode) {
        return visitors.stream()
                .filter(v -> {
                    return v.getAccessCode().equals(accessCode);
                })
                .findFirst()
                .orElse(null);
    }

}
