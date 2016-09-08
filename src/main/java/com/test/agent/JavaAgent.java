package com.test.agent;

import java.lang.instrument.Instrumentation;

/**
 * Created by swaraj on 9/8/16.
 */
public class JavaAgent {

	public static void premain(String args, Instrumentation instrumentation) {
		instrumentation.addTransformer(new JavaAgentTransformer());
	}

	public static void agentmain(String args, Instrumentation instrumentation) {
		premain(args, instrumentation);
	}

}
