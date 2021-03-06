/**
 * Operation.java
 *
 * CS4341: Project 1
 * Group: Zachary Robbins, Kyle McCormick, Elijah Gonzalez, Peter Raspe
 */

public class Operation {
	public final Operator type;
	public final int value;

	public Operation(Operator type, int value) {
		this.type = type;  
		this.value = value;
	}

	public int applyTo(int operand) {
		switch (this.type) {
		case ADD: return operand + this.value;
		case SUBTRACT: return operand - this.value;
		case MULTIPLY: return operand * this.value;
		case DIVIDE: return operand / this.value;
		case POWER: return (int) Math.pow(operand, this.value);
		}
		throw new IllegalStateException("bad operator: " + this.type);
	}

	@Override
	public String toString() {
		return this.type + " " + this.value;
	}
}
