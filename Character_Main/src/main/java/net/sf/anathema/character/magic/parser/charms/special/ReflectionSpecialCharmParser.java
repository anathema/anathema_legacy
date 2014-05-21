package net.sf.anathema.character.magic.parser.charms.special;

import net.sf.anathema.character.magic.parser.dto.special.SpecialCharmDto;
import net.sf.anathema.framework.environment.ObjectFactory;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

public class ReflectionSpecialCharmParser {

  private final List<SpecialCharmParser> parsers = new ArrayList<>();

  public ReflectionSpecialCharmParser(ObjectFactory objectFactory) {
    this.parsers.addAll(objectFactory.instantiateAllImplementers(SpecialCharmParser.class));
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
}