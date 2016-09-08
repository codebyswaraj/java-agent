package com.test.agent;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

/**
 * Created by swaraj on 9/8/16.
 */
public class JavaAgentTransformer implements ClassFileTransformer {

	public byte[] transform(ClassLoader classLoader, String className, Class<?> aClass,
			ProtectionDomain protectionDomain, byte[] bytes) throws IllegalClassFormatException {
		System.out.println(className);
		if (className.equals("com/test/TestAgent")) {
			System.out.println("Starting Instrumentation");
			ClassPool classPool = ClassPool.getDefault();
			try {
				className = className.replace("/", ".");
				CtClass ctClass = classPool.get(className);
				System.out.println(ctClass.getSimpleName());
				for (CtMethod method : ctClass.getDeclaredMethods()) {
					method.addLocalVariable("startTime", CtClass.longType);
					method.insertBefore("startTime=System.currentTimeMillis();");
					String message = "Total Time Taken in Class :" + ctClass.getSimpleName() + "Method :- "
							+ method.getName() + " is ";
					String finalStr = "System.out.println(" + "\"" + message + "\""
							+ "+(System.currentTimeMillis()-startTime)+" + "\" ms\");";
					method.insertAfter(finalStr);
				}
				bytes = ctClass.toBytecode();
				ctClass.detach();
				System.out.println("Instrumentation Complete");
			} catch (Exception ex) {
				System.out.println("Exception: " + ex);
				ex.printStackTrace();
			}
		}
		return bytes;
	}
}
