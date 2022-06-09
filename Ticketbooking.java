import java.util.*;

public class Ticketbooking {
    static int aLB = 10;
    static int aMB = 10;
    static int aUP = 10;

    static int aRAC = 5;
    static int aWL = 5;

    static List<Integer> lBP = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));// lBP = Lower birth
                                                                                                    // position;

    static List<Integer> mBP = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));

    static List<Integer> uBP = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));

    static List<Integer> racP = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5));

    static List<Integer> wLP = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5));

    static Queue<Integer> wlList = new LinkedList<Integer>();

    static Queue<Integer> racList = new LinkedList<Integer>();

    static List<Integer> bookedTicketList = new ArrayList<Integer>(); // to get a id for store a passenger id in unique
                                                                      // for this line;;;

    static Map<Integer, Passenger> passenger_stored_data = new HashMap<Integer, Passenger>();

    public void bookTicket(Passenger p, int snumber, String allotedBerth) {
        p.number = snumber;
        p.alloted = allotedBerth;
        passenger_stored_data.put(p.passengerId, p);
        bookedTicketList.add(p.passengerId);

        System.out.println("Passenger Id:" + p.passengerId);
        System.out.println("Passenger Name:" + p.name);
        System.out.println("Passenger Age:" + p.age);
        System.out.println("passenger Gender:" + p.gender);
        System.out.println("Alloted Berth:" + snumber + allotedBerth);
        System.out
                .println("------------------------------------------ Your Ticket Was Successfully Booked!!!!!!!!!!!!");
    }

    public void racTicket(Passenger p, int snumber, String RACBerth) {
        p.number = snumber;
        p.alloted = RACBerth;
        passenger_stored_data.put(p.passengerId, p);
        racList.add(p.passengerId);
        System.out.println("Passenger Id:" + p.passengerId);
        System.out.println("Passenger Name:" + p.name);
        System.out.println("Passenger Age:" + p.age);
        System.out.println("passenger Gender:" + p.gender);
        System.out.println("Alloted Berth:" + snumber + RACBerth);
        System.out.println("---------------------RAC are given");
    }

    public void wlTicket(Passenger p, int snumber, String WLBerth) {
        p.number = snumber;
        p.alloted = WLBerth;
        passenger_stored_data.put(p.passengerId, p);
        wlList.add(p.passengerId);
        System.out.println("Passenger Id:" + p.passengerId);
        System.out.println("Passenger Name:" + p.name);
        System.out.println("Passenger Age:" + p.age);
        System.out.println("passenger Gender:" + p.gender);
        System.out.println("Alloted Berth:" + snumber + WLBerth);
        System.out.println(
                "---------------------You are in WAITING BERTH if any passenger cancelled tickets you will move on RAC");

    }

    public void cancelTicket(int passengerId) {
        Passenger p = passenger_stored_data.get(passengerId);
        passenger_stored_data.remove(passengerId);
        bookedTicketList.remove(Integer.valueOf(passengerId));
        int psnumber = p.number;
        System.out.println("---------------Your Ticket was Successfully Cancelled :------------");
        if (p.alloted.equals("L")) {
            lBP.add(psnumber);
            aLB++;
        } else if (p.alloted.equals("M")) {
            mBP.add(psnumber);
            aMB++;
        } else if (p.alloted.equals("U")) {
            uBP.add(psnumber);
            aUP++;
        }
        if (racList.size() > 0) {
            Passenger pRAC = passenger_stored_data.get(racList.poll());
            int pRACnum = pRAC.number;
            racP.add(pRACnum);
            racList.remove(pRAC.passengerId);
            aRAC++;

            if (wlList.size() > 0) {
                Passenger pWL = passenger_stored_data.get(wlList.poll());
                int pWLnum = pWL.number;
                wLP.add(pWLnum);
                wlList.remove(pWL.passengerId);
                pWL.number = racP.get(0);
                pWL.alloted = "RAC";
                racP.remove(0);
                racList.add(pWL.passengerId);
                aWL++;
                aRAC--;
            }
            Main.bookTicket(pRAC);
        }

    }

    public void availableTickets() {
        System.out.println("The LowerBerth Available :" + aLB);
        System.out.println("The MiddleBerth Available :" + aMB);
        System.out.println("The UpperBerth Available:" + aUP);
        System.out.println("Rac List Available :" + aRAC);
        System.out.println("Waiting List Available:" + aWL);
    }

    public void passengerDetails() {
        if (passenger_stored_data.size() == 0) {
            System.out.println("No Passenger Details Found");
            return;
        } else {
            // for(Passenger p : passenger_stored_data.values())
            for (Passenger p : passenger_stored_data.values()) {
                System.out.println("Passenger Id :" + p.passengerId);
                System.out.println("Passenger Name :" + p.name);
                System.out.println("Passenger Age :" + p.age);
                System.out.println("Passenger Gender :" + p.gender);
                System.out.println("Alloted Berth :" + p.number + p.alloted);
                System.out.println("child Name :" + p.cname);
                System.out.println("child Age :" + p.cage);
                System.out.println("-------------------------------------------------------------");
            }
            System.out.println();
        }
    }

}
