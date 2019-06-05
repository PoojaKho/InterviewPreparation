import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AnotherTestJava {

    private String add(char[] c) {
        String s = "";
        for (char c1 : c) {

            if (Character.isDigit(c1)) {
                s = s + c1;
            }
        }
        return s;
    }

    @Test
    public void test() {
        assertEquals(add(new char[]{'1', 'a', '0'}), "10");
    }

    @Test
    public void test1(){
       List<String> l= Arrays.asList(new String[]{"a","b", "k", "m", "l"});
        assertEquals(l.get(l.size()-1),"l");
        Collections.reverse(l);
        System.out.println(l);
        }
    }

