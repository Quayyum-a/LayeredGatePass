package data.model;

import java.time.LocalDateTime;

public class AccessCode {
    private int id;
    private String code;
    private String status;
    private Visitor visitor;
    private Resident resident;
    private LocalDateTime createdTime;
}
