package net.sf.anathema.character.main.magic.parser.charms.special.paintolerance;

import net.sf.anathema.character.main.magic.parser.charms.special.RegisteredSpecialCharmParser;
import net.sf.anathema.character.main.magic.parser.charms.special.SpecialCharmParser;
import net.sf.anathema.character.main.magic.parser.dto.special.PainToleranceDto;
import net.sf.anathema.character.main.magic.parser.dto.special.SpecialCharmDto;
import org.dom4j.Element;

import java.util.List;

@RegisteredSpecialCharmParser
public class PainToleranceParser implements SpecialCharmParser {

  private static final String ATTRIB_VALUE = "value";
  private static final String TAG_PAIN_TOLERANCE = "painTolerance";
  private static final String TAG_LEVEL = "level";

  @Override
  public void parse(Element charmElement, SpecialCharmDto overallDto) {
    Element painToleranceElement = charmElement.element(TAG_PAIN_TOLERANCE);
    PainToleranceDto dto = new PainToleranceDto();
    List<Element> elements = painToleranceElement.elements(TAG_LEVEL);
    for (Element levelElement : elements) {
      dto.levels.add(parseValue(levelElement));
    }
    dto.learnCount = dto.levels.size();
    overallDto.painTolerance = dto;
  }

  private int parseValue(Element levelElement) {
    String attributeValue = levelElement.attributeValue(ATTRIB_VALUE);
    return Integer.parseInt(attributeValue);
  }

  @Override
  public boolean supports(Element charmElement) {
    return charmElement.element(TAG_PAIN_TOLERANCE) != null;
  }
}