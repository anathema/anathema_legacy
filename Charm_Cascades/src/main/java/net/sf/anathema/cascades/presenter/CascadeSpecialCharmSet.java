package net.sf.anathema.cascades.presenter;

import net.sf.anathema.hero.charms.compiler.CharmCache;
import net.sf.anathema.hero.charms.display.view.SpecialCharmSet;
import net.sf.anathema.hero.charms.model.special.ISpecialCharm;
import net.sf.anathema.lib.util.Identifier;

import java.util.Arrays;
import java.util.Iterator;

public class CascadeSpecialCharmSet implements SpecialCharmSet {
  private Identifier type;
  private final CharmCache cache;

  public CascadeSpecialCharmSet(CharmCache cache) {
    this.cache = cache;
  }

  public void setType(Identifier type){
    this.type = type;
  }

  @Override
  public Iterator<ISpecialCharm> iterator() {
    ISpecialCharm[] specialCharms = cache.getCharmProvider().getSpecialCharms(type);
    return Arrays.asList(specialCharms).iterator();
  }
}
