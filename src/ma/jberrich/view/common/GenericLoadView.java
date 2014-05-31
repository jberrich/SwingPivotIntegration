package ma.jberrich.view.common;

import java.io.IOException;
import java.net.URL;

import org.apache.pivot.beans.BXMLSerializer;
import org.apache.pivot.serialization.SerializationException;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.ApplicationContext;
import org.apache.pivot.wtk.Window;
import org.apache.pivot.wtk.WindowStateListener;

public abstract class GenericLoadView extends ApplicationContext {
	
	private Window window = null;
	private ApplicationContext.DisplayHost displayHost = null;

	static {
		createTimer();
	}

	public ApplicationContext.DisplayHost getDisplayHost() {
		if (displayHost == null) {
			displayHost = new ApplicationContext.DisplayHost();
		}
		return displayHost;
	}

	public void init() {
		displays.add(displayHost.getDisplay());
		initialize();
        window.getWindowStateListeners().add(
        		new WindowStateListener.Adapter() {
					
					public void windowOpened(Window oWindow) {
				    	open(oWindow);
					}
	
				}
        );
		displayWindow();
	}

	public void initWindow(URL location, Class<?> type, Resources resources) {
		BXMLSerializer bxmlSerializer = new BXMLSerializer();
		try {
			window = (GenericWindow) type.cast(bxmlSerializer.readObject(location, resources));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SerializationException e) {
			e.printStackTrace();
		}
	}

	public void initWindow(URL location, Class<?> type) {
		initWindow(location, type, null);
	}

	protected abstract void initialize();
	protected abstract void open(Window oWindow);
	public abstract void close();

	private void displayWindow() {
		window.open(displayHost.getDisplay());
	}
	
	public GenericWindow getWindow() {
		return GenericWindow.class.cast(window);
	}

}
