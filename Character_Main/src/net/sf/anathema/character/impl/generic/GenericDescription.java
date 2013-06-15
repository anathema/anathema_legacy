package net.sf.anathema.character.impl.generic;

import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.main.description.model.CharacterDescription;

public class GenericDescription implements IGenericDescription {

  private final CharacterDescription description;

  public GenericDescription(CharacterDescription description) {
    this.description = description;
  }

  @Override
  public String getName() {
    return description.getName().getText();
  }

  @Override
  public String getPeriphrase() {
    return description.getPeriphrase().getText();
  }

  @Override
  public String getCharacterization() {
    return description.getCharacterization().getText();
  }

  @Override
  public String getPhysicalAppearance() {
    return description.getPhysicalDescription().getText();
  }

  @Override
  public String getEyes() {
    return description.getEyes().getText();
  }

  @Override
  public String getHair() {
    return description.getHair().getText();
  }

  @Override
  public String getSex() {
    return description.getSex().getText();
  }

  @Override
  public String getSkin() {
    return description.getSkin().getText();
  }

  @Override
  public String getAnima() {
    return description.getAnima().getText();
  }

  @Override
  public String getPlayer() {
    return description.getPlayer().getText();
  }

  @Override
  public String getNotes() {
    return description.getNotes().getText();
  }

  @Override
  public String getConceptText() {
    return description.getConcept().getText();
  }
}