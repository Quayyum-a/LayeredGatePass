package services;

import data.model.AccessCode;
import data.repository.AccessCodeRepository;

public class AccessCodeService {
    private final AccessCodeRepository accessCodeRepository;

    public AccessCodeService(AccessCodeRepository accessCodeRepository) {
        this.accessCodeRepository = accessCodeRepository;
    }

    public boolean validateAccessCode(String code) {
        AccessCode accessCode = accessCodeRepository.findByCode(code);
        if (accessCode == null) {
            return false;
        }
        // Optionally check expiration or status
        return "ACTIVE".equals(accessCode.getStatus());
    }

    public AccessCode findAccessCodeByVisitorId(int visitorId) {
        return accessCodeRepository.findByVisitorId(visitorId);
    }

    public AccessCode findAccessCodeByResidentId(int residentId) {
        return accessCodeRepository.findByResidentId(residentId);
    }
}
