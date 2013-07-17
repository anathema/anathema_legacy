package net.sf.anathema.hero.charms.compiler.special;

import net.sf.anathema.hero.charms.model.special.charms.ISpecialCharm;
import net.sf.anathema.character.main.magic.parser.dto.special.SpecialCharmDto;
import net.sf.anathema.initialization.ObjectFactory;

import java.util.ArrayList;
import java.util.List;

public class ReflectionSpecialCharmBuilder {

  private final List<SpecialCharmBuilder> builders = new ArrayList<>();

  public ReflectionSpecialCharmBuilder(ObjectFactory objectFactory) {
    this.builders.addAll(objectFactory.<SpecialCharmBuilder>instantiateAll(RegisteredSpecialCharmBuilder.class));
  }

  public ISpecialCharm readCharm(SpecialCharmDto overallDto) {
    return findBuilder(overallDto).readCharm(overallDto);
  }

  private SpecialCharmBuilder findBuilder(SpecialCharmDto dto) {
    for (SpecialCharmBuilder builder : builders) {
      if (builder.supports(dto)) {
        return builder;
      }
    }
    return new NullSpecialCharmBuilder();
  }
}