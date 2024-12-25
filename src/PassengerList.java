

public class PassengerList  {
    private NodePassenger head;

    // Time complexity = O(1)
    public boolean isEmpty() {
        return (head == null);
    }

    // Time complexity = O(n)
    public void displayAllPassengers() {
        if (isEmpty()) {
            System.out.println("Linked list is empty!");
            return;
        }
        System.out.println("Passenger Database ");
        NodePassenger current = head;
        while (current != null) {
            current.display();
            System.out.println();
            current = current.next;
        }
    }

    // Time complexity = O(n)
    public int listLength() {
        NodePassenger current = head;
        int count = 0;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    // Time complexity = O(n)
    public boolean searchDuplicationPassport(String passportNum){
        NodePassenger current = head;
        while (current != null) {
            if (current.passNum.equals(passportNum)){
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // Time complexity = O(1)
    public NodePassenger addPassenger(String name, String passNum) {
        NodePassenger newNode = new NodePassenger(name, passNum);
        newNode.next = head;
        head = newNode;
        return newNode;
    }

    // Time complexity = O(n)
    public NodePassenger findPassenger(int passengerId) {
        System.out.println("passenger work");
        NodePassenger current = head;
        while (current != null) {
            if (passengerId == current.getPassengerId()) {
                current.display();
                return current;
            }
            current = current.next;
        }
        System.out.println("No such passenger Id!");
        return null;
    }

    // Time complexity = O(n)
    public void deleteFromPosition(int pos) {
        if (isEmpty()) {
            System.out.println("List is already empty.");
        } else if (pos > listLength() || pos < 1) {
            System.out.println("Invalid position!");
        } else if (head.next == null && pos == 1) {
            head = null;
        } else if (pos == 1) {
            NodePassenger temp = head;
            head = head.next;
            temp.next = null;
        } else {
            NodePassenger temp = head;
            int i = 1;
            while (i < pos - 1) {
                temp = temp.next;
                pos--;
            }
            temp.next = temp.next.next;
        }
    }

    // Time complexity = O(n)
    public int count() {
        NodePassenger current = head;
        int count = 0;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }
}