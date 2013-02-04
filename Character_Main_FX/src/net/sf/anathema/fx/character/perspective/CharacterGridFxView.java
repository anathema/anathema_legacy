package net.sf.anathema.fx.character.perspective;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import net.miginfocom.layout.AC;
import net.miginfocom.layout.LC;
import net.sf.anathema.character.perspective.CharacterButtonDto;
import net.sf.anathema.character.perspective.CharacterGridView;
import net.sf.anathema.character.perspective.model.CharacterIdentifier;
import net.sf.anathema.character.perspective.Selector;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.gui.IView;
import org.tbee.javafx.scene.layout.MigPane;

import javax.swing.JComponent;
import java.util.List;

public class CharacterGridFxView implements IView, CharacterGridView {
  private final JFXPanel panel = new JFXPanel();
  private final ToggleGroup toggleGroup = new ToggleGroup();
  private final MigPane gridPane = new MigPane(new LC().insets("10").gridGap("8", "8").wrapAfter(1), new AC().grow().fill());

  public CharacterGridFxView() {
    Platform.runLater(new InitScene(panel, gridPane));
  }

  @Override
  public void addButtons(final List<CharacterButtonDto> dtoList, final Selector<CharacterIdentifier> characterSelector) {
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        for (CharacterButtonDto dto : dtoList) {
          addButton(dto, characterSelector);
        }
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

}
