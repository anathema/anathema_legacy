package net.sf.anathema.charmtree.view;

import net.sf.anathema.character.main.magic.charms.special.ISpecialCharm;
import net.sf.anathema.character.main.magic.charms.special.ISpecialCharmVisitor;

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