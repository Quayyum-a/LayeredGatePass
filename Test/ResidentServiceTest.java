import data.model.AccessCode;
import data.model.Resident;
import data.model.Visitor;
import data.repository.AccessCodeRepository;
import data.repository.ResidentRepository;
import data.repository.VisitorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import services.ResidentService;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ResidentServiceTest {

    @Mock
    private ResidentRepository residentRepository;

    @Mock
    private VisitorRepository visitorRepository;

    @Mock
    private AccessCodeRepository accessCodeRepository;

    private ResidentService residentService;

    private Resident resident;
    private Visitor visitor;

    @BeforeEach
    public void setUp() {
        residentService = new ResidentService(residentRepository, visitorRepository, accessCodeRepository);

        // Initialize test data
        resident = new Resident(1, "John Doe", "123 Main St", "555-1234", "johndoe@gmail.com");
        visitor = new Visitor(1, "Jane Smith", resident, "555-5678");
    }

    // Tests for generateAccessCode
    @Test
    public void testGenerateAccessCode_Success() {
        // Arrange
        when(residentRepository.findById(1)).thenReturn(resident);
        when(visitorRepository.findById(1)).thenReturn(visitor);
        when(accessCodeRepository.save(any(AccessCode.class))).thenAnswer(invocation -> {
            AccessCode savedCode = invocation.getArgument(0);
            savedCode.setId(1); // Simulate saving by setting an ID
            return savedCode;
        });

        // Act
        AccessCode result = residentService.generateAccessCode(1, 1);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getCode());
        assertEquals("ACTIVE", result.getStatus());
        assertEquals(resident, result.getResident());
        assertEquals(visitor, result.getVisitor());
        assertNotNull(result.getCreatedTime());
        assertTrue(result.getCreatedTime().isBefore(LocalDateTime.now().plusSeconds(1)));

        // Verify repository interactions
        verify(residentRepository).findById(1);
        verify(visitorRepository).findById(1);
        verify(accessCodeRepository).save(any(AccessCode.class));
    }

    @Test
    public void testGenerateAccessCode_ResidentNotFound() {
        // Arrange
        when(residentRepository.findById(1)).thenReturn(null);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            residentService.generateAccessCode(1, 1);
        });
        assertEquals("Resident not found", exception.getMessage());

        // Verify repository interactions
        verify(residentRepository).findById(1);
        verify(visitorRepository, never()).findById(anyInt());
        verify(accessCodeRepository, never()).save(any());
    }

    @Test
    public void testGenerateAccessCode_VisitorNotFound() {
        // Arrange
        when(residentRepository.findById(1)).thenReturn(resident);
        when(visitorRepository.findById(1)).thenReturn(null);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            residentService.generateAccessCode(1, 1);
        });
        assertEquals("Visitor not found", exception.getMessage());

        // Verify repository interactions
        verify(residentRepository).findById(1);
        verify(visitorRepository).findById(1);
        verify(accessCodeRepository, never()).save(any());
    }

    // Tests for findResidentByEmail
    @Test
    public void testFindResidentByEmail_Success() {
        // Arrange
        String email = "johndoe@gmail.com";
        when(residentRepository.findByEmail(email)).thenReturn(resident);

        // Act
        Resident result = residentService.findResidentByEmail(email);

        // Assert
        assertNotNull(result);
        assertEquals(resident, result);
        assertEquals(email, result.getEmail());

        // Verify repository interaction
        verify(residentRepository).findByEmail(email);
    }

    @Test
    public void testFindResidentByEmail_NotFound() {
        // Arrange
        String email = "nonexistent@gmail.com";
        when(residentRepository.findByEmail(email)).thenReturn(null);

        // Act
        Resident result = residentService.findResidentByEmail(email);

        // Assert
        assertNull(result);

        // Verify repository interaction
        verify(residentRepository).findByEmail(email);
    }

}