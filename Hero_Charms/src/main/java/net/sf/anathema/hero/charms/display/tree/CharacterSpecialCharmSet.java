package net.sf.anathema.hero.charms.display.tree;

import net.sf.anathema.hero.charms.display.model.CharmDisplayModel;
import net.sf.anathema.hero.charms.display.view.SpecialCharmSet;
import net.sf.anathema.hero.charms.model.special.ISpecialCharm;

import java.util.Arrays;
import java.util.Iterator;

public class CharacterSpecialCharmSet implements SpecialCharmSet {
  private CharmDisplayModel model;

  public CharacterSpecialCharmSet(CharmDisplayModel model) {
    this.model = model;
  }

  @Override
  public Iterator<ISpecialCharm> iterator() {
    ISpecialCharm[] specialCharms = model.getCharmModel().getSpecialCharms();
    return Arrays.asList(specialCharms).iterator();
  }
}
