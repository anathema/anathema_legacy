package net.sf.anathema.development.character;

import net.sf.anathema.character.generic.character.IGenericDescription;

public class DemoGenericDescription implements IGenericDescription {

  private String characterization;
  private String name;
  private String periphrase;
  private String appearance;
  private String player;

  public String getAppearance() {
    return appearance;
  }

  public String getCharacterization() {
    return characterization;
  }

  public String getName() {
    return name;
  }

  public String getPeriphrase() {
    return periphrase;
  }

  public String getPhysicalAppearance() {
    return appearance;
  }

  public void setAppearance(String appearance) {
    this.appearance = appearance;
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

  public String getPlayer() {
    return player;
  }

  public void setPlayer(String player) {
    this.player = player;
  }
}