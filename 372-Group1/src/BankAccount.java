
/**
 * Class for Credit Card object, stores donation amounts as well as the ID it's associated with
 * @author Oleg Semenenko
 * Oleg, Henry, Deaven, James, Drew 
 */

import java.io.Serializable;

public class BankAccount extends PaymentOption implements Serializable, Matchable<String> {
	private static final long serialVersionUID = 1L;
	private String bankNum;

	/**
	 * @author Oleg Semenenko Constructor for CreditCard object, storing @param
	 *         string Number, donation amount, and associated ID.
	 */
	public BankAccount(String bankNum, double donationAmount, String donorId, int type) {
		super(donationAmount, donorId, bankNum, type);
		this.bankNum = bankNum;
	}

	/**
	 * @author Oleg Semenenko Getter for @return donorID.
	 */

	public String getDonorId() {
		return super.getDonorId();
	}

	/**
	 * @author Oleg Semeneko Getter for @return creditNumber
	 */

	public String getBankNum() {
		return bankNum;
	}

	/**
	 * @author Oleg Semeneko Setter for @param creditNumber
	 */
	public void setBankNum(String bankNum) {
		this.bankNum = bankNum;
	}

	/**
	 * @author Oleg Semeneko Getter for @return donationAmount
	 */
	public double getDonationAmount() {
		return super.getDonationAmount();
	}

	/**
	 * Dummy charge method
	 */
	public void charge() {
	}

	/**
	 * Accept method for visitor pattern
	 */
	public void accept(PaymentOptionVisitor visitor) {
		visitor.visit(this);
	}

	/**
	 * toString override method for debug/print dialog statements
	 */
	@Override
	public String toString() {
		return "Bank Account " + bankNum;
	}

}