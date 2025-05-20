package models;

public class Passenger {
    private int id;
    private String firstName;
    private String secondName;
    private String passportNumber;

    public Passenger(int id, String firstName, String secondName, String passportNumber){
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.passportNumber = passportNumber;
    }

    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id = id;
    }

    public String getFirstName(){
        return this.firstName;
    }
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getSecondName(){
        return this.secondName;
    }
    public void setSecondName(String secondName){
        this.secondName = secondName;
    }

    public String getPassportNumber(){
        return this.passportNumber;
    }
    public void setPassportNumber(String passportNumber){
        this.passportNumber = passportNumber;
    }
}