import data.model.Resident;
import data.model.Visitor;
import data.repository.ResidentRepository;
import data.repository.VisitorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import services.VisitorService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VisitorServiceTest {

    @Mock
    private VisitorRepository visitorRepository;

    @Mock
    private ResidentRepository residentRepository;

    private VisitorService visitorService;

    private Resident resident;

    @BeforeEach
    void setUp() {
        visitorService = new VisitorService(visitorRepository, residentRepository);
        resident = new Resident(1, "John Doe", "123 Main St", "555-1234", "johndoe@gmail.com");
    }

    @Test
    void testRegisterVisitor_Success() {
        // Arrange
        when(residentRepository.findById(1)).thenReturn(resident);
        when(visitorRepository.save(any(Visitor.class))).thenAnswer(invocation -> {
            Visitor savedVisitor = invocation.getArgument(0);
            savedVisitor.setId(1); // Simulate ID assignment
            return savedVisitor;
        });

        // Act
        Visitor result = visitorService.registerVisitor("Jane Smith", "555-5678", 1);

        // Assert
        assertNotNull(result);
        assertEquals("Jane Smith", result.getFullName());
        assertEquals("555-5678", result.getPhoneNumber());
        assertEquals(resident, result.getWhomToVisit());
        assertEquals(1, result.getId());

        // Verify
        verify(residentRepository).findById(1);
        verify(visitorRepository).save(any(Visitor.class));
    }

    @Test
    void testRegisterVisitor_ResidentNotFound() {
        // Arrange
        when(residentRepository.findById(1)).thenReturn(null);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            visitorService.registerVisitor("Jane Smith", "555-5678", 1);
        });
        // Verify
        verify(residentRepository).findById(1);
        verify(visitorRepository, never()).save(any());
    }

    @Test
    void testFindVisitorByPhoneNumber_Success() {
        // Arrange
        Visitor visitor = new Visitor(1, "Jane Smith", resident, "555-5678");
        when(visitorRepository.findByPhoneNumber("555-5678")).thenReturn(visitor);

        // Act
        Visitor result = visitorService.findVisitorByPhoneNumber("555-5678");

        // Assert
        assertNotNull(result);
        assertEquals("Jane Smith", result.getFullName());

        // Verify
        verify(visitorRepository).findByPhoneNumber("555-5678");
    }
}