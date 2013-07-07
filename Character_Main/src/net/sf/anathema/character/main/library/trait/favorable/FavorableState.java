package net.sf.anathema.character.main.library.trait.favorable;

import net.sf.anathema.lib.util.Identifier;

public enum FavorableState implements Identifier {

  Default, Favored, Caste;

  @Override
  public String getId() {
    return name();
  }
}