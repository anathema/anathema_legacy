package net.sf.anathema.fx.character.perspective;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.FontSmoothingType;
import javafx.scene.text.Text;
import net.miginfocom.layout.AC;
import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.sf.anathema.character.perspective.CharacterButtonDto;
import net.sf.anathema.character.perspective.CharacterGridView;
import net.sf.anathema.character.perspective.Selector;
import net.sf.anathema.character.perspective.model.model.CharacterIdentifier;
import net.sf.anathema.lib.gui.IView;
import org.tbee.javafx.scene.layout.MigPane;

import javax.swing.JComponent;
import java.io.InputStream;
import java.util.Collection;

public class CharacterGridFxView implements IView, CharacterGridView {
  private final JFXPanel panel = new JFXPanel();
  private final ToggleGroup toggleGroup = new ToggleGroup();
  private final MigPane gridPane = new MigPane(new LC().insets("0").gridGap("0", "0").wrapAfter(1), new AC().grow().fill());

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
        ToggleButton button = addButton(dto, characterSelector);
        button.setSelected(true);
      }
    });
  }

  private ToggleButton addButton(CharacterButtonDto dto, Selector<CharacterIdentifier> characterSelector) {
    MigPane buttonContent = new MigPane(new LC().insets("0").gridGap("0", "0").wrapAfter(2).gridGapX("0").debug(0), new AC().gap("5"));
    Image image = new Image(getImage(dto.pathToImage), 30, 30, true, true);
    Text name = new Text(dto.text);
    name.setFontSmoothingType(FontSmoothingType.LCD);
    name.getStyleClass().add("name");
    Label details = new Label(dto.details);
    details.getStyleClass().add("details");
    buttonContent.add(new ImageView(image), new CC().pushY().gapBottom("0"));
    buttonContent.add(name, new CC().span().split(2).flowY().gapTop("0").gapBottom("0"));
    buttonContent.add(details, new CC().pad("0").gapTop("0"));
    ToggleButton button = new ToggleButton("", buttonContent);
    button.getStyleClass().add("character-grid-button");
    button.setOnAction(new CharacterSelected(characterSelector, dto.identifier));
    button.setToggleGroup(toggleGroup);
    gridPane.getChildren().add(button);
    return button;
  }

  private InputStream getImage(String pathToImage) {
    return new ResourceLoader().loadResource(pathToImage);
  }

  public JComponent getComponent() {
    return panel;
  }

}
