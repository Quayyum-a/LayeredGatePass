import data.model.Resident;
import data.model.Visitor;
import org.junit.jupiter.api.Test;
import services.AccessCodeService;

import static org.junit.jupiter.api.Assertions.*;

public class AccessCodeServiceTest {
    @Test
    public void testGenerateAccessCode() {
        // Arrange
        AccessCodeService accessCodeService = new AccessCodeService();
        Visitor visitor = new Visitor();
        Resident resident = new Resident();
        // Act
        String actualCode = accessCodeService.generateAccessCode(visitor, resident);
        // Assert
        assertEquals(actualCode, accessCodeService.generateAccessCode(visitor, resident));
    }
    @Test
    public void testGenerateAccessCodeWithDifferentVisitor() {
        // Arrange
        AccessCodeService accessCodeService = new AccessCodeService();

        Visitor visitor1 = new Visitor();
        Visitor visitor2 = new Visitor();
        Resident resident = new Resident();
        Resident resident2 = new Resident();
        // Act
        String code1 = accessCodeService.generateAccessCode(visitor1, resident);
        String code2 = accessCodeService.generateAccessCode(visitor2, resident2);
        // Assert
        assertNotEquals(code1, code2);
    }
}
