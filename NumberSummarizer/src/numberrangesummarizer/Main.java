package numberrangesummarizer;

import java.util.Collection;

public class Main {
	public static void main(String[] args) {
		//For manual testing
		NumberSummarizer summarizer = new NumberSummarizer();
		Collection<Integer> ints = summarizer.collect("1,2,2,1,1,6,5,7,8,9,10,20,21,22,30,35");
		System.out.println(summarizer.summarizeCollection(ints));
		
		ints = summarizer.collect("1,3,6,7,8,12,13,14,15,21,22,23,24,31");
		System.out.println(summarizer.summarizeCollection(ints));
		
		ints = summarizer.collect("1,2,3,4,5,6,7,8,9,10,11,12,13,14,15");
		System.out.println(summarizer.summarizeCollection(ints));
	}
}
