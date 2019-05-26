/** Oleg, Henry, Deaven, James, Drew
 * @author Oleg Semeneko
 * After talking in class with you, you wanted collection classes for Transactions so one was added.
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class TransactionList implements Serializable {
	private static final long serialVersionUID = 1L;
	private ArrayList<Transaction> transactions = new ArrayList<Transaction>();
	private static TransactionList transactionList;

	private TransactionList() {
	}

	/**
	 * Checks for an instance of TransactionList, creates a new one if none is
	 * found.
	 */
	public static TransactionList instance() {
		if (transactionList == null) {
			return (transactionList = new TransactionList());
		} else {
			return transactionList;
		}

	}

	/**
	 * @author Oleg Semeneko Adds transaction to Business's transaction arraylist
	 */
	public void addTransaction(Transaction transaction) {
		transactions.add(transaction);
	}

	/**
	 * @author Oleg Semeneko
	 * @param donor, creditNum Returns true/false if removal of transaction from
	 *        business' record is successful or not.
	 */

	/**
	 * @author Oleg Semeneko
	 * @param donorID Iterator through list of Transactions associated with donor
	 *                ID, returns list of transactions for UserInterface
	 */
	public Iterator getDonorTransaction(String donorId) { // Internal Dev comment: Changed @param "itr" to compliant
															// "iteratorTransaction"
		ArrayList<Transaction> donorTransactionList = new ArrayList<Transaction>(); // Changed @param "list" to
																					// "donorTransactionList"
		for (Iterator<Transaction> iteratorTransaction = transactions.iterator(); iteratorTransaction.hasNext();) {
			Transaction transaction = (Transaction) iteratorTransaction.next();
			if (transaction.getDonorId().equals(donorId)) {
				donorTransactionList.add(transaction);

			}

		}
		return (donorTransactionList.iterator());
	}

	/**
	 * @author Oleg Semeneko Iterator through list off all business transactions-
	 *         not passed as @param because it is a singleton that it has access to
	 *         by default. Adds all transactions by every donor to ArrayList
	 */
	public Iterator getAllDonorTransactions() { // Ditto, changed "itr" to "iteratorTransaction"
		ArrayList<Transaction> generalTransactionList = new ArrayList<Transaction>(); // Changed @param "list" to
																						// "generalTransactionList"
		for (Iterator<Transaction> iteratorTransaction = transactions.iterator(); iteratorTransaction.hasNext();) {
			Transaction transaction = (Transaction) iteratorTransaction.next();

			generalTransactionList.add(transaction);

		}

		return (generalTransactionList.iterator());
	}

}
