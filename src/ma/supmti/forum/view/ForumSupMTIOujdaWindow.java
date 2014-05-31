package ma.supmti.forum.view;

import java.io.File;
import java.net.URL;
import java.util.StringTokenizer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import ma.jberrich.view.common.GenericWindow;
import ma.supmti.forum.common.RegexValidator;
import ma.supmti.forum.data.jaxb.Atelier;
import ma.supmti.forum.data.jaxb.Fatelier;
import ma.supmti.forum.data.jaxb.Forum;
import ma.supmti.forum.data.jaxb.Inscrit;
import ma.supmti.forum.data.jaxb.ObjectFactory;
import ma.supmti.forum.view.task.LoadDataTask;

import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.HashMap;
import org.apache.pivot.collections.List;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.util.concurrent.TaskExecutionException;
import org.apache.pivot.wtk.Alert;
import org.apache.pivot.wtk.ApplicationContext;
import org.apache.pivot.wtk.Border;
import org.apache.pivot.wtk.BoxPane;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.Button.State;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.ButtonStateListener;
import org.apache.pivot.wtk.Checkbox;
import org.apache.pivot.wtk.Component;
import org.apache.pivot.wtk.ComponentStateListener;
import org.apache.pivot.wtk.FillPane;
import org.apache.pivot.wtk.ImageView;
import org.apache.pivot.wtk.Label;
import org.apache.pivot.wtk.MessageType;
import org.apache.pivot.wtk.PushButton;
import org.apache.pivot.wtk.TabPane;
import org.apache.pivot.wtk.TableView;
import org.apache.pivot.wtk.TableViewRowListener;
import org.apache.pivot.wtk.TableViewSelectionListener;
import org.apache.pivot.wtk.TextInput;
import org.apache.pivot.wtk.TextInputListener;
import org.apache.pivot.wtk.Window;
import org.apache.pivot.wtk.content.TableViewRowEditor;
import org.apache.pivot.wtk.media.Image;
import org.apache.pivot.wtk.skin.CardPaneSkin;
import org.apache.pivot.wtk.validation.IntValidator;

public class ForumSupMTIOujdaWindow extends GenericWindow {

	private java.util.List<java.util.Map<String, Object>> jRTableData = null;
	private int rTotalConfirmation = 0;
	
	private java.util.List<java.util.Map<String, Object>> jITableData = null;
	private int iTotalConfirmation = 0;

	private TabPane    fTabPane            = null;
	
	private TableView  rechercheTable      = null;
	private TextInput  rNomTextInput       = null;
	private TextInput  rPrenomTextInput    = null;
	private TextInput  rEmailTextInput     = null;
	private TextInput  rTelephoneTextInput = null;
	private PushButton chercherButton      = null;
	private PushButton newButton           = null;
	private Label      rTotalLabel         = null; 
	
	private TableView  inscriptionTable    = null;
	private PushButton ajouterButton       = null;
	private Label      iTotalLabel         = null;
	private TextInput  iNomTextInput       = null;
	private TextInput  iPrenomTextInput    = null;
	private TextInput  iEmailTextInput     = null;
	private TextInput  iTelephoneTextInput = null;
	private TextInput  iCodeTextInput      = null;
	private Checkbox   a1Checkbox          = null;
	private Checkbox   a2Checkbox          = null;
	private Checkbox   a3Checkbox          = null;
	private Checkbox   a4Checkbox          = null;

	private PushButton rSaveButton         = null;
	private PushButton iSaveButton         = null;
	
	public void initialize(Map<String, Object> namespace, URL location, Resources resources) {
		fTabPane = TabPane.class.cast(namespace.get("fTabPane"));
		
		rechercheTable = TableView.class.cast(namespace.get("rechercheTable"));
		rNomTextInput = TextInput.class.cast(namespace.get("rNomTextInput"));
		rPrenomTextInput = TextInput.class.cast(namespace.get("rPrenomTextInput"));
		rEmailTextInput = TextInput.class.cast(namespace.get("rEmailTextInput"));
		rTelephoneTextInput = TextInput.class.cast(namespace.get("rTelephoneTextInput"));
		chercherButton = PushButton.class.cast(namespace.get("chercherButton"));
		newButton = PushButton.class.cast(namespace.get("newButton"));
		rTotalLabel = Label.class.cast(namespace.get("rTotalLabel"));
		
		inscriptionTable = TableView.class.cast(namespace.get("inscriptionTable"));
		ajouterButton = PushButton.class.cast(namespace.get("ajouterButton"));
		iTotalLabel = Label.class.cast(namespace.get("iTotalLabel"));
		iNomTextInput = TextInput.class.cast(namespace.get("iNomTextInput"));
		iPrenomTextInput = TextInput.class.cast(namespace.get("iPrenomTextInput"));
		iEmailTextInput = TextInput.class.cast(namespace.get("iEmailTextInput"));
		iTelephoneTextInput = TextInput.class.cast(namespace.get("iTelephoneTextInput"));
		iCodeTextInput = TextInput.class.cast(namespace.get("iCodeTextInput"));
		a1Checkbox = Checkbox.class.cast(namespace.get("a1Checkbox"));
		a2Checkbox = Checkbox.class.cast(namespace.get("a2Checkbox"));
		a3Checkbox = Checkbox.class.cast(namespace.get("a3Checkbox"));
		a4Checkbox = Checkbox.class.cast(namespace.get("a4Checkbox"));

		rSaveButton = PushButton.class.cast(namespace.get("rSaveButton"));
		iSaveButton = PushButton.class.cast(namespace.get("iSaveButton"));
		
		init();
	}
	
	private void init() {
		initFTabPane();
		
		initRechercheTable();
		initRechercheTextInput();
		initRechercheButton();
		initRechercheLabel();
		
		initInscriptionTable();
		initInscriptionButton();
		initInscriptionLabel();
		initInscriptionTextInput();
		
		initSaveButton();
	}
	
	private void initFTabPane() {
		fTabPane.setVisible(false);		
	}
	
	private FillPane getImageView(String key) {
		FillPane fillpane = new FillPane();
		
		Border border = new Border();
		border.getStyles().put("backgroundColor", "#DDDCD5");
		border.getStyles().put("color", "#999999");

		BoxPane boxpane = new BoxPane();
		boxpane.getStyles().put("horizontalAlignment", "center");
		boxpane.getStyles().put("verticalAlignment", "center");
		
		ImageView imageview = new ImageView();
		imageview.setImageKey(key);
		imageview.getStyles().put("fill"               , "false");
		imageview.getStyles().put("preserveAspectRatio", "false");
		
		boxpane.add(imageview);
		border.setContent(boxpane);
		fillpane.add(border);
		return fillpane;
	}
	
	private void updateUserRechercheState(Boolean state, int index) {
		try {
			Map<String, Object> tableRowData = (Map<String, Object>) rechercheTable.getTableData().get(index);
			java.util.Map<String, Object> jRTableRowData = null;
			int code = (int) tableRowData.get("code");			
			for(int i=0; i<jRTableData.size(); i++) {
				java.util.Map<String, Object> rowData = jRTableData.get(i);
				if(code == (int) rowData.get("code")) {
					jRTableRowData = rowData;
					break;
				}
			}
			tableRowData.put("state", state);			
			jRTableRowData.put("state", state);
			Image acceptedUser = Image.load(ForumSupMTIOujdaWindow.class.getResource("/ma/supmti/forum/view/img/pre.png"));
			Image canceledUser = Image.load(ForumSupMTIOujdaWindow.class.getResource("/ma/supmti/forum/view/img/abs.png"));
			int confirmation = 0;
			if(state) {
				tableRowData.put("user", acceptedUser);
				jRTableRowData.put("user", acceptedUser);
				confirmation = +1;
			}else {
				tableRowData.put("user", canceledUser);
				jRTableRowData.put("user", canceledUser);
				confirmation = -1;
			}
			int rTConfirmation = Integer.parseInt(rTotalLabel.getText().trim()) + confirmation;
			rTotalLabel.setText(String.format("%d", rTConfirmation));
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	private void updateAtelierInscriptionState(String key, Boolean state, int index) {
		try {
			Map<String, Object> tableRowData = (Map<String, Object>) inscriptionTable.getTableData().get(index);
			java.util.Map<String, Object> jITableRowData = null;
			int code = (int) tableRowData.get("code");			
			for(int i=0; i<jITableData.size(); i++) {
				java.util.Map<String, Object> rowData = jITableData.get(i);
				if(code == (int) rowData.get("code")) {
					jITableRowData = rowData;
					break;
				}
			}
			tableRowData.put(key+"state", state);			
			jITableRowData.put(key+"state", state);
			String acceptedPath = "/ma/supmti/forum/view/img/accept.png";
			String canceledPath = "/ma/supmti/forum/view/img/cancel.png";
			Image acceptedAtelier = Image.load(LoadDataTask.class.getResource(acceptedPath));
			Image canceledAtelier = Image.load(LoadDataTask.class.getResource(canceledPath));			
			if(state) {
				tableRowData.put(key, acceptedAtelier);
				jITableRowData.put(key, acceptedPath);
			}else {
				tableRowData.put(key, canceledAtelier);
				jITableRowData.put(key, canceledPath);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	private FillPane getUserView(boolean state, final int index) {
		FillPane fillpane = new FillPane();
		
		Border border = new Border();
		border.getStyles().put("backgroundColor", "#BBEC9A");
		border.getStyles().put("color", "#000000");

		BoxPane boxpane = new BoxPane();
		boxpane.getStyles().put("horizontalAlignment", "center");
		boxpane.getStyles().put("verticalAlignment", "center");
		
		Checkbox checkbox = new Checkbox();
		checkbox.setSelected(state);
		checkbox.getButtonStateListeners().add(
				new ButtonStateListener() {
					
					public void stateChanged(Button button, State bState) {
						updateUserRechercheState(button.isSelected(), index);
					}
					
				}
		);
		
		boxpane.add(checkbox);
		border.setContent(boxpane);
		fillpane.add(border);
		return fillpane;
	}
	
	private FillPane getAtelierView(final String key, boolean state, final int index) {	
		FillPane fillpane = new FillPane();
		
		Border border = new Border();
		border.getStyles().put("backgroundColor", "#BBEC9A");
		border.getStyles().put("color", "#000000");

		BoxPane boxpane = new BoxPane();
		boxpane.getStyles().put("horizontalAlignment", "center");
		boxpane.getStyles().put("verticalAlignment", "center");
		
		Checkbox checkbox = new Checkbox();
		checkbox.setSelected(state);
		checkbox.getButtonStateListeners().add(
				new ButtonStateListener() {
					
					public void stateChanged(Button button, State bState) {
						updateAtelierInscriptionState(key, button.isSelected(), index);
					}
					
				}
		);
		
		boxpane.add(checkbox);
		border.setContent(boxpane);
		fillpane.add(border);
		return fillpane;
	}
	
	private TableViewRowEditor getRechercheTableViewRowEditor(boolean state, int index) {
		TableViewRowEditor tableViewRowEditor = new TableViewRowEditor();
        tableViewRowEditor.setEditEffect(CardPaneSkin.SelectionChangeEffect.HORIZONTAL_SLIDE);
        tableViewRowEditor.getCellEditors().put("a1",   getImageView("a1"));
        tableViewRowEditor.getCellEditors().put("a2",   getImageView("a2"));
        tableViewRowEditor.getCellEditors().put("a3",   getImageView("a3"));
        tableViewRowEditor.getCellEditors().put("a4",   getImageView("a4"));
        tableViewRowEditor.getCellEditors().put("user", getUserView(state, index));
        return tableViewRowEditor;
	}
	
	private void setTextInputEditorStyles(StyleDictionary styles) {
		styles.put("backgroundColor"         , "#BBEC9A");
		styles.put("borderColor"             , "#000000");
		styles.put("color"                   , "#15428B");
		styles.put("selectionBackgroundColor", "#FDEB9F");
		styles.put("selectionColor"          , "#7B572A");
		styles.put("invalidBackgroundColor"  , "#EA8E93");
		styles.put("invalidColor"            , "#15428B");
	}
	
	private void textInputValidChanged(TextInput textInput) {
		if(textInput.isTextValid()) {
			ajouterButton.setEnabled(true);
		}else {
			ajouterButton.setEnabled(false);
		}		
	}
	
	private void setTextInputEditorListeners(TextInput textInput) {
		textInput.getTextInputListeners().add(
				new TextInputListener.Adapter() {
					
					public void textValidChanged(TextInput textInput) {
						textInputValidChanged(textInput);
					}
												
				}
		);
		textInput.getComponentStateListeners().add(
				new ComponentStateListener.Adapter() {

					public void focusedChanged(Component component, Component obverseComponent) {
						if (component.isFocused() && !ajouterButton.isEnabled()) {
							ajouterButton.setEnabled(true);
							
						}		
					}

				}
		);
	}
	
	private TextInput getInscriptionTextInput(String key) {
		TextInput textInput = new TextInput();
		textInput.setTextKey(key);
		setTextInputEditorStyles(textInput.getStyles());
		RegexValidator validator = null;
		if(key.equals("nom")) {
			validator = new RegexValidator("forum.validator.nom.REGEX");	
		}else if(key.equals("prenom")) {
			validator = new RegexValidator("forum.validator.prenom.REGEX");			
		}else if(key.equals("email")) {
			validator = new RegexValidator("forum.validator.email.REGEX");			
		}else if(key.equals("telephone")) {
			validator = new RegexValidator("forum.validator.telephone.REGEX");			
		}
		if(validator != null) {
			textInput.setValidator(validator);
		}
		return textInput;
	}
	
	private TableViewRowEditor getInscriptionTableViewRowEditor(boolean a1state, 
			                                                    boolean a2state, 
			                                                    boolean a3state, 
			                                                    boolean a4state, int index) {
		TableViewRowEditor tableViewRowEditor = new TableViewRowEditor();
        tableViewRowEditor.setEditEffect(CardPaneSkin.SelectionChangeEffect.HORIZONTAL_SLIDE);
        tableViewRowEditor.getCellEditors().put("user", getImageView("user"));
        
        tableViewRowEditor.getCellEditors().put("nom", getInscriptionTextInput("nom"));
        tableViewRowEditor.getCellEditors().put("prenom", getInscriptionTextInput("prenom"));
        tableViewRowEditor.getCellEditors().put("email", getInscriptionTextInput("email"));
        tableViewRowEditor.getCellEditors().put("telephone", getInscriptionTextInput("telephone"));

        tableViewRowEditor.getCellEditors().put("a1", getAtelierView("a1", a1state, index));
        tableViewRowEditor.getCellEditors().put("a2", getAtelierView("a2", a2state, index));
        tableViewRowEditor.getCellEditors().put("a3", getAtelierView("a3", a3state, index));
        tableViewRowEditor.getCellEditors().put("a4", getAtelierView("a4", a4state, index));
        return tableViewRowEditor;
	}
	
	private void initRechercheTable() {
		rechercheTable.getColumns().get(0).getHeaderDataRenderer().getStyles().put("horizontalAlignment", "center");
		rechercheTable.getColumns().get(0).getHeaderDataRenderer().getStyles().put("verticalAlignment", "center");
		int uWidth = 20;
		int cWidth = 40;
		int aWidth = 60;
		rechercheTable.getColumns().get(0).setMaximumWidth(uWidth);
		rechercheTable.getColumns().get(0).setMinimumWidth(uWidth);
		rechercheTable.getColumns().get(1).setMaximumWidth(cWidth);
		rechercheTable.getColumns().get(1).setMinimumWidth(cWidth);
		rechercheTable.getColumns().get(4).setMaximumWidth(aWidth);
		rechercheTable.getColumns().get(4).setMinimumWidth(aWidth);
		rechercheTable.getColumns().get(5).setMaximumWidth(aWidth);
		rechercheTable.getColumns().get(5).setMinimumWidth(aWidth);
		rechercheTable.getColumns().get(6).setMaximumWidth(aWidth);
		rechercheTable.getColumns().get(6).setMinimumWidth(aWidth);
		rechercheTable.getColumns().get(7).setMaximumWidth(aWidth);
		rechercheTable.getColumns().get(7).setMinimumWidth(aWidth);

		rechercheTable.getColumns().get(10).setMaximumWidth(0);
		rechercheTable.getColumns().get(10).setMinimumWidth(0);
		
		rechercheTable.getTableViewSelectionListeners().add(
				new TableViewSelectionListener.Adapter() {
					
					public void selectedRowChanged(TableView tableView,	Object previousSelectedRow) {
						int index = tableView.getSelectedIndex();
						if(index != -1) {
							Map<String, Object> tableRowData = (Map<String, Object>) tableView.getTableData().get(index);
							rechercheTable.setRowEditor(getRechercheTableViewRowEditor((boolean) tableRowData.get("state"), index));							
						}
					}
					
				}
		);		
	}
	
	private void initInscriptionTable() {
		inscriptionTable.getColumns().get(0).getHeaderDataRenderer().getStyles().put("horizontalAlignment", "center");
		inscriptionTable.getColumns().get(0).getHeaderDataRenderer().getStyles().put("verticalAlignment", "center");
		int uWidth = 20;
		int cWidth = 40;
		int aWidth = 60;
		inscriptionTable.getColumns().get(0).setMaximumWidth(uWidth);
		inscriptionTable.getColumns().get(0).setMinimumWidth(uWidth);
		inscriptionTable.getColumns().get(1).setMaximumWidth(cWidth);
		inscriptionTable.getColumns().get(1).setMinimumWidth(cWidth);
		inscriptionTable.getColumns().get(4).setMaximumWidth(aWidth);
		inscriptionTable.getColumns().get(4).setMinimumWidth(aWidth);
		inscriptionTable.getColumns().get(5).setMaximumWidth(aWidth);
		inscriptionTable.getColumns().get(5).setMinimumWidth(aWidth);
		inscriptionTable.getColumns().get(6).setMaximumWidth(aWidth);
		inscriptionTable.getColumns().get(6).setMinimumWidth(aWidth);
		inscriptionTable.getColumns().get(7).setMaximumWidth(aWidth);
		inscriptionTable.getColumns().get(7).setMinimumWidth(aWidth);

		inscriptionTable.getColumns().get(10).setMaximumWidth(0);
		inscriptionTable.getColumns().get(10).setMinimumWidth(0);
		inscriptionTable.getColumns().get(11).setMaximumWidth(0);
		inscriptionTable.getColumns().get(11).setMinimumWidth(0);
		inscriptionTable.getColumns().get(12).setMaximumWidth(0);
		inscriptionTable.getColumns().get(12).setMinimumWidth(0);
		inscriptionTable.getColumns().get(13).setMaximumWidth(0);
		inscriptionTable.getColumns().get(13).setMinimumWidth(0);
		
		inscriptionTable.getTableViewSelectionListeners().add(
				new TableViewSelectionListener.Adapter() {
					
					public void selectedRowChanged(TableView tableView,	Object previousSelectedRow) {
						int index = tableView.getSelectedIndex();
						if(index != -1) {
							Map<String, Object> tableRowData = (Map<String, Object>) tableView.getTableData().get(index);
							inscriptionTable.setRowEditor(getInscriptionTableViewRowEditor((boolean) tableRowData.get("a1state"), 
																						   (boolean) tableRowData.get("a2state"), 
																						   (boolean) tableRowData.get("a3state"), 
																						   (boolean) tableRowData.get("a4state"), index));							
						}
					}
					
				}
		);
        inscriptionTable.getTableViewRowListeners().add(
        		new TableViewRowListener.Adapter(){
        			
        			private void restartRowEdit(TableView tableView,int index, int columnIndex) {
						tableView.clearSelection();
						if(tableView.getRowEditor().isEditing()) {
							try {
								tableView.getRowEditor().endEdit(true);
							} catch(Exception e) {
								
							}							
						}
						tableView.setSelectedIndex(columnIndex);
						tableView.getRowEditor().beginEdit(tableView, index, columnIndex);
        			}
        			
        			private void updateJITableData(int code, String key, String value) {
						for(int i=0; i<jITableData.size(); i++) {
							java.util.Map<String, Object> rowData = jITableData.get(i);
							if(code == (int) rowData.get("code")) {
								rowData.put(key, value);
								break;
							}
						}
        			}
        			
					public void rowUpdated(TableView tableView, int index) {
						Map<String, Object> tableRowData = (Map<String, Object>) tableView.getTableData().get(index);
						int code = (int) tableRowData.get("code");
						String nom = tableRowData.get("nom").toString().trim().toUpperCase();
						RegexValidator validator = new RegexValidator("forum.validator.nom.REGEX");
						if(validator.isValid(nom)) {
							tableRowData.put("nom", nom);
							updateJITableData(code, "nom", nom);
						}else {
							restartRowEdit(tableView, index, 2);
							return;
						}
						String prenom = getStringCap(tableRowData.get("prenom").toString().trim().toLowerCase());
						validator = new RegexValidator("forum.validator.prenom.REGEX");
						if(validator.isValid(prenom)) {
							tableRowData.put("prenom", prenom);
							updateJITableData(code, "prenom", prenom);
						}else {
							restartRowEdit(tableView, index, 3);
							return;
						}						
						String email = tableRowData.get("email").toString();
						validator = new RegexValidator("forum.validator.email.REGEX");
						if(validator.isValid(email)) {
							tableRowData.put("email", email);
							updateJITableData(code, "email", email);
						}else {
							restartRowEdit(tableView, index, 8);
							return;
						}
						String telephone = tableRowData.get("telephone").toString();
						validator = new RegexValidator("forum.validator.telephone.REGEX");
						if(validator.isValid(telephone)) {
							tableRowData.put("telephone", telephone);
							updateJITableData(code, "telephone", telephone);
						}else {
							restartRowEdit(tableView, index, 9);
							return;
						}
					}
        	
        		}
        );
	}
	
	private void setTextInputFocusedListener(TextInput textInput) {
		textInput.getComponentStateListeners().add(
				new ComponentStateListener.Adapter() {

					public void focusedChanged(Component component, Component obverseComponent) {
						if (component.isFocused()) {
							component.getStyles().put("backgroundColor", "#BBEC9A");
							component.getStyles().put("borderColor",     "#FEAE3B");
						}else {
							component.getStyles().put("backgroundColor", "#C6D8F1");
							component.getStyles().put("borderColor",     "#8AADDB");							
						}
					}

				}
		);
	}
	
	private void initRechercheTextInput() {
		rNomTextInput.setValidator(new RegexValidator("forum.validator.nom.REGEX"));
		rPrenomTextInput.setValidator(new RegexValidator("forum.validator.prenom.REGEX"));
		rEmailTextInput.setValidator(new RegexValidator("forum.validator.email.REGEX"));
		rTelephoneTextInput.setValidator(new RegexValidator("forum.validator.telephone.REGEX"));
		setTextInputFocusedListener(rNomTextInput);
		setTextInputFocusedListener(rPrenomTextInput);
		setTextInputFocusedListener(rEmailTextInput);
		setTextInputFocusedListener(rTelephoneTextInput);
	}
	
	private void initInscriptionTextInput() {
		iNomTextInput.setValidator(new RegexValidator("forum.validator.nom.REGEX"));
		iPrenomTextInput.setValidator(new RegexValidator("forum.validator.prenom.REGEX"));
		iEmailTextInput.setValidator(new RegexValidator("forum.validator.email.REGEX"));
		iTelephoneTextInput.setValidator(new RegexValidator("forum.validator.telephone.REGEX"));
		iCodeTextInput.setValidator(
				new IntValidator() {

					public boolean isValid(String text) {
						if(text.isEmpty()) return true;
						if(super.isValid(text)) {
							int code = Integer.parseInt(text);
							for(int i=0; i<jITableData.size(); i++) {
								java.util.Map<String, Object> rowData = jITableData.get(i);
								if(code == (int) rowData.get("code")) {
									return false;
								}
							}
							return true;
						}
						return false;
					}
				}
		);
		setTextInputFocusedListener(iNomTextInput);
		setTextInputFocusedListener(iPrenomTextInput);
		setTextInputFocusedListener(iEmailTextInput);
		setTextInputFocusedListener(iTelephoneTextInput);
		setTextInputFocusedListener(iCodeTextInput);
		setTextInputEditorListeners(iNomTextInput);
		setTextInputEditorListeners(iPrenomTextInput);
		setTextInputEditorListeners(iEmailTextInput);
		setTextInputEditorListeners(iTelephoneTextInput);
		setTextInputEditorListeners(iEmailTextInput);
	}
	
	private void resetRechercheTextInput() {
		rNomTextInput.removeText(0, rNomTextInput.getText().length());
		rPrenomTextInput.removeText(0, rPrenomTextInput.getText().length());
		rEmailTextInput.removeText(0, rEmailTextInput.getText().length());
		rTelephoneTextInput.removeText(0, rTelephoneTextInput.getText().length());
		rNomTextInput.requestFocus();
	}
	
	private void resetInscriptionForm() {
		iNomTextInput.removeText(0, iNomTextInput.getText().length());
		iPrenomTextInput.removeText(0, iPrenomTextInput.getText().length());
		iEmailTextInput.removeText(0, iEmailTextInput.getText().length());
		iTelephoneTextInput.removeText(0, iTelephoneTextInput.getText().length());
		iCodeTextInput.removeText(0, iCodeTextInput.getText().length());
		a1Checkbox.setSelected(false);
		a2Checkbox.setSelected(false);
		a3Checkbox.setSelected(false);
		a4Checkbox.setSelected(false);
		iNomTextInput.requestFocus();
	}

	private void chercher(String nom, String prenom, String email, String telephone) {
		List<Map<String, Object>> tableData = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> rTableData = getRTableData();
		for(int i=0; i<rTableData.getLength(); i++) {
			Map<String, Object> rowData = rTableData.get(i);
			String rNom = rowData.get("nom").toString().trim().toLowerCase();
			String rPrenom = rowData.get("prenom").toString().trim().toLowerCase();
			String rEmail = rowData.get("email").toString().trim().toLowerCase();
			String rTelephone = rowData.get("telephone").toString().trim().toLowerCase();
			
			boolean bNom = true;
			boolean bPrenom = true;
			boolean bEmail = true;
			boolean bTelephone = true;
			
			if(!nom.equals("") && !rNom.contains(nom)) {
				bNom = false;
			}
			if(!prenom.equals("") && !rPrenom.contains(prenom)) {
				bPrenom = false;
			}
			if(!email.equals("") && !rEmail.contains(email)) {
				bEmail = false;
			}
			if(!telephone.equals("") && !rTelephone.contains(telephone)) {
				bTelephone = false;
			}
			
			if(bNom && bPrenom && bEmail && bTelephone) {
				tableData.add(rowData);				
			}
		}
		if(!tableData.isEmpty()) {
			updateRechercheTableData(tableData);
		}
	}
	
	private void initInscriptionButton() {
		final Window aWindow = this;
		ajouterButton.getButtonPressListeners().add(
				new ButtonPressListener() {
					
					public void buttonPressed(Button button) {
						if(iNomTextInput.getText().isEmpty()    || 
						   iPrenomTextInput.getText().isEmpty() || 
						   iEmailTextInput.getText().isEmpty()  || 
						   iCodeTextInput.getText().isEmpty()   || 
						   (!a1Checkbox.isSelected() && !a2Checkbox.isSelected() && !a3Checkbox.isSelected() && !a4Checkbox.isSelected())) {
							Alert.alert(MessageType.ERROR, "Veuillez saisir les informations manquantes", aWindow);
						}else {
							try {
								List<Map<String, Object>> tableData = (List<Map<String, Object>>) inscriptionTable.getTableData();
								Map<String, Object> mapData = new HashMap<String, Object>();
								java.util.Map<String, Object> jMapData = new java.util.HashMap<String, Object>();
								int code = Integer.parseInt(iCodeTextInput.getText());
								String nom = iNomTextInput.getText().trim().toUpperCase();
								String prenom = getStringCap(iPrenomTextInput.getText().trim().toLowerCase());
								String email = iEmailTextInput.getText();
								String telephone = iTelephoneTextInput.getText();
								
								mapData.put("code",      code);
								mapData.put("nom",       nom);
								mapData.put("prenom",    prenom);
								mapData.put("email",     email);
								mapData.put("telephone", telephone);
								
								jMapData.put("code",      code);
								jMapData.put("nom",       nom);
								jMapData.put("prenom",    prenom);
								jMapData.put("email",     email);
								jMapData.put("telephone", telephone);
								
								String acceptedPath = "/ma/supmti/forum/view/img/accept.png";
								String canceledPath = "/ma/supmti/forum/view/img/cancel.png";

								Image acceptedAtelier = Image.load(ForumSupMTIOujdaWindow.class.getResource(acceptedPath));
								Image canceledAtelier = Image.load(ForumSupMTIOujdaWindow.class.getResource(canceledPath));

								if(a1Checkbox.isSelected()) {
									mapData.put("a1", acceptedAtelier);
									mapData.put("a1state", true);

									jMapData.put("a1", acceptedPath);
									jMapData.put("a1state", true);
								}else {
									mapData.put("a1", canceledAtelier);
									mapData.put("a1state", false);

									jMapData.put("a1", canceledPath);
									jMapData.put("a1state", false);
								}
								if(a2Checkbox.isSelected()) {
									mapData.put("a2", acceptedAtelier);
									mapData.put("a2state", true);

									jMapData.put("a2", acceptedPath);
									jMapData.put("a2state", true);
								}else {
									mapData.put("a2", canceledAtelier);
									mapData.put("a2state", false);

									jMapData.put("a2", canceledPath);
									jMapData.put("a2state", false);
								}
								if(a3Checkbox.isSelected()) {
									mapData.put("a3", acceptedAtelier);
									mapData.put("a3state", true);

									jMapData.put("a3", acceptedPath);
									jMapData.put("a3state", true);
								}else {
									mapData.put("a3", canceledAtelier);
									mapData.put("a3state", false);

									jMapData.put("a3", canceledPath);
									jMapData.put("a3state", false);
								}
								if(a4Checkbox.isSelected()) {
									mapData.put("a4", acceptedAtelier);
									mapData.put("a4state", true);

									jMapData.put("a4", acceptedPath);
									jMapData.put("a4state", true);
								}else {
									mapData.put("a4", canceledAtelier);
									mapData.put("a4state", false);

									jMapData.put("a4", canceledPath);
									jMapData.put("a4state", false);
								}
								Image acceptedUser = Image.load(LoadDataTask.class.getResource("/ma/supmti/forum/view/img/pre.png"));
								mapData.put("user", acceptedUser);
								jMapData.put("user", acceptedUser);
								
								tableData.add(mapData);
								jITableData.add(jMapData);
								initTotalInscription();
								resetInscriptionForm();
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
					
				}
		);
		ajouterButton.setEnabled(false);
	}
	
	private void initRechercheButton() {
		chercherButton.getButtonPressListeners().add(
				new ButtonPressListener() {
					
					public void buttonPressed(Button button) {
						String nom = rNomTextInput.getText().trim().toLowerCase();
						String prenom = rPrenomTextInput.getText().trim().toLowerCase();
						String email = rEmailTextInput.getText().trim().toLowerCase();
						String telephone = rTelephoneTextInput.getText().trim().toLowerCase();
						chercher(nom, prenom, email, telephone);
					}
					
				}
		);
		newButton.getButtonPressListeners().add(
				new ButtonPressListener() {
					
					public void buttonPressed(Button button) {
						resetRechercheTextInput();
						updateRechercheTableData(getRTableData());
					}
					
				}
		);
	}
	
	private List<Map<String, Object>> getRTableData() {
		List<Map<String, Object>> rTableData = new ArrayList<Map<String,Object>>();
		try {
			for(int i=0; i<jRTableData.size(); i++) {
				java.util.Map<String, Object> jMapData = jRTableData.get(i);
				Map<String, Object> mapData = new HashMap<String, Object>();
				mapData.put("code", jMapData.get("code"));
				mapData.put("nom", jMapData.get("nom"));
				mapData.put("prenom", jMapData.get("prenom"));
				mapData.put("email", jMapData.get("email"));
				mapData.put("telephone", jMapData.get("telephone"));
				mapData.put("a1", Image.load(ForumSupMTIOujdaWindow.class.getResource(jMapData.get("a1").toString())));
				mapData.put("a2", Image.load(ForumSupMTIOujdaWindow.class.getResource(jMapData.get("a2").toString())));
				mapData.put("a3", Image.load(ForumSupMTIOujdaWindow.class.getResource(jMapData.get("a3").toString())));
				mapData.put("a4", Image.load(ForumSupMTIOujdaWindow.class.getResource(jMapData.get("a4").toString())));
				mapData.put("user", jMapData.get("user"));
				mapData.put("state", jMapData.get("state"));
				rTableData.add(mapData);
			}
		} catch (TaskExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rTableData;
	}
	
	private void initRechercheLabel() {
		rTotalLabel.setText(String.format("%d", rTotalConfirmation));
	}
	
	private void initInscriptionLabel() {
		iTotalLabel.setText(String.format("%d", iTotalConfirmation));
	}
	
	public void showContent() {
		fTabPane.setVisible(true);
	}

	public void updateRechercheTableData(final List<Map<String, Object>> tableData) {
		if(!rechercheTable.getTableData().isEmpty()) {
			rechercheTable.clearSelection();
			rechercheTable.getTableData().clear();
		}
		rechercheTable.setTableData(tableData);	
	}

	public void updateInscriptionTableData(final List<Map<String, Object>> tableData) {
		if(!inscriptionTable.getTableData().isEmpty()) {
			inscriptionTable.clearSelection();
			inscriptionTable.getTableData().clear();
		}
		inscriptionTable.setTableData(tableData);	
	}
	
	private void initTotalConfirmation() {
		for(int i=0; i<jRTableData.size(); i++) {
			if(Boolean.class.cast(jRTableData.get(i).get("state"))) {
				rTotalConfirmation++;
			}
		}
		initRechercheLabel();
	}
	
	private void initTotalInscription() {
		iTotalConfirmation =jITableData.size();
		initInscriptionLabel();
	}
	
	public void updateRechercheTableData(final List<Map<String, Object>> tableData, final java.util.List<java.util.Map<String, Object>> jTableData) {
		ApplicationContext.queueCallback(
				new Runnable() {
					
					public void run() {						
						if(jRTableData == null) {
							jRTableData = jTableData;
							initTotalConfirmation();
						}
						updateRechercheTableData(tableData);
					}
					
				}
		);
	}
	
	public void updateInscriptionTableData(final List<Map<String, Object>> tableData, final java.util.List<java.util.Map<String, Object>> jTableData) {
		ApplicationContext.queueCallback(
				new Runnable() {
					
					public void run() {						
						if(jITableData == null) {
							jITableData = jTableData;
							initTotalInscription();
						}
						updateInscriptionTableData(tableData);
					}
					
				}
		);
	}

	private String getStringCap(String string) {
		String cap = null;
		StringTokenizer tokenizer = new StringTokenizer(string, " ");
		while(tokenizer.hasMoreTokens()) {
			String prenomToken = tokenizer.nextToken();
		    
		    StringBuilder prenomCap = new StringBuilder(prenomToken);
			prenomCap.replace(0, 1, prenomCap.substring(0, 1).toUpperCase());
		    
		    if(cap == null) {
		    	cap = prenomCap.toString();
		    }else {
			    cap = cap + " " + prenomCap.toString();	
		    }
		}
		return cap;
	}
	
	private void saveRechercheData() {
		try {
			String xmlFilePath = String.format("%s/data/rdata.xml", System.getProperty("user.dir")).replace('\\', '/'); 
			ObjectFactory oFactory = new ObjectFactory();
			JAXBContext context = JAXBContext.newInstance(Forum.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			Forum forum = oFactory.createForum();
			
			// Ateliers :
			forum.setFateliers(oFactory.createFateliers());
			Fatelier a1 = oFactory.createFatelier();
			a1.setId("a1");
			a1.setName("Atelier 1 : Entreprenariat et cr�ation d'entreprise");
			Fatelier a2 = oFactory.createFatelier();
			a2.setId("a2");
			a2.setName("Atelier 2 : Techniques de recherche d'emploi");
			Fatelier a3 = oFactory.createFatelier();
			a3.setId("a3");
			a3.setName("Atelier 3 : Programmation Neuro Linguistique");
			Fatelier a4 = oFactory.createFatelier();
			a4.setId("a4");
			a4.setName("Atelier 4 : Entretiens de stage et de recrutement");
			forum.getFateliers().getFatelier().add(a1);
			forum.getFateliers().getFatelier().add(a2);
			forum.getFateliers().getFatelier().add(a3);
			forum.getFateliers().getFatelier().add(a4);
			
			//Inscrits :
			forum.setInscrits(oFactory.createInscrits());
			for(int i=0; i<jRTableData.size(); i++) {
				java.util.Map<String, Object> bean = jRTableData.get(i); 
				Inscrit inscrit = oFactory.createInscrit();
				inscrit.setCode((int) bean.get("code"));
				inscrit.setConfirmed((Boolean) bean.get("state"));
				inscrit.setNom(bean.get("nom").toString().toUpperCase());
				inscrit.setPrenom(getStringCap(bean.get("prenom").toString().toLowerCase()));				
				inscrit.setEmail(bean.get("email").toString());
				inscrit.setTelephone(bean.get("telephone").toString());
				inscrit.setAteliers(oFactory.createAteliers());
				for(int k=1; k<5; k++) {
					Atelier atelier = oFactory.createAtelier();
					if(bean.get("a"+k).toString().endsWith("accept.png")) {
						switch (k) {
							case 1:
								atelier.setRef(a1);
								break;
							case 2:
								atelier.setRef(a2);
								break;
							case 3:
								atelier.setRef(a3);
								break;
							case 4:
								atelier.setRef(a4);
								break;
						}
						inscrit.getAteliers().getAtelier().add(atelier);
					}
				}
				forum.getInscrits().getInscrit().add(inscrit);
			}
			
			//Save :
			marshaller.marshal(forum, new File(xmlFilePath));
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	private void saveInscriptionData() {
		try {
			String xmlFilePath = String.format("%s/data/idata.xml", System.getProperty("user.dir")).replace('\\', '/'); 
			ObjectFactory oFactory = new ObjectFactory();
			JAXBContext context = JAXBContext.newInstance(Forum.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			Forum forum = oFactory.createForum();
			
			// Ateliers :
			forum.setFateliers(oFactory.createFateliers());
			Fatelier a1 = oFactory.createFatelier();
			a1.setId("a1");
			a1.setName("Atelier 1 : Entreprenariat et cr�ation d'entreprise");
			Fatelier a2 = oFactory.createFatelier();
			a2.setId("a2");
			a2.setName("Atelier 2 : Techniques de recherche d'emploi");
			Fatelier a3 = oFactory.createFatelier();
			a3.setId("a3");
			a3.setName("Atelier 3 : Programmation Neuro Linguistique");
			Fatelier a4 = oFactory.createFatelier();
			a4.setId("a4");
			a4.setName("Atelier 4 : Entretiens de stage et de recrutement");
			forum.getFateliers().getFatelier().add(a1);
			forum.getFateliers().getFatelier().add(a2);
			forum.getFateliers().getFatelier().add(a3);
			forum.getFateliers().getFatelier().add(a4);
			
			//Inscrits :
			forum.setInscrits(oFactory.createInscrits());
			for(int i=0; i<jITableData.size(); i++) {
				java.util.Map<String, Object> bean = jITableData.get(i); 
				Inscrit inscrit = oFactory.createInscrit();
				inscrit.setCode((int) bean.get("code"));
				inscrit.setConfirmed(true);
				inscrit.setNom(bean.get("nom").toString().toUpperCase());
				inscrit.setPrenom(getStringCap(bean.get("prenom").toString().toLowerCase()));
				inscrit.setEmail(bean.get("email").toString());
				inscrit.setTelephone(bean.get("telephone").toString());
				inscrit.setAteliers(oFactory.createAteliers());
				for(int k=1; k<5; k++) {
					Atelier atelier = oFactory.createAtelier();
					if(bean.get("a"+k).toString().endsWith("accept.png")) {
						switch (k) {
							case 1:
								atelier.setRef(a1);
								break;
							case 2:
								atelier.setRef(a2);
								break;
							case 3:
								atelier.setRef(a3);
								break;
							case 4:
								atelier.setRef(a4);
								break;
						}
						inscrit.getAteliers().getAtelier().add(atelier);
					}
				}
				forum.getInscrits().getInscrit().add(inscrit);
			}
			
			//Save :
			marshaller.marshal(forum, new File(xmlFilePath));
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	private void initSaveButton() {
		rSaveButton.getButtonPressListeners().add(
				new ButtonPressListener() {
					
					public void buttonPressed(Button button) {
						saveRechercheData();
					}
					
				}
		);
		iSaveButton.getButtonPressListeners().add(
				new ButtonPressListener() {
					
					public void buttonPressed(Button button) {
						saveInscriptionData();
					}
					
				}
		);
	}
	
	public void save() {
		saveRechercheData();
		saveInscriptionData();
	}

}
