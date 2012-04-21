package net.sf.anathema.character.generic.framework.magic.stringbuilder;

import com.google.common.collect.Maps;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;

import java.util.Map;

public class CharmInfoStringCache implements ICharmInfoStringBuilder {
  private final Map<ICharm, String> cache = Maps.newHashMap();
  private final ICharmInfoStringBuilder builder;

  public CharmInfoStringCache(ICharmInfoStringBuilder builder) {
    this.builder = builder;
  }

  @Override
  public String getInfoString(ICharm charm, ISpecialCharm special) {
    if (cache.containsKey(charm)) {
      return cache.get(charm);
    }
    String result = builder.getInfoString(charm, special);
    cache.put(charm, result);
    return result;
  }
}