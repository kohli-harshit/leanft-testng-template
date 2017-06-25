package utils;

import java.lang.annotation.*;

/**
 * Class for storing Custom annotations related to CSV Manipulation
 */
public class CSVAnnotation {

	/**
	 * This is a Custom Annotation which provides the facility to provide a CSV File Path to the GenericDataProvider Class
	 * In order to use it, we need to add the following annotation to the test case declaration :-
	 * @CSVFileParameters(path = "test-data\\api\\cards\\csv\\CardsPostApiTestData.csv", delimiter = "###")
	 */
	@Target(ElementType.METHOD)
	@Retention(RetentionPolicy.RUNTIME)
	public @interface CSVFileParameters {
		String path();
		String delimiter();
	}
}