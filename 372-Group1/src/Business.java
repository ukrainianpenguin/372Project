// Oleg, Henry, Deaven, James, Drew
/**
 * Business object class, complies with Singleton implementation.
 * Stores donorList, creditCardList, transactionList. 
 * Constructor called with @param donorList, creditCards, transactions
 */

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class Business implements Serializable {
	private ArrayList<Expenses> expenses = new ArrayList<Expenses>();
	private static final long serialVersionUID = 1L; // If serializable was successful or not
	private static Business business;
	private DonorList donorList;
	private TransactionList transactions;
	private PaymentList paymentList;
	private double totalDonationAmount = 0.0;
	private double totalExpenseAmount = 0.0;
	private double balance = 0.0;

	public static final int CreditCard = 1;
	public static final int BankAccount = 2;

	/**
	 * @author Oleg Semeneko Private constructor for Singleton implementation.
	 *         Creates new instance of all ArrayLists being stored within Business
	 *         object instance.
	 */
	private Business() {
		donorList = DonorList.instance();

		transactions = TransactionList.instance();
		paymentList = PaymentList.instance();

	}

	/**
	 * Creates business instance if serialization doesn't provide an existing one.
	 * Supports the singleton pattern
	 * 
	 * @return the singleton object
	 */
	public static Business instance() {
		if (business == null) {
			DonorIdServer.instance(); // instantiate all singletons
			return (business = new Business());
		} else {
			return business;
		}
	}

	/**
	 * @author Henry Rheault Gets total donation amount double var for Use Case 8
	 */
	public Double getTotalDonationAmount() {
		return totalDonationAmount;
	}

	/**
	 * @author Oleg Semenenko Checks donorList to see if @param Donor is contained.
	 */

	public Donor isDonor(String DonorId) {
		return donorList.search(DonorId);
	}

	/**
	 * @author Oleg Semenenko Calls constructor for Donor, creates new donor object
	 *         in list. Does NOT check to see if name and phone are already
	 *         associated as the group decided such check was redunant. @return
	 *         donor object.
	 */

	public Donor addDonor(String name, String phone) {
		Donor donor = new Donor(name, phone);
		if (donorList.insertDonor(donor)) {
			return donor;
		}
		return null;
	}

	/**
	 * @author Oleg Semeneko Adds a credit card object to Donor and other associated
	 *         Card Lists.
	 * @return true if successful.
	 */
	public boolean addPayment(String donorId, String paymentNum, String donationAmount, int type) {
		double donationParsed;
		try {
			donationParsed = Double.parseDouble(donationAmount);
		} catch (NumberFormatException e) {
			return false;
		}
		PaymentOption option = createPaymentOption(type, donationParsed, donorId, paymentNum);
		donorList.search(donorId).getAddMethods(option);
		return paymentList.add(option);

	}

	/*
	 * This was going to be used for the factory design pattern, but later it was
	 * realized we were not required to use it
	 */
	public PaymentOption createPaymentOption(int type, double amount, String donorId, String accountNumber) {
		switch (type) {
		case 1:
			return new CreditCard(accountNumber, amount, donorId, type);
		case 2:
			return new BankAccount(accountNumber, amount, donorId, type);
		default:
			return null;
		}
	}

	/**
	 * @author Oleg Semenenko Processes donations of all donors for the month when
	 *         processDonations() is used.
	 * @return total funds raised
	 */
	public double processDonations() {
		double total = 0;
		{
			Iterator iterator = paymentList.getType();
			while (iterator.hasNext()) {
				PaymentOption option = (PaymentOption) iterator.next();
				total = option.getDonationAmount() + total;
				option.setTotalCollected(option.getDonationAmount() + option.getTotalCollected());
				option.setTimesCollected();
				Transaction trans = new Transaction(option);
				transactions.addTransaction(trans);
			}

			// Add transaction to current donor's transaction list

		}
		totalDonationAmount += total;
		return total;
	}

	public Double getTotalExpenseAmount() {
		return totalExpenseAmount;
	}

	public Iterator getDonors() {
		return donorList.getDonors();
	}

	/**
	 * @author Oleg Semeneko Iterator through transaction list, returns as list.
	 */
	public Iterator getTransactions() {
		return transactions.getAllDonorTransactions();
	}

	/**
	 * @author Oleg Semeneko Iterator through donors associated with each card in
	 *         master credit card list. Returns list of donor IDs associated.
	 */
	public Iterator getDonorPaymentMethods(String donorId) {
		return paymentList.getDonorPaymentMethods(donorId);
	}

	/**
	 * Removes donor from donors arraylist.
	 * 
	 * @return true if successful.
	 */
	public boolean removeDonor(Donor donor) {
		paymentList.removePaymentOptions(donor);
		return donorList.removeDonor(donor);
	}

	/**
	 * @author Oleg Semenenko Removes a given credit card from association with a
	 *         given Donor, therefore the business as a whole.
	 * @return true if successful.
	 */
	public boolean removePayment(String paymentNum) {
		return paymentList.removePaymentOption(paymentNum);

	}

	/*
	 * add an expense and modify any needed values
	 */
	public void addExpense(String type, double amount) {
		Expenses e = new Expenses(type, amount);
		expenses.add(e);
		totalExpenseAmount = totalExpenseAmount + e.getAmount();
	}

	/*
	 * Gives the payment info using the visitor pattern
	 */
	public void paymentInfo(PaymentOptionVisitor visitor) {
		for (Iterator<PaymentOption> itemIterator = paymentList.iterator(); itemIterator.hasNext();) {
			itemIterator.next().accept(visitor);

		}
	}

	public double getBalance() {
		balance = totalDonationAmount - totalExpenseAmount;
		return balance;
	}

	public Iterator getExpenseList() {
		return expenses.iterator();
	}

	/**
	 * Gets business object from file serializable.
	 * 
	 * @return object if it exists; @return IOException error & Stack Trace if File
	 *         System IO error;
	 * @return ClassNotFoundException error & Stack Trace if Business object not
	 *         found.
	 */
	public static Business retrieve() { // DEV NOTE: Changed all exception names to ALL CAPS
		try {
			FileInputStream file = new FileInputStream("BusinessData");
			ObjectInputStream input = new ObjectInputStream(file);
			business = (Business) input.readObject();
			DonorIdServer.retrieve(input);
			return business;
		} catch (IOException IOE) {
			IOE.printStackTrace();
			return null;
		} catch (ClassNotFoundException CNFE) {
			CNFE.printStackTrace();
			return null;
		}
	}

	/**
	 * Attempts a save of business object to disk.
	 * 
	 * @return true if successful. @return IOException & Stack Trace if general file
	 *         system error occurrs. IE: Full disk, invalid save target passed, disk
	 *         disconnects during transfer, etc.
	 */
	public boolean save() { // DEV NOTE: Changed exception name to ALL CAPS
		try {
			FileOutputStream file = new FileOutputStream("BusinessData");
			ObjectOutputStream output = new ObjectOutputStream(file);
			output.writeObject(business);
			output.writeObject(DonorIdServer.instance());
			file.close();
			return true;
		} catch (IOException IOE) {
			IOE.printStackTrace();
			return false;
		}
	}

}
