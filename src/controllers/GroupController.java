package controllers;

import application.Main;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import qcm.models.pojo.Groupe;
import qcm.models.pojo.Questionnaire;
import qcm.models.pojo.Utilisateur;

public class GroupController extends AbstractController{

    @FXML
    private TableView<Groupe> groupList;

    @FXML
    private TableColumn<Groupe, String> codeColumn;

    @FXML
    private TableColumn<Groupe, String> libelleColumn;

    @FXML
    private TextField idField;
    
    @FXML
    private TextField codeField;

    @FXML
    private TextField libelleField;

    @FXML
    private TableView<Utilisateur> userList;

    @FXML
    private TableColumn<Utilisateur, String> userColumn;

    @FXML
    private TableView<Questionnaire> quizzList;

    @FXML
    private TableColumn<Questionnaire, String> quizzColumn;

    @Override
    public void setMainApp(Main mainApp) {
    	super.setMainApp(mainApp);
    	ObservableList<Groupe> groupObs = mainApp.getWebGate().getList(Groupe.class);
    	groupList.setItems(groupObs);
    }

    @FXML
	private void initialize() {
    		
		libelleColumn.setCellValueFactory((CellDataFeatures<Groupe, String> feature) -> {
			Groupe groupe = feature.getValue();
			return new SimpleObjectProperty<>(groupe.getLibelle());
		});
		
		codeColumn.setCellValueFactory((CellDataFeatures<Groupe, String> feature) -> {
			Groupe groupe = feature.getValue();
			return new SimpleObjectProperty<>(groupe.getCode());
		});
	
		userColumn.setCellValueFactory((CellDataFeatures<Utilisateur, String> feature) -> {
			Utilisateur user = feature.getValue();
			return new SimpleObjectProperty<>(user.getNom()+" "+user.getPrenom());
		});
		
		quizzColumn.setCellValueFactory((CellDataFeatures<Questionnaire, String> feature) -> {
			Questionnaire quizz = feature.getValue();
			return new SimpleObjectProperty<>(quizz.getLibelle());
		});
		
		showGroup(null);
		groupList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showGroup(newValue));	
	
	}
    
    public void showGroup(Groupe group){
    	if(group == null){
    		idField.setText("0");
    		libelleField.setText("");
    		codeField.setText("");
    	}else{
    		idField.setText(String.valueOf(group.getId()));
    		codeField.setText(group.getCode());
    		libelleField.setText(group.getLibelle());

    	}
    }
    
    @FXML
    void handleSave(ActionEvent event) {
    	int selInxdex = groupList.getSelectionModel().getSelectedIndex();
		if (selInxdex >= 0) {
			//Update
			Groupe selectedGroup = groupList.getSelectionModel().getSelectedItem();
			selectedGroup.setLibelle(libelleField.getText());
			selectedGroup.setCode(codeField.getText());
			try {
				mainApp.getWebGate().update(selectedGroup, selectedGroup.getId());
				mainApp.getTaskQueue().getAll(Groupe.class);
			} catch (Exception e) {
				
			}
		} else {
			//Insertion
			Groupe group = new Groupe();
			group.setLibelle(libelleField.getText());
			group.setCode(codeField.getText());
			try {
				String res = mainApp.getWebGate().add(group);
				Groupe g = (Groupe) mainApp.getWebGate().getObjectFromJson(res, Groupe.class);
				mainApp.getWebGate().getList(Groupe.class).add(g);
				showGroup(g);
			} catch (Exception e) {
		
			}
		}
    }
    
    @FXML
    void handleNew(ActionEvent event) {
    	showGroup(null);
    	groupList.getSelectionModel().clearSelection();
    }
    
    @FXML
    void handleDelete(ActionEvent event) {
    	Alert alert = new Alert(AlertType.WARNING);
    	int selInxdex = groupList.getSelectionModel().getSelectedIndex();
		Groupe selectedGroup = groupList.getSelectionModel().getSelectedItem();
		if (selInxdex >= 0) {
			boolean response = alert.showDialog("Suppression", "Supprimer un groupe ?", "Voulez-vous vraiment supprimer le groupe '" + selectedGroup.getLibelle() + "' ?");
			if(response){
				groupList.getItems().remove(selInxdex);
				try {
					mainApp.getTaskQueue().delete(selectedGroup, selectedGroup.getId());
				} catch (Exception e) {
					
				}
			}
		} else {
			
		}
    }

}
