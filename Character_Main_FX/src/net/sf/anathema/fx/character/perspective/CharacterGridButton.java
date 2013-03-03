package net.sf.anathema.fx.character.perspective;

import javafx.scene.Node;
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
import net.sf.anathema.character.perspective.Selector;
import net.sf.anathema.character.perspective.model.model.CharacterIdentifier;
import org.tbee.javafx.scene.layout.MigPane;

import java.io.InputStream;

public class CharacterGridButton {

  private final MigPane buttonContent = new MigPane(new LC().insets("0").gridGap("0", "0").wrapAfter(2).gridGapX("0"), new AC().gap("5"));
  private final ToggleButton button = new ToggleButton("", buttonContent);
  private final Text name = new Text("");
  private final ImageView imageView = new ImageView();
  private final Label details = new Label("");

  public CharacterGridButton() {
    button.setMaxWidth(Double.MAX_VALUE);
    name.setFontSmoothingType(FontSmoothingType.LCD);
    name.getStyleClass().add("name");
    button.getStyleClass().add("character-grid-button");
    details.getStyleClass().add("details");
    buttonContent.add(imageView, new CC().pushY().gapBottom("0"));
    buttonContent.add(name, new CC().span().split(2).flowY().gapTop("0").gapBottom("0"));
    buttonContent.add(details, new CC().pad("0").gapTop("0"));
  }

  public void initContent(CharacterButtonDto dto, Selector<CharacterIdentifier> characterSelector) {
    button.setOnAction(new CharacterSelected(characterSelector, dto.identifier));
    setContent(dto);
  }

  public void setContent(CharacterButtonDto dto) {
    name.setText(dto.text);
    details.setText(dto.details);
    imageView.setImage(createImage(dto));
    if (dto.isDirty) {
      name.getStyleClass().add("dirty");
      name.getStyleClass().remove("clean");
    } else {
      name.getStyleClass().add("clean");
      name.getStyleClass().remove("dirty");
    }
  }

  private Image createImage(CharacterButtonDto dto) {
    ResourceLoader resourceLoader = new ResourceLoader();
    InputStream imageStream = resourceLoader.loadResource(dto.pathToImage);
    return new Image(imageStream, 30, 30, true, true);
  }

  public Node getNode() {
    return button;
  }

  public void setToggleGroup(ToggleGroup toggleGroup) {
    button.setToggleGroup(toggleGroup);
  }

  public void setSelected(boolean selected) {
    button.setSelected(selected);
  }
}
