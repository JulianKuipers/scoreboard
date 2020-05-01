package com.juliankuipers.views;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;

public class MakeAlert {
    /**
     * Show an errormessage for Server communication failed.
     */
    public static void serverCommunicationFailed() {
        error("Server communication failed", "Please try again.");
    }

    public static void warning(String header, String content) {
        make(Alert.AlertType.WARNING, header, content).showAndWait();
    }

    public static void error(String header, String content) {
        make(Alert.AlertType.ERROR, header, content).showAndWait();
    }

    public static void information(String header, String content) {
        make(Alert.AlertType.INFORMATION, header, content).showAndWait();
    }

    public static Alert make(Alert.AlertType alertType, String header, String content) {
        return make(alertType, header, content, null);
    }

    /**
     * Build an alert.
     *
     * @param type The type of alert.
     * @param header    The header text.
     * @param content   The context of the alert.
     * @param buttons   The buttons in the alert.
     * @return The alert.
     */
    public static Alert make(Alert.AlertType type, String header, String content,
                             List<ButtonType> buttons) {
        Alert alert = new Alert(type);
        alert.setHeaderText(header);
        alert.setContentText(content);

        if (buttons != null && !buttons.isEmpty()) {
            alert.getButtonTypes().setAll(buttons);
        }

        return alert;
    }
}
