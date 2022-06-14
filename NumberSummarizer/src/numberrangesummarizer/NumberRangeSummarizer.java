package numberrangesummarizer;

import java.util.Collection;

public interface NumberRangeSummarizer {
    Collection<Integer> collect(String input);
    String summarizeCollection(Collection<Integer> input);
}
