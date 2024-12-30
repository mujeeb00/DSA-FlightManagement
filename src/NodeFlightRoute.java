public class NodeFlightRoute {
    public static int nextRouteId = 0;
    int routeId;

    // Time complexity = O(1)
    public String getDate() {
        return date;
    }

    public String date;
    FlightList flights;

    public NodeFlightRoute next = null;

    public NodeFlightRoute(String date) {
        this.date = date;
        this.routeId = nextRouteId;
        nextRouteId++;
        flights = new FlightList();
    }

    public int getRouteId() {
        return routeId;
    }

    // Time complexity = O(n^2)
    public NodeFlight[] getFlights(String citySource, String cityDestination) {
        int j = 0; //count for cities nodes between source city and destination city

        // to check if the source and destination match in the flight
        boolean source = false;
        boolean destination = false;

        NodeFlight temp = null; // to preserve the original flights head
        NodeFlight current = flights.head;
        while (current != null) {
            if (citySource.equals(current.source.name)) {
                source = true;
                // set the head where the source city is
                temp = current;
            }
            // count cities from the source if true
            if (source)
                j += 1;
            if (cityDestination.equals(current.destination.name)) {
                destination = true;
                //break if destination is found
                break;
            }
            current = current.next;
        }

        //setting current to newly set flights head
        int i = 0; //index for cities array
        if (source && destination) {
            NodeFlight[] flights = new NodeFlight[j];
            while (temp != null && j != 0) {
                flights[i] = temp;
                temp = temp.next;
                j--;
                i++;
            }
            return flights;
        }
        return null;

    }


    // Time complexity = O(1) combine with display method Time complexity = O(n)
    public void display() {
        System.out.println("Route ID: " + this.routeId);
        System.out.println("Date    : " + getDate());
        flights.display();
        System.out.println();
    }
}

