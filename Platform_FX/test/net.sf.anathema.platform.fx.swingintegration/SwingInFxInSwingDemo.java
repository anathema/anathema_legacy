package net.sf.anathema.lib.gui.swing;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class SwingInFxInSwingDemo {

  private static void initAndShowGUI() {
    JFrame frame = new JFrame("JFrame showing FX-Button");
    final JFXPanel fxPanel = new JFXPanel();
    frame.add(fxPanel);
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        initFX(fxPanel);
      }
    });
    frame.pack();
    frame.setVisible(true);
  }

  private static void initFX(JFXPanel fxPanel) {
    Scene scene = createScene();
    fxPanel.setScene(scene);
  }

  private static Scene createScene() {
    BorderPane pane = new BorderPane();
    Scene scene = new Scene(pane);
    Button button = new Button("Show Swing Dialog");
    pane.setCenter(button);
    button.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent actionEvent) {
        JDialog jDialog = new JDialog();
        jDialog.add(new JLabel("JLabel in a JDialog"));
        jDialog.pack();
        jDialog.show();
      }
    });
    return scene;
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        initAndShowGUI();
      }
    });
  }
}
