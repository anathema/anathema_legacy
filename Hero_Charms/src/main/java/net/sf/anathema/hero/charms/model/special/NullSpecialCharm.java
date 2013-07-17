package net.sf.anathema.hero.charms.model.special;

import net.sf.anathema.character.main.magic.charm.special.charms.ISpecialCharm;
import net.sf.anathema.character.main.magic.charm.special.charms.ISpecialCharmVisitor;

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