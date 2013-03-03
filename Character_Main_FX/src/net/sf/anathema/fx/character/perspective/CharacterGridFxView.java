package net.sf.anathema.fx.character.perspective;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import net.sf.anathema.character.perspective.CharacterButtonDto;
import net.sf.anathema.character.perspective.CharacterGridView;
import net.sf.anathema.character.perspective.Selector;
import net.sf.anathema.character.perspective.model.model.CharacterIdentifier;
import net.sf.anathema.lib.gui.IView;
import net.sf.anathema.platform.fx.InitScene;

import javax.swing.JComponent;
import java.util.HashMap;
import java.util.Map;

import static javafx.scene.control.ScrollPane.ScrollBarPolicy.NEVER;

public class CharacterGridFxView implements IView, CharacterGridView {
  private final ScrollPane scrollPane = new ScrollPane();
  private final JFXPanel panel = new JFXPanel();
  private final ToggleGroup toggleGroup = new ToggleGroup();
  private final VBox gridPane = new VBox(2);
  private final Map<CharacterIdentifier, CharacterGridButton> buttonsByIdentifier = new HashMap<>();

  public CharacterGridFxView() {
    scrollPane.setContent(gridPane);
    scrollPane.setHbarPolicy(NEVER);
    Platform.runLater(new InitScene(panel, scrollPane, "skin/sandra/sandra.css"));
  }

  @Override
  public void addButton(final CharacterButtonDto dto, final Selector<CharacterIdentifier> characterSelector) {
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        createGridButton(dto, characterSelector);
      }
    });
  }

  @Override
  public void selectButton(final CharacterIdentifier identifier) {
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        buttonsByIdentifier.get(identifier).setSelected(true);
      }
    });
  }

  @Override
  public void updateButton(final CharacterButtonDto dto) {
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        buttonsByIdentifier.get(dto.identifier).setContent(dto);
      }
    });
  }

  private CharacterGridButton createGridButton(CharacterButtonDto dto, Selector<CharacterIdentifier> characterSelector) {
    CharacterGridButton characterGridButton = new CharacterGridButton();
    characterGridButton.initContent(dto, characterSelector);
    characterGridButton.setToggleGroup(toggleGroup);
    buttonsByIdentifier.put(dto.identifier, characterGridButton);
    gridPane.getChildren().add(0, characterGridButton.getNode());
    scrollPane.setVvalue(0);
    return characterGridButton;
  }

  public JComponent getComponent() {
    return panel;
  }
}