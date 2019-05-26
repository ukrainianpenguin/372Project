/** Oleg, Henry, Deaven, James, Drew 
 * 
 * Class for construction of Transaction objects, called by User Interface
 * @author Oleg Semeneko
 */

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;

public class Transaction implements Serializable {

	private static final long serialVersionUID = 1L;
	private double value;
	private PaymentOption option;
	private String paymentNum;
	private LocalDate date;
	private String donorId;

	/**
	 * @author Oleg Semeneko Constructor for Transaction objects, @param creditCard
	 *         Takes donation amount associated with given card, called during
	 *         processDonation() in UserInterface
	 */
	public Transaction(PaymentOption option) {

		this.option = option;
		donorId = option.getDonorId();
		value = option.getDonationAmount();
		paymentNum = option.getId();
		date = LocalDate.now(ZoneId.systemDefault());

	}

	/**
	 * @author Oleg Semeneko getter for @return donorID
	 */

	public String getDonorId() {
		return donorId;
	}

	/**
	 * @author Oleg Semeneko getter for @return creditCardNum
	 */

	public String getPaymentNum() {
		return paymentNum;
	}

	/**
	 * @author Oleg Semeneko getter for credit card @return value
	 */

	public double getValue() { // Dev comment: Changed "val" to "value" to be in compliance
		return value;
	}

	/**
	 * @author Oleg Semeneko Setter for value associated with credit card @param
	 *         valueInput
	 */
	public void setValue(double valueInput) { // Dev comment: Changed "val" to "value"
		this.value = valueInput; // Changed @param "val" to "valueInput"
	}

	/**
	 * @author Oleg Semeneko Getter for @return creditCard (object)
	 */
	public PaymentOption getPayment() {
		return option;
	}

	/**
	 * @author Oleg Semeneko Setter for @param creditCard
	 */

	public void setPaymentOption(PaymentOption option) {
		this.option = option;
	}

	/**
	 * @author Oleg Semeneko Getter for transaction records of @return date
	 */
	public LocalDate getDate() {
		return date;
	}

	/**
	 * @author Oleg Semeneko Setter for transaction object records of @param date
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}

	/**
	 * toString override method to comply with standards, returns relevant info as String
	 * for print dialogs/diagnostics
	 */
	@Override
	public String toString() {
		return "Transaction [value=" + value + ", option=" + option + ", paymentNum=" + paymentNum + ", date=" + date
				+ ", donorId=" + donorId + "]";
	}

}