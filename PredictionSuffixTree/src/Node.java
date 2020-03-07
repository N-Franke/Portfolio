import java.util.ArrayList;

public class Node<T> {
	// Data
	ArrayList<T> tokenSequence = new ArrayList<T>(); // tokenSequence at this node
	ArrayList<Node> children = new ArrayList<Node>();

	// Methods
	void setSequence(ArrayList<T> inputArray) {
		tokenSequence = inputArray; // this works
		// System.out.println("Node = " +tokenSequence);
	}

	ArrayList<T> getSequence() {
		return tokenSequence;
	}

	boolean addNode(Node testNode) {
		boolean found = false;
		if (tokenSequence.equals(testNode.getSequence())) {
			found = true;
			// DO NOTHING
		}

		else if (amIaSuffix(testNode) || (tokenSequence.size() == 0)) {
			// Add nodes to children
			//System.out.println("Children = " + children.size());
			int m = 0;
			while (!found && (m < children.size())) {
				//System.out.println("in while loop");
				found=children.get(m).addNode(testNode);
				m++;
			}
			if(!found) {
			children.add(testNode);
			}
		}
		return found;
	}

	void print() {
		System.out.println(tokenSequence);
		for (int i = 0; i < children.size(); i++) {
			children.get(i).print(1);
		}
	}

	void print(int numSpacesBefore) {
		for (int i = 0; i < numSpacesBefore; i++) {
			System.out.print("  ");
		}
		System.out.print("-->");
		System.out.println(tokenSequence);
		
		for (int i = 0; i < children.size(); i++) {
			children.get(i).print(numSpacesBefore + 1);
		}
	}

	boolean amIaSuffix(Node input) {
		boolean suffix;
		ArrayList<T> testArray = new ArrayList<T>();
		testArray = input.getSequence();
		int lengthCurrent = tokenSequence.size();
		int lengthTest = testArray.size() - lengthCurrent;
		if (tokenSequence.equals(testArray.subList(lengthTest, testArray.size()))) {
			suffix = true;
		} else {
			suffix = false;
		}
		return suffix;
	}

}
