

public class CityDynamicArray {
    private City[] data;
    private int size;
    private static final int INITIAL_CAPACITY = 10;

    public CityDynamicArray() {
        data = new City[INITIAL_CAPACITY];
        size = 0;
    }

    // Adds a new city to the dynamic array
    public void add(City city) {
        if (size == data.length) {
            resize();
        }
        data[size++] = city;
    }

    // Resizes array when the size limit is reached
    private void resize() {
        int newCapacity = data.length * 2;
        City[] newData = new City[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    // city at the specified index
    public City get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return data[index];
    }

    // Returns size of the array
    public int size() {
        return size;
    }

    // Display
    public void display() {
        for (int i = 0; i < size; i++) {
            System.out.println(data[i].toString());
        }
    }
}
