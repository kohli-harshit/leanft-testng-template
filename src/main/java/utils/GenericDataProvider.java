package utils;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;

import com.opencsv.CSVReader;

import utils.CSVAnnotation.CSVFileParameters;

/**
 * <p>
 * Class to give Generic dataprovider <br/>
 * Returns 2D array for input to Test class<br/>
 * use the following parameters in your test class:
 * 
 * <pre>
 * testname(int rowNo, Map&lt;String, String&gt; inputDataMap)
 * </pre>
 * 
 * </p>
 */
public class GenericDataProvider {
	@DataProvider(name = "dataproviderForTestCase")
	public Object[][] sampleDataProvider(final Method testMethod) throws IOException {
		Object[][] finalObject = null; // this would be returned
		Map<Integer, Map<String, String>> outerMap = new HashMap<Integer, Map<String, String>>();
		Map<String, String> innerMap = new HashMap<String, String>();

		CSVFileParameters parameters = testMethod.getAnnotation(CSVFileParameters.class);
		if (parameters != null) {
			String path = parameters.path();
			String delimiter = parameters.delimiter();
			String[][] csvArray = loadDataFromCSVFile(path);
			if (csvArray.length > 0) {
				int length = csvArray[0][0].split(delimiter).length;
				for (int j = 0; j < csvArray.length; j++) {
					innerMap = new HashMap<String, String>();
					String[] key = csvArray[j][0].toString().split(delimiter);
					String[] value = csvArray[j][1].toString().split(delimiter);
					for (int k = 0; k < length; k++) {
						innerMap.put(key[k], value[k]);
					}
					outerMap.put(j, innerMap);
				}

				finalObject = new Object[outerMap.size()][outerMap.size()];
				for (int count = 0; count < outerMap.size(); count++) {
					finalObject[count] = new Object[] { count, outerMap.get(count) };
				}
			}

			return finalObject;
		}

		return null;
	}

	/**
	 * 
	 * @param path
	 *            path of the file to read
	 * @return 2d string array
	 */
	private static String[][] loadDataFromCSVFile(final String path) {
		CSVReader reader = null;
		String[][] returnArray = null;
		final char Separator = ((char) 007);
		try {
			reader = new CSVReader(new FileReader(path), Separator, '\'');
			List<String[]> csvList = reader.readAll();
			if (csvList.size() > 0) {
				returnArray = new String[csvList.size() - 1][csvList.size() - 1];
				int iterator = 0;
				for (int count = 1; count < csvList.size(); count++) {
					returnArray[iterator] = new String[] { csvList.get(0)[0], csvList.get(count)[0] };
					iterator++;
				}
				reader.close();
			}
		} catch (IOException ex) {
			System.out.println("IO Exception while reading CSV.." + ex.toString());
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		return returnArray;
	}
}