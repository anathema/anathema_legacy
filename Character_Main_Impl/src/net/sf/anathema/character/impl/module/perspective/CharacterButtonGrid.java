package net.sf.anathema.character.impl.module.perspective;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;
import net.miginfocom.layout.AC;
import net.miginfocom.layout.LC;
import net.sf.anathema.character.perspective.CharacterStackPresenter;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.repository.access.printname.IPrintNameFileAccess;
import net.sf.anathema.framework.view.PrintNameFile;
import org.tbee.javafx.scene.layout.MigPane;

import javax.swing.JComponent;

import static net.sf.anathema.character.itemtype.CharacterItemTypeRetrieval.CHARACTER_ITEM_TYPE_ID;

public class CharacterButtonGrid {

  private final JFXPanel panel = new JFXPanel();
  private final ToggleGroup toggleGroup = new ToggleGroup();
  private final MigPane gridPane = new MigPane(new LC().insets("10").gridGap("8", "8").wrapAfter(1), new AC().grow().fill());

  public void fillFromRepository(final IAnathemaModel model, final CharacterStackPresenter characterStack) {
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        Scene scene = createScene();
        scene.getStylesheets().add("skin/sandra/sandra.css");
        addCharacterButtons(model, characterStack);
        panel.setScene(scene);

      }
    });
  }

  private Scene createScene() {
    Group root = new Group();
    Scene scene = new Scene(root, Color.LAVENDER);
    root.getChildren().add(gridPane);
    return scene;
  }

  private void addCharacterButtons(IAnathemaModel model, CharacterStackPresenter characterStack) {
    for (PrintNameFile printNameFile : collectCharacterPrintNameFiles(model)) {
      ToggleButton button = createButton(printNameFile, characterStack);
      button.setToggleGroup(toggleGroup);
      gridPane.getChildren().add(button);
    }
  }

  private ToggleButton createButton(PrintNameFile printNameFile, CharacterStackPresenter characterStack) {
    ToggleButton button = new ToggleButton(printNameFile.getPrintName());
    button.getStyleClass().add("character-grid-button");
    button.setOnAction(new ShowCharacter(printNameFile, characterStack));
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

  public JComponent getComponent() {
    return panel;
  }
}
