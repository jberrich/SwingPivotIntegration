package ma.supmti.forum.view.task;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import ma.supmti.forum.data.jaxb.Atelier;
import ma.supmti.forum.data.jaxb.Fatelier;
import ma.supmti.forum.data.jaxb.Forum;
import ma.supmti.forum.data.jaxb.Inscrit;
import ma.supmti.forum.view.ForumSupMTIOujdaWindow;

import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.HashMap;
import org.apache.pivot.collections.List;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.concurrent.Task;
import org.apache.pivot.util.concurrent.TaskExecutionException;
import org.apache.pivot.wtk.media.Image;

public class LoadDataTask extends Task<Boolean> {

	private ForumSupMTIOujdaWindow window = null;
	
	public LoadDataTask(ForumSupMTIOujdaWindow window) {
		this.window = window;
	}

	public Boolean execute() throws TaskExecutionException {
		loadRechercheTableData();
		loadInscriptionTableData();
		return true;
	}
	
	private void loadRechercheTableData() {
		List<Map<String, Object>> tableData = new ArrayList<Map<String, Object>>();
		java.util.List<java.util.Map<String, Object>> jTableData = new java.util.ArrayList<java.util.Map<String, Object>>();
		try {
			String rechercheDataFilePath = String.format("%s/data/rdata.xml", System.getProperty("user.dir")).replace('\\', '/');
			JAXBContext	context = JAXBContext.newInstance(Forum.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			Forum forum = (Forum) unmarshaller.unmarshal(new File(rechercheDataFilePath));
			for(int i=0; i<forum.getInscrits().getInscrit().size(); i++) {
				Inscrit inscrit = forum.getInscrits().getInscrit().get(i);
				Map<String, Object> mapData = new HashMap<String, Object>();
				java.util.Map<String, Object> jMapData = new java.util.HashMap<String, Object>();
				mapData.put("code",      inscrit.getCode());
				mapData.put("nom",       inscrit.getNom());
				mapData.put("prenom",    inscrit.getPrenom());
				mapData.put("email",     inscrit.getEmail());
				mapData.put("telephone", inscrit.getTelephone());
				
				jMapData.put("code",      inscrit.getCode());
				jMapData.put("nom",       inscrit.getNom());
				jMapData.put("prenom",    inscrit.getPrenom());
				jMapData.put("email",     inscrit.getEmail());
				jMapData.put("telephone", inscrit.getTelephone());
				
				String acceptedPath = "/ma/supmti/forum/view/img/accept.png";
				String canceledPath = "/ma/supmti/forum/view/img/cancel.png";
				Image acceptedAtelier = Image.load(LoadDataTask.class.getResource(acceptedPath));
				Image canceledAtelier = Image.load(LoadDataTask.class.getResource(canceledPath));

				mapData.put("a1", canceledAtelier);
				mapData.put("a2", canceledAtelier);
				mapData.put("a3", canceledAtelier);
				mapData.put("a4", canceledAtelier);
				
				jMapData.put("a1", canceledPath);
				jMapData.put("a2", canceledPath);
				jMapData.put("a3", canceledPath);
				jMapData.put("a4", canceledPath);
				
				for(int j=0; j<inscrit.getAteliers().getAtelier().size(); j++) {
					Atelier atelier = inscrit.getAteliers().getAtelier().get(j);
					String aCode = Fatelier.class.cast(atelier.getRef()).getId();
					if(aCode.equals("a1")) {
						mapData.put("a1", acceptedAtelier);
						jMapData.put("a1", acceptedPath);
					} else if(aCode.equals("a2")) {
						mapData.put("a2", acceptedAtelier);
						jMapData.put("a2", acceptedPath);
					} else if(aCode.equals("a3")) {
						mapData.put("a3", acceptedAtelier);
						jMapData.put("a3", acceptedPath);
					} else if(aCode.equals("a4")) {
						mapData.put("a4", acceptedAtelier);
						jMapData.put("a4", acceptedPath);
					}
				}
				Image acceptedUser = Image.load(LoadDataTask.class.getResource("/ma/supmti/forum/view/img/pre.png"));
				Image canceledUser = Image.load(LoadDataTask.class.getResource("/ma/supmti/forum/view/img/abs.png"));
				if(inscrit.isConfirmed()) {
					mapData.put("user", acceptedUser);
					mapData.put("state", true);
					jMapData.put("user", acceptedUser);
					jMapData.put("state", true);
				}else {
					mapData.put("user", canceledUser);
					mapData.put("state", false);
					jMapData.put("user", canceledUser);
					jMapData.put("state", false);
				}
				tableData.add(mapData);
				jTableData.add(jMapData);
			}
			window.updateRechercheTableData(tableData, jTableData);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void loadInscriptionTableData() {
		List<Map<String, Object>> tableData = new ArrayList<Map<String, Object>>();
		java.util.List<java.util.Map<String, Object>> jTableData = new java.util.ArrayList<java.util.Map<String, Object>>();
		try {
			String inscriptionDataFilePath = String.format("%s/data/idata.xml", System.getProperty("user.dir")).replace('\\', '/');
			File iFile = new File(inscriptionDataFilePath);
			if(iFile.exists()) {
				JAXBContext	context = JAXBContext.newInstance(Forum.class);
				Unmarshaller unmarshaller = context.createUnmarshaller();
				Forum forum = (Forum) unmarshaller.unmarshal(iFile);
				for(int i=0; i<forum.getInscrits().getInscrit().size(); i++) {
					Inscrit inscrit = forum.getInscrits().getInscrit().get(i);
					Map<String, Object> mapData = new HashMap<String, Object>();
					java.util.Map<String, Object> jMapData = new java.util.HashMap<String, Object>();
					mapData.put("code",      inscrit.getCode());
					mapData.put("nom",       inscrit.getNom());
					mapData.put("prenom",    inscrit.getPrenom());
					mapData.put("email",     inscrit.getEmail());
					mapData.put("telephone", inscrit.getTelephone());
					
					jMapData.put("code",      inscrit.getCode());
					jMapData.put("nom",       inscrit.getNom());
					jMapData.put("prenom",    inscrit.getPrenom());
					jMapData.put("email",     inscrit.getEmail());
					jMapData.put("telephone", inscrit.getTelephone());
					
					String acceptedPath = "/ma/supmti/forum/view/img/accept.png";
					String canceledPath = "/ma/supmti/forum/view/img/cancel.png";
					Image acceptedAtelier = Image.load(LoadDataTask.class.getResource(acceptedPath));
					Image canceledAtelier = Image.load(LoadDataTask.class.getResource(canceledPath));

					mapData.put("a1", canceledAtelier);
					mapData.put("a2", canceledAtelier);
					mapData.put("a3", canceledAtelier);
					mapData.put("a4", canceledAtelier);
					mapData.put("a1state", false);
					mapData.put("a2state", false);
					mapData.put("a3state", false);
					mapData.put("a4state", false);
					
					jMapData.put("a1", canceledPath);
					jMapData.put("a2", canceledPath);
					jMapData.put("a3", canceledPath);
					jMapData.put("a4", canceledPath);
					jMapData.put("a1state", false);
					jMapData.put("a2state", false);
					jMapData.put("a3state", false);
					jMapData.put("a4state", false);
					
					for(int j=0; j<inscrit.getAteliers().getAtelier().size(); j++) {
						Atelier atelier = inscrit.getAteliers().getAtelier().get(j);
						String aCode = Fatelier.class.cast(atelier.getRef()).getId();
						if(aCode.equals("a1")) {
							mapData.put("a1", acceptedAtelier);
							mapData.put("a1state", true);
							jMapData.put("a1", acceptedPath);
							jMapData.put("a1state", true);
						} else if(aCode.equals("a2")) {
							mapData.put("a2", acceptedAtelier);
							mapData.put("a2state", true);
							jMapData.put("a2", acceptedPath);
							jMapData.put("a2state", true);
						} else if(aCode.equals("a3")) {
							mapData.put("a3", acceptedAtelier);
							mapData.put("a3state", true);
							jMapData.put("a3", acceptedPath);
							jMapData.put("a3state", true);
						} else if(aCode.equals("a4")) {
							mapData.put("a4", acceptedAtelier);
							mapData.put("a4state", true);
							jMapData.put("a4", acceptedPath);
							jMapData.put("a4state", true);
						}
					}
					Image acceptedUser = Image.load(LoadDataTask.class.getResource("/ma/supmti/forum/view/img/pre.png"));
					if(inscrit.isConfirmed()) {
						mapData.put("user", acceptedUser);
						jMapData.put("user", acceptedUser);
					}
					tableData.add(mapData);
					jTableData.add(jMapData);
				}				
			}			
			window.updateInscriptionTableData(tableData, jTableData);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
