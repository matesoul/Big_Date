package com.lsy.hive;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.hadoop.hive.ql.exec.UDF;

public class UDFZodiacSign extends UDF {
	private SimpleDateFormat df;

	public UDFZodiacSign() {
		df = new SimpleDateFormat("MM-dd-yyyy");
	}

	@SuppressWarnings("deprecation")
	public String evaluate(Date bday) {
		return this.evaluate(bday.getMonth() + 1, bday.getDate());
	}

	@SuppressWarnings("deprecation")
	public String evaluate(String bday) {
		Date date;
		try {
			date = df.parse(bday);
		} catch (ParseException e) {
			return null;
		}
		return this.evaluate(date.getMonth() + 1, date.getDate());
	}

	private String evaluate(Integer month, Integer day) {
		if (month == 1) {
			if (day < 20) {
				return "Capricorn";
			} else {
				return "Aquarius";
			}
		}
		/*other month there	*/
		
		return null;
	}
}
