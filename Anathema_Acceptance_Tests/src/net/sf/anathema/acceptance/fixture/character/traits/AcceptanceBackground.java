package net.sf.anathema.acceptance.fixture.character.traits;

import net.sf.anathema.character.library.trait.IModifiableTrait;

public class AcceptanceBackground {

  public String name;
  public int value;

  public AcceptanceBackground(IModifiableTrait background) {
    this.name = background.getType().getId();
    this.value = background.getCurrentValue();
  }
}