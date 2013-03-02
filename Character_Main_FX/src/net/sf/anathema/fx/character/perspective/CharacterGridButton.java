package net.sf.anathema.fx.character.perspective;

import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.FontSmoothingType;
import javafx.scene.text.Text;
import net.miginfocom.layout.AC;
import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.sf.anathema.character.perspective.CharacterButtonDto;
import net.sf.anathema.character.perspective.Selector;
import net.sf.anathema.character.perspective.model.model.CharacterIdentifier;
import org.tbee.javafx.scene.layout.MigPane;

import java.io.InputStream;

public class CharacterGridButton {

  private final MigPane buttonContent = new MigPane(new LC().insets("0").gridGap("0", "0").wrapAfter(2).gridGapX("0").debug(0), new AC().gap("5"));
  private final ToggleButton button = new ToggleButton("", buttonContent);
  private final Text name = new Text("");

  public CharacterGridButton() {
    name.setFontSmoothingType(FontSmoothingType.LCD);
    name.getStyleClass().add("name");
  }

  public ToggleButton initContent(CharacterButtonDto dto, Selector<CharacterIdentifier> characterSelector) {
    Image image = new Image(getImage(dto.pathToImage), 30, 30, true, true);
    name.setText(dto.text);
    Label details = new Label(dto.details);
    details.getStyleClass().add("details");
    buttonContent.add(new ImageView(image), new CC().pushY().gapBottom("0"));
    buttonContent.add(name, new CC().span().split(2).flowY().gapTop("0").gapBottom("0"));
    buttonContent.add(details, new CC().pad("0").gapTop("0"));
    button.getStyleClass().add("character-grid-button");
    button.setOnAction(new CharacterSelected(characterSelector, dto.identifier));
    return button;
  }

  public void setContent(String newName) {
    name.setText(newName);
  }

  private Text createNameDisplay() {
    name.setFontSmoothingType(FontSmoothingType.LCD);
    name.getStyleClass().add("name");
    return name;
  }

  private InputStream getImage(String pathToImage) {
    return new ResourceLoader().loadResource(pathToImage);
  }
}
