package net.sf.anathema.character.main.magic.parser.charms.special.repurchase;

import net.sf.anathema.character.main.magic.charm.special.EssenceFixedMultiLearnableCharm;
import net.sf.anathema.character.main.magic.charm.special.ISpecialCharm;
import net.sf.anathema.character.main.magic.parser.charms.special.RegisteredSpecialCharmBuilder;
import net.sf.anathema.character.main.magic.parser.charms.special.SpecialCharmBuilder;
import net.sf.anathema.character.main.magic.parser.dto.special.SpecialCharmDto;
import org.dom4j.Element;

@RegisteredSpecialCharmBuilder
public class EssenceFixedRepurchaseCharmBuilder implements SpecialCharmBuilder {
  private static final String TAG_ESSENCE_FIXED_REPURCHASES = "essenceFixedRepurchases";

  @Override
  public ISpecialCharm readCharm(Element charmElement, String id) {
    SpecialCharmDto overallDto = new SpecialCharmDto();
    overallDto.charmId = id;
    new EssenceFixedRepurchaseParser().parse(charmElement, overallDto);
    return new EssenceFixedMultiLearnableCharm(id);
  }

  @Override
  public boolean supports(Element charmElement) {
    return charmElement.element(TAG_ESSENCE_FIXED_REPURCHASES) != null;
  }
}