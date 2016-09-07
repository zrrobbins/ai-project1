/**
 * IterativeDeepeningSearch.java
 *
 * CS4341: Project 1
 * Group: Zachary Robbins, Kyle McCormick, Elijah Gonzalez, Peter Raspe
 */

import java.util.Stack;
import java.util.Arrays;

public class IterativeDeepeningSearch extends Search {

	public IterativeDeepeningSearch(int startValue, int targetValue, double timeLimit, Operation[] operations) {
		super(startValue, targetValue, timeLimit, operations);
	}

	@Override
	public void performSearch() {
		long startTimeMillis = System.currentTimeMillis();
		int maxDepth = 1;
		int lastVal = 0;
		int nodesExpanded = 0;
		int stepsRequired = 0;
		long elapsedTime = 0;

		while(System.currentTimeMillis() - startTimeMillis < timeLimit * 1000){
			Node startNode = new Node(startValue, null, null);
			Node currentNode = startNode;
			int currentDepth = 0;

			//The frontier is the the stack for nodes to explore
			Stack<Node> frontier = new Stack<Node>();

			frontier.push(startNode);

			//Runs a while loop until the frontier is exhausted
			while(!frontier.isEmpty() && currentNode.value != targetValue && (System.currentTimeMillis() - startTimeMillis < timeLimit * 1000) ){
				//This function checks how deep the current node is. If it is deeper than the max depth then we simply dont expand any further and pop the next node off the frontier
				currentDepth = currentNode.depth;//countPathDepth(currentNode, startNode);
				if(currentDepth <= maxDepth){
					currentNode.expand(operations);
					nodesExpanded += 1;
					for(Node node: currentNode.children){
						frontier.push(node);
					}
					currentNode = frontier.pop();
				}
				else{
					currentNode = frontier.pop();
				}
			}

			elapsedTime = System.currentTimeMillis() - startTimeMillis;
			lastVal = currentNode.value;

			//If we reach the target node, then we print out the path from the start node to the target
			if(currentNode.value == targetValue){
				Stack<String> path = new Stack<String>();

				while (currentNode != startNode) {
					path.push(currentNode.parent.value + " " + currentNode.operation + " = " + currentNode.value);
					currentNode = currentNode.parent;
				}

				stepsRequired = path.size();
				while (!path.empty()) {
					System.out.println(path.pop());
				}	

				break;
			}

			maxDepth++;
		}

		System.out.println("Error: " + (targetValue - lastVal));
		System.out.println("Number of steps required: " + stepsRequired);
		System.out.println("Search required: " + elapsedTime + " milliseconds");
		System.out.println("Nodes expanded: " + nodesExpanded);
		System.out.println("Maximum search depth: " + maxDepth);
	}
}

	