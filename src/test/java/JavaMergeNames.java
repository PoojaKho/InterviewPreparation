import com.google.gson.internal.Streams;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.lang.instrument.UnmodifiableClassException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;
import static org.junit.Assert.assertEquals;


public class JavaMergeNames {
    private String uniqueNames(String[] s1, String[] s2) {

        List<String> a = Arrays.asList(s1);
        List<String> b = Arrays.asList(s2);
        return Stream.concat(
                a.stream(),
                b.stream().filter(v1 -> !a.contains(v1))).collect(Collectors.joining(", "));
    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void testUniqueNames() {
        String s = "Ava, Emma, Olivia, Sophia";
        String res = uniqueNames(new String[]{"Ava", "Emma", "Olivia"}, new String[]{"Olivia", "Sophia", "Emma"});
        System.out.println(res);
        assertEquals(s, res);

        String res1 = uniqueNames1(new String[]{"Ava", "Emma", "Olivia"}, new String[]{"Olivia", "Sophia", "Emma"});
        System.out.println(res1);
        assertEquals(s, res1);
    }

    private String uniqueNames1(String[] s1, String[] s2) {
        List<String> a = Arrays.asList(s1);
        List<String> b = Arrays.asList(s2);
        Set<String> set = Stream.concat(a.stream(), b.stream()).collect(toSet());
        return set.stream().sorted().collect(Collectors.joining(", "));
    }

    @Test
    public void testCollectors() {
        List<String> givenList = Arrays.asList("a", "bb", "ccc", "dd");
        Map<String, Integer> result1 = givenList.stream()
                .collect(Collectors.toMap(v -> v, String::length));

        //Function.identity() is just a shortcut for defining a function that accepts and returns the same value.
        Map<String, Integer> result = givenList.stream()
                .collect(Collectors.toMap(Function.identity(), String::length));

        //result.forEach((k,v)->System.out.print(k + " : " + v + " "));

//listWithDuplicates
        /*List<String> listWithDuplicates = Arrays.asList("a", "bb", "c", "d", "bb");
        listWithDuplicates.stream().collect(Collectors.toMap(Function.identity(), String::length));
        exceptionRule.expect(IllegalStateException.class);*/
        List<String> listWithDuplicates = Arrays.asList("a", "bb", "c", "d", "bb");

       /* Map<String, Integer> result2 = listWithDuplicates.stream()
                .collect(Collectors.toMap(Function.identity(), String::length, (item, identicalItem) -> item));*/

        Map<String, Integer> result2 = listWithDuplicates.stream().distinct().collect(Collectors.toMap(k -> k, String::length));
        result2.forEach((k, v) -> System.out.print(k + " : " + v + " "));
    }

    @Test(expected = IllegalStateException.class)
    public void testMapDuplicateKeys() {
        List<String> listWithDuplicates = Arrays.asList("a", "bb", "c", "d", "bb");
        listWithDuplicates.stream().collect(Collectors.toMap(Function.identity(), String::length));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testCollectingAndThen() {
        List<String> givenList = Arrays.asList("a", "bb", "ccc", "dd");
        List<String> result = givenList.stream()
                .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
        result.add("c");
    }

    @Test
    public void testGroupingBy() {
        List<String> givenList = Arrays.asList("a", "bb", "ccc", "dd");
        Map<Integer, Set<String>> result = givenList.stream()
                .collect(groupingBy(String::length, toSet()));
        result.forEach((k, v) -> System.out.print("result1 " + k + " : " + v + " "));

        Map<Integer, List<String>> result1 = givenList.stream()
                .collect(groupingBy(String::length, toList()));
        System.out.println("");
        result1.forEach((k, v) -> System.out.print("result2 " + k + " : " + v + " "));
    }
}
