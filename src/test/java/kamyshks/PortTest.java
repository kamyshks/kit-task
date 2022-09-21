package kamyshks;

import kamyshks.exceptions.ValidateIndexException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@RunWith(JUnit4.class)
public class PortTest {

    private Port port;
    private final String [] indexes = {"1-4,7", "3-8", "56,33,58,11"};

    @Before
    public void setUp() throws ValidateIndexException {
        port = new Port(indexes);
    }

    @Test
    public void getAllCombinationsTest(){
        final List<List<Integer>> inputList = new ArrayList<>(Arrays.asList(
                Arrays.asList(1, 2, 3), Collections.singletonList(5), Arrays.asList(8, 9, 10)));
        final List<List<Integer>> assertList = new ArrayList<>(Arrays.asList(
                Arrays.asList(1, 5, 8), Arrays.asList(1, 5, 9), Arrays.asList(1, 5, 10),
                Arrays.asList(2, 5, 8), Arrays.asList(2, 5, 9), Arrays.asList(2, 5, 10),
                Arrays.asList(3, 5, 8), Arrays.asList(3, 5, 9), Arrays.asList(3, 5, 10)
                ));
        assertEquals(assertList, port.getAllCombinations(inputList));
    }

    @Test
    public void convertIndexesTest() {
        final List<List<Integer>> assertList = new ArrayList<>(Arrays.asList(
                Arrays.asList(1,2,3,4,7), Arrays.asList(3,4,5,6,7,8), Arrays.asList(56,33,58,11)));
        assertEquals(assertList, port.convertIndexes());
    }

    @Test
    public void parseOneItemTest() {
        final List<Integer> assertList = new ArrayList<>(Arrays.asList(3, 4, 5, 6, 7, 8));
        assertEquals(assertList, port.parseOneItem(indexes[1]));
    }

    @Test
    public void parseAllTest() {
        final List<Integer> assertList = new ArrayList<>(Arrays.asList(1,2,3,4,7));
        assertEquals(assertList, port.parseAll(indexes[0]));
    }

    @Test
    public void asserException() {
        assertThrows(ValidateIndexException.class, () -> new Port(new String[]{"g,7-j", "j"}));
    }

}
