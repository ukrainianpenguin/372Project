// Oleg, Henry, Deaven, James, Drew
/**
 * Class for Credit Card object, stores donation amounts as well as the ID it's associated with
 */

import java.io.Serializable;

public class CreditCard extends PaymentOption implements Serializable, Matchable<String> {
	private static final long serialVersionUID = 1L;
	private String creditNumber;

	/**
	 * @author Oleg Semeneko Constructor for CreditCard object, storing @param
	 *         string Number, donation amount, and associated ID.
	 */
	public CreditCard(String creditNumber, Double donationAmount, String donorId, int type) {
		super(donationAmount, donorId, creditNumber, type);
		this.creditNumber = creditNumber;

	}

	/**
	 * 
	 * /**
	 * 
	 * @author Oleg Semeneko Getter for @return creditNumber
	 */

	public String getCreditNumber() {
		return creditNumber;
	}

	/**
	 * @author Oleg Semeneko Setter for @param creditNumber
	 */
	public void setCreditNumber(String creditNumber) {
		this.creditNumber = creditNumber;
	}

	/**
	 * @author Oleg Semeneko Getter for @return donationAmount
	 */

	public void charge() {
	}

	@Override
	public void accept(PaymentOptionVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public String toString() {
		return "Credit Card" + creditNumber;
	}

}