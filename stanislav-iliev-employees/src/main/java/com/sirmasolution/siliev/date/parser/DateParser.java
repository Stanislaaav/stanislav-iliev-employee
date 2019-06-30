package com.sirmasolution.siliev.date.parser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Stream;
import org.apache.commons.lang.time.DateUtils;

/**
 * 
 * Class can parse :
 *("20181118"),
 *("2018-11-18")
 *("18/11/18")
 *("18/11/2018")
 *("181118120856-0700")
 *("2018-11-18T12:08:56.235-0700")
 *("2018-11-18T12:08:56.235-07:00")
 *("2018-W27-3")
 * 
 * 
 * @author Stan
 *
 */

public class DateParser {

	private static Date parseDate(String inputDate) {

		Date outputDate = null;
		String[] possibleDateFormats =
            {
                  "yyyy.MM.dd G 'at' HH:mm:ss z",
                  "EEE, MMM d, ''yy",
                  "h:mm a",
                  "hh 'o''clock' a, zzzz",
                  "K:mm a, z",
                  "yyyyy.MMMMM.dd GGG hh:mm aaa",
                  "EEE, d MMM yyyy HH:mm:ss Z",
                  "yyMMddHHmmssZ",
                  "yyyy-MM-dd'T'HH:mm:ss.SSSZ",
                  "yyyy-MM-dd'T'HH:mm:ss.SSSXXX",
                  "YYYY-'W'ww-u",
                  "EEE, dd MMM yyyy HH:mm:ss z", 
                  "EEE, dd MMM yyyy HH:mm zzzz",
                  "yyyy-MM-dd'T'HH:mm:ssZ",
                  "yyyy-MM-dd'T'HH:mm:ss.SSSzzzz", 
                  "yyyy-MM-dd'T'HH:mm:sszzzz",
                  "yyyy-MM-dd'T'HH:mm:ss z",
                  "yyyy-MM-dd'T'HH:mm:ssz", 
                  "yyyy-MM-dd'T'HH:mm:ss",
                  "yyyy-MM-dd'T'HHmmss.SSSz",
                  "yyyy-MM-dd",
                  "yyyyMMdd",
                  "dd/MM/yy",
                  "dd/MM/yyyy"
            };

		try {
			outputDate = DateUtils.parseDate(inputDate.trim(), possibleDateFormats);
			// System.out.println("inputDate ==> " + inputDate + ", outputDate ==> " +
			// outputDate);

		} catch (ParseException pe) {
			pe.printStackTrace();
		}

		return outputDate;

	}

	private static String formatDate(Date date, String requiredDateFormat) {

		SimpleDateFormat df = new SimpleDateFormat(requiredDateFormat);
		String outputDateFormatted = df.format(date);
		return outputDateFormatted;
	}

	public static void parseDateIntoNewFile(File file)  {

		File outFile = new File("outFile.txt");
		if (outFile.exists()) {
			outFile.delete();
		}

		try (Stream<String> stream = Files.lines(Paths.get(file.getPath()))) {
			stream.forEach(e -> {
				String[] split = e.split(",");
				String employeeId = split[0];
				String projectId = split[1];
				String dateFrom = split[2];
				String dateTo = split[3].trim();

				String parsedDateFrom;
				String parsedDateTo;
				
				if (dateTo.equals("NULL")) {
					parsedDateFrom = formatDate(parseDate(dateFrom), "yyyy-MM-dd");
					parsedDateTo = formatDate(new Date(), "yyyy-MM-dd");
				} else {
					parsedDateFrom = formatDate(parseDate(dateFrom), "yyyy-MM-dd");
					parsedDateTo = formatDate(parseDate(dateTo), "yyyy-MM-dd");
				}
				
				try {
					PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(outFile, true)));
					out.println(employeeId + "," + projectId + ", " + parsedDateFrom + ", " + parsedDateTo);
					out.close();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			});
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}




