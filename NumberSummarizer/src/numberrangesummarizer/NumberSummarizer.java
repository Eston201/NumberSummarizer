package numberrangesummarizer;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;

public class NumberSummarizer implements NumberRangeSummarizer {

	@Override
	public Collection<Integer> collect(String input) {
		String[] numbers = input.split(",");
		Collection<Integer> collection = new HashSet<>();
		
		for (String str : numbers) {
			try {
				collection.add(Integer.parseInt(str));
			} catch (NumberFormatException e) {
				throw new NumberFormatException("Input string contains non-digits or is empty");
			}
		}
		
		return collection;
	}

	@Override
	public String summarizeCollection(Collection<Integer> input) {
		input = new TreeSet<>(input);
		StringBuilder summary = new StringBuilder("");
		int curr,prev;
		boolean isSeries = false;
		Iterator<Integer> iterator = input.iterator();
		
		if (iterator.hasNext()) {
			prev = iterator.next();
		}
		else {
			return summary.toString();
		}
		summary.append(prev);
		
		while (iterator.hasNext()) {
			curr = iterator.next();
			
			if (curr == (prev + 1)) {
				isSeries = true;
			}
			else {
				if (isSeries) {
					summary.append("-").append(prev);
					isSeries = false;
				}
				summary.append(", ").append(curr);
			}
			prev = curr;
		}
		if (isSeries) {
			summary.append("-").append(prev);
		}
		return summary.toString();
	}
}
