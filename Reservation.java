/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restaurantsystem;


public class Reservation implements Comparable <Reservation>{
    
    private int year;
    private int month;
    private int day;
    private int militaryHour;
    private int militaryMinute;
    private String lastName;
    private String firstName;
    private int partySize;

    public Reservation(int year, int month, int day, int militaryHour, int militaryMinute, String lastName, String firstName, int partySize) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.militaryHour = militaryHour;
        this.militaryMinute = militaryMinute;
        this.lastName = lastName;
        this.firstName = firstName;
        this.partySize = partySize;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMilitaryHour() {
        return militaryHour;
    }

    public void setMilitaryHour(int militaryHour) {
        this.militaryHour = militaryHour;
    }

    public int getMilitaryMinute() {
        return militaryMinute;
    }

    public void setMilitaryMinute(int militaryMinute) {
        this.militaryMinute = militaryMinute;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getSize() {
        return partySize;
    }

    public void setSize(int partySize) {
        this.partySize = partySize;
    }

    /**
     * this is the compareTO method and will compare the reservations for the LL 
     * in order of dates for the reservation in ascending order
     * @param other
     * @return int 
     */
    public int compareTo(Reservation other){
        
        int compareVal;
        
        if (this.year > other.year){
            compareVal = 1;
        }
        else if(this.year < other.year){
            compareVal = -1; 
        }
        else if(this.month > other.month){
            compareVal = 1;
        }
        else if(this.month > other.month){
            compareVal = -1;
        }
        else if(this.day > other.day){
            compareVal = 1;
        }
        else if(this.day < other.day){
            compareVal = -1;   
        }
        else{
            compareVal = this.lastName.compareTo(other.lastName);
            if (compareVal == 0){
                compareVal = this.firstName.compareTo(other.firstName);
            }
        }
        return compareVal;
    }
    
    /**
     * this will serve as the format the reservation will be called on
     * @return string 
     */
    @Override
    public String toString() {
        return "Reservation: " + "year = " + year + ", month = " + month + ", day = " + day + ", Hour = " + militaryHour + ", Minute = " + militaryMinute + ", last Name = " + lastName + ", first Name = " + firstName + ", party Size = " + partySize;
    }
    
    
    
    

    
    
}
