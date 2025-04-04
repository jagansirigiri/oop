package com.sai.four;
import java.util.Date;

class PatientIdentity {
    private Name name;
    private Date dateOfBirth;

    public PatientIdentity(Name name, Date dob) {
        this.name = name;
        this.dateOfBirth = dob;
    }

    public Name getName() {
        return name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public boolean match(PatientIdentity other) {
        return this.name.match(other.name) && this.dateOfBirth.equals(other.dateOfBirth);
    }

    public boolean isLessThan(PatientIdentity other) {
        if (this.name.isLessThan(other.name)) {
            return true;
        }
        if (this.name.match(other.name)) {
            return this.dateOfBirth.compareTo(other.dateOfBirth) < 0;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Name: " + name.toString() + ", DOB: " + dateOfBirth;
    }

    // Unit Tests
    public static void runTests() {
        System.out.println("Running PatientIdentity Tests...");
        int testCount = 0, failCount = 0;

        Name name1 = new Name("John", "Doe");
        Name name2 = new Name("Jane", "Doe");
        Date dob1 = new Date(2000 - 1900, 1, 15);
        Date dob2 = new Date(1995 - 1900, 5, 20);

        PatientIdentity id1 = new PatientIdentity(name1, dob1);
        PatientIdentity id2 = new PatientIdentity(name2, dob2);
        PatientIdentity id3 = new PatientIdentity(name1, dob1);

        if (!id1.match(id3)) {
            System.out.println("FAIL: match() failed for identical identities.");
            failCount++;
        }
        testCount++;

        if (id1.match(id2)) {
            System.out.println("FAIL: match() failed for different identities.");
            failCount++;
        }
        testCount++;

        if (!id2.isLessThan(id1)) {
            System.out.println("FAIL: isLessThan() failed.");
            failCount++;
        }
        testCount++;

        System.out.printf("PatientIdentity Tests: %d run, %d failed\n", testCount, failCount);
    }
}