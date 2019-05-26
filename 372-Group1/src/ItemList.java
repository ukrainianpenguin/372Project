
/**
 * @author Oleg Semeneko
 * Oleg, Henry, Deaven, James, Drew 
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class ItemList<T extends Matchable<K>, K> implements Serializable {
	private static final long serialVersionUID = 1L;
	private ArrayList<T> donorInfoList = new ArrayList<T>(); // Changed from former name "list"
																// Cards, bank accounts, donors

	/**
	 * Removes item from donor list. Generic
	 */

	public boolean remove(T item) {
		return donorInfoList.remove(item);
	}

	/**
	 * Returns an iterator for the collection
	 * 
	 * @return iterator for the collection
	 */
	public Iterator<T> iterator() {
		return donorInfoList.iterator();
	}

	/**
	 * Adds generic item to organization's info list
	 * 
	 * @return true iff successful
	 */
	public boolean add(T item) {
		return donorInfoList.add(item);
	}

	/**
	 * Generic search for generic Key Returns item if found, null if not.
	 */
	public T search(K key) {
		for (T item : donorInfoList) {
			if (item.matches(key)) {

				return item;
			}
		}
		return null;
	}

	/**
	 * Returns iterator through type T of all items matching input Returns iterator
	 */
	public Iterator<T> getItems(K key) {
		ArrayList<T> toReturn = new ArrayList<T>();
		for (T item : donorInfoList) {
			if (item.matches(key)) {
				toReturn.add(item);
			}

		}
		return toReturn.iterator();
	}

	/**
	 * Generic method to get all the items of type <T> returns an iterator
	 */
	public Iterator<T> getType() {
		ArrayList<T> toReturn = new ArrayList<T>();
		for (T item : donorInfoList) {
			toReturn.add(item);

		}
		return toReturn.iterator();
	}

}