
// Oleg, Henry, Deaven, James, Drew
import java.io.Serializable;

public class Expenses implements Serializable {

	private static final long serialVersionUID = 1L;
	double amount;
	String type;
	String label;

	public Expenses(String type, double amount) {
		this.amount = amount;
		this.type = type;
	}

	public double getAmount() {
		return amount;
	}

	public String getType() {
		return type;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

}
