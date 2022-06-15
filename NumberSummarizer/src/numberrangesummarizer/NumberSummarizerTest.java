package numberrangesummarizer;

/*****************************************************************************************************************
 * Assumptions :
 * 
 * + The input String for the collect() method contains Numbers in random order and can have duplicate numbers 
 * 	 as well as negatives
 * 	 If the input string is empty or contains a word then an exception is thrown.
 * 	 Any Collection return type can be used
 * 	 
 * + The summarizeCollection method needs to return a string of numbers with ranges(if applicable) in sorted
 * 	 ascending order.
 * 	 If the input collection to the summarizeCollection method is empty, an empty string is returned
 * **************************************************************************************************************/

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Collection;
import java.util.HashSet;
import java.util.TreeSet;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class NumberSummarizerTest {
	private NumberSummarizer summarizer;
	private Collection<Integer> expectedCollection;
	private String input;
	
	@BeforeAll
	public void setup() {
		this.summarizer = new NumberSummarizer();
		this.expectedCollection = new HashSet<>();
	}
	
	public void populateExpectedCollection(String input) {
		String[] numbers = input.split(",");
		for (String str : numbers) {
			this.expectedCollection.add(Integer.parseInt(str));
		}
	}
	
	public void clear() {
		this.expectedCollection.clear();
	}
	
	@Test
	public void collect_emptyInput() {
		input = "";
		NumberFormatException exception =  assertThrows(NumberFormatException.class, ()-> {
			summarizer.collect(input);
		});
		assertEquals("Input string contains non-digits or is empty", exception.getMessage());
	}
	
	@Test
	public void collect_inputWithWords() {
		input = "1,3,Hello,7,8,12,World,14,15,21,22,23,24,31";
		NumberFormatException exception =  assertThrows(NumberFormatException.class, ()-> {
			summarizer.collect(input);
		});
		assertEquals("Input string contains non-digits or is empty", exception.getMessage());
	}
	
	@Test
	public void collect_singleDigit() {
		input = "1";
		Collection<Integer> output = summarizer.collect(input);
		populateExpectedCollection(input);
		assertEquals(expectedCollection, output);
		clear();
	}
	
	@Test
	public void collect_allSame() {
		input = "1,1,1,1,1,1,1,1,1,1,1,1,1";
		Collection<Integer> output = summarizer.collect(input);
		populateExpectedCollection("1");
		assertEquals(expectedCollection, output);
		clear();
	}
	
	@Test
	public void collect_randomWithDuplicates() {
		input = "1,1,2,3,8,9,3,20,23,21,22,50,11,12,1,13,4,23";
		Collection<Integer> output = summarizer.collect(input);
		populateExpectedCollection("1,2,3,4,8,9,20,21,23,22,50,11,12,13");
		assertEquals(expectedCollection, output);
		clear();
	}
	
	@Test
	public void collect_test1() {
		input = "1,3,6,7,8,12,13,14,15,21,22,23,24,31";
		Collection<Integer> output = summarizer.collect(input);
		populateExpectedCollection(input);
		assertEquals(expectedCollection, output);
		clear();
	}
	
	@Test
	public void collect_test2() {
		input = "100,101,102,103,50,49,30,12,10,1,3,5,11,48,100,103";
		Collection<Integer> output = summarizer.collect(input);
		populateExpectedCollection("100,101,102,103,50,49,30,12,10,1,3,5,11,48");
		assertEquals(expectedCollection, output);
		clear();
	}
	
	@Test
	public void summarizeCollection_emptyInput() {
		String output = summarizer.summarizeCollection(new TreeSet<>());
		assertEquals("", output);
	}
	
	@Test
	public void summarizeCollection_singleDigit() {
		input = "1";
		Collection<Integer> output = summarizer.collect(input);
		assertEquals("1", summarizer.summarizeCollection(output));
	}
	
	@Test
	public void summarizeCollection_twoDigits() {
		input = "1,50";
		Collection<Integer> output = summarizer.collect(input);
		assertEquals("1, 50", summarizer.summarizeCollection(output));
	}
	
	@Test
	public void summarizeCollection_twoDigitSeries() {
		input = "1,2";
		Collection<Integer> output = summarizer.collect(input);
		assertEquals("1-2", summarizer.summarizeCollection(output));
	}
	
	@Test
	public void summarizeCollection_allSame() {
		input = "1,1,1,1,1,1,1,1,1,1,1,1,1";
		Collection<Integer> output = summarizer.collect(input);
		assertEquals("1", summarizer.summarizeCollection(output));
	}
	
	@Test
	public void summarizeCollection_withDuplicates() {
		input = "1,1,2,3,2,5,10,1,12,11,13,20,25,33,34,35";
		Collection<Integer> output = summarizer.collect(input);
		assertEquals("1-3, 5, 10-13, 20, 25, 33-35", summarizer.summarizeCollection(output));
	}
	
	@Test
	public void summarizeCollection_series() {
		input = "1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17";
		Collection<Integer> output = summarizer.collect(input);
		assertEquals("1-17", summarizer.summarizeCollection(output));
	}
	
	@Test
	public void summarizeCollection_testNegatives() {
		input = "-50,-10,-5,-15,-4,-9,-6,-1,-51,-49,-3,-11,-14,-13";
		Collection<Integer> output = summarizer.collect(input);
		assertEquals("-51--49, -15--13, -11--9, -6--3, -1", summarizer.summarizeCollection(output));
	}
	
	@Test
	public void summarizeCollection_testPositveAndNegatives() {
		input = "1,-1,1,2,-5,-4,3,5,6,7,10,11,-10,-12,21,20,25,22,-3,-2";
		Collection<Integer> output = summarizer.collect(input);
		assertEquals("-12, -10, -5--1, 1-3, 5-7, 10-11, 20-22, 25", summarizer.summarizeCollection(output));
	}
	
	@Test
	public void summarizeCollection_test1() {
		input = "1,3,6,7,8,12,13,14,15,21,22,23,24,31";
		Collection<Integer> output = summarizer.collect(input);
		assertEquals("1, 3, 6-8, 12-15, 21-24, 31", summarizer.summarizeCollection(output));
	}
	
	@Test
	public void summarizeCollection_test2() {
		input = "100,101,105,102,5,1,4,6,10,19,21,11,15,20,2,3";
		Collection<Integer> output = summarizer.collect(input);
		assertEquals("1-6, 10-11, 15, 19-21, 100-102, 105", summarizer.summarizeCollection(output));
	}
}
