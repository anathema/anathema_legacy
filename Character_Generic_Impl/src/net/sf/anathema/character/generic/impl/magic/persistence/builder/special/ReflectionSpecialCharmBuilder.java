package net.sf.anathema.character.generic.impl.magic.persistence.builder.special;

import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.initialization.Instantiater;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ReflectionSpecialCharmBuilder implements SpecialCharmBuilder {

  private final List<SpecialCharmBuilder> builders = new ArrayList<>();

  public ReflectionSpecialCharmBuilder(Instantiater instantiater) {
    Collection<SpecialCharmBuilder> builders
            = instantiater.instantiateAll(SpecialCharmParser.class);
    this.builders.addAll(builders);
  }

  public ISpecialCharm readCharm(Element charmElement, String id) {
    for (SpecialCharmBuilder builder : builders) {
      if (!builder.willReadCharm(charmElement)) {
        continue;
      }
      return builder.readCharm(charmElement, id);
    }
    return null;
  }

  @Override
  public boolean willReadCharm(Element charmElement) {
    for (SpecialCharmBuilder builder : builders) {
      if (builder.willReadCharm(charmElement)) {
        return true;
      }
    }
    return false;
  }
}