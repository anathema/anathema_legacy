package net.sf.anathema.character.main.magic.parser.charms.special.subeffect;

import net.sf.anathema.character.main.magic.charm.special.ISpecialCharm;
import net.sf.anathema.character.main.magic.charm.special.SubEffectCharm;
import net.sf.anathema.character.main.magic.parser.charms.special.RegisteredSpecialCharmBuilder;
import net.sf.anathema.character.main.magic.parser.charms.special.SpecialCharmBuilder;
import net.sf.anathema.character.main.magic.parser.dto.special.SpecialCharmDto;
import net.sf.anathema.character.main.magic.parser.dto.special.SubEffectDto;
import org.dom4j.Element;

@RegisteredSpecialCharmBuilder
public class SubEffectCharmBuilder implements SpecialCharmBuilder {

  private static final String TAG_SUB_EFFECTS = "subeffects";

  @Override
  public ISpecialCharm readCharm(Element charmElement, String id) {
    SpecialCharmDto overallDto = new SpecialCharmDto();
    overallDto.charmId = id;
    new SubEffectParser().parse(charmElement, overallDto);
    return createSpecialCharm(id, overallDto.subEffect);
  }

  private ISpecialCharm createSpecialCharm(String id, SubEffectDto subEffect) {
    String[] effects = subEffect.subEffects.toArray(new String[subEffect.subEffects.size()]);
    return new SubEffectCharm(id, effects, subEffect.cost);
  }

  @Override
  public boolean supports(Element charmElement) {
    return charmElement.element(TAG_SUB_EFFECTS) != null;
  }
}