package net.sf.anathema.hero.charms.model.special;

import net.sf.anathema.hero.charms.model.special.charms.ISpecialCharm;
import net.sf.anathema.hero.charms.model.special.charms.ISpecialCharmVisitor;

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