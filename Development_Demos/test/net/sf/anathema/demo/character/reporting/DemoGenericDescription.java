package net.sf.anathema.demo.character.reporting;

import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.lib.exception.NotYetImplementedException;

public class DemoGenericDescription implements IGenericDescription {

  private String characterization;
  private String name;
  private String periphrase;
  private String appearance;
  private String eyes;
  private String hair;
  private String sex;
  private String skin;
  private String player;
  private String concept;

  public String getAppearance() {
    return appearance;
  }

  @Override
  public String getCharacterization() {
    return characterization;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public String getPeriphrase() {
    return periphrase;
  }

  @Override
  public String getPhysicalAppearance() {
    return appearance;
  }

  @Override
  public String getEyes() {
    return eyes;
  }

  @Override
  public String getHair() {
    return hair;
  }

  @Override
  public String getSex() {
    return sex;
  }

  @Override
  public String getSkin() {
    return skin;
  }

  public void setAppearance(String appearance) {
    this.appearance = appearance;
  }
  
  public void setEyes(String eyes) {
    this.eyes = eyes;
  }
  
  public void setHair(String hair) {
    this.hair = hair;
  }
  
  public void setSex(String sex) {
    this.sex = sex;
  }
  
  public void setSkin(String skin) {
    this.skin = skin;
  }

  public void setCharacterization(String characterization) {
    this.characterization = characterization;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setPeriphrase(String periphrase) {
    this.periphrase = periphrase;
  }

  @Override
  public String getPlayer() {
    return player;
  }

  public void setPlayer(String player) {
    this.player = player;
  }

  public void setConcept(String concept) {
    this.concept = concept;
  }

  @Override
  public String getNotes() {
    throw new NotYetImplementedException();
  }

  @Override
  public String getConceptText() {
    return concept;
  }
}