package net.sf.anathema.acceptance.fixture.character.traits;

import net.sf.anathema.character.library.trait.IDefaultTrait;

public class AcceptanceBackground {

  public String name;
  public int value;

  public AcceptanceBackground(IDefaultTrait background) {
    this.name = background.getType().getId();
    this.value = background.getCurrentValue();
  }
}