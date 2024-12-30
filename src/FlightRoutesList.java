    public class FlightRoutesList{
        NodeFlightRoute head;
        private NodeFlightRoute tail;

        // Time complexity = O(1)
        public FlightRoutesList() {
            head = null;
            tail = null;
        }

        // Time complexity = O(1)
        public boolean isEmpty() {
            return (head == null);
        }

        // Time complexity = O(n)
        public void addRoute(City[] cities,String date,int Seats) { // added date
            NodeFlightRoute route = new NodeFlightRoute(date); // added date
            for (int i = 0; i < cities.length - 1; i++) {
                route.flights.addFlight(cities[i], cities[i + 1],Seats);
            }
            insertLast(route);
        }

        // Time complexity = O(1)
        private void insertLast(NodeFlightRoute newNode) {
            if (isEmpty()) {
                head = newNode;
                tail=newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
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

        public void addPassengerToFlights(NodePassenger passenger, int routeId, String sourceCity, String destinationCity) {
            NodeFlightRoute current = this.head;

            // Traverse the route list to find the matching routeId
            while (current != null) {
                if (current.getRouteId() == routeId) {
                    NodeFlight flight = current.flights.head; // Get the first flight in the route
                    boolean bookingStarted = false; // Flag to start booking when sourceCity is found

                    // Traverse the flight list to book seats
                    while (flight != null) {
                        // Check if this is the source city to start booking
                        if (flight.source.name.equals(sourceCity)) {
                            bookingStarted = true;
                        }

                        //reserving seats until destination
                        if (bookingStarted) {
                            for (int i = 0; i < flight.seats.length; i++) {
                                if (flight.seats[i] == null) {
                                    flight.seats[i] = passenger; // Book the seat
                                    System.out.println("Booked seat " + i + " on flight " + flight.flightId);
                                    break;
                                }
                            }
                        }

                        // Stop booking if we've reached the destination city
                        if (flight.destination.name.equals(destinationCity)) {
                            return; // Booking complete
                        }

                        flight = flight.next;
                    }
                }
                current = current.next;
            }
        }


        public void removePassengerFromFlights(NodePassenger passenger, int routeId) {
            NodeFlightRoute currentRoute = this.head;
            // Traverse through the flight routes
            while (currentRoute != null) {
                if (currentRoute.getRouteId() == routeId) {
                    // Get the first flight in the current route
                    NodeFlight flight = currentRoute.flights.head;

                    // Traverse through the flights in the current route
                    while (flight != null) {
                        // Check each seat in the flight
                        for (int i = 0; i < flight.seats.length; i++) {
                            if (flight.seats[i] == passenger) {
                                // Remove the passenger from the seat
                                flight.seats[i] = null;
                                System.out.println("Passenger removed from seat " + i + " on flight " + flight.flightId);
                                break; // Exit the loop after removing the passenger
                            }
                        }
                        flight = flight.next;
                    }
                    break;
                }
                currentRoute = currentRoute.next;
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
    }
