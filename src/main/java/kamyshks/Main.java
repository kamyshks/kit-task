package kamyshks;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        final Port port = new Port(args);

        final List<List<Integer>> lists = port.convertIndexes();
        printList(lists);
        printList(port.getAllCombinations(lists));
    }

    private static void printList(final List<List<Integer>> lists) {
        System.out.println(Arrays.toString(lists.toArray()));
    }
}
