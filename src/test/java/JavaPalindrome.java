import com.sun.deploy.util.StringUtils;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class JavaPalindrome {
    private boolean isPalindrome(String s) {
        StringBuilder sb = new StringBuilder(s);
        String s2 = sb.reverse().toString();
        if (s2.equalsIgnoreCase(s)) {
            return true;
        }
        return false;
    }

    @Test
    public void testIsPalindrome(){
        assertTrue(isPalindrome("Deleveled"));
    }
}
