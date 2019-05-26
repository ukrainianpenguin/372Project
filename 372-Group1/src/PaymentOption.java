/** Oleg, Henry, Deaven, James, Drew 
 * @author Oleg Semeneko
 * PaymentOptions.class contains helper methods for generic payment options
 */
import java.io.Serializable;

public abstract class PaymentOption implements Matchable<String>, Serializable {
	private static final long serialVersionUID = 1L;
	private int type;
	private double donationAmount;
	private String id;
	private String donorId;
	private int timesCollected = 0;
	private double totalCollected = 0;

	/**
	 * Generic payment option type constructor
	 */
	public PaymentOption(Double donationAmount, String donorId, String id, int type) {
		this.donationAmount = donationAmount;
		this.donorId = donorId;
		this.id = id;
		this.type = type;

	}

	/**
	 * Returns time collected field of PaymentOption object
	 */
	public int getTimesCollected() {
		return timesCollected;
	}
	/**
	 * Sets the times collected for PaymentOption object
	 */
	public void setTimesCollected() {
		timesCollected++;
	}
	/**
	 * Returns total collected from PaymentOption object
	 */
	public double getTotalCollected() {
		return totalCollected;
	}
	/**
	 * Sets total collected for PaymentOption object
	 */
	public void setTotalCollected(double totalCollected) {
		this.totalCollected = totalCollected;
	}
	/**
	 * Returns donor ID associated with given PaymentOption object
	 */
	public String getDonorId() {
		return donorId;
	}
	/**
	 * Matches method of given PaymentOption object, returns true iff
	 * objects comply with matchable standard.
	 */
	public boolean matches(String id) {
		return (this.id.equals(id));
	}
	/**
	 * Returns ID of given payment option
	 */
	public String getId() {
		return id;
	}
	/**
	 * Returns payment amount associated with given payment method
	 */
	public double getDonationAmount() {
		return donationAmount;
	}
	/**
	 * toString override for print dialogs/diagnostics, returns string.
	 */
	@Override
	public String toString() {
		return "PaymentOption [donationAmount=" + donationAmount + ", id=" + id + "]";
	}
	/**
	 * Acceptance method for PaymentOption visitor pattern.
	 */
	public void accept(PaymentOptionVisitor visitor) {
		visitor.visit(this);
	}
	/**
	 * equals override/substitute method to comply with standards.
	 * Confirms all fields are equal and returns true iff.
	 */
	public boolean equals(Object object) {
		PaymentOption option = (PaymentOption) object;
		return this.id.matches(option.getId());
	}
}
