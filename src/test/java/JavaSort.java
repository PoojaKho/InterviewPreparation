import model.Human;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JavaSort {

    @Test
    public void whenSortingEntitiesByName_thenCorrectlySorted() {
        List<Human> humans = Arrays.asList(
                new Human("Sarah", 10),
                new Human("Jack", 12)
        );
       // humans.sort((h1,h2) -> h1.getName().compareTo(h2.getName()));
        humans.sort(Human::compareByNameThenAge);
        assertEquals(humans.get(0),new Human("Jack", 12));
    }

    @Test
    public void givenInstanceMethod_whenSortingEntitiesByNameThenAge_thenCorrectlySorted() {
        List<Human> humans = Arrays.asList(
                new Human("Sarah", 10),
                new Human("Jack", 12)
        );
        Collections.sort(
                humans, Comparator.comparing(Human::getName));
        assertEquals(humans.get(0), new Human("Jack", 12));
    }

    @Test
    public void whenSortingEntitiesByNameReversed_thenCorrectlySorted() {
        List<Human> humans = Arrays.asList(
                new Human("Sarah", 10),
                new Human("Jack", 12)
        );

        Comparator<Human> comparator
                = (h1, h2) -> h1.getName().compareTo(h2.getName());

        humans.sort(comparator.reversed());

        assertEquals(humans.get(0), new Human("Sarah", 10));
    }

    @Test
    public void
    givenComposition_whenSortingEntitiesByNameThenAge_thenCorrectlySorted() {

        List<Human> humans = Arrays.asList(
                new Human("Sarah", 12),
                new Human("Sarah", 10),
                new Human("Zack", 12)
        );

        humans.sort(
                Comparator.comparing(Human::getName).thenComparing(Human::getAge)
        );

        assertEquals(humans.get(0), new Human("Sarah", 10));
    }

    @Test
    public final void givenStreamComparatorOrdering_whenSortingEntitiesByName_thenCorrectlySorted() {
        List<Human> humans = Arrays.asList(new Human("Sarah", 10), new Human("Jack", 12));

        List<Human> sortedHumans = humans.stream()
                .sorted(Comparator.comparing(Human::getName))
                .collect(Collectors.toList());

        assertEquals(sortedHumans.get(0), new Human("Jack", 12));
    }

    @Test
    public final void givenStreamComparatorOrdering_whenSortingEntitiesByNameReversed_thenCorrectlySorted() {
        List<Human> humans = Arrays.asList(new Human("Sarah", 10), new Human("Jack", 12));

        List<Human> reverseSortedHumans = humans.stream()
                .sorted(Comparator.comparing(Human::getName, Comparator.reverseOrder()).thenComparing(Human::getAge))
                .collect(Collectors.toList());

        assertEquals(reverseSortedHumans.get(0), new Human("Sarah", 10));
    }

    @Test
    public void testMapAndReduce() {
        List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
        double bill = costBeforeTax.stream().map(Double::valueOf).map(cost -> cost + .12 * cost).reduce((sum, cost) -> sum + cost).get();
        //System.out.println("Total : " + bill);
        assertEquals(bill, 1680.0, 0.000);
    }

    @Test

    public void testSummaryStatistics() {
        List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
        IntSummaryStatistics stats = primes.stream().mapToInt((x) -> x).summaryStatistics();
        System.out.println("Highest prime number in List : " + stats.getMax());
        System.out.println("Lowest prime number in List : " + stats.getMin());
        System.out.println("Sum of all prime numbers : " + stats.getSum());
        System.out.println("Average of all prime numbers : " + stats.getAverage());


    }
}
