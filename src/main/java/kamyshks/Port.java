package kamyshks;

import kamyshks.exceptions.ValidateIndexException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Port {
    private String[] indexes;

    public Port(final String[] indexes) throws ValidateIndexException {
        validateIndexes(indexes);
        this.indexes = indexes;
    }

    public void validateIndexes(final String[] indexes) throws ValidateIndexException {
        try {
            Stream.of(indexes).map(e-> e.split("[,\\-]")).flatMap(Arrays::stream).forEach(Integer::parseInt);
        }catch (Exception e){
            throw new ValidateIndexException("Error during convert indexes");
        }
    }

    public List<List<Integer>> convertIndexes() {
        return Arrays.stream(this.indexes).map(this::parseAll).collect(Collectors.toList());
    }

    public List<List<Integer>> getAllCombinations(final List<List<Integer>> inputArray) {
        final List<List<Integer>> allCombinations = new ArrayList<>();
        int length = inputArray.size();
        int currentIdx = length - 1;
        int[] index = new int[length];
        int[] maxIndex = inputArray.stream().mapToInt(List::size).map(it -> it - 1).toArray();

        while (true) {
            allCombinations.add(IntStream.range(0, length)
                    .map(idx -> inputArray.get(idx).get(index[idx]))
                    .boxed()
                    .collect(Collectors.toList()));

            index[currentIdx]++;
            if (index[currentIdx] <= maxIndex[currentIdx]) continue;
            while (currentIdx >= 0 && index[currentIdx] >= maxIndex[currentIdx]) currentIdx--;
            if (currentIdx < 0) break;
            index[currentIdx]++;
            for (int j = currentIdx + 1; j < length; j++) index[j] = 0;
            currentIdx = length - 1;
        }
        return allCombinations;
    }

    List<Integer> parseOneItem(final String item) {
        Pattern pattern = Pattern.compile("^\\d+-\\d+$");
        if (pattern.matcher(item).find()) {
            final String[] range = item.split("-");
            return IntStream.rangeClosed(Integer.parseInt(range[0]), Integer.parseInt(range[1]))
                    .boxed()
                    .collect(Collectors.toList());
        }
        return Collections.singletonList(Integer.parseInt(item));
    }

    List<Integer> parseAll(final String str) {
        return Arrays.stream(str.split(","))
                .map(this::parseOneItem)
                .flatMap(List::stream)
                .distinct()
                .collect(Collectors.toList());
    }
}
