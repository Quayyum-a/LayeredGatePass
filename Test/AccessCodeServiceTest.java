import data.model.AccessCode;
import data.model.Resident;
import data.model.Visitor;
import data.repository.AccessCodeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import services.AccessCodeService;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccessCodeServiceTest {

    @Mock
    private AccessCodeRepository accessCodeRepository;

    private AccessCodeService accessCodeService;

    private Resident resident;
    private Visitor visitor;
    private AccessCode accessCode;

    @BeforeEach
    public void setUp() {
        accessCodeService = new AccessCodeService(accessCodeRepository);
        resident = new Resident(1, "John Doe", "123 Main St", "555-1234", "johndoe@gmail.com");
        visitor = new Visitor(1, "Jane Smith", resident, "555-5678");
        accessCode = new AccessCode();
        accessCode.setId(1);
        accessCode.setCode("ABC123");
        accessCode.setStatus("ACTIVE");
        accessCode.setResident(resident);
        accessCode.setVisitor(visitor);
        accessCode.setCreatedTime(LocalDateTime.now());
    }

    @Test
    public void testValidateAccessCode_Valid() {
        // Arrange
        when(accessCodeRepository.findByCode("ABC123")).thenReturn(accessCode);

        // Act
        boolean result = accessCodeService.validateAccessCode("ABC123");

        // Assert
        assertTrue(result);
        verify(accessCodeRepository).findByCode("ABC123");
    }

    @Test
    public void testValidateAccessCode_Invalid() {
        // Arrange
        when(accessCodeRepository.findByCode("XYZ789")).thenReturn(null);

        // Act
        boolean result = accessCodeService.validateAccessCode("XYZ789");

        // Assert
        assertFalse(result);
        verify(accessCodeRepository).findByCode("XYZ789");
    }

    @Test
    public void testFindAccessCodeByVisitorId_Success() {
        // Arrange
        when(accessCodeRepository.findByVisitorId(1)).thenReturn(accessCode);

        // Act
        AccessCode result = accessCodeService.findAccessCodeByVisitorId(1);

        // Assert
        assertNotNull(result);
        assertEquals("ABC123", result.getCode());
        verify(accessCodeRepository).findByVisitorId(1);
    }
}