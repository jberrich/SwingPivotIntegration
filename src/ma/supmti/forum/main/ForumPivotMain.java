package ma.supmti.forum.main;

import ma.supmti.forum.view.ForumSupMTIOujdaWindow;
import ma.supmti.forum.view.LoadDataSheetView;

import org.apache.pivot.beans.BXMLSerializer;
import org.apache.pivot.collections.Map;
import org.apache.pivot.wtk.Application;
import org.apache.pivot.wtk.DesktopApplicationContext;
import org.apache.pivot.wtk.Display;
import org.apache.pivot.wtk.Window;
import org.apache.pivot.wtk.WindowStateListener;

public class ForumPivotMain implements Application {

	private String resource = "/ma/supmti/forum/view/bxml/ForumSupMTIOujdaWindow.bxml"; 
	
	private ForumSupMTIOujdaWindow window = null;
	
	@Override
	public void resume() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean shutdown(boolean optional) throws Exception {
		if(window != null) {
			window.close();
		}
		return false;
	}

	@Override
	public void startup(Display display, Map<String, String> properties)
			throws Exception {
		BXMLSerializer bxmlSerializer = new BXMLSerializer();
        window = (ForumSupMTIOujdaWindow) bxmlSerializer.readObject(ForumPivotMain.class, resource);
        window.getWindowStateListeners().add(
        		new WindowStateListener.Adapter() {
					
					public void windowOpened(Window oWindow) {
				    	try {
				        	BXMLSerializer bxmlSerializer = new BXMLSerializer();
							LoadDataSheetView sheet = (LoadDataSheetView) bxmlSerializer.readObject(LoadDataSheetView.class, "/ma/supmti/forum/view/bxml/LoadDataSheetView.bxml");
							sheet.setWindow(window);
							sheet.open(oWindow);
				    	} catch (Exception e) {
							e.printStackTrace();
						}
					}
					
					@Override
					public void windowClosed(Window cWindow, Display display, Window owner) {
						window.save();
					}
	
				}
        );
        window.setIcon("/ma/supmti/forum/view/img/logo.png");
        window.open(display);
        System.out.println(String.format("W:%d - H:%d", display.getWidth(), display.getHeight()));
	}

	@Override
	public void suspend() throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DesktopApplicationContext.main(ForumPivotMain.class, args);
	}

}
