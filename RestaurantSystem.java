/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package restaurantsystem;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Queue;
import java.util.Scanner;


public class RestaurantSystem {

    /**
     * @param args the command line arguments
     * 
     * Main calls the menu method for the user to make their choice of the options below
     * each one calls another method which creates/changes a linked list or queue 
     */
    public static  LinkedList<Reservation> userReservations = new LinkedList<Reservation>();
    public static Queue<Reservation> reservationQueue = new LinkedList<Reservation>();
    public static Scanner scan = new Scanner(System.in);
    
    public static void main(String[] args) {

        RestaurantSystem reservationObj = new RestaurantSystem();

        int menueOption = displayMenue(); 
        
       while (menueOption != 9)  {
           switch(menueOption){
               case 1:  
                   reservationObj.addReservation(userReservations);
                   break; 
               case 2:
                   reservationObj.changeReservation(userReservations);
                   break;
               case 3:
                   reservationObj.cancelReservation(userReservations);
                   break;
               case 4:
                   reservationObj.listAllReservations(userReservations);
                   break;
               case 5: 
                   reservationObj.seatAReservation(userReservations);
                   break;
               case 6:
                   reservationObj.addAWalkIn(reservationQueue);
                   break; 
               case 7:
                   reservationObj.listAllWalkIns(reservationQueue);
                   break;
               case 8: 
                   reservationObj.seatAWalkIn(reservationQueue);
                   break;
               default:
                   System.out.println("Thank you for coming");
    
            }  
           menueOption = displayMenue();
       } 
        
    }
    
    /**
     * This method is the menu where the user can choose what they would like to do 
     * with making a reservation or walk in 
     * @return int of choice 
     */    
    public static int displayMenue(){
        
        boolean errorOccurred = true;
        int menuOption = 0;
  
        do {
        
            try{
                System.out.println("Enter a number from the Menu to make your request:");
                System.out.println("1. Make a Reservation");  
                System.out.println("2. Change a Reservation");
                System.out.println("3. Cancel a Reservation");
                System.out.println("4. List all of the Reservations");
                System.out.println("5. Seat a Reservation");
                System.out.println("6. Add a Walk-In");
                System.out.println("7. List all of the Walk-Ins");
                System.out.println("8. Seat a Walk-In");
                System.out.println("9. Exit ");

                menuOption = scan.nextInt(); 
                errorOccurred = false;
                if(menuOption > 9 || menuOption < 1){
                    System.out.println("Enter a valid number ");
                }
            }catch(InputMismatchException e){
                System.out.println("Please Enter a valid number");
                errorOccurred = true; 
                scan.nextLine();
            }
        }while(errorOccurred == true || menuOption < 1 || menuOption > 9);
        
        return menuOption; 

    }
    
    /**
     * This takes in a LL creates a reservation object and adds it to the LL 
     * @param theLL 
     */
    public void addReservation(LinkedList<Reservation> theLL){
        //add a reservation object to linkedList in the order of ascending dates
        ListIterator<Reservation> listIterator = theLL.listIterator();// to go through linkedlist 
        Boolean isFound = false;
       
        Reservation userRes;
        Reservation exist;
       
 
        System.out.println("Lets add your Reservation");
        userRes = reservationInfo();

        
        while(listIterator.hasNext() && !isFound){

            exist = listIterator.next();
            
            if(exist.compareTo(userRes) > 0){ // if the current reservation is bigger than new res go back one and add before it 
               listIterator.previous();
               listIterator.add(userRes);
                System.out.println("Added reservation to list");
               isFound = true;
              
            }
        }if (!isFound){
            listIterator.add(userRes);          //if we go through whole list and not found then just add it to last position
            System.out.println("Added Reservation to end of List");
           
        }
        
    }
    
    /**
     * this method will take a LL to find a reservation and if found it will either change the party date or 
     * some other option. The user will have to input their info of their old reservation and new reservation info 
     * @param theLL 
     */
    public void changeReservation(LinkedList<Reservation> theLL){
        //iterate through the linkedList to find a reservation object in the linkedList, 
        //set it to the new reservation withthe changed party size. Must stay in same order of ascending dates.
        
        ListIterator<Reservation> listIterator = theLL.listIterator();// to go through linkedlist 
        Boolean found = false;
        Boolean errorOccured = true; 
        Reservation exist; 
        Reservation oldRes, newRes;
        
    
        int userChoice = 0; 
        System.out.println("Enter old reservation info");
        oldRes = reservationInfo();
       do {
        try{
        //print what are you changing 
            System.out.println("What are you changing ");
            System.out.println("1 - change party size: ");
            System.out.println("2 - change other info: ");
            userChoice = scan.nextInt();
            errorOccured = false;
        }catch(InputMismatchException e){
            System.out.println("Enter either option 1 or 2 please");
        }
       }while (userChoice < 1 || userChoice > 2 || errorOccured);
        
        while(listIterator.hasNext() && !found){
         //try{}
        exist = listIterator.next();
        if(exist.compareTo(oldRes) == 0){  //finds the reservation equal to it  
            if(userChoice == 1){
                System.out.println("Enter the new party size: ");
                int newPartySize = scan.nextInt();
                exist.setSize(newPartySize);
                System.out.println("Part size has changed");
            }else{
                listIterator.remove();
                System.out.println("Enter the new info for your new reservation: ");
                newRes = reservationInfo();
                theLL.add(newRes);
                Collections.sort(theLL);
                System.out.println("Updated Reservation info");
            }
            found = true; 
        }
       }//end while 
        if (!found) {
    System.out.println("Sorry we couldn't find that reservation");
}
 
    }
    
    /**
     * This takes in LL and prompts user to find their reservation, and if found 
     * it will delete it, otherwise it will say not found 
     * @param theLL 
     */
    
    public void cancelReservation(LinkedList<Reservation> theLL){
        ListIterator<Reservation> listIterator = theLL.listIterator();
        Boolean found = false;
        Reservation exist; 
        Reservation oldRes;

        System.out.println("Enter the reservation info you'd like to cancel");
        oldRes = reservationInfo();

        while(listIterator.hasNext() && !found){
            exist = listIterator.next();
            if(exist.compareTo(oldRes) == 0){
                listIterator.remove();
                found = true;
                System.out.println("The reservation was found and cancelled");
          }
//             else{
//                System.out.println("The reservation was not found");
//                found = false; 
//            }

        }if (!found){
            System.out.println("The reservation was not found");
        }
       
    }
    
    /**
     * This user a list iterator and go through the whole LL to print out 
     * each reservation in order of date
     * @param theLL 
     */
    public void listAllReservations(LinkedList<Reservation> theLL){
        ListIterator<Reservation> listIterator = theLL.listIterator();
        Reservation aReservation; 
        
        while(listIterator.hasNext()){
            aReservation = listIterator.next();
            System.out.println(aReservation);
            
        }    
    }
    
    /**
     * this will find the specified reservation and see if it is in LL 
     * if it is then it will be deleted and the reservation will be seated
     * @param theLL 
     */
    
    public void seatAReservation(LinkedList<Reservation> theLL){
        ListIterator<Reservation> listIterator = theLL.listIterator();
        Boolean found = false;
        Reservation exist; 
        Reservation oldRes;

        System.out.println("Enter your reservation info so we can seat you: ");
        oldRes = reservationInfo();

        while(listIterator.hasNext() && !found){
            exist = listIterator.next();
            if(exist.compareTo(oldRes) == 0){
                listIterator.remove();
                found = true;
                System.out.println("We found your reservation, please follow me");
            }
//                else{
//                System.out.println("The reservation was not found, sorry dog");
//                found = false; 
//            }

        }
        if(!found){
           
                System.out.println("The reservation was not found");
                
         
        }
        
    }
    
    /** 
     * This will prompt a user to put in their info of the walk-in and add it to the queue
     * 
     * @param theQ 
     */
    public void addAWalkIn(Queue<Reservation> theQ){
        //Queue<Reservation> myQueue = new LinkedList<Reservation>();
        Reservation walkIn;
        boolean errorOccured;
        String lastName = "";
        String firstName = "";
        int partySize = 0;
                
                
        System.out.println("Welcome Walk-in, please enter your parties info and current time");
        // Get the current date and time
        LocalDateTime now = LocalDateTime.now();
        // Get the current year, month, and day
        int year = now.getYear();
        int month = now.getMonthValue();
        int day = now.getDayOfMonth();
        // Get the current military hour and minute
        int hour = now.getHour();
        int minute = now.getMinute();
        do {
            try{ 
            System.out.println("Enter your last name:");
            lastName =scan.next();
            System.out.println("Enter your first name:");
            firstName =scan.next();
            System.out.println("Enter your parties size");
            partySize = scan.nextInt();
                errorOccured = false;
            }catch(InputMismatchException e){
                System.out.println("Pleae enter a number for the party size");
                errorOccured = true;
            }
        }while(errorOccured);
        walkIn = new Reservation(year,month,day,hour,minute,lastName,firstName,partySize);
        
        
        //walkIn = reservationInfo();
        
        theQ.add(walkIn);
        System.out.println("Walk-in added to the queue");
     
    }
    
    /**
     * This will see if the queue is empty and if not then it will 
     * go through the queue in order of FIFO
     * @param theQ 
     */
    
    public void listAllWalkIns(Queue<Reservation> theQ){
        Queue<Reservation> newQ = new LinkedList<>(theQ);
        Reservation walkIn;
        
        while(!newQ.isEmpty()){
            walkIn = newQ.poll();
            System.out.println(walkIn);
            
        }  
       
    }
    
    /**This will see if the queue is empty and then if not it will 
     * remove the first reservation in the queue and seat them 
     * 
     * @param theQ 
     */
    public void seatAWalkIn(Queue<Reservation> theQ){
        Reservation walkIn;
        
        if(theQ.peek()!= null){
            walkIn = theQ.poll();
            System.out.println("Follow me this way");
            System.out.println(walkIn);
        }  
        else{
            System.out.println("No one in in queue, sign in first");
        }
        
    }
    
    /** 
     * this will be called in other methods and ask the user for input and create the reservation object 
     * @return 
     */
    public Reservation reservationInfo(){
        
        int year = 0;
        int month = 0;
        int day =0 ;
        int militaryHour = 0;
        int militaryMinute = 0;
        String lastName = "";
        String firstName = "";
        int partySize = 0;
        Reservation userRes;
        boolean errorOccured;
       
        do{
            try{
                System.out.println("Enter the year of the reservation: ");
                year = scan.nextInt();
                System.out.println("Enter the month of the reservation: ");
                month = scan.nextInt();
                System.out.println("Enter the day of the reservation: ");
                day = scan.nextInt();
                System.out.println("Enter the hour in military time please: ");
                militaryHour = scan.nextInt();
                System.out.println("Enter the minute in military time please: ");
                militaryMinute = scan.nextInt();
                System.out.println("Enter your last name: ");
                lastName = scan.next();
                System.out.println("Enter your first name");
                firstName = scan.next();
                System.out.println("Enter your party size: ");
                partySize = scan.nextInt();
                errorOccured = false;



            }catch(InputMismatchException e){
                     System.out.println("Please enter a valid entry for your reservation");
                     scan.nextLine();
                     errorOccured = true;
            }
        }while(errorOccured);
        
        return userRes = new Reservation(year,month,day,militaryHour, militaryMinute,lastName,firstName,partySize); 
    }
}//end of class 
