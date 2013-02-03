package net.sf.anathema.character.impl.module.perspective;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import net.miginfocom.layout.AC;
import net.miginfocom.layout.LC;
import net.sf.anathema.character.perspective.CharacterButtonDto;
import net.sf.anathema.character.perspective.CharacterIdentifier;
import net.sf.anathema.fx.character.perspective.CharacterSelected;
import net.sf.anathema.fx.character.perspective.InitScene;
import net.sf.anathema.fx.character.perspective.Selector;
import net.sf.anathema.lib.control.IChangeListener;
import org.tbee.javafx.scene.layout.MigPane;

import javax.swing.JComponent;
import java.util.List;

public class CharacterGridView {
  private final JFXPanel panel = new JFXPanel();
  private final ToggleGroup toggleGroup = new ToggleGroup();
  private final MigPane gridPane = new MigPane(new LC().insets("10").gridGap("8", "8").wrapAfter(1), new AC().grow().fill());
  private final IChangeListener buttonsChangedListener;

  public CharacterGridView(IChangeListener buttonsChangedListener) {
    this.buttonsChangedListener = buttonsChangedListener;
    Platform.runLater(new InitScene(panel, gridPane));
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

}
