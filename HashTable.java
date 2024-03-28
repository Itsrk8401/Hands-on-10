class HashNode {
    int key;
    int value;
    HashNode next;
    HashNode prev;

    public HashNode(int key, int value) {
        this.key = key;
        this.value = value;
        this.next = null;
        this.prev = null;
    }
}

public class HashTable {
    private HashNode[] table;
    private int size;
    private int capacity;
    private static final double LOAD_FACTOR = 0.75;

    public HashTable(int initialCapacity) {
        this.capacity = initialCapacity;
        this.size = 0;
        this.table = new HashNode[capacity];
    }

    private int hash(int key) {
        // Multiplication method
        double A = (Math.sqrt(5) - 1) / 2;
        int multiplicationHashCode = (int) Math.floor(capacity * ((key * A) % 1));

        // Division method
        int divisionHashCode = key % capacity;

        // Combine the hash codes using XOR
        return multiplicationHashCode ^ divisionHashCode;
    }

    public void put(int key, int value) {
        int index = hash(key) % capacity; // Apply modulo operation to ensure index is within bounds
        HashNode newNode = new HashNode(key, value);

        if (table[index] == null) {
            table[index] = newNode;
        } else {
            HashNode current = table[index];
            while (current.next != null) {
                if (current.key == key) {
                    current.value = value;
                    return;
                }
                current = current.next;
            }
            current.next = newNode;
            newNode.prev = current;
        }
        size++;
        if ((double) size / capacity >= LOAD_FACTOR) {
            resizeTable();
        }
    }

    public int get(int key) {
        int index = hash(key);
        HashNode current = table[index];
        while (current != null) {
            if (current.key == key) {
                return current.value;
            }
            current = current.next;
        }
        return -1; // Key not found
    }

    public void remove(int key) {
        int index = hash(key);
        HashNode current = table[index];
        while (current != null) {
            if (current.key == key) {
                if (current.prev == null) {
                    table[index] = current.next;
                } else {
                    current.prev.next = current.next;
                }
                size--;
                if ((double) size / capacity <= 0.25) {
                    resizeTable();
                }
                return;
            }
            current = current.next;
        }
    }

    private void resizeTable() {
        int newCapacity = capacity * 2;
        HashNode[] newTable = new HashNode[newCapacity];

        for (int i = 0; i < capacity; i++) {
            HashNode current = table[i];
            while (current != null) {
                HashNode next = current.next;
                int newIndex = hash(current.key) % newCapacity; // Apply modulo operation for new capacity
                if (newTable[newIndex] == null) {
                    newTable[newIndex] = current;
                } else {
                    HashNode temp = newTable[newIndex];
                    while (temp.next != null) {
                        temp = temp.next;
                    }
                    temp.next = current;
                    current.prev = temp;
                }
                current = next;
            }
        }
        capacity = newCapacity;
        table = newTable;
    }

    public static void main(String[] args) {
        HashTable ht = new HashTable(10);
        ht.put(1, 10);
        ht.put(2, 20);
        ht.put(11, 30);
        ht.put(21, 40);
        ht.put(3, 50);
        ht.put(12, 60);
        ht.put(4, 70);
        ht.put(13, 80);

        System.out.println("Value corresponding to key 21: " + ht.get(21));

        ht.remove(11);
        System.out.println("Value corresponding to key 11 after removal: " + ht.get(11));
    }
}

Output :

Value corresponding to key 21: -1
Value corresponding to key 11 after removal: -1


