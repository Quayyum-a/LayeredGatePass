package services;

import data.model.AccessCode;
import data.model.Resident;
import data.model.Visitor;
import data.repository.AccessCodeRepository;
import data.repository.ResidentRepository;
import data.repository.VisitorRepository;

import java.time.LocalDateTime;
import java.util.UUID;

public class ResidentService {
    private final ResidentRepository residentRepository;
    private final VisitorRepository visitorRepository;
    private final AccessCodeRepository accessCodeRepository;

    public ResidentService(ResidentRepository residentRepository,
                           VisitorRepository visitorRepository,
                           AccessCodeRepository accessCodeRepository) {
        this.residentRepository = residentRepository;
        this.visitorRepository = visitorRepository;
        this.accessCodeRepository = accessCodeRepository;
    }

    public AccessCode generateAccessCode(int residentId, int visitorId) {
        Resident resident = residentRepository.findById(residentId);
        if (resident == null) {
            throw new IllegalArgumentException("Resident not found");
        }
        Visitor visitor = visitorRepository.findById(visitorId);
        if (visitor == null) {
            throw new IllegalArgumentException("Visitor not found");
        }
        AccessCode accessCode = new AccessCode();
        accessCode.setCode(UUID.randomUUID().toString());
        accessCode.setStatus("ACTIVE");
        accessCode.setResident(resident);
        accessCode.setVisitor(visitor);
        accessCode.setCreatedTime(LocalDateTime.now());
        return accessCodeRepository.save(accessCode);
    }

    public Resident findResidentByEmail(String email) {
        return residentRepository.findByEmail(email);
    }
}