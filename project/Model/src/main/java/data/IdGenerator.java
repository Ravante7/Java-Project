package data;

import java.util.ArrayList;
import java.util.Random;


public class IdGenerator {

    private static Random random = new Random();
    private static ArrayList<Integer> usedIds = new ArrayList<Integer>();

    public static int newStudentId() {
        return generate(1000, 9999);
    }

    public static int newEmployeeId() {
        return generate(10000, 99999);
    }

    public static int newAdminId() {
        return generate(100000, 999999);
    }


    public static void register(int id) {
        if (!usedIds.contains(id)) {
            usedIds.add(id);
        }
    }


    private static int generate(int min, int max) {
        int id;
        do {
            id = min + random.nextInt(max - min + 1);
        } while (usedIds.contains(id));
        usedIds.add(id);
        return id;
    }
}
