package com.sai.four;

class Name {
    private String firstName;
    private String lastName;

    public Name(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String fullname() {
        return lastName + "," + firstName;
    }

    public boolean match(Name other) {
    	
    	boolean firstNameMatched = this.firstName.equalsIgnoreCase(other.firstName) ;
    	boolean lastNameMatched = this.lastName.equalsIgnoreCase(other.lastName) ;

        return this.firstName.equalsIgnoreCase(other.firstName) &&
               this.lastName.equalsIgnoreCase(other.lastName);
    }

    public boolean isLessThan(Name other) {
        int lastCompare = this.lastName.compareToIgnoreCase(other.lastName);
        if (lastCompare != 0) {
            return lastCompare < 0;
        }
        return this.firstName.compareToIgnoreCase(other.firstName) < 0;
    }

    @Override
    public String toString() {
        return fullname();
    }

    // Unit Tests
    public static void runTests() {
        System.out.println("Running Name Tests...");
        int testCount = 0, failCount = 0;

        Name name1 = new Name("John", "Doe");
        Name name2 = new Name("Jane", "Doe");
        Name name3 = new Name("John", "Doe");

        if (!name1.match(name3)) {
            System.out.println("FAIL: match() failed for identical names.");
            failCount++;
        }
        testCount++;

        if (name1.match(name2)) {
            System.out.println("FAIL: match() failed for different names.");
            failCount++;
        }
        testCount++;

        if (!name2.isLessThan(name1)) {
            System.out.println("FAIL: isLessThan() failed.");
            failCount++;
        }
        testCount++;

        System.out.printf("Name Tests: %d run, %d failed\n", testCount, failCount);
    }
}