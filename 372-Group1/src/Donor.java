// Oleg, Henry, Deaven, James, Drew
/**
 * Class of Donor objects
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class Donor implements Serializable, Matchable<String> {

	private static final long serialVersionUID = 1L;

	private String name;
	private String phone;
	private String id;
	private ArrayList<PaymentOption> methods = new ArrayList<PaymentOption>();

	private ArrayList<Transaction> donorTransactions = new ArrayList<Transaction>(); // Transactions list stored for
																						// each Donor
	// Dev note: Changed "Transactions" to "donorTransactions"
	private static final String DONOR_STRING = "D";

	/**
	 * @author Oleg Semeneko Constructor for Donor object. Calls DonorIDServer to
	 *         get ID number.
	 */
	public Donor(String name, String phone) {
		this.name = name;
		this.phone = phone;
		id = DONOR_STRING + (DonorIdServer.instance()).getId();

	}

	public Iterator getMethods() {
		return methods.iterator();
	}

	/**
	 * @author Oleg Semeneko Setter for @param transaction
	 */
	public void addTransaction(Transaction transaction) {
		donorTransactions.add(transaction);
	}

	/**
	 * @author Oleg Semeneko Getter for each @return transaction
	 */
	public Iterator getTransaction() {
		return donorTransactions.iterator();
	}

	/**
	 * @author Oleg Semeneko Getter for Donor @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @author Oleg Semeneko Setter for Donor @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @author Oleg Semeneko toString @Override for Donor objects
	 */
	@Override
	public String toString() {
		return "Donor [name=" + name + ", phone=" + phone + ", id=" + id + " ]";
	}

	/**
	 * @author Oleg Semeneko Auto-generated getter for phone number associated with
	 *         donor
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @author Oleg Semeneko Auto-generated getter for userID
	 */
	public String getId() {
		return id;
	}

	/**
	 * @author Oleg Semeneko quasi-@Override for equals() method in purview of Donor
	 *         objects Compares by ID
	 */
	public boolean equals(Object object) {
		Donor donor = (Donor) object;
		return this.id.matches(donor.getId());
	}

	public boolean matches(String key) {
		return id.equals(key);
	}

	public void getAddMethods(PaymentOption method) {
		methods.add(method);

	}
}