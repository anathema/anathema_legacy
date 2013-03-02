package net.sf.anathema.fx.character.perspective;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.control.ToggleGroup;
import net.miginfocom.layout.AC;
import net.miginfocom.layout.LC;
import net.sf.anathema.character.perspective.CharacterButtonDto;
import net.sf.anathema.character.perspective.CharacterGridView;
import net.sf.anathema.character.perspective.Selector;
import net.sf.anathema.character.perspective.model.model.CharacterIdentifier;
import net.sf.anathema.lib.gui.IView;
import org.tbee.javafx.scene.layout.MigPane;

import javax.swing.JComponent;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CharacterGridFxView implements IView, CharacterGridView {
  private final JFXPanel panel = new JFXPanel();
  private final ToggleGroup toggleGroup = new ToggleGroup();
  private final MigPane gridPane = new MigPane(new LC().insets("0").gridGap("0", "0").wrapAfter(1), new AC().grow().fill());
  private final Map<CharacterIdentifier, CharacterGridButton> buttonsByIdentifier = new HashMap<>();

  public CharacterGridFxView() {
    Platform.runLater(new InitScene(panel, gridPane));
  }

  @Override
  public void addButtons(final Collection<CharacterButtonDto> dtoList, final Selector<CharacterIdentifier> characterSelector) {
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        for (CharacterButtonDto dto : dtoList) {
          addButton(dto, characterSelector);
        }
      }
    });
  }

  @Override
  public void addAndSelectButton(final CharacterButtonDto dto, final Selector<CharacterIdentifier> characterSelector) {
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        CharacterGridButton button = addButton(dto, characterSelector);
        button.setSelected(true);
      }
    });
  }

  @Override
  public void setName(final CharacterIdentifier identifier, final String name) {
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        buttonsByIdentifier.get(identifier).setContent(name);
      }
    });
  }

  private CharacterGridButton addButton(CharacterButtonDto dto, Selector<CharacterIdentifier> characterSelector) {
    CharacterGridButton characterGridButton = new CharacterGridButton();
    characterGridButton.initContent(dto, characterSelector);
    characterGridButton.setToggleGroup(toggleGroup);
    gridPane.getChildren().add(characterGridButton.getNode());
    return characterGridButton;
  }

  public JComponent getComponent() {
    return panel;
  }
}