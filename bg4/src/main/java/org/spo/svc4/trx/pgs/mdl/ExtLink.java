package org.spo.svc4.trx.pgs.mdl;



import java.util.Locale;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class ExtLink {

	private String href;
	private String metaInfo;
	private String label;
	private boolean showIc;
	private String description;

	private DateTime date1;
	private String date;



	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDateString() {
		String format1 = "yyyy-MMM-dd";
		String formatted1 = formatJodaDateTime(date1, format1);
		return formatted1;
	}
	public void setDateString(String date) {
		String pattern1 = "yyyy-MM-dd";
		DateTime jodaDateTime1 = parseStringToJodaDateTime(date, pattern1);
		this.date1 = jodaDateTime1;
	}
	public void setDate(DateTime date) {
		this.date1 = date;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getMetaInfo() {
		return metaInfo;
	}
	public void setMetaInfo(String metaInfo) {
		this.metaInfo = metaInfo;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public boolean isShowIc() {
		return showIc;
	}
	public void setShowIc(boolean showIc) {
		this.showIc = showIc;
	}



	public static String formatJodaDateTime(DateTime dateTime, String pattern) {
		DateTimeFormatter dtf = DateTimeFormat.forPattern(pattern);
		return dtf.print(dateTime);
	}
	public static DateTime parseStringToJodaDateTime(String dateTimeString, String pattern) {
		DateTimeFormatter dtf = DateTimeFormat
				.forPattern(pattern)
				.withLocale(Locale.getDefault()) // Use default locale for parsing
				.withZone(DateTimeZone.forID("Asia/Kolkata")); // Specify the time zone if known

		return dtf.parseDateTime(dateTimeString);
	}

}
