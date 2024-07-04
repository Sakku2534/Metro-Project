import java.util.ArrayList;
import java.util.HashMap;

///This means T must be a type that can be compared to itself using the Comparable interface
public class Heap<T extends Comparable<T>>
{
	ArrayList<T> data = new ArrayList<>(); //store the elements of your heap by using methods like add, remove, and heapify.
	HashMap<T, Integer> map = new HashMap<>(); //store the indices of elements in your ArrayList.

	public void add(T item)
	{
		data.add(item); //add the item to data list
		map.put(item, this.data.size() - 1); //map the item to its index
		upheapify(data.size() - 1);  //maintain heap properties
	}

	private void upheapify(int ci)
	{
		int pi = (ci - 1) / 2; //calculate the parent index
		if (isLarger(data.get(ci), data.get(pi)) > 0) //check if ci>pi
		{
			swap(pi, ci);
			upheapify(pi);  //if ci>pi then swap with pi and up-heapify recursively
		}
	}

	private void swap(int i, int j)
	{
		T ith = data.get(i);  // Get the element at index i
		T jth = data.get(j);  // Get the element at index j

		data.set(i, jth);  // Set the element at index i to jth
		data.set(j, ith);  // Set the element at index j to ith
		map.put(ith, j);   // Update the map with ith's new index
		map.put(jth, i);   // Update the map with jth's new index
	}

	public void display()
	{
		System.out.println(data);
	}

	public int size()
	{
		return this.data.size();  //returns the no. of elements in the data list
	}

	public boolean isEmpty()
	{
		return this.size() == 0;  //checks if heap is empty
	}

	//removal of the root element
	public T remove()
	{
		swap(0, this.data.size() - 1);  //swaps the root element (at index 0) with the last element
		//This removes the last element (which was originally the root due to the swap)
		// from the data list and captures its value in rv
		T rv = this.data.remove(this.data.size() - 1);

		//This method call ensures that the heap properties are maintained by
		// performing down-heapify starting from the root index (0).
		downheapify(0);

		//This removes the entry associated with the removed value rv from the map
		map.remove(rv);
		return rv;
	}

	private void downheapify(int pi)
	{
		int lci = 2 * pi + 1; // Left child index
		int rci = 2 * pi + 2; // Right child index
		int mini = pi; // Assume the current node is the smallest (for min-heap) or largest (for max-heap)

		if (lci < this.data.size() && isLarger(data.get(lci), data.get(mini)) > 0)
		{
			mini = lci;  // If left child is larger, update mini index
		}

		if (rci < this.data.size() && isLarger(data.get(rci), data.get(mini)) > 0)
		{
			mini = rci;  // If right child is larger, update mini index
		}

		if (mini != pi)
		{
			swap(mini, pi);  // Swap parent with the smaller or larger child
			downheapify(mini);  // Recursively downheapify at the affected child index
		}
	}

	public T get()
	{
		return this.data.get(0);  // Return the element at the root
	}

	public int isLarger(T t, T o)
	{
		// Implement your comparison logic here
		// Return positive if item1 > item2, zero if item1 == item2, negative if item1 < item2
		// This is just a placeholder for demonstration purposes.
		return t.compareTo(o);
	}

	public void updatePriority(T pair)
	{
		int index = map.get(pair);  // Get the index of the element in the heap
		upheapify(index);  // Perform up-heapify to restore heap properties
	}
}
