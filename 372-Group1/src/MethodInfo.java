/*
 * Oleg, Henry, Deaven, James, Drew
 * The Method info Class implements the abstract class and allows us to do things we wouldn't be able to do otherwise.
 */
public class MethodInfo implements PaymentOptionVisitor {
	private static MethodInfo visitor;
	private static double threshold;

	private MethodInfo() {

	}

	/**
	 * Returns the only instance of the class
	 * 
	 * @return the instance of the class
	 */
	public static MethodInfo instance(double threshold) {
		setThreshold(threshold);
		if (visitor == null) {
			visitor = new MethodInfo();
		}
		return visitor;
	}

	public static void setThreshold(double threshold) {
		MethodInfo.threshold = threshold;
	}

	/*
	 * The next three methods are visitor methods that print if they are above the
	 * threshold
	 */
	public void visit(PaymentOption option) {
		if (option.getTotalCollected() > threshold) {
			System.out.println("Payment option Number of Transactions " + option.getTimesCollected()
					+ " Total Collected " + option.getTotalCollected());
		}
	}

	public void visit(CreditCard card) {
		if (card.getTotalCollected() > threshold) {
			System.out.println("Credit Card: Number of Transactions " + card.getTimesCollected() + " Total Collected "
					+ card.getTotalCollected());
		}
	}

	public void visit(BankAccount account) {
		if (account.getTotalCollected() > threshold) {
			System.out.println("Bank Account: Number of Transactions " + account.getTimesCollected()
					+ " Total Collected " + account.getTotalCollected());
		}
	}
}
