package fxlogin;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Grit Login JavaFX");
		primaryStage.getIcons().add(new Image(
				"https://as1.ftcdn.net/jpg/03/34/67/50/500_F_334675005_Je1dwijye7s6NAWsjAzKAfQwGRh0dRIm.jpg"));

		// Uses Gridpane as layout
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 25, 25, 25));

		Text scenetitle = new Text("Grit logga här");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

		Label eMail = new Label("Email:");

		Label errorEmail = new Label("");

		TextField userTextField = new TextField();

		Label pw = new Label("Password:");

		Label errorpw = new Label("");

		PasswordField pwBox = new PasswordField();

		// Creating sign in button
		Button btn = new Button("Sign in");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(btn);

		final Text actiontarget = new Text();

		// Arranging all the nodes in the grid
		grid.add(scenetitle, 0, 0, 2, 1);
		grid.add(eMail, 0, 1);
		grid.add(errorEmail, 1, 3);
		grid.add(userTextField, 1, 1);
		grid.add(pw, 0, 2);
		grid.add(errorpw, 1, 4);
		grid.add(pwBox, 1, 2);
		grid.add(hbBtn, 1, 4);
		grid.add(actiontarget, 1, 6);

		btn.setOnAction((event) -> {
			actiontarget.setText("Lambda");
			actiontarget.setFill(Color.FIREBRICK);

		});

		/*
		 * // Check if pw is valid on invalid when key is released
		 * pwBox.setOnKeyReleased(new EventHandler<KeyEvent>() {
		 * 
		 * @Override public void handle(KeyEvent event) {
		 * 
		 * if (isValidPassword(pwBox.getText())) {
		 * 
		 * errorpw.setStyle("-fx-text-fill:green"); errorpw.setText("Valid pw"); } else
		 * { errorpw.setText("Invalid pw"); errorpw.setStyle("-fx-text-fill:red");
		 * 
		 * } } });
		 */

		pwBox.setOnKeyReleased((event) -> {
			if (isValidPassword(pwBox.getText())) {

				errorpw.setStyle("-fx-text-fill:green");
				errorpw.setText("Valid pw");
			} else {
				errorpw.setText("Invalid pw");
				errorpw.setStyle("-fx-text-fill:red");

			}

		});

		/*
		 * // Check if un is valid on invalid when key is released
		 * userTextField.setOnKeyReleased(new EventHandler<KeyEvent>() {
		 * 
		 * @Override public void handle(KeyEvent event) {
		 * 
		 * if (isValidName(userTextField.getText())) {
		 * 
		 * errorEmail.setStyle("-fx-text-fill:green");
		 * errorEmail.setText("Valid Email"); } else {
		 * errorEmail.setText("Invalid Email");
		 * errorEmail.setStyle("-fx-text-fill:red");
		 * 
		 * } } });
		 */

		userTextField.setOnKeyReleased((event) -> {
			if (isValidName(userTextField.getText())) {

				errorEmail.setStyle("-fx-text-fill:green");
				errorEmail.setText("Valid Email");
			} else {
				errorEmail.setText("Invalid Email");
				errorEmail.setStyle("-fx-text-fill:red");

			}
		});
		// Creating a scene object
		Scene scene = new Scene(grid, 350, 350);

		// Adding scene to the stage
		primaryStage.setScene(scene);

		// Displaying the contents of the stage
		primaryStage.show();
	}

	// Check if username is valid email adress
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile(
			"(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])",
			Pattern.CASE_INSENSITIVE);

	public static boolean isValidName(String emailStr) {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
		return matcher.find();
	}

	// Check for 2 number, 2 lowercase letters, 2 uppercase letters and 2 special
	// characters
	public static final Pattern VALID_PASSWORD_REGEX = Pattern
			.compile("^(?=.*\\d){2}(?=.*[a-z]){2}(?=.*[A-Z]){2}(?=.*[^a-zA-Z0-9]){2}(?!.*\\s).{8,20}$");

	public static boolean isValidPassword(String passwordStr) {
		Matcher matcher = VALID_PASSWORD_REGEX.matcher(passwordStr);
		return matcher.find();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
