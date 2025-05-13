import data.model.Resident;
import data.repository.InMemoryResidentRepository;
import data.repository.InMemoryVisitorRepository;
import data.repository.InMemoryAccessCodeRepository;
import services.ResidentService;
import services.VisitorService;
import services.AccessCodeService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Initialize repositories
        InMemoryResidentRepository residentRepository = new InMemoryResidentRepository();
        InMemoryVisitorRepository visitorRepository = new InMemoryVisitorRepository();
        InMemoryAccessCodeRepository accessCodeRepository = new InMemoryAccessCodeRepository();

        // Initialize services
        ResidentService residentService = new ResidentService(residentRepository, visitorRepository, accessCodeRepository);
        VisitorService visitorService = new VisitorService(visitorRepository, residentRepository);
        AccessCodeService accessCodeService = new AccessCodeService(accessCodeRepository);

        // Console interface
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nVisitor Management System");
            System.out.println("1. Register Resident");
            System.out.println("2. Register Visitor");
            System.out.println("3. Generate Access Code");
            System.out.println("4. Validate Access Code");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter full name: ");
                    String fullName = scanner.nextLine();
                    System.out.print("Enter address: ");
                    String address = scanner.nextLine();
                    System.out.print("Enter phone number: ");
                    String phoneNumber = scanner.nextLine();
                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();
                    Resident resident = new Resident(0, fullName, address, phoneNumber, email);
                    residentService.saveResident(resident);
                    System.out.println("Resident registered successfully!");
                    break;

                case 2:
                    System.out.print("Enter visitor full name: ");
                    String visitorName = scanner.nextLine();
                    System.out.print("Enter visitor phone number: ");
                    String visitorPhone = scanner.nextLine();
                    System.out.print("Enter resident ID to visit: ");
                    int residentId = scanner.nextInt();
                    visitorService.registerVisitor(visitorName, visitorPhone, residentId);
                    System.out.println("Visitor registered successfully!");
                    break;

                case 3:
                    System.out.print("Enter resident ID: ");
                    int residentIdGen = scanner.nextInt();
                    System.out.print("Enter visitor ID: ");
                    int visitorId = scanner.nextInt();
                    String accessCode = residentService.generateAccessCode(residentId, visitorId).getCode();
                    System.out.println("Access code generated: " + accessCode);
                    break;

                case 4:
                    System.out.print("Enter access code: ");
                    String code = scanner.nextLine();
                    boolean valid = accessCodeService.validateAccessCode(code);
                    System.out.println("Access code is " + (valid ? "valid" : "invalid"));
                    break;

                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option!");
            }
        }
    }
}