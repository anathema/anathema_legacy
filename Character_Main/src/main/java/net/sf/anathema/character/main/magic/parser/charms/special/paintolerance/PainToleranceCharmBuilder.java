package net.sf.anathema.character.main.magic.parser.charms.special.paintolerance;

import net.sf.anathema.character.main.magic.model.charm.special.ISpecialCharm;
import net.sf.anathema.character.main.magic.model.charm.special.StaticPainToleranceCharm;
import net.sf.anathema.character.main.magic.parser.charms.special.SpecialCharmBuilder;
import net.sf.anathema.character.main.magic.parser.charms.special.SpecialCharmParser;
import org.dom4j.Element;

import java.util.List;

@SpecialCharmParser
public class PainToleranceCharmBuilder implements SpecialCharmBuilder {

  private static final String ATTRIB_VALUE = "value";
  private static final String TAG_PAIN_TOLERANCE = "painTolerance";
  private static final String TAG_LEVEL = "level";

  public ISpecialCharm readCharm(Element charmElement, String id) {
    PainToleranceDto dto = createDto(charmElement.element(TAG_PAIN_TOLERANCE));
    return new StaticPainToleranceCharm(id, dto);
  }

  private PainToleranceDto createDto(Element painToleranceElement) {
    PainToleranceDto dto = new PainToleranceDto();
    List<Element> elements = painToleranceElement.elements(TAG_LEVEL);
    for (Element levelElement : elements) {
      String attributeValue = levelElement.attributeValue(ATTRIB_VALUE);
      dto.levels.add(Integer.parseInt(attributeValue));
    }
    dto.learnCount = dto.levels.size();
    return dto;
  }

  @Override
  public boolean willReadCharm(Element charmElement) {
    Element painToleranceElement = charmElement.element(TAG_PAIN_TOLERANCE);
    return painToleranceElement != null;
  }
}