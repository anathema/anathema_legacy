package net.sf.anathema.fx.character.perspective;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;
import net.miginfocom.layout.AC;
import net.miginfocom.layout.LC;
import net.sf.anathema.character.main.perspective.CharacterButtonDto;
import net.sf.anathema.character.main.perspective.CharacterGridView;
import net.sf.anathema.character.main.perspective.Selector;
import net.sf.anathema.character.main.perspective.model.CharacterIdentifier;
import net.sf.anathema.framework.swing.IView;
import net.sf.anathema.platform.fx.InitScene;
import net.sf.anathema.platform.fx.ParentHolder;
import org.tbee.javafx.scene.layout.MigPane;

import javax.swing.JComponent;
import java.util.HashMap;
import java.util.Map;

import static javafx.scene.control.ScrollPane.ScrollBarPolicy.NEVER;

public class CharacterGridFxView implements IView, CharacterGridView {
  private final ScrollPane scrollPane = new ScrollPane();
  private final JFXPanel panel = new JFXPanel();
  private final ToggleGroup toggleGroup = new ToggleGroup();
  private final MigPane gridPane = new MigPane(new LC().insets("0").gridGap("0", "2").wrapAfter(1), new AC().grow().fill());
  private final Map<CharacterIdentifier, CharacterGridButton> buttonsByIdentifier = new HashMap<>();

  public CharacterGridFxView() {
    scrollPane.setContent(gridPane);
    scrollPane.setHbarPolicy(NEVER);
    Platform.runLater(new InitScene(panel, new SceneParentHolder(), "skin/character/characternavigation.css"));
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

  private class SceneParentHolder implements ParentHolder {
    @Override
    public Parent getParent() {
      return scrollPane;
    }
  }
}