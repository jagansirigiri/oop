package com.sai.four;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class PatientList {

	private Patient[] patientArray = null;
	private final int MAX_PATIENTS = 7;
	private int indexOfIteration = -1;

	private static String filename = "test.csv";

	public PatientList() {
		patientArray = new Patient[MAX_PATIENTS];
	}

	public Patient[] getPatientArray() {
		return patientArray;
	}

	public boolean add(Patient pat) {
		return add_ordered(pat);
	}

	public Patient find(PatientIdentity id) {
		return binary_search(id);
	}

	private boolean add_ordered(Patient pat) {
		int index_of_last_patient = get_index_of_last_patient();
		// System.out.println("#index_of_last_patient = " + index_of_last_patient);

		// patientArray[index_of_last_patient+1] = pat;
		// System.out.println("######### added = " + pat);

		if (index_of_last_patient == -1) {
			patientArray[0] = pat;
			// System.out.println(displayArray());
			return true;
		}

		if (index_of_last_patient == MAX_PATIENTS) {
			return false;
		}

		if (pat == null) {
			return false;
		}

		try {
			while (index_of_last_patient >= 0) {

				if (!patientArray[index_of_last_patient].getIdentity().isLessThan(pat.getIdentity())) {
					patientArray[index_of_last_patient + 1] = patientArray[index_of_last_patient];
					patientArray[index_of_last_patient] = pat;
					index_of_last_patient--;

				} else if (patientArray[index_of_last_patient].getIdentity().isLessThan(pat.getIdentity())) {
					patientArray[index_of_last_patient + 1] = pat;
					break;
				}
			}

			// System.out.println(displayArray());
			return true;
		}

		catch (Exception e) {
			System.out.println("error in adding patient:" + e.getMessage());
			return false;
		}

	}

	private Patient binary_search(PatientIdentity id) {
		int upper = get_index_of_last_patient();
		int lower = 0;

		while (upper >= lower) {
			int mid = (upper + lower) / 2;
			if (patientArray[mid].getIdentity().match(id)) {
				return patientArray[mid];
			}
			if (patientArray[mid].getIdentity().isLessThan(id)) {
				lower = mid + 1;
			} else {
				upper = mid - 1;
			}

		}

		return null;
	}

	public String displayArray() {
		StringBuffer sb = new StringBuffer("[");
		for (int i = 0; i < patientArray.length; i++) {
			if (patientArray[i] != null) {
				sb.append(patientArray[i].getIdentity().getName().fullname() + "|");
			} else {
				sb.append(patientArray[i] + " | ");
			}
		}
		sb.append("]");
		return sb.toString();
	}

	public int get_index_of_last_patient() {
		for (int i = patientArray.length - 1; i >= 0; i--) {
			if (patientArray[i] != null) {
				return i; // Return the index if not null
			}
		}
		return -1; // Return -1 if all elements are null
	}

	// Unit Tests
	public static void runTests() {
		System.out.println("Running PatientList Tests...");
		int testCount = 0, failCount = 0;

		if (!testSaveToFile()) {
			System.out.println("FAIL: testSaveToFile() failed.");
			failCount++;
		}
		testCount++;

		
		 if (!testImportCSVFile()) {
		   System.out.println("FAIL: testImportCSVFile() failed."); 
		   failCount++; 
		   
		  }
		  testCount++;
		 
		// System.out.println(test.displayArray());

		System.out.printf("PatientList Tests: %d run, %d failed\n", testCount, failCount);
	}

	private static Date getDateOfBirth(String dateString) {
		Date dob = null;
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
		try {

			dob = sdf.parse(dateString);
			// System.out.println(dob);
		} catch (Exception e) {
			System.out.println("error in parsing dateOfBirth");
		}
		return dob;
	}

	private static String getFormattedDateOfBirthString(Date date) {
		String strDob = null;
		try {

			SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
			strDob = dateFormat.format(date);
			// System.out.println(dob);
		} catch (Exception e) {
			System.out.println("error in parsing date");
		}
		return strDob;
	}

	public void initIteration() {
		if (patientArray == null) {
			indexOfIteration = -1;
		} else {
			indexOfIteration = 0;
		}
		//System.out.println("Iteration initialized");
	}

	public Patient next() {
		Patient pat = null;
		if ((indexOfIteration == -1) || (indexOfIteration == MAX_PATIENTS)) {
			return null;
		}

		else {
			if (patientArray[indexOfIteration] != null) {
				pat = patientArray[indexOfIteration];
				indexOfIteration++;

			}
		}
		return pat;
	}

	Patient linearSearch(PatientIdentity id) {
		Patient pat = null;
		initIteration();
		while ((pat = next()) != null) {
			if (pat.getIdentity().match(id)) {
				return pat;
			}
		}
		return null;
	}

	public boolean saveToFile(String filename) {
		boolean result = true;
		File file = new File(filename);
		FileWriter writer = null;
		try {
			writer = new FileWriter(file);
			this.initIteration(); // initialize iteration
			Patient pat = null;
			while ((pat = next()) != null) {
				//System.out.println(pat.toString());
				String formattedDob = getFormattedDateOfBirthString(pat.getIdentity().getDateOfBirth());
				writer.write(pat.getIdentity().getName() + "," + formattedDob + "\n");
			}

			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
			result = false;
		}
		return result;

	}

	public boolean importCSVFile(String filename) {
		boolean result = true;
		File file = new File(filename);
		Scanner scanner = null;
		Patient pat = null;
		try {
			scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				pat = makePatient(scanner.nextLine());
				add(pat);
			}
		} catch (IOException e) {
			e.printStackTrace();
			result = false;
		}
		//System.out.println("#########################");
		displayArray();
		return result;
	}

	static Patient makePatient(String line) {
		//System.out.println("############ Line:" + line);
		Patient pat = null;
		String lastName = null;
		String firstName = null;
		String strDob = null;
		try {

			String[] tokens = line.split(",");
			if ((tokens[0] != null) && (tokens[1] != null) && (tokens[2] != null)) {
				lastName = tokens[0];
				firstName = tokens[1];
				strDob = tokens[2];

				Name name = new Name(firstName,lastName);
				Date dob = getDateOfBirth(strDob);
				PatientIdentity id = new PatientIdentity(name, dob);
				pat = new Patient(id);
			}
		} catch (Exception e) {
			System.out.println("Error in parsing null records in csv:"+ lastName + "," + firstName + "," + strDob);
			// e.printStackTrace();
		}
		return pat;
	}

	public static boolean testSaveToFile() {
		System.out.println("##### testSaveToFile");
		Patient expectedPatient = createPatientForTesting("Sai", "Sirigiri", "02-11-2014");

		PatientList patientList = new PatientList();
		patientList.add(expectedPatient);
		patientList.saveToFile(filename);
		
		patientList.importCSVFile("test.csv");
		Patient[] resultPatArray = patientList.getPatientArray();
		Patient resultPatient = resultPatArray[0];
		System.out.println("resultantPatient:" + resultPatient);
		System.out.println("expectedPatient :" + expectedPatient);
		
		  if (resultPatient.getIdentity().match(expectedPatient.getIdentity())) {
			  return true;
		  }
		  else {
			  return false;
		  }
		
	}

	public static boolean testImportCSVFile() {
		
		PatientList patList = new PatientList();
		patList.importCSVFile("test-import.csv");

		boolean matchedNoOfRedcords = false;
		int noOfPatientsInArray = patList.get_index_of_last_patient() + 1;
		//System.out.println("####### patArrLength:" + patArrLength + "," + "indexOfLastPat:" + noOfPatientsInArray);
		matchedNoOfRedcords = (noOfPatientsInArray == 4);

		return matchedNoOfRedcords;
	}

	private static Patient createPatientForTesting(String firstName, String lastName, String strDob) {
		Name name = new Name(firstName, lastName);
		Date dob = getDateOfBirth(strDob);
		PatientIdentity id = new PatientIdentity(name, dob);
		Patient patient = new Patient(id);
		return patient;
	}

}
