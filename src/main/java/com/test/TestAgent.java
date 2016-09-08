package com.test;

public class TestAgent {

	public void runMethod() throws InterruptedException {
		System.out.println("Starting RunMethod");
		Thread.sleep(2000);
		System.out.println("Exiting RunMethod");

	}

	public static void main(String[] args) throws InterruptedException {
		TestAgent testAgent = new TestAgent();
		testAgent.runMethod();
	}
}
