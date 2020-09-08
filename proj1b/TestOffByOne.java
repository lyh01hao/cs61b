import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    OffByOne obo = new OffByOne();
    /*
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    Uncomment this class once you've created your CharacterComparator interface and OffByOne class. **/
    @Test
    public void TestOffByOne() {
        assertTrue(obo.equalChars('r', 'q'));
        assertTrue(obo.equalChars('e', 'd'));
        assertTrue(obo.equalChars('a', 'b'));
        assertFalse(obo.equalChars('a', 'a'));
    }
}
