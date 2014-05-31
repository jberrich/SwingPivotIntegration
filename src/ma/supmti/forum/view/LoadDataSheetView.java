package ma.supmti.forum.view;

import java.net.URL;

import ma.supmti.forum.view.task.LoadDataTask;

import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.util.concurrent.Task;
import org.apache.pivot.util.concurrent.TaskListener;
import org.apache.pivot.wtk.Display;
import org.apache.pivot.wtk.Sheet;
import org.apache.pivot.wtk.SheetCloseListener;
import org.apache.pivot.wtk.TaskAdapter;
import org.apache.pivot.wtk.Window;

public class LoadDataSheetView extends Sheet implements Bindable {

	private ForumSupMTIOujdaWindow window = null;
	
	public void initialize(Map<String, Object> namespace, URL location, Resources resources) {
		
	}

	public void setWindow(ForumSupMTIOujdaWindow window) {
		this.window = window;
	}
	
	public void open(Display display, Window owner,	SheetCloseListener sheetCloseListenerArgument) {
		super.open(display, owner, sheetCloseListenerArgument);
		runTask();
	}

	private void runTask() {
		LoadDataTask task = new LoadDataTask(window);
		TaskListener<Boolean> taskListener = new TaskListener<Boolean>() {
			
			public void taskExecuted(Task<Boolean> task) {
				window.showContent();
				close();
			}

			public void executeFailed(Task<Boolean> task) {
				
			}
			
		};
		task.execute(new TaskAdapter<Boolean>(taskListener));
	}

}
