package net.sf.anathema.character.impl.module.perspective;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import net.miginfocom.layout.AC;
import net.miginfocom.layout.LC;
import net.sf.anathema.character.itemtype.CharacterItemTypeRetrieval;
import net.sf.anathema.character.perspective.CharacterIdentifier;
import net.sf.anathema.character.perspective.CharacterStackPresenter;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.repository.access.printname.IPrintNameFileAccess;
import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.fx.character.perspective.CharacterSelected;
import net.sf.anathema.fx.character.perspective.InitScene;
import net.sf.anathema.fx.character.perspective.Selector;
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

  private void addCharacterButtons(IAnathemaModel model, final CharacterStackPresenter characterStack) {
    for (final PrintNameFile printNameFile : collectCharacterPrintNameFiles(model)) {
      String text = printNameFile.getPrintName();
      String repositoryId = printNameFile.getRepositoryId();
      final CharacterIdentifier identifier = new CharacterIdentifier(repositoryId);
      final Selector<CharacterIdentifier> characterSelector = new Selector<CharacterIdentifier>() {
        @Override
        public void selected(CharacterIdentifier item) {
          characterStack.showCharacter(item);
        }
      };

      ToggleButton button = new ToggleButton(text);
      button.getStyleClass().add("character-grid-button");
      button.setOnAction(new CharacterSelected(characterSelector, identifier));
      button.setToggleGroup(toggleGroup);
      gridPane.getChildren().add(button);
    }
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
