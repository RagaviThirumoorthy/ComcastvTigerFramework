package com.vTiger.generic.webdriverUtility;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {

	public int getRandomNumber() {

		Random random = new Random();
		int randomNumber = random.nextInt(5000);
		return randomNumber;
	}

	public StringBuilder getRandomString() {
		
		int n = 20;
		// choose a character random from this string
		String alphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";
		// Create a Stringbuilder size of AlphaNumeric String
		StringBuilder sb = new StringBuilder(n);
		
		for (int i = 0; i < n; i++) {
		// generate a random number between 0 to alphaNumericString variable length
//			int index =(int) (alphaNumericString.length()* Math.random());				//Code efficiency
			Random random = new Random();
			int index = random.nextInt(alphaNumericString.length());
			// add Character one by one in end of sb
			sb.append(alphaNumericString.charAt(index));
		}

		return sb;
	}

	public String getSystemDate() {

		Date dateObj = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(dateObj);
		return date;
	}

	public String getRequiredDate(int days) {

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, days);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(cal.getTime());
		return date;
	}

	public String getCurrentDateTime() {

		String time = LocalDateTime.now().toString().replace(':', '-');
		System.out.println(time);
		return time;
	}
}
