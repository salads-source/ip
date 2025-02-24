package ron;

import ron.ui.Ui;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
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

    private Ron ron;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/peter.png"));
    private final Image botImage = new Image(this.getClass().getResourceAsStream("/images/quagmire.png"));

    /** Initialises the Container and attaches a welcome message */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        assert dialogContainer != null : "initialize: dialogContainer should be initialized";

        dialogContainer.getChildren().add(
                DialogBox.getRonDialog(Ui.greetUser(), botImage)
        );
    }

    /** Injects the Ron instance */
    public void setRon(Ron ron) {
        assert ron != null : "setRon: Ron instance should not be null";
        this.ron = ron;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Ron's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        assert userInput != null : "handleUserInput: userInput field should be initialised";
        assert dialogContainer != null : "handleUserInput: dialogContainer should be initialised";

        String input = userInput.getText();
        String response = this.ron.getResponse(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getRonDialog(response, botImage)
        );

        assert response != null : "handleUserInput: Response should not be null";
        userInput.clear();
    }
}
