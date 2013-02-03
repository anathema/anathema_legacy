package net.sf.anathema.character.impl.module.perspective;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import net.miginfocom.layout.AC;
import net.miginfocom.layout.LC;
import net.sf.anathema.character.itemtype.CharacterItemTypeRetrieval;
import net.sf.anathema.character.perspective.CharacterStackPresenter;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.repository.access.printname.IPrintNameFileAccess;
import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.lib.control.IChangeListener;
import org.tbee.javafx.scene.layout.MigPane;

import javax.swing.JComponent;

public class CharacterButtonGrid {

  private final JFXPanel panel = new JFXPanel();
  private final ToggleGroup toggleGroup = new ToggleGroup();
  private final MigPane gridPane = new MigPane(new LC().insets("10").gridGap("8", "8").wrapAfter(1), new AC().grow().fill());
  private final IChangeListener buttonsChangedListener;

  public CharacterButtonGrid(IChangeListener buttonsChangedListener) {
    this.buttonsChangedListener = buttonsChangedListener;
    Platform.runLater(new InitScene(panel, gridPane));
  }

  public void fillFromRepository(final IAnathemaModel model, final CharacterStackPresenter characterStack) {
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        addCharacterButtons(model, characterStack);
        buttonsChangedListener.changeOccurred();
      }
    });
  }

  private void addCharacterButtons(IAnathemaModel model, CharacterStackPresenter characterStack) {
    for (PrintNameFile printNameFile : collectCharacterPrintNameFiles(model)) {
      ToggleButton button = createButton(printNameFile, characterStack);
      button.setToggleGroup(toggleGroup);
      gridPane.getChildren().add(button);
    }
  }

  private ToggleButton createButton(final PrintNameFile printNameFile, final CharacterStackPresenter presenter) {
    ToggleButton button = new ToggleButton(printNameFile.getPrintName());
    button.getStyleClass().add("character-grid-button");
    button.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent actionEvent) {
        String identifier = printNameFile.getRepositoryId();
        presenter.showCharacter(identifier);
      }
    });
    return button;
  }

  private PrintNameFile[] collectCharacterPrintNameFiles(IAnathemaModel model) {
    IItemType characterItemType = CharacterItemTypeRetrieval.retrieveCharacterItemType(model);
    IPrintNameFileAccess access = model.getRepository().getPrintNameFileAccess();
    return access.collectAllPrintNameFiles(characterItemType);
  }

  public JComponent getComponent() {
    return panel;
  }

}
