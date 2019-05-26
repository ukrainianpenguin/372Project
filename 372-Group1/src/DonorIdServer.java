// Oleg, Henry, Deaven, James, Drew
/**
 * DonorIDServer assigns donorIDs to new donors when called.
 */

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class DonorIdServer implements Serializable {

	private static final long serialVersionUID = 1L;
	private int idCounter;
	private static DonorIdServer server;

	/**
	 * Private constructor for singleton pattern
	 */
	private DonorIdServer() {
		idCounter = 1;
	}

	/**
	 * Constructor for instance of DonorIdServer. Supports the singleton pattern
	 * 
	 * @return the singleton object
	 */
	public static DonorIdServer instance() {
		if (server == null) {
			return (server = new DonorIdServer());
		} else {
			return server;
		}
	}

	/**
	 * 
	 * Auto-generated Getter for id
	 * 
	 * @return id of the Donor
	 */
	public int getId() {
		return idCounter++;
	}

	/**
	 * String form @Override of the collection
	 */
	@Override
	public String toString() {
		return ("IdServer" + idCounter);
	}

	/**
	 * * Retrieves the server object via deserializaion
	 * 
	 * @param input inputstream for deserialization
	 */
	public static void retrieve(ObjectInputStream input) {
		try {
			server = (DonorIdServer) input.readObject();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (Exception cnfe) {
			cnfe.printStackTrace();
		}
	}

}