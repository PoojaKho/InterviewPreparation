import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JavaWordCounter {
    static String[] COUNTRY_NAMES
            = {"China", "Australia", "India", "USA", "USSR", "UK", "China",
            "France", "Poland", "Austria", "India", "USA", "Egypt", "China"};

    @Test
    public void count() {
        Map<String, Integer> counterMap = new HashMap<>();
        Stream.of(COUNTRY_NAMES).forEach(v -> {
            if (counterMap.containsKey(v)) {
                int val = counterMap.get(v);
                counterMap.remove(v);
                counterMap.put(v, ++val);
            } else {
                counterMap.put(v, 1);
            }
        });
        counterMap.forEach((k, v) -> System.out.println(k + "  " + v));
    }
@Test
    public void test2(){
        Optional<List<String>> s= Optional.of(Arrays.asList());
        System.out.println(s.orElseGet(() -> new ArrayList<String>()).isEmpty());
    }
}
