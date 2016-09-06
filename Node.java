/**
 * Node.java
 *
 * CS4341: Project 1
 * Group: Zachary Robbins, Kyle McCormick, Elijah Gonzalez, Peter Raspe
 */

public class Node {

	public final int value;
	public final Operation operation; // NULL if this is root
	public final Node parent;	      // Parent node, NULL if this is root
	public Node[] children;           // Children, determined by operators list from parsing.
	                                  //  NULL if not expanded

	public Node(int value, Operation operation, Node parent) {
		this.value = value;
		this.operation = operation;
		this.parent = parent;
		this.children = null;
	}

	public void expand(Operation[] operations) {
		this.children = new Node[operations.length];
		for (int i = 0; i < operations.length; i++) {
			this.children[i] = new Node(operations[i].applyTo(this.value), operations[i], this);
		}
	}
}
