package net.sf.anathema.character.impl.module.perspective;

import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
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
  private final ToggleGroup toggleGroup = new ToggleGroup();
  private final MigPane gridPane = new MigPane(new LC().insets("10").gridGap("8", "8").wrapAfter(1), new AC().grow().fill());

  public InitCharacterButtons(IAnathemaModel model, CharacterStack characterStack, JFXPanel panel) {
    this.model = model;
    this.characterStack = characterStack;
    this.panel = panel;
  }

  @Override
  public void run() {
    Scene scene = createScene();
    scene.getStylesheets().add("skin/sandra/sandra.css");
    addCharacterButtons();
    panel.setScene(scene);
  }

  private Scene createScene() {
    Group root = new Group();
    Scene scene = new Scene(root, Color.LAVENDER);
    root.getChildren().add(gridPane);
    return scene;
  }

  private void addCharacterButtons() {
    for (PrintNameFile printNameFile : collectCharacterPrintNameFiles(model)) {
      ToggleButton button = createButton(printNameFile);
      button.setToggleGroup(toggleGroup);
      gridPane.getChildren().add(button);
    }
  }

  private ToggleButton createButton(PrintNameFile printNameFile) {
    ToggleButton button = new ToggleButton(printNameFile.getPrintName());
    button.getStyleClass().add("character-grid-button");
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
