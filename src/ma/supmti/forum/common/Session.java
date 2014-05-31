package ma.supmti.forum.common;

import java.util.HashMap;
import java.util.Map;

public class Session {
	
	private static final Session instance = new Session();
	
	private static Map<String, Object> objects = null;
	
	public static Session getInstance() {
		return instance;
	}
	
	public void init() {
		objects = new HashMap<String, Object>();
	}
	
	public Object getAttribute(String name) {
		return objects.get(name);
	}
	
	public void setAttribute(String name, Object value) {
		objects.put(name, value);
	}
	
	public void invalidate() {
		objects.clear();
	}
	
	public boolean hasAttributeName(String name) {
		return objects.containsKey(name);
	}

}
