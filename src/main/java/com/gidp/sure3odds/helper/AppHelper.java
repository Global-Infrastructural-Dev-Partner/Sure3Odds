package com.gidp.sure3odds.helper;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AppHelper {
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$";
	private Pattern pattern;
	private Matcher matcher;

	/**
	 * @param email
	 * @return
	 */
	public boolean isValidEmail(String email) {
		pattern = Pattern.compile(EMAIL_PATTERN);
		matcher = pattern.matcher(email);
		return matcher.matches();
	}

	/**
	 * @param number
	 * @param countryCode
	 * @return
	 * @throws NumberParseException
	 */
	public boolean isValidPhone(String number, String countryCode) throws NumberParseException {
		PhoneNumberUtil numberUtil = PhoneNumberUtil.getInstance();
		PhoneNumber phoneNumber = numberUtil.parse(number, countryCode);
		boolean result = numberUtil.isValidNumber(phoneNumber);
		return result;
	}

	/**
	 * @param dateToConvert
	 * Convert localDate to Date
	 * @return
	 */
	public Date convertToDateViaInstant(LocalDate dateToConvert) {
		return java.util.Date.from(dateToConvert.atStartOfDay()
				.atZone(ZoneId.systemDefault())
				.toInstant());
	}

	/**
	 * @param dateToConvert
	 * Convert Date to LocalDate
	 * @return
	 */
	public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
		return dateToConvert.toInstant()
				.atZone(ZoneId.systemDefault())
				.toLocalDate();
	}
}
