package net.sf.anathema.character.impl.generic;

import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.model.ICharacterDescription;

public class GenericDescription implements IGenericDescription {

  private final ICharacterDescription description;

  public GenericDescription(ICharacterDescription description) {
    this.description = description;
  }

  public String getName() {
    return description.getName().getText();
  }

  public String getPeriphrase() {
    return description.getPeriphrase().getText();
  }

  public String getCharacterization() {
    return description.getCharacterization().getText();
  }

  public String getPhysicalAppearance() {
    return description.getPhysicalDescription().getText();
  }

  public String getEyes() {
    return description.getEyes().getText();
  }

  public String getHair() {
    return description.getHair().getText();
  }

  public String getSex() {
    return description.getSex().getText();
  }

  public String getSkin() {
    return description.getSkin().getText();
  }

  public String getPlayer() {
    return description.getPlayer().getText();
  }

  public String getNotes() {
    return description.getNotes().getText();
  }

  public String getConceptText() {
    return description.getConcept().getText();
  }
}