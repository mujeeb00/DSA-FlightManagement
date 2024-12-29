public class FlightRoutesList{
    NodeFlightRoute head;

    // Time complexity = O(1)
    public FlightRoutesList() {
        head = null;
    }

    // Time complexity = O(1)
    public boolean isEmpty() {
        return (head == null);
    }

    // Time complexity = O(n)
    public int addRoute(City[] cities,String date,int Seats) { // added date
        NodeFlightRoute route = new NodeFlightRoute(date); // added date
        for (int i = 0; i < cities.length - 1; i++) {
            route.flights.addFlight(cities[i], cities[i + 1],Seats);
        }
        insertLast(route);
        return route.getRouteId();
    }

    // Time complexity = O(n)
    private void insertLast(NodeFlightRoute newNode) {
        if (isEmpty()) {
            newNode.next = head;
            head = newNode;
        } else {
            NodeFlightRoute currentNode = head;
            while (currentNode.next != null) {
                currentNode = currentNode.next;
            }
            currentNode.next = newNode;
            newNode.next = null;
        }
    }

    // Time complexity = O(n)
    public void display() {
        if (isEmpty()) {
            System.out.println("Linked list is empty!");
            return;
        }
        System.out.println("FLight Routes Linked list: ");
        NodeFlightRoute current = head;
        while (current != null) {
            current.display();
            current = current.next;
        }
    }

    // Time complexity = O(n^2)
    public boolean getRoutes(String sourceCity, String destinationCity) {
        boolean routeFound = false;
        boolean seatsAvailable = false;
        if (isEmpty()) {
            System.out.println("No flights Available! It's doomsday!");
        } else {
            NodeFlightRoute currentNode = head;
            while (currentNode != null) {
                NodeFlight[] route = currentNode.getFlights(sourceCity, destinationCity);
                if (route != null) {
                    routeFound = true;
                    System.out.println("Route id " + currentNode.getRouteId() + " : ");
                    for (int i = 0; i < route.length; i++) {
                        if (route[i].getVacantSeats() != 0){
                            seatsAvailable = true;
                            System.out.println(
                                    route[i].source.name + "-->" + route[i].destination.name + "(Seats: "
                                            + route[i].getVacantSeats() + ")");
                        }
                    }
                }
                System.out.println();
                currentNode = currentNode.next;
            }
            if(!seatsAvailable){
                System.out.println("No seats available in this flight!");
            }
            if (!routeFound) {
                System.out.println("No flights available for this route");
            }
        }
        return(routeFound && seatsAvailable);
    }

    // Time complexity = O(n^4)
    public void addPassengerToFlights(NodePassenger passenger, int routeId, String sourceCity, String destinationCity) {
        NodeFlightRoute current = this.head; // flightLinkedList's head which is a NodeFlight node

        //Getting connection numbers to add passenger between specific flights
        while (current != null) {
            if (current.getRouteId() == routeId) {
                //flight node (for from-to)
                NodeFlight flight = current.flights.head;
                while (flight != null) {
                    int seatNum; //seat number
                    if (flight.source.name.equals(sourceCity)) {
                        for (int i = 0; i < flight.seats.length; i++) {
                            if (flight.seats[i] == null) {
                                flight.seats[i] = passenger;
                                seatNum = i;
                                System.out.println("Booked seat " + seatNum + " on flight " + flight.flightId);
                                if (!flight.destination.name.equals(destinationCity)) {
                                    flight = flight.next;
                                } else {
                                    break;
                                }
                                //traverse from source city to destination city and set seats in between
                                while (flight != null) {
                                    for (i = 0; i < flight.seats.length; i++) {
                                        if (flight.seats[i] == null) {
                                            flight.seats[i] = passenger;
                                            seatNum = i;
                                            System.out.println(
                                                    "Booked seat " + seatNum + " in flight " + flight.flightId);
                                            break;
                                        }
                                    }
                                    if (flight.destination.name.equals(destinationCity)) {
                                        break;
                                    }
                                    flight = flight.next;
                                }
                                break;
                            }
                        }
                    }
                    flight = flight.next;
                }
            }
            current = current.next;
        }
    }

    //-------------------------------------//

    // Time complexity = O(n^4)
    public void removePassengerFromFlights(NodePassenger passenger, int routeId, String sourceCity, String destinationCity) {

        NodeFlightRoute current = this.head; // flightLinkedList's head which is a NodeFlight node

        while (current != null) {
            if (current.getRouteId() == routeId) {
                //flight node (for from-to)
                NodeFlight flight = current.flights.head;
                while (flight != null) {
                    int seatNum; //seat number
                    if (flight.source.name.equals(sourceCity)) {
                        for (int i = 0; i < flight.seats.length; i++) {
                            if (flight.seats[i] == passenger) {
                                flight.seats[i] = null;
                                seatNum = i;
                                System.out.println("Passenger removed from seat " + seatNum + " on flight " + flight.flightId);
                                if (!flight.destination.name.equals(destinationCity)) {
                                    flight = flight.next;
                                } else {
                                    break;
                                }
                                //traverse from source city to destination city and set seats in between
                                while (flight != null) {
                                    for (i = 0; i < flight.seats.length; i++) {
                                        if (flight.seats[i] == passenger) {
                                            flight.seats[i] = null;
                                            seatNum = i;
                                            System.out.println(
                                                    "Passenger removed from seat " + seatNum + " on flight " + flight.flightId);
                                            break;
                                        }
                                    }
                                    if (flight.destination.name.equals(destinationCity)) {
                                        break;
                                    }
                                    flight = flight.next;
                                }
                                break;
                            }
                        }
                    }
                    flight = flight.next;
                }
            }
            current = current.next;
        }
    }
    //-------------------------------------//

    // temporary print seats to check if its working
    // Time complexity = O(n^2)
    public void printSeats(int flightId, FlightRoutesList frl) {
        NodeFlightRoute current = frl.head;
        while (current != null) {
            if (current.routeId == flightId) {
                break;
                //current node stays at the flight node after break
            } else {
                current = current.next;
            }
        }
        NodeFlight nf = current.flights.head; //First Flight in Route
        while (nf != null) {
            System.out.println("Flight " + nf.flightId + ": ");
            for (int i = 0; i < nf.seats.length; i++) {
                if (nf.seats[i] == null) {
                    System.out.println(i + " Empty");
                } else {
                    System.out.println(nf.seats[i].passengerName);
                }
            }
            System.out.println();
            nf = nf.next;
        }
    }

    // Time complexity = O(n^2)
    public boolean verifyRoute(int routeId, String sourceCity, String destinationCity) {
        NodeFlightRoute current = head;
        boolean sourceExists = false;
        boolean destinationExists = false;
        //Getting connection numbers to add passenger between specific flights
        while (current != null && !(sourceExists && destinationExists)) {
            if (current.getRouteId() == routeId) {
                //flight node (for from-to)
                NodeFlight flight = current.flights.head;
                while (flight != null) {
                    if (flight.source.name.equals(sourceCity)) {
                        sourceExists = true;
                    }
                    if (flight.destination.name.equals(destinationCity)) {
                        destinationExists = true;
                    }
                    flight = flight.next;

                }
            }
            current = current.next;
        }

        return (sourceExists && destinationExists);
    }

    // Time complexity = O(n)
    public int count() {
        int count = 0;
        NodeFlightRoute current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }
}
