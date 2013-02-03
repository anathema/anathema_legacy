package net.sf.anathema.character.impl.module.perspective;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import net.miginfocom.layout.AC;
import net.miginfocom.layout.LC;
import net.sf.anathema.character.perspective.CharacterButtonDto;
import net.sf.anathema.character.perspective.CharacterIdentifier;
import net.sf.anathema.character.perspective.CharacterStackPresenter;
import net.sf.anathema.character.perspective.CharacterSystemModel;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.fx.character.perspective.CharacterSelected;
import net.sf.anathema.fx.character.perspective.InitScene;
import net.sf.anathema.fx.character.perspective.Selector;
import net.sf.anathema.lib.control.IChangeListener;
import org.tbee.javafx.scene.layout.MigPane;

import javax.annotation.Nullable;
import javax.swing.JComponent;
import java.util.List;

public class CharacterButtonGrid {

  private final JFXPanel panel = new JFXPanel();
  private final ToggleGroup toggleGroup = new ToggleGroup();
  private final MigPane gridPane = new MigPane(new LC().insets("10").gridGap("8", "8").wrapAfter(1), new AC().grow().fill());
  private final IChangeListener buttonsChangedListener;

  public CharacterButtonGrid(IChangeListener buttonsChangedListener) {
    this.buttonsChangedListener = buttonsChangedListener;
    Platform.runLater(new InitScene(panel, gridPane));
  }

  public void initPresentation(final IAnathemaModel model, final CharacterStackPresenter characterStack) {
    Selector<CharacterIdentifier> characterSelector = new Selector<CharacterIdentifier>() {
      @Override
      public void selected(CharacterIdentifier item) {
        characterStack.showCharacter(item);
      }
    };
    List<PrintNameFile> printNameFiles = new CharacterSystemModel(model).collectCharacterPrintNameFiles();
    List<CharacterButtonDto> dtoList = Lists.transform(printNameFiles, new ToCharacterButtonDto());
    addButtons(dtoList, characterSelector);
  }

  public void addButtons(final List<CharacterButtonDto> dtoList, final Selector<CharacterIdentifier> characterSelector) {
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        for (CharacterButtonDto dto : dtoList) {
          addButton(dto, characterSelector);
        }
        buttonsChangedListener.changeOccurred();
      }
    });
  }

  private void addButton(CharacterButtonDto dto, Selector<CharacterIdentifier> characterSelector) {
    ToggleButton button = new ToggleButton(dto.text);
    button.getStyleClass().add("character-grid-button");
    button.setOnAction(new CharacterSelected(characterSelector, dto.identifier));
    button.setToggleGroup(toggleGroup);
    gridPane.getChildren().add(button);
  }

  public JComponent getComponent() {
    return panel;
  }

  private static class ToCharacterButtonDto implements Function<PrintNameFile, CharacterButtonDto> {
    @Nullable
    @Override
    public CharacterButtonDto apply(@Nullable PrintNameFile input) {
      String text = input.getPrintName();
      String repositoryId = input.getRepositoryId();
      CharacterIdentifier identifier = new CharacterIdentifier(repositoryId);
      return new CharacterButtonDto(identifier, text);
    }
  }
}
