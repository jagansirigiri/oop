package com.sai.four;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

class Patient {
    private PatientIdentity identity;
    private PrescriptionList prescriptionList;
    
    public Patient(PatientIdentity id) {
        this.identity = id;
    }

    public PatientIdentity getIdentity() {
        return identity;
    }
    
    

    public PrescriptionList getPrescriptionList() {
		return prescriptionList;
	}

	public void setPrescriptionList(PrescriptionList prescriptionList) {
		this.prescriptionList = prescriptionList;
	}

	@Override
    public String toString() {
        return "Patient Identity: " + identity.toString();
    }
    
    
    // Unit Tests
    public static void runTests() {
        System.out.println("Running Patient Tests...");
        int testCount = 0, failCount = 0;

        Name name = new Name("John", "Doe");
        Date dob = new Date(2000 - 1900, 1, 15);
        String inputString = "11-11-1980";
       
        
        PatientIdentity id = new PatientIdentity(name, dob);
        Patient patient = new Patient(id);

        if (!patient.getIdentity().match(id)) {
            System.out.println("FAIL: Patient Identity match() failed.");
            failCount++;
        }
        testCount++;

        if (!patient.toString().contains("John")) {
            System.out.println("FAIL: toString() failed.");
            failCount++;
        }
        testCount++;

        System.out.printf("Patient Tests: %d run, %d failed\n", testCount, failCount);
    }
}