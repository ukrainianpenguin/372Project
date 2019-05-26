/** Oleg, Henry, Deaven, James, Drew
 * @author Oleg Semeneko
 * PaymentList supports Singleton pattern and stores payment options
 * in generic form for organization.
 */
import java.io.IOException;
import java.util.Iterator;

public class PaymentList extends ItemList<PaymentOption, String> {
	private static final long serialVersionUID = 1L;
	private static PaymentList paymentList; // Changed from "paymentlist"

	/**
	 * Singleton pattern constructor
	 */
	private PaymentList() {
	}

	/**
	 * Checks for instance of PaymentList and creates a new one if none found.
	 */
	public static PaymentList instance() {
		if (paymentList == null) {
			return (paymentList = new PaymentList());
		} else {
			return paymentList;
		}
	}

	/**
	 * Iterator for a given donor's payment methods, returns all 
	 * payment methods info associated with a given @param donorID
	 */
	
	public Iterator getDonorPaymentMethods(String donorId) {
		return super.getItems(donorId);
	}

	/**
	 * Removes a given payment option when given a paymentID,
	 * @return true iff successful
	 */
	public boolean removePaymentOption(String paymentId) {
		PaymentOption paymentOption = search(paymentId);
		if (paymentOption == null) {

			return false;
		} else {
			return super.remove(paymentOption);
		}
	}

	/**
	 * Clears all payment options associated with a given donor
	 */
	public void removePaymentOptions(Donor donor) {
		Iterator iterator = donor.getMethods();
		while (iterator.hasNext()) {

			PaymentOption option = (PaymentOption) (iterator.next());
			super.remove(option);

		}

	}

	/**
	 * Adds generic payment option to master list
	 */
	public boolean insertPaymentOption(PaymentOption option) {
		return super.add(option);
	}

	/**
	 * Serializes payment method object to disk, generic
	 */
	private void writeObject(java.io.ObjectOutputStream output) throws IOException {
		output.defaultWriteObject();
		output.writeObject(paymentList);
	}

	/**
	 * De-serializes payment method object from disk, generic
	 */
	private void readObject(java.io.ObjectInputStream input) throws IOException, ClassNotFoundException {
		input.defaultReadObject();
		if (paymentList == null) {
			paymentList = (PaymentList) input.readObject();
		} else {
			input.readObject();
		}
	}

}
