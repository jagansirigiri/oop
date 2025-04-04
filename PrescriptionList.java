package com.sai.four;

//Java Program to insert in a sorted linked list
class PrescriptionList {
	ListRecord head; // head of list

	class ListRecord {
		Prescription data;
		ListRecord next;

		ListRecord(Prescription p) {
			data = p;
			next = null;
		}
	}

	public void add(Prescription p) {
		ListRecord pBefore;
		ListRecord new_node = new ListRecord(p);
		/* Special case for head node */
		if (head == null) {
			// new_node.next = head;
			head = new_node;
			return;
		}
		if (new_node.data.getDateOfIssue().compareTo(head.data.getDateOfIssue()) >= 0) {
			new_node.next = head;
			head = new_node;
			return;
		}

		/* Locate the node before point of insertion. */
		pBefore = head;

		while (pBefore.next != null
				&& pBefore.next.data.getDateOfIssue().compareTo(new_node.data.getDateOfIssue()) >= 0) {

			pBefore = pBefore.next;
		}

		new_node.next = pBefore.next;
		pBefore.next = new_node;

	}

	/* Function to print linked list */
	void printList() {
		ListRecord temp = head;
		while (temp != null) {
			System.out.print(temp.data + "\n ");
			temp = temp.next;
		}
	}

	// Unit Tests
	public static void runTests() {
		System.out.println("Running PrescriptionList Tests...");
		int testCount = 0, failCount = 0;

		if (!testPrescriptionsDateOfIssueOrdering()) {
			System.out.println("FAIL: testPrescriptionsDateOfIssueOrdering() failed.");
			failCount++;
		}
		testCount++;

		// System.out.println(test.displayArray());
		System.out.println("####################################");
		System.out.printf("PrescriptionList Tests: %d run, %d failed\n", testCount, failCount);
	}

	private static boolean testPrescriptionsDateOfIssueOrdering() {
		PrescriptionList pList = new PrescriptionList();

		Prescription p1 = new Prescription("Amoxicillin", "01-01-2025", 200, "Dr John Doe");
		pList.add(p1);

		Prescription p2 = new Prescription("Levothyroxine", "03-02-2025", 150, "Dr Pete Dickerson");
		pList.add(p2);

		Prescription p3 = new Prescription("Gabapentin", "07-06-2024", 400, "Dr Amie Jackson");
		pList.add(p3);

		Prescription p4 = new Prescription("Benzonatate", "09-10-2021", 100, "Dr Mary Gibson");
		pList.add(p4);

		Prescription p5 = new Prescription("Cetirizine", "06-17-2017", 50, "Dr Parul Goel");
		pList.add(p5);

		System.out.println("Created Linked List with prescriptions in descending order of dateOfIssue\n");
	//	pList.printList();
		
		Prescription[] resultPrescriptions = new Prescription[5];
		
		
		//iterate through the prescription list and find and verify the resultant prescriptions order 
		//by dateOfIuuse descending order
		
		ListRecord temp = pList.head;
		//store head value at resultPrescriptions[0]
		resultPrescriptions[0] = temp.data;
		
		//store rest of the values beginning from index1
		int i = 1;
		while (temp != null ) {
			//System.out.print(temp.data + "\n ");
			temp = temp.next;
			if(temp == null ) break;
			resultPrescriptions[i] = temp.data;
			i++;
			
		}
		System.out.println("Printing resultPrescriptions array:\n");
		for(int j=0;j< resultPrescriptions.length;j++) {
			System.out.println(resultPrescriptions[j]);
		}
		
		if( resultPrescriptions[0].getDateOfIssue().equals(p2.getDateOfIssue()) &&
			resultPrescriptions[1].getDateOfIssue().equals(p1.getDateOfIssue()) &&
			resultPrescriptions[2].getDateOfIssue().equals(p3.getDateOfIssue()) &&
			resultPrescriptions[3].getDateOfIssue().equals(p4.getDateOfIssue()) 
			 ) {
			return true;
		}else {
			return false;
		}
		
	}

	/* Driver function to test above methods */
	public static void main(String args[]) {
		PrescriptionList pList = new PrescriptionList();

		Prescription p1 = new Prescription("Amoxicillin", "2025-01-01", 200, "Dr John Doe");
		pList.add(p1);

		Prescription p2 = new Prescription("Levothyroxine", "2025-03-02", 150, "Dr Pete Dickerson");
		pList.add(p2);

		Prescription p3 = new Prescription("Gabapentin", "2024-07-06", 400, "Dr Amie Jackson");
		pList.add(p3);

		Prescription p4 = new Prescription("Benzonatate", "2021-09-10", 100, "Dr Mary Gibson");
		pList.add(p4);

		Prescription p5 = new Prescription("Cetirizine", "2017-06-17", 50, "Dr Parul Goel");
		pList.add(p5);

		System.out.println("Created Linked List with prescriptions in descending order of dateOfIssue\n");
		pList.printList();
	}
}
