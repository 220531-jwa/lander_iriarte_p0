package com.revature;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;

public class BankingAppTest {

	// Inside this class is where we will write our Unit Tests
	// for our reverse() method in the ReverseAString class.
	
	/*
	 * JUnit uses these annotations: 
	 * 
	 * @Test
	 * @BeforeAll // JUnit 4 uses @BeforeClass
	 * @BeforeEach // JUnit 4 uses @Before
	 * @After
	 * @AfterClass
	 * 
	 * @Ignore
	 */
	
	@BeforeAll
	public static void setUpBeforeAll() {
		System.out.println("BeforeAll - this will run once before ALL Tests");
	}
	
	@BeforeEach
	public void beforeEach() {
		System.out.println("BeforeEach - will run once before each and every test");
	}
	
	@AfterAll
	public static void teardownAfterAll() {
		System.out.println("AfterAll - this will run once after ALL Tests");
	}
	
	@AfterEach
	public void afterEach() {
		System.out.println("AfterEach - will run once after each and every test");
	}
	
	// we want to make sure that our method can respond to various types of input
	
	@Test
	public void emptyStringInputTest() {
		System.out.println("Empty String Test");
		String inputString = "";
		
		String actual = ReverseAString.reverse(inputString);
		
		// use an assert method to check that the expected output
		// matches the actual output
		assertEquals("", actual);
	}
	
	@Test
	public void shouldReturnSingleCharacter() {
		System.out.println("Single Character Test");
		String inputString = "a";
		String actual = ReverseAString.reverse(inputString);
		assertEquals("a", actual);
	}
	
	@Test
	public void shouldReturnReverseStringWhenInputOk() {
		System.out.println("Input Ok");
		assertEquals("olleH", ReverseAString.reverse("Hello"));
	}
	
	@Test
	public void shouldReturnReverseStringWhenInputHasSpaces() {
		System.out.println("Input with Spaces");
		assertEquals("dlroW olleH", ReverseAString.reverse("Hello World"));
	}
	
	@Test
	public void inputSpecialCharactersTest() {
		System.out.println("Special Characters");
		assertEquals("@#)sdajk$*", ReverseAString.reverse("*$kjads)#@"));
	}
	
	// Sometimes we want to make sure that a specific input throws a specific exception
	// we want to be sure that our unit of code that we're testing fails in the right way
	@Test
	public void shouldThrowNullPointerExceptionIfInputNull() {
		System.out.println("assert Throws");
		Executable executable = () -> {ReverseAString.reverse(null);};
		
		assertThrows(NullPointerException.class, executable);
	}
	
}