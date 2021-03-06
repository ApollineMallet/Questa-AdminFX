package controllers;

import java.util.List;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.util.Callback;
import qcm.models.pojo.Questionnaire;

public class AccueilController extends AbstractController {

	@FXML
	private TitledPane grpMyQuiz;

	@FXML
	private ListView<Questionnaire> lvMyQuiz;

	@FXML
	private Button btUsers;
	@FXML
	private Button btDisconnect;
	@FXML
	private Button btGroupes;
	@FXML
	private Button btDomaines;
	@FXML
	private Button btRangs;

	@FXML
	private void initialize() {
		Callback<Questionnaire, ObservableValue<Boolean>> getProperty = new Callback<Questionnaire, ObservableValue<Boolean>>() {

			@Override
			public ObservableValue<Boolean> call(Questionnaire quiz) {
				return null;
			}

		};
		Callback<ListView<Questionnaire>, ListCell<Questionnaire>> forListView = CheckBoxListCell
				.forListView(getProperty);
		lvMyQuiz.setCellFactory(forListView);
	}

	public void setQuizzes(List<Questionnaire> quizzes) {
		lvMyQuiz.setItems(FXCollections.observableArrayList(quizzes));
	}

	public void handleBtUsers() {
		mainApp.showPersonOverview();
	}

	public void handleBtGroup() {
		mainApp.showGroupOverview();
	}
	
	public void handleBtQuest(){
		mainApp.showQuestOverview();
	}

	public void handleBtDomaine() {
		mainApp.showDomaineOverview();
	}
	
	public void handleBtRang() {
		mainApp.showRangOverview();
	}
	
	public void handleBtQuiz() {
		mainApp.showQuizOverview();
	}

	public void handleDisconnect() {
		if (mainApp.getWebGate().disconnect()) {
			mainApp.getRootLayout().setCenter(null);
			mainApp.showConnexion();

		}
	}
}
