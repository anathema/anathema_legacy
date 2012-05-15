package org.jmock.example.announcer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

//Nat Pryce's Announcer, taken from jmock examples: http://nat.truemesh.com/archives/000710.html
public class Announcer<T> {
	private final T proxy;
	private final List<T> listeners = new ArrayList<T>();
	
	
	public Announcer(Class<? extends T> listenerType) {
		proxy = listenerType.cast(Proxy.newProxyInstance(
			listenerType.getClassLoader(), 
			new Class<?>[]{listenerType}, 
			new InvocationHandler() {
				public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
					announce(method, args);
					return null;
				}
			}));
	}
	
	public void addListener(T listener) {
		listeners.add(listener);
	}
	
	public void removeListener(T listener) {
		listeners.remove(listener);
	}
	
	public T announce() {
		return proxy;
	}
	
	private void announce(Method m, Object[] args) {
		try {
			for (T listener : new ArrayList<T>(listeners)) {
				m.invoke(listener, args);
			}
		}
		catch (IllegalAccessException e) {
			throw new IllegalArgumentException("could not invoke listener", e);
		}
		catch (InvocationTargetException e) {
			Throwable cause = e.getCause();
			
			if (cause instanceof RuntimeException) {
				throw (RuntimeException)cause;
			} 
			else if (cause instanceof Error) {
				throw (Error)cause;
			}
			else {
				throw new UnsupportedOperationException("listener threw exception", cause);
			}
		}
	}
	
	public static <T> Announcer<T> to(Class<? extends T> listenerType) {
		return new Announcer<T>(listenerType);
	}
}