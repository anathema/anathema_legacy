package net.sf.anathema.charmtree.view;

import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharmVisitor;

public class NullSpecialCharm implements ISpecialCharm {
  @Override
  public void accept(ISpecialCharmVisitor visitor) {
    //nothing to do
  }

  @Override
  public String getCharmId() {
    return "";
  }
}