package net.sf.anathema.character.main.magic.parser.charms.special;

import net.sf.anathema.character.main.magic.charm.special.ISpecialCharm;
import net.sf.anathema.initialization.ObjectFactory;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ReflectionSpecialCharmBuilder implements SpecialCharmBuilder {

  private final List<SpecialCharmBuilder> builders = new ArrayList<>();

  public ReflectionSpecialCharmBuilder(ObjectFactory objectFactory) {
    Collection<SpecialCharmBuilder> builders = objectFactory.instantiateAll(RegisteredSpecialCharmBuilder.class);
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