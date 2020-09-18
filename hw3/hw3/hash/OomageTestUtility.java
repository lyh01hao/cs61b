package hw3.hash;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        /* TODO:
         * Write a utility function that returns true if the given oomages
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */
        int N = oomages.size();
        HashSet<Integer> set = new HashSet<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (Oomage o : oomages) {
            int bucketNum = (o.hashCode() & 0x7FFFFFFF) % M;
            set.add(bucketNum);
            if (map.containsKey(bucketNum)) {
                int value = map.get(bucketNum) + 1;
                map.put(bucketNum, value);
            } else {
                map.put(bucketNum, 0);
            }
        }
        boolean flag = true;
        for (Integer i : set) {
            if (map.get(i) < N / 50 || map.get(i) > N / 2.5) {
                flag = false;
                break;
            }
        }
        return flag;
    }
}
