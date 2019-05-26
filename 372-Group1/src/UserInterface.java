
/** Oleg, Henry, Deaven, James, Drew
/**
 * @author Oleg Semeneko
 * UserInterface class acts as Mediator for all business
 * logic methods in other classes
 * 
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.StringTokenizer;

public class UserInterface {
	// Data fields, for UI input processing
	private static UserInterface userInterface;
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static Business business;
	double profit;
	double expense;
	double total;
	private static final int EXIT = 0;
	private static final int ADD_DONOR = 1;
	private static final int ADD_PAYMENT = 2;
	private static final int PROCESS_DONATION = 3;
	private static final int LIST_TRANSACTIONS = 4;
	private static final int LIST_DONORS = 5;
	private static final int LIST_DONOR = 6;
	private static final int REMOVE_DONOR = 7;
	private static final int REMOVE_CREDIT = 8;
	private static final int REMOVE_BANK = 9;
	private static final int ADD_EXPENSE = 10;
	private static final int ORGANIZATION_INFO = 11;
	private static final int LIST_ALL_PAYMENT_INFO = 12;
	private static final int LIST_ALL_EXPENSES = 13;
	private static final int SAVE = 14;
	private static final int HELP = 15;

	/**
	 * @author Oleg Semeneko Singleton constructor for UserInterface object, checks
	 *         for user input as whether to import existing business or create a new
	 *         one.
	 */

	private UserInterface() {
		if (yesOrNo("Look for saved data and  use it?")) {
			retrieve();
		} else {
			business = Business.instance();
		}

	}

	/**
	 * @author Oleg Semeneko Creates an instance of Business if Constructor is
	 *         passed "No"
	 */

	public static UserInterface instance() {
		if (userInterface == null) {
			return userInterface = new UserInterface();
		} else {
			return userInterface;
		}
	}

	/**
	 * @author Oleg Semeneko Handles user input for yesOrNo and Constructor methods
	 *         Returns string token to be passed
	 */

	public String getToken(String prompt) {
		do {
			try {
				System.out.println(prompt);
				String line = reader.readLine();
				StringTokenizer tokenizer = new StringTokenizer(line, "\n\r\f");
				if (tokenizer.hasMoreTokens()) {
					return tokenizer.nextToken();
				}
			} catch (IOException ioe) {
				System.exit(0);
			}
		} while (true);

	}

	/**
	 * @author Oleg Semeneko Returns true or false based on getToken's string,
	 *         passes bool to construtor
	 */

	private boolean yesOrNo(String prompt) {
		String more = getToken(prompt + " (Y|y)[es] or anything else for no");
		if (more.charAt(0) != 'y' && more.charAt(0) != 'Y') {
			return false;
		}
		return true;
	}

	/**
	 * @author Oleg Semeneko Handles user input for CLI of business, passes @param
	 *         int value to determine what business logic method to call
	 */

	public int getCommand() {
		do {
			try {
				int value = Integer.parseInt(getToken("Enter command:" + HELP + " for help"));
				if (value >= EXIT && value <= HELP) {
					return value;
				}
			} catch (NumberFormatException nfe) {
				System.out.println("Enter a number");
			}
		} while (true);
	}

	/**
	 * @author Oleg Semeneko Displays CLI options for user interface/mediator
	 */

	public void help() {
		System.out.println("Enter a number between 0 and 15 as explained below:");
		System.out.println(EXIT + " to Exit");
		System.out.println(ADD_DONOR + " to add a member");
		System.out.println(ADD_PAYMENT + " to add a payment option");
		System.out.println(PROCESS_DONATION + " to process a donation");
		System.out.println(LIST_TRANSACTIONS + " to view all transactions made by all Donors ");
		System.out.println(LIST_DONORS + " to view all donors ");
		System.out.println(LIST_DONOR + " to  view a specific donor");
		System.out.println(REMOVE_DONOR + " to  remove a donor account");
		System.out.println(REMOVE_CREDIT + " to  remove a credit card");
		System.out.println(REMOVE_BANK + " to  remove a bank account");
		System.out.println(ADD_EXPENSE + " to add an expense ");
		System.out.println(ORGANIZATION_INFO + " to  display information about the organization");
		System.out.println(LIST_ALL_PAYMENT_INFO + " to display payment information");
		System.out.println(LIST_ALL_EXPENSES + " to list all expenses");
		System.out.println(SAVE + " to  save data");
		System.out.println(HELP + " for help");
	}

	/**
	 * Converts the string to a number
	 * 
	 * @param prompt the string for prompting
	 * @return the integer corresponding to the string
	 * 
	 */
	public int getNumber(String prompt) {
		do {
			try {
				String item = getToken(prompt);
				Integer number = Integer.valueOf(item);
				return number.intValue();
			} catch (NumberFormatException nfe) {
				System.out.println("Please input a number ");
			}
		} while (true);
	}

	/**
	 * @author Oleg Semeneko Method to add a donor, if successful prints a success,
	 *         if not it will alert the user
	 */
	public void addDonor() {
		String name = getToken("Enter member name");
		String phone = getToken("Enter phone");
		Donor result;
		result = business.addDonor(name, phone);
		if (result == null) {
			System.out.println("Could not add member");
		}
		System.out.println(result);
	}

	/**
	 * @author Oleg Semeneko Method to add a Payment option. When input DonorID and
	 *         amount of donation, check is run if the card number is in the system,
	 *         if so it is not added
	 */
	public void addPayment() {
		boolean result;
		String id = getToken("Enter your DonorID");
		if (business.isDonor(id) == null) {
			System.out.println("No such member");
			return;
		}
		int type = getNumber("Enter 1 for Credit Card and 2 for a Bank account");
		String paymentdNum = getToken("Enter the number associated with your bank account/Card");

		String donation = getToken("Enter how much you wish to donate");

		result = business.addPayment(id, paymentdNum, donation, type);

		if (result) {
			System.out.println("Added payment method");

		} else
			System.out.println("Could not add payment method");

	}

	/**
	 * @author Oleg Semeneko When called handles payment via dummy charge() method
	 *         for all cards for all donators on record. Adds transaction objects to
	 *         each donor's transaction list and prints total
	 */
	public void processDonation() {
		double total = 0;
		total = business.processDonations();
		System.out.println("All donations processed.");
		System.out.println("Total collected is " + total);

	}

	/**
	 * @author Oleg Semeneko When called prints out all transaction objects with
	 *         their Number, Amount and Date.
	 */

	public void listTransactions() {
		int count = 0;
		Iterator result = business.getTransactions();
		while (result.hasNext()) {
			Transaction transaction = (Transaction) (result.next());
			count++;
			System.out.println("Payment Account Number: " + transaction.getPaymentNum() + " Transaction Amount: "
					+ transaction.getValue() + " Date " + transaction.getDate());

		}
		if (count == 0) {
			System.out.println("None found");

		}
	}

	/**
	 * @author Oleg Semeneko When called prints a list of all donors with all info
	 *         on record except Credit Cards and Donation amounts
	 */
	public void listDonors() {
		int count = 0;
		Iterator result = business.getDonors();
		while (result.hasNext()) {
			++count;
			Donor d = (Donor) result.next();
			System.out.println("Name: " + d.getName() + " ID: " + d.getId() + " Phone Number: " + d.getPhone());

		}
		if (count == 0) {
			System.out.println("None found");
		}
	}

	/**
	 * @author Oleg Semeneko When called takes input of Donor ID number, prints all
	 *         info on record associated with said donor.
	 */

	public void listDonor() {
		int count = 0;
		String id = getToken("Enter the id of the donor you wish to see");
		if (business.isDonor(id) != null) {

			Donor donor = business.isDonor(id);
			System.out.println("Name: " + donor.getName() + " Phone: " + donor.getPhone());
			Iterator result = donor.getMethods();
			while (result.hasNext()) {
				count++;
				PaymentOption option = (PaymentOption) (result.next());
				System.out.println(option);
			}
		}
		if (count == 0) {
			System.out.println("None found");
		}

	}

	/**
	 * @author Oleg Semenko When called takes input of Donor ID number, removes all
	 *         info associated with given donor. Prints success or failure
	 */

	public void removeDonor() {
		boolean result = false;
		String id = getToken("Enter the id of the donor you wish to remove");
		if (business.isDonor(id) != null) {
			result = business.removeDonor(business.isDonor(id));
		}
		if (result) {
			System.out.println("Donor successfully removed");
		} else {
			System.out.println("Donor was not removed");
		}

	}

	/**
	 * @author Oleg Semeneko When called takes input of Donor ID number, then their
	 *         credit card number and removes the info from the donor's account.
	 *         Prints success or failure.
	 */

	public void removeCredit() {
		boolean result = false;
		int type = 1;
		String id = getToken("Enter the id of the of the account");
		if (business.isDonor(id) != null) {
			String paymentNum = getToken("Enter the credit card number  you wish to remove");
			result = business.removePayment(paymentNum);
		}
		if (result) {
			System.out.println("Payment method was successfully removed");
		} else {
			System.out.println("Payment method was not removed");
		}

	}

	/**
	 * @author Oleg Semeneko For the use case of removing a bank account when called
	 *         by UI driver
	 * 
	 */

	public void removeBank() {
		boolean result = false;
		int type = 2;
		String id = getToken("Enter the id of the of the account");
		if (business.isDonor(id) != null) {
			String paymentNum = getToken("Enter the bank account you wish to remove");
			result = business.removePayment(paymentNum);
		}
		if (result) {
			System.out.println("Payment method was successfully removed");
		} else {
			System.out.println("Payment method was not removed");
		}

	}

	/**
	 * @author Oleg Semeneko
	 * 
	 *         Adds expense to the array list of expenses
	 */

	public void addExpense() {

		do {
			String type = getToken("What is the expense type");
			double amount = getNumber("Enter the expense amount");
			business.addExpense(type, amount);

			if (!yesOrNo("Add more expenses?")) {
				break;
			}
		} while (true);
	}

	/**
	 * @author Oleg Semenenko, Henry Rheault Use case #8 Project 2, prints out
	 *         organization info
	 */

	public void showInfo() {
		System.out.println("Total amount donated: " + business.getTotalDonationAmount() + ".");
		System.out.println("Total amount of expenses: " + business.getTotalExpenseAmount() + ".");
		System.out.println("Total Balance: " + business.getBalance() + ".");

	}

	/**
	 * @author Oleg Semeneko Lists all payment info associated with amount greater
	 *         than threshold
	 */

	public void listPayment() {
		double threshold = getNumber("Enter the threshold you wish to use");
		business.paymentInfo(MethodInfo.instance(threshold));
		// list all bank accounts and cards associated with threshold or higher payment
		// amounts monthly
		// and total amount raised through each

	}

	/*
	 * @author Oleg Semeneko list total amount ever donated, total amount of
	 * expenses, amount for each expense
	 * 
	 */
	public void listExpenses() {

		Iterator result = business.getExpenseList();
		if (!result.hasNext()) {
			System.out.println("No expenses to display");
		} else {
			System.out.println("Expenses list: ");
		}

		while (result.hasNext()) {

			Expenses expense = (Expenses) (result.next());
			System.out.println(expense.getType() + " , " + expense.getAmount() + " .");
		}

	}

	/**
	 * @author Oleg Semeneko When called saves business object to disk serializable
	 *         for later usage. Prints success or failure.
	 */

	private void save() {
		if (business.save()) {
			System.out.println(" The business has been successfully saved in the file BusinessData \n");
		} else {
			System.out.println(" There has been an error in saving \n");
		}
	}

	/**
	 * @author Oleg Semeneko When called attempts retrieval of business object from
	 *         disk. Prints success or failure.
	 */

	private void retrieve() {
		try {
			if (business == null) {
				business = Business.retrieve();
				if (business != null) {
					System.out.println(" The business has been successfully retrieved from the file BusinessData \n");
				} else {
					System.out.println("File doesnt exist; creating new library");
					business = Business.instance();
				}
			}
		} catch (Exception cnfe) {
			cnfe.printStackTrace();
		}
	}

	/**
	 * @author Oleg Semeneko Driver of UserInterface.class, handles I/O via above
	 *         methods and calls Business Logic methods through UserInterface
	 *         methods.
	 */

	public void process() {
		int command;
		help();
		while ((command = getCommand()) != EXIT) {
			switch (command) {
			case ADD_DONOR:
				addDonor();
				break;
			case ADD_PAYMENT:
				addPayment();
				break;
			case PROCESS_DONATION:
				processDonation();
				break;
			case LIST_TRANSACTIONS:
				listTransactions();
				break;
			case LIST_DONORS:
				listDonors();
				break;
			case LIST_DONOR:
				listDonor();
				break;
			case REMOVE_DONOR:
				removeDonor();
				break;
			case REMOVE_CREDIT:
				removeCredit();
				break;
			case REMOVE_BANK:
				removeBank();
				break;
			case ADD_EXPENSE:
				addExpense();
				break;
			case ORGANIZATION_INFO:
				showInfo();
				break;
			case LIST_ALL_PAYMENT_INFO:
				listPayment();
				break;
			case LIST_ALL_EXPENSES:
				listExpenses();
				break;

			case SAVE:
				save();
				break;
			case HELP:
				help();
				break;
			}
		}
	}

	/**
	 * @author Henry Rheault Runs process driver on Business instance.
	 */
	public static void main(String[] args) {
		UserInterface.instance().process();
	}
}