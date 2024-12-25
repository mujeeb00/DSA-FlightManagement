public class DynamicArray {
    private Object[] data;
    private int size;

    public DynamicArray() {
        data = new Object[10];  // initial capacity
        size = 0;
    }

    public void add(Object element) {
        if (size == data.length) {
            resize();
        }
        data[size++] = element;
    }

    public Object get(int index) {
        if (index >= 0 && index < size) {
            return data[index];
        } else {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
    }

    public int size() {
        return size;
    }

    public void resize() {
        Object[] newData = new Object[data.length * 2];
        System.arraycopy(data, 0, newData, 0, data.length);
        data = newData;
    }

    public void clear() {
        size = 0;
        data = new Object[10];  // reset to initial capacity
    }

    public boolean contains(Object element) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(element)) {
                return true;
            }
        }
        return false;
    }
}
