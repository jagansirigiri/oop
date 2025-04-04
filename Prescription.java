package com.sai.four;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Prescription {
	private String name;
	private Date dateOfIssue;
	private int dosage;
	private String prescriber;

	Prescription(String name,String strDateOfIssue,int dosage,String prescriber){
		this.name = name;
		this.dateOfIssue = getDateOfIssue(strDateOfIssue);
		this.dosage = dosage;
		this.prescriber = prescriber;
		
	}
	public String getName() {
		return name;
	}

	public Date getDateOfIssue() {
		return dateOfIssue;
	}

	public String getPrescriber() {
		return prescriber;
	}
	
	private static Date getDateOfIssue(String dateString) {
		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
		try {

			date = sdf.parse(dateString);
			// System.out.println(date);
		} catch (Exception e) {
			System.out.println("error in parsing");
		}
		return date;
	}
	
	private static String getDateStr(Date date) {
		String  dateStr = null;
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
		try {

			dateStr = sdf.format(date);
			// System.out.println(dateStr);
		} catch (Exception e) {
			System.out.println("error in formatting date");
		}
		return dateStr;
	}
	
	
	public String toString() {
		return this.name +"," +getDateStr(this.dateOfIssue) +","+ this.dosage +","+ this.prescriber;
	}

}
