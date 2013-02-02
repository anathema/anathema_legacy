package net.sf.anathema.character.impl.module.perspective;

import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import net.miginfocom.layout.AC;
import net.miginfocom.layout.LC;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.repository.access.printname.IPrintNameFileAccess;
import net.sf.anathema.framework.view.PrintNameFile;
import org.tbee.javafx.scene.layout.MigPane;

import static net.sf.anathema.character.impl.module.ExaltedCharacterItemTypeConfiguration.CHARACTER_ITEM_TYPE_ID;

public class InitCharacterButtons implements Runnable {
  private final IAnathemaModel model;
  private final CharacterStack characterStack;
  private final JFXPanel panel;
  private final MigPane gridPane = new MigPane(new LC().insets("5").wrapAfter(1), new AC().grow().fill());

  public InitCharacterButtons(IAnathemaModel model, CharacterStack characterStack, JFXPanel panel) {
    this.model = model;
    this.characterStack = characterStack;
    this.panel = panel;
  }

  @Override
  public void run() {
    Scene scene = createScene();
    addCharacterButtons();
    panel.setScene(scene);
  }

  private Scene createScene() {
    Group root = new Group();
    Scene scene = new Scene(root, Color.SKYBLUE);
    root.getChildren().add(gridPane);
    return scene;
  }

  private void addCharacterButtons() {
    for (PrintNameFile printNameFile : collectCharacterPrintNameFiles(model)) {
      Button button = createButton(printNameFile);
      gridPane.getChildren().add(button);
    }
  }

  private Button createButton(PrintNameFile printNameFile) {
    Button button = new Button(printNameFile.getPrintName());
    button.setStyle("-fx-base: crimson;");
    button.setOnAction(new ShowCharacter(printNameFile, model, characterStack));
    return button;
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
