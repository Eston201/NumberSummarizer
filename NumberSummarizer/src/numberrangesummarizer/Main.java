package numberrangesummarizer;

import java.util.Collection;

public class Main {
	public static void main(String[] args) {
		//For manual testing
		NumberSummarizer summarizer = new NumberSummarizer();
		Collection<Integer> ints = summarizer.collect("1,3,6,7,8,12,13,14,15,21,22,23,24,31");
		System.out.println(summarizer.summarizeCollection(ints));
	}
}
