package homeposting.app.common.data.util;

import org.ocpsoft.logging.Logger;

public class LOG {
	public static void info(Object object, String msg){
		Logger.getLogger(object.getClass()).info(msg);
	}

	public static void error(Object object, String msg, Throwable t){
		Logger.getLogger(object.getClass()).error(msg,t);
	}

	public static void error(Class<?> clazz, String msg, Throwable t){
		Logger.getLogger(clazz).error(msg,t);
	}
	
	public static void info(String msg){
		StackTraceElement[] stack = Thread.currentThread().getStackTrace();
		StackTraceElement element = stack[2];
		try {
			Logger.getLogger(Class.forName(element.getClassName())).info(msg);
		} catch (ClassNotFoundException e) {
			error(LOG.class,"Nie można było zalogowac dla klasy: " + element.getClassName(), e);
		}
	}
}
