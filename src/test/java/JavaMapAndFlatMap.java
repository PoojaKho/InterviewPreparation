import model.Human;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class JavaMapAndFlatMap {

    @Test
    public void testFlatMap() {
        List<List<String>> list = Arrays.asList(Arrays.asList("a", "b"), Arrays.asList("c", "d"));
        List<String> upperCaseList = list.stream().flatMap(v -> v.stream().map(String::toUpperCase)).collect(Collectors.toList());
        upperCaseList.forEach(System.out::print);
    }

    @Test
    public void test2() {
        Human h = new Human();
        h.setName(null);
        Optional<Human> human = Optional.ofNullable(h);
        System.out.println(human
                .map(Human::getName)
                .orElse("not specified"));

        String value = "test";
        Optional<String> valueOpt = Optional.ofNullable(value);
       valueOpt.ifPresent(s -> System.out.println(s.toUpperCase()));
    }
}
