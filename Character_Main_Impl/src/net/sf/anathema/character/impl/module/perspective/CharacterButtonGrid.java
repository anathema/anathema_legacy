package net.sf.anathema.character.impl.module.perspective;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.repository.access.printname.IPrintNameFileAccess;
import net.sf.anathema.framework.view.PrintNameFile;

import javax.swing.JComponent;

import static net.sf.anathema.character.impl.module.ExaltedCharacterItemTypeConfiguration.CHARACTER_ITEM_TYPE_ID;

public class CharacterButtonGrid {

  private final GridPane gridPane = new GridPane();
  private final JFXPanel panel = new JFXPanel();

  public void fillFromRepository(final IAnathemaModel model, final CharacterStack characterStack) {
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        Scene scene = createScene();
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        PrintNameFile[] printNameFiles = collectCharacterPrintNameFiles(model);
        for (int index = 0; index < printNameFiles.length; index++) {
          PrintNameFile printNameFile = printNameFiles[index];
          Button button = new Button(printNameFile.getPrintName());
          button.setOnAction(new ShowCharacterAction(printNameFile, model, characterStack));
          GridPane.setHgrow(button, Priority.ALWAYS);
          GridPane.setVgrow(button, Priority.ALWAYS);
          gridPane.add(button, 0, index);
        }
        panel.setScene(scene);
      }
    });
  }

  private Scene createScene() {
    Group root = new Group();
    Scene scene = new Scene(root, Color.CHOCOLATE);
    root.getChildren().add(gridPane);
    return (scene);
  }

  public JComponent getContent() {
    return panel;
  }

  private PrintNameFile[] collectCharacterPrintNameFiles(IAnathemaModel model) {
    IItemType characterItemType = collectCharacterItemType(model);
    IPrintNameFileAccess access = model.getRepository().getPrintNameFileAccess();
    return access.collectAllPrintNameFiles(characterItemType);
  }

  private IItemType collectCharacterItemType(IAnathemaModel model) {
    return model.getItemTypeRegistry().getById(CHARACTER_ITEM_TYPE_ID);
  }

}
