import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {
    /*
    Test add methods and remove methods
     */
    @Test
    public void TestArrauDeque() {
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads = new ArrayDequeSolution<>();
        String output = "";
        int count = 0;

        while (true) {
            double randNum = StdRandom.uniform();

            if(randNum < 0.25) {
                sad.addFirst(count);
                ads.addFirst(count);
                Integer actual = sad.get(0);
                Integer expected = ads.get(0);
                output += "\naddFirst(" + count + ")";
                assertEquals(output,expected,actual);
            }
        }
    }

}
