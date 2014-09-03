package be.groept.tests;

import java.util.ArrayList;
import java.util.Collection;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test
public class Exercise4 {

	private Collection<Person> data;

	@BeforeMethod
	public void beforeMethod() {
		data = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			Person p = new Person(i);
			data.add(p);
		}
	}

	public void testFilterPersons() {
		// Filter the person list, removing all persons below 50 Use Lambda expressions and the Stream API, see
		// http://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html
		// to help you. If you successfully filter the persons, the test will succeed.
		// You must only use one (1) line of code todo this using the Stream API!

		// add your single line of code here to filter "data", if the line is correct the test below will succeed

		Assert.assertEquals(data.size(), 50);
		Assert.assertEquals(data.iterator().next().getAge(), 50);
	}

	public void testAverageAgeOnFilteredPersonsUsingJavaStreams() {
		// This one is a bit harder. Use the same line of code you used to solve the previous test, but this time
		// it will not modify the collection, but it will instead averages the ages of persons which are 50 or older.
		// So, the result will be a double which represents the average age of the persons from or older than 50.
		// Again, the wanted solution using the Stream API is exactly one line of code (not formatted of course)!

		double averageAge = 0; // add you code here, remove the = 0 first of course, if the code is correct the test
		// will succeed
		Assert.assertEquals(averageAge, 74.5);
	}

	class Person {
		private int age;

		public Person(int age) {
			this.age = age;
		}

		public int getAge() {
			return age;
		}
	}
}
