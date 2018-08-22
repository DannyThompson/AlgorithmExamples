import java.util.ArrayList;

/**
 * Simple class demonstrating modular hashing of a predetermined list of numbers.
 */

public class HashTable {
    private static final int TABLE_LENGTH = 13;
    private static final int PRIME_INT =  71;

    private Long[] hashTable = new Long[TABLE_LENGTH];

    /**
     * Hashes the numbers in the array list.
     * The hash value is determined by the first 3 values multiplied by a prime # (71).
     * 
     * @param list - The list of numbers to add to the hash table.
     */
    private void hashList(ArrayList<Long> list) {
        for(Long num : list) {
            String key = Long.toString(num).substring(0, 3);
            int hash = Integer.parseInt(key) * PRIME_INT;
            System.out.println("Hash value for " + num + " is: " + hash);
            insertIntoTable(hash, num);
        }
    }
    
    /**
     * Insert the value into a hashtable.
     * If a collision is detected, notify the user. Index will be incremented each time a collision happens.
     * 
     * @param hash - The hash value that was determined using the first 3 values of the number.
     * @param numToAdd - The number to add to the hash table.
     */
    private void insertIntoTable(int hash, long numToAdd) {
        int index = hash % TABLE_LENGTH;
        int collisions = 0;
        
        while(hashTable[index] != null) {
            collisions++;
            index = (index + 1) % TABLE_LENGTH;
        }
        if(collisions > 0) {
            System.out.println("Collision detected for " + numToAdd + ", index incremented " + collisions + " times.");
        }
        hashTable[index] = numToAdd;
        System.out.println("Value " + numToAdd + " added at index: " + index + "\n");
    }

    public static void main(String[] args) {
        HashTable hashTable = new HashTable();

        ArrayList<Long> mockSSNList = new ArrayList<Long>() {{
		add(6000031234L);
		add(6000031235L);
		add(6010031236L);
		add(5120031234L);
		add(5100031234L);
		add(5900031234L);
		add(5120031534L);
	    }};

        hashTable.hashList(mockSSNList);
    }
}
