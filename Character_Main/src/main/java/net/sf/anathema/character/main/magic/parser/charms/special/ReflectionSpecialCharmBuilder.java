package net.sf.anathema.character.main.magic.parser.charms.special;

import net.sf.anathema.character.main.magic.charm.special.ISpecialCharm;
import net.sf.anathema.character.main.magic.parser.dto.special.SpecialCharmDto;
import net.sf.anathema.initialization.ObjectFactory;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

public class ReflectionSpecialCharmBuilder {

  private final List<SpecialCharmBuilder> builders = new ArrayList<>();
  private final List<SpecialCharmParser> parsers = new ArrayList<>();

  public ReflectionSpecialCharmBuilder(ObjectFactory objectFactory) {
    this.builders.addAll(objectFactory.<SpecialCharmBuilder>instantiateAll(RegisteredSpecialCharmBuilder.class));
    this.parsers.addAll(objectFactory.<SpecialCharmParser>instantiateAll(RegisteredSpecialCharmParser.class));
  }

  public SpecialCharmDto readCharmDto(Element charmElement, String id) {
    SpecialCharmDto overallDto = new SpecialCharmDto();
    overallDto.charmId = id;
    findParser(charmElement).parse(charmElement, overallDto);
    return overallDto;
  }

  private SpecialCharmParser findParser(Element charmElement) {
    for (SpecialCharmParser parser : parsers) {
      if (parser.supports(charmElement)) {
        return parser;
      }
    }
    return new NullSpecialCharmParser();
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