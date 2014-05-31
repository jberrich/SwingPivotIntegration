package ma.jberrich.view.common;

import java.net.URL;

import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.Window;

public abstract class GenericWindow extends Window implements Bindable {

	public abstract void initialize(Map<String, Object> namespace, URL location, Resources resources);

}
