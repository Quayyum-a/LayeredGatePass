package data.model;

public class Visitor {
    private int id;
    private String fullName;
    private Resident whomToVisit;
    private String phoneNumber;
    private AccessCode accessCode;


    public Visitor(int id, String fullName, Resident whomToVisit, String phoneNumber) {
        this.id = id;
        this.fullName = fullName;
        this.whomToVisit = whomToVisit;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Resident getWhomToVisit() {
        return whomToVisit;
    }

    public void setWhomToVisit(Resident whomToVisit) {
        this.whomToVisit = whomToVisit;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public AccessCode getAccessCode() {
        return accessCode;
    }

}
