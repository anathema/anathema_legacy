package net.sf.anathema.character.main.magic.model.charmtree.builder.stringbuilder;

import com.google.common.collect.Maps;
import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.model.charm.special.ISpecialCharm;

import java.util.Map;

public class CharmInfoStringCache implements ICharmInfoStringBuilder {
  private final Map<Charm, String> cache = Maps.newHashMap();
  private final ICharmInfoStringBuilder builder;

  public CharmInfoStringCache(ICharmInfoStringBuilder builder) {
    this.builder = builder;
  }

  @Override
  public String getInfoString(Charm charm, ISpecialCharm special) {
    if (cache.containsKey(charm)) {
      return cache.get(charm);
    }
    String result = builder.getInfoString(charm, special);
    cache.put(charm, result);
    return result;
  }
}