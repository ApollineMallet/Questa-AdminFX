package controllers;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import qcm.models.pojo.Domaine;
import qcm.models.pojo.Groupe;
import qcm.models.pojo.Question;
import qcm.models.pojo.Questionnaire;
import qcm.models.pojo.Utilisateur;

public class EditController extends ModalController {

	private Utilisateur user;
	@FXML
	private TextField txtNomUser;
	@FXML
	private TextField txtCode;
	@FXML
	private TextField txtLibelle;
	@FXML
	private TextField txtPrenomUser;
	@FXML
	private TextField txtEmailUser;
	@FXML
	private ComboBox<String> comboxRang;
	@FXML
	private Button btOkayUser;
	@FXML
	private Button btCancelUser;

	private Groupe group;

	private Question quest;

	private Domaine domaine;
	@FXML
	private TextField txtLibelleDomaine;
	@FXML
	private Button btValiderDomaine;
	@FXML
	private Button btCancelDomaine;

	private Questionnaire quiz;
	@FXML
	private TextField txtLibelleQuiz;
	@FXML
	private TextField txtLibelleQuizDom;
	@FXML
	private Button btValiderQuiz;
	@FXML
	private Button btCancelQuiz;

	public Main mainApp;

	public void setUser(Utilisateur user) {
		this.user = user;
		txtNomUser.setText(user.getNom());
		txtPrenomUser.setText(user.getPrenom());
		txtEmailUser.setText(user.getMail());

		// comboxRang.setItems(mainApp.getRangData());
		comboxRang.getItems().addAll("utilisateur", "admin");

	}

	public void setGroup(Groupe group) {
		this.group = group;
		txtCode.setText(group.getCode());
		txtLibelle.setText(group.getLibelle());
	}

	public void setQuest(Question quest) {
		this.quest = quest;
		txtLibelle.setText(quest.getLibelle());

	}

	public void setDomaine(Domaine domaine) {
		this.domaine = domaine;
		txtLibelleDomaine.setText(domaine.getLibelle());
	}

	public void setQuiz(Questionnaire Q) {
		this.quiz = Q;
		txtLibelleQuiz.setText(Q.getLibelle());
		txtLibelleQuizDom.setText(Q.getDomaine().toString());
	}

	// public void setGroup(Groupe group) {
	// this.user = user;
	// txtCode.setText(.getNom());
	// txtPrenom.setText(user.getPrenom());
	// txtEmail.setText(user.getMail());
	// }

	/**
	 * Called when the user clicks ok.
	 */
	@FXML
	private void handleUserOk() {
		if (isInputValidUser()) {
			user.setNom(txtNomUser.getText());
			user.setPrenom(txtPrenomUser.getText());
			user.setMail(txtEmailUser.getText());
			okClicked = true;
			dialogStage.close();
		}
	}

	@FXML
	private void handleOkGroupe() {
		if (isInputValidGroupe()) {
			group.setCode(txtCode.getText());
			group.setLibelle(txtLibelle.getText());
			okClicked = true;
			dialogStage.close();

		}
	}

	@FXML
	private void handleOkQuest() {
		if (isInputValidQuest()) {

			quest.setLibelle(txtLibelle.getText());
			okClicked = true;
			dialogStage.close();

		}
	}

	/**
	 * Called when the user clicks ok.
	 */
	@FXML
	private void handleDomaineOk() {
		if (isInputValidDomaine()) {
			domaine.setLibelle(txtLibelleDomaine.getText());
			okClicked = true;
			dialogStage.close();
		}
	}

	@FXML
	private void handleQuizOK() {
		if (isInputValidQuiz()) {
			quiz.setLibelle(txtLibelleQuiz.getText());
			// quiz.setDomaine(txtLibelleQuizDom.getselectedobject);
			okClicked = true;
			dialogStage.close();
		}
	}

	/**
	 * Validates the user input in the text fields.
	 *
	 * @return true if the input is valid
	 */
	private boolean isInputValidUser() {
		String errorMessage = "";

		if (txtNomUser.getText() == null || txtNomUser.getText().length() == 0) {
			errorMessage += "No valid first name!\n";
		}
		if (txtPrenomUser.getText() == null || txtPrenomUser.getText().length() == 0) {
			errorMessage += "No valid last name!\n";
		}
		if (txtEmailUser.getText() == null || txtEmailUser.getText().length() == 0) {
			errorMessage += "No valid email!\n";
		}

		if (errorMessage.length() == 0) {
			return true;
		} else {
			// Show the error message.
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(dialogStage);
			alert.setTitle("Invalid Fields");
			alert.setHeaderText("Please correct invalid fields");
			alert.setContentText(errorMessage);

			alert.showAndWait();

			return false;
		}
	}

	private boolean isInputValidGroupe() {
		String errorMessage = "";

		if (txtCode.getText() == null || txtCode.getText().length() == 0) {
			errorMessage += "Code non valide!\n";
		}
		if (txtLibelle.getText() == null || txtLibelle.getText().length() == 0) {
			errorMessage += "LibellÃ© non valide!\n";
		}

		if (errorMessage.length() == 0) {
			return true;
		} else {
			// Show the error message.
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(dialogStage);
			alert.setTitle("Invalid Fields");
			alert.setHeaderText("Please correct invalid fields");
			alert.setContentText(errorMessage);

			alert.showAndWait();

			return false;
		}
	}

	private boolean isInputValidQuest() {
		String errorMessage = "";

		if (txtLibelle.getText() == null || txtLibelle.getText().length() == 0) {
			errorMessage += "Libellé non valide!\n";
		}

		if (errorMessage.length() == 0) {
			return true;
		} else {
			// Show the error message.
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(dialogStage);
			alert.setTitle("Invalid Fields");
			alert.setHeaderText("Please correct invalid fields");
			alert.setContentText(errorMessage);

			alert.showAndWait();

			return false;
		}
	}

	/**
	 * Validates the user input in the text fields.
	 *
	 * @return true if the input is valid
	 */
	private boolean isInputValidDomaine() {
		String errorMessage = "";

		if (txtLibelleDomaine.getText() == null || txtLibelleDomaine.getText().length() == 0) {
			errorMessage = "Libelle non valide !\n";
		}

		if (errorMessage.length() == 0) {
			return true;
		} else {
			// Show the error message.
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(dialogStage);
			alert.setTitle("Invalid Fields");
			alert.setHeaderText("Please correct invalid fields");
			alert.setContentText(errorMessage);
			alert.showAndWait();
			return false;
		}
	}

	private boolean isInputValidQuiz() {
		String errorMessage = "";

		if (txtLibelleQuiz.getText() == null || txtLibelleQuiz.getText().length() == 0) {
			errorMessage = "Libelle non valide !\n";
		}

		if (errorMessage.length() == 0) {
			return true;
		} else {
			// Show the error message.
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(dialogStage);
			alert.setTitle("Invalid Fields");
			alert.setHeaderText("Please correct invalid fields");
			alert.setContentText(errorMessage);
			alert.showAndWait();
			return false;
		}
	}

	/**
	 * Called when the user clicks cancel.
	 */
	@FXML
	private void handleCancel() {
		dialogStage.close();
	}
}