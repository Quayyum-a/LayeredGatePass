package services;

import data.model.Resident;
import data.model.Visitor;
import data.repository.ResidentRepository;
import data.repository.VisitorRepository;

public class VisitorService {
    private final VisitorRepository visitorRepository;
    private final ResidentRepository residentRepository;

    public VisitorService(VisitorRepository visitorRepository, ResidentRepository residentRepository) {
        this.visitorRepository = visitorRepository;
        this.residentRepository = residentRepository;
    }

    public Visitor registerVisitor(String fullName, String phoneNumber, int residentId) {
        Resident resident = residentRepository.findById(residentId);
        if (resident == null) {
            throw new IllegalArgumentException("Resident not found");
        }
        Visitor visitor = new Visitor(0, fullName, resident, phoneNumber);
        return visitorRepository.save(visitor);
    }

    public Visitor findVisitorByPhoneNumber(String phoneNumber) {
        return visitorRepository.findByPhoneNumber(phoneNumber);
    }

    public Visitor findVisitorById(int visitorId) {
        return visitorRepository.findById(visitorId);
    }
}
