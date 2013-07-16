package net.sf.anathema.character.main.magic.parser.charms.special.multi;

import net.sf.anathema.character.main.magic.charm.special.ComplexMultipleEffectCharm;
import net.sf.anathema.character.main.magic.charm.special.ISpecialCharm;
import net.sf.anathema.character.main.magic.parser.charms.special.RegisteredSpecialCharmBuilder;
import net.sf.anathema.character.main.magic.parser.charms.special.SpecialCharmBuilder;
import net.sf.anathema.character.main.magic.parser.dto.special.MultiEffectDto;
import net.sf.anathema.character.main.magic.parser.dto.special.SpecialCharmDto;
import org.dom4j.Element;

@RegisteredSpecialCharmBuilder
public class MultiEffectCharmBuilder implements SpecialCharmBuilder {
  private static final String TAG_MULTI_EFFECT = "multiEffects";

  @Override
  public ISpecialCharm readCharm(Element charmElement, String id) {
    SpecialCharmDto overallDto = new SpecialCharmDto();
    overallDto.charmId = id;
    new MultiEffectParser().parse(charmElement, overallDto);
    return createSpecialCharm(id, overallDto);
  }

  private ISpecialCharm createSpecialCharm(String id, SpecialCharmDto overallDto) {
    MultiEffectDto multiEffect = overallDto.multiEffect;
    String[] effects = multiEffect.effects.toArray(new String[multiEffect.effects.size()]);
    return new ComplexMultipleEffectCharm(id, effects, multiEffect.prerequisiteEffectMap);
  }

  @Override
  public boolean willReadCharm(Element charmElement) {
    return charmElement.element(TAG_MULTI_EFFECT) != null;
  }
}