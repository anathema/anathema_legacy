package net.sf.anathema.character.main;

import net.sf.anathema.hero.description.HeroDescription;

public class GenericDescription implements IGenericDescription {

  private final HeroDescription description;

  public GenericDescription(HeroDescription description) {
    this.description = description;
  }

  @Override
  public String getName() {
    return description.getName().getText();
  }

  @Override
  public String getPeriphrase() {
    return description.getPeriphrasis().getText();
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