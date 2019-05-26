
// Oleg, Henry, Deaven, James, Drew
import java.io.IOException;
import java.util.Iterator;

public class DonorList extends ItemList<Donor, String> {
	private static final long serialVersionUID = 1L;
	private static DonorList donorList;

	/*
	 * Private constructor for singleton pattern of DonorList
	 */
	private DonorList() {

	}

	/**
	 * 
	 * Creates instance of DonorList in event of serializable retrieval failure.
	 * Supports the singleton pattern
	 * 
	 * @return the singleton object
	 */
	public static DonorList instance() {
		if (donorList == null) {
			return (donorList = new DonorList());
		} else {
			return donorList;
		}
	}

	public Iterator getDonors() {
		Iterator itr = super.getType();
		return itr;
	}

	public boolean removeDonor(Donor donor) {

		return super.remove(donor);
	}

	public boolean insertDonor(Donor donor) {

		return super.add(donor);
	}

	public Donor search(String donorId) {
		return super.search(donorId);
	}

	private void writeObject(java.io.ObjectOutputStream output) throws IOException {
		output.defaultWriteObject();
		output.writeObject(donorList);
	}

	/*
	 * Supports serialization
	 * 
	 * @param input the stream to be read from
	 */
	private void readObject(java.io.ObjectInputStream input) throws IOException, ClassNotFoundException {
		input.defaultReadObject();
		if (donorList == null) {
			donorList = (DonorList) input.readObject();
		} else {
			input.readObject();
		}
	}
}