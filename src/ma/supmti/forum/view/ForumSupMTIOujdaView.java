package ma.supmti.forum.view;

import org.apache.pivot.beans.BXMLSerializer;
import org.apache.pivot.wtk.Window;

import ma.jberrich.view.common.GenericLoadView;

public class ForumSupMTIOujdaView extends GenericLoadView {

	private String resource = "/ma/supmti/forum/view/bxml/ForumSupMTIOujdaWindow.bxml";

	@Override
	protected void initialize() {
		this.initWindow(this.getClass().getResource(resource), ForumSupMTIOujdaWindow.class);
	}

	@Override
	protected void open(Window oWindow) {
		try {
        	BXMLSerializer bxmlSerializer = new BXMLSerializer();
			LoadDataSheetView sheet = (LoadDataSheetView) bxmlSerializer.readObject(LoadDataSheetView.class, "/ma/supmti/forum/view/bxml/LoadDataSheetView.bxml");
			sheet.setWindow((ForumSupMTIOujdaWindow) getWindow());
			sheet.open(oWindow);
    	} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void close() {
		System.out.println("Save data");
		((ForumSupMTIOujdaWindow) getWindow()).save();
	}

}
