package net.sf.anathema.character.main.magic.parser.charms.special.paintolerance;

import net.sf.anathema.character.main.magic.charm.special.ISpecialCharm;
import net.sf.anathema.character.main.magic.charm.special.StaticPainToleranceCharm;
import net.sf.anathema.character.main.magic.parser.charms.special.RegisteredSpecialCharmBuilder;
import net.sf.anathema.character.main.magic.parser.charms.special.SpecialCharmBuilder;
import net.sf.anathema.character.main.magic.parser.dto.special.SpecialCharmDto;
import org.dom4j.Element;

@RegisteredSpecialCharmBuilder
public class PainToleranceCharmBuilder implements SpecialCharmBuilder {

  private static final String TAG_PAIN_TOLERANCE = "painTolerance";

  public ISpecialCharm readCharm(Element charmElement, String id) {
    SpecialCharmDto overallDto = new SpecialCharmDto();
    overallDto.charmId = id;
    new PainToleranceParser().parse(charmElement, overallDto);
    return new StaticPainToleranceCharm(id, overallDto.painTolerance);
  }

  @Override
  public boolean willReadCharm(Element charmElement) {
    Element painToleranceElement = charmElement.element(TAG_PAIN_TOLERANCE);
    return painToleranceElement != null;
  }
}