/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra, Alexander Hwang
 *
 */
public class RadixSort {
    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     * @return String[] the sorted array
     */
    public static String[] sort(String[] asciis) {

        String[] copy = new String[asciis.length];
        System.arraycopy(asciis, 0, copy, 0, asciis.length);
        int stringLength = copy[1].length();
        for (int i = stringLength - 1; i >= 0; i--) {
            sortHelperLSD(copy, i);
        }
        return copy;
    }

    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.
     *
     * @param asciis Input array of Strings
     * @param index  The position to sort the Strings on.
     */
    private static void sortHelperLSD(String[] asciis, int index) {
        // Optional LSD helper method for required LSD radix sort
        int[] ascii = new int[128];
        for (String s : asciis) {
            ascii[s.charAt(index)]++;
        }
        int pos = 0;
        int[] starts = new int[128];
        for (int i = 0; i < 128; i++) {
            starts[i] = pos;
            pos += ascii[i];
        }

        String[] sorted = new String[asciis.length];
        for (int i = 0; i < asciis.length; i++) {
            char item = asciis[i].charAt(index);
            int place = starts[item];
            sorted[place] = asciis[i];
            starts[item] += 1;

        }
        System.arraycopy(sorted, 0, asciis, 0, asciis.length);
        return;
    }

    /**
     * MSD radix sort helper function that recursively calls itself to achieve the sorted array.
     * Destructive method that changes the passed in array, asciis.
     *
     * @param asciis String[] to be sorted
     * @param start  int for where to start sorting in this method (includes String at start)
     * @param end    int for where to end sorting in this method (does not include String at end)
     * @param index  the index of the character the method is currently sorting on
     **/
    private static void sortHelperMSD(String[] asciis, int start, int end, int index) {
        // Optional MSD helper method for optional MSD radix sort
        return;
    }

//    public static void main(String[] args) {
//        String[] test = new String[]{"cat", "ele", "bal", "fuc", "app", "gia", "dic", "aka"};
//        String[] sorted = RadixSort.sort(test);
//        for (int i = 0; i < sorted.length; i++) {
//            System.out.println(sorted[i]);
//        }
//    }
}
