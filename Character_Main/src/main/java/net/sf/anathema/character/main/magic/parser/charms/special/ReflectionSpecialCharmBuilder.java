package net.sf.anathema.character.main.magic.parser.charms.special;

import net.sf.anathema.character.main.magic.charm.special.ISpecialCharm;
import net.sf.anathema.initialization.ObjectFactory;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

public class ReflectionSpecialCharmBuilder {

  private final List<SpecialCharmBuilder> builders = new ArrayList<>();
  private final List<SpecialCharmBuilder> parsers = new ArrayList<>();

  public ReflectionSpecialCharmBuilder(ObjectFactory objectFactory) {
    this.builders.addAll(objectFactory.<SpecialCharmBuilder>instantiateAll(RegisteredSpecialCharmBuilder.class));
    this.parsers.addAll(objectFactory.<SpecialCharmBuilder>instantiateAll(RegisteredSpecialCharmParser.class));
  }

  public ISpecialCharm readCharm(Element charmElement, String id) {
    for (SpecialCharmBuilder builder : builders) {
      if (!builder.supports(charmElement)) {
        continue;
      }
      return builder.readCharm(charmElement, id);
    }
    return null;
  }

  public boolean supports(Element charmElement) {
    for (SpecialCharmBuilder builder : builders) {
      if (builder.supports(charmElement)) {
        return true;
      }
    }
    return false;
  }
}