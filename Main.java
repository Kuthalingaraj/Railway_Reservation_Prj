import java.util.*;

public class Main {
    public static void bookTicket(Passenger p) {
        Ticketbooking tb = new Ticketbooking();
        if (Ticketbooking.aWL == 0) {
            System.out.println("Sorry No Tickets are Available:");
            return;
            // above age conditions
        } else if (p.age > 50 && Ticketbooking.aLB > 0) {
            System.out.println("You are a senior citizen, you will give LowerBerth");
            tb.bookTicket(p, (Ticketbooking.lBP.get(0)), "L");
            Ticketbooking.lBP.remove(0);
            Ticketbooking.aLB--;
        }
        // children conditions
        else if (p.cname != ("null") && Ticketbooking.aLB > 0) {
            System.out.println("You have a child so we arrange a Lower Berth :");
            tb.bookTicket(p, (Ticketbooking.lBP.get(0)), "L");
            Ticketbooking.lBP.remove(0);
            Ticketbooking.aLB--;
        }
        // berth conditions
        else if ((p.bp.equals("L") && Ticketbooking.aLB > 0) ||
                (p.bp.equals("M") && Ticketbooking.aMB > 0) || (p.bp.equals("U") && Ticketbooking.aUP > 0)) {
            System.out.println("Prefernce Birth are Available");

            if (p.bp.equals("L")) {
                System.out.println("Lower Berth given");
                tb.bookTicket(p, (Ticketbooking.lBP.get(0)), "L");
                Ticketbooking.lBP.remove(0);
                Ticketbooking.aLB--;
            } else if (p.bp.equals("M")) {
                System.out.println("Middle Berth given");
                tb.bookTicket(p, (Ticketbooking.mBP.get(0)), "M");
                Ticketbooking.mBP.remove(0);
                Ticketbooking.aMB--;
            } else if (p.bp.equals("U")) {
                System.out.println("Upper Berth given");
                tb.bookTicket(p, (Ticketbooking.uBP.get(0)), "U");
                Ticketbooking.uBP.remove(0);
                Ticketbooking.aUP--;
            }
        }
        // Avaiable tickets are given conditions
        else if (Ticketbooking.aLB > 0) {
            System.out.println("Lower Berth are given...");
            tb.bookTicket(p, (Ticketbooking.lBP.get(0)), "L");
            Ticketbooking.lBP.remove(0);
            Ticketbooking.aLB--;
        } else if (Ticketbooking.aMB > 0) {
            System.out.println("Middle Berth are given");
            tb.bookTicket(p, (Ticketbooking.mBP.get(0)), "M");
            Ticketbooking.mBP.remove(0);
            Ticketbooking.aMB--;
        } else if (Ticketbooking.aUP > 0) {
            System.out.println("Upper Berth are given");
            tb.bookTicket(p, (Ticketbooking.uBP.get(0)), "U");
            Ticketbooking.uBP.remove(0);
            Ticketbooking.aUP--;
        } else if (Ticketbooking.aRAC > 0) {
            System.out.println("RAC given");
            tb.racTicket(p, (Ticketbooking.racP.get(0)), "RAC");
            Ticketbooking.racP.remove(0);
            Ticketbooking.aRAC--;
        } else if (Ticketbooking.aWL > 0) {
            System.out.println("You are in Waiting List");
            tb.wlTicket(p, (Ticketbooking.wLP.get(0)), "WL");
            Ticketbooking.wLP.remove(0);
            Ticketbooking.aWL--;
        }

    }
    // cancellation process...........

    public static void cancelTicket(int id) {
        Ticketbooking tb = new Ticketbooking();
        if (!tb.passenger_stored_data.containsKey(id)) {
            System.out.println("Ticket ID is not found :");
        } else
            tb.cancelTicket(id);

    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            boolean loop = true; // first we Intialize loop = true

            while (loop) {
                System.out.println(
                        "1.  Book Your Tickets\n2.  Cancell Your Tickets\n3.  Available Tickets\n4.  Show Booked Tickets\n5.  Exit");
                int choice = sc.nextInt();
                // extra features

                if (Ticketbooking.aWL == 0) {
                    System.out.println("Sorry No Tickets are Available Try again Later...........");
                    
                } 

                switch (choice) {

                    case 1: {

                        System.out.println("Enter the Passenger Name: ");
                        String name = sc.next();
                        System.out.println("Enter the Passenger Age:");
                        int age = sc.nextInt();
                        System.out.println("Enter the Passenger Gender: [M,F]");
                        String gender = sc.next();
                        if (gender.equals("F")) {
                            System.out.println("If you have a child Press 1 \n If you have not a child Press 2");
                            int gchoice = sc.nextInt();
                            if (gchoice == 1) {
                                System.out.println("Your children Name :");
                                String cname = sc.next();
                                System.out.println("Enter Your child age:");
                                int cage = sc.nextInt();
                                //extra
                                if(cage>5){
                                    System.out.println("Age is Less than five is a child so please book separately");
                                   break;
                                }
                                System.out.println("Enter the Passenger Birth Preference: [L,M,U]");
                                String bp = sc.next();
                               
                                Passenger p = new Passenger(name, age, gender, cname, cage, bp);
                                bookTicket(p);
                            } else if (gchoice == 2) {
                                System.out.println("Enter the Passenger Birth Preference: [L,M,U]");
                                String bp = sc.next();
                                String cname = "null";
                                int cage = 0;
                                Passenger p = new Passenger(name, age, gender, cname, cage, bp);
                                bookTicket(p);
                            }
                            break;
                        }
                        if (gender.equals("M")) {
                            System.out.println("Enter the Passenger Birth Preference: [L,M,U]");
                            String bp = sc.next();
                            String cname = "null";
                            int cage = 0;
                            Passenger p = new Passenger(name, age, gender, cname, cage, bp);
                            bookTicket(p);
                        }
                        break;

                    }

                    case 2: {
                        System.out.println("Enter the Passenger ID:");
                        int id = sc.nextInt();
                        cancelTicket(id);
                        break;
                    }
                    case 3: {
                        Ticketbooking tb = new Ticketbooking();
                        tb.availableTickets();

                        break;
                    }
                    case 4: {
                        Ticketbooking tb = new Ticketbooking();
                        tb.passengerDetails();

                        break;
                    }
                    case 5: {
                        loop = false;
                    }
                        break;

                }

            
        }

        }

    }

}
