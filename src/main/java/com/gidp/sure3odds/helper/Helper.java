package com.gidp.sure3odds.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

public class Helper {
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$";
	private Pattern pattern;
	private Matcher matcher;
	
	public boolean isValidEmail(String email) {
		pattern = Pattern.compile(EMAIL_PATTERN);
		matcher = pattern.matcher(email);
		return matcher.matches();
	}

	public boolean isValidPhone(String number, String countryCode) throws NumberParseException {
		PhoneNumberUtil numberUtil = PhoneNumberUtil.getInstance();
		PhoneNumber phoneNumber = numberUtil.parse(number, countryCode);
		boolean result = numberUtil.isValidNumber(phoneNumber);
		return result;
	}
}
