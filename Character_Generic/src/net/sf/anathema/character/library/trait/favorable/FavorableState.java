package net.sf.anathema.character.library.trait.favorable;

import net.sf.anathema.lib.util.Identified;

public enum FavorableState implements Identified {

  Default, Favored, Caste;

  @Override
  public String getId() {
    return name();
  }
}