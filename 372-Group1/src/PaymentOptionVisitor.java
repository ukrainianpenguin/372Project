/** Oleg, Henry, Deaven, James, Drew 
 * @author Oleg Semeneko
 * 
 * PaymentOptionVisitor supports payment options for visitor pattern.
 */
public interface PaymentOptionVisitor {
	public void visit(PaymentOption option);

	public void visit(CreditCard card);

	public void visit(BankAccount account);

}
