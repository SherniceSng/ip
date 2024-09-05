package chacha;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private ChaCha chacha;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/UserImage.png"));
    private Image chachaImage = new Image(this.getClass().getResourceAsStream("/images/ChaChaImage.png"));
    private final static String GREETING = "Hello! I'm ChaCha the ChatBot. :) \n"
            + "What can I do for you? \n";

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        dialogContainer.getChildren().add(DialogBox.getChaChaDialog(GREETING, chachaImage));
    }

    /** Injects the Duke instance */
    public void setChaCha(ChaCha d) {
        this.chacha = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing ChaCha's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        if (this.chacha.getIsEnd()) {
            this.closeApplication();
        }
        String input = userInput.getText();
        String response = chacha.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getChaChaDialog(response, chachaImage)
        );
        userInput.clear();

        if (this.chacha.getIsEnd()) {
            this.closeApplication();
        }
    }

    private void closeApplication() {
        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished(action -> Platform.exit());
        delay.play();
    }
}
