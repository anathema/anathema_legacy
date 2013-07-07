package net.sf.anathema.character.reporting.text.description;

import net.sf.anathema.hero.description.HeroDescription;

public class TextDescriptionContentImpl implements TextDescriptionContent {

  private final HeroDescription description;

  public TextDescriptionContentImpl(HeroDescription description) {
    this.description = description;
  }

  @Override
  public String getName() {
    return description.getName().getText();
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
  public String getNotes() {
    return description.getNotes().getText();
  }

  @Override
  public String getConceptText() {
    return description.getConcept().getText();
  }
}