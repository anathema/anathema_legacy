package net.sf.anathema.character.main.magic.parser.charms.special.repurchase;

import net.sf.anathema.character.main.magic.parser.charms.special.RegisteredSpecialCharmParser;
import net.sf.anathema.character.main.magic.parser.charms.special.SpecialCharmParser;
import net.sf.anathema.character.main.magic.parser.dto.special.SpecialCharmDto;
import org.dom4j.Element;

@RegisteredSpecialCharmParser
public class EssenceFixedRepurchaseParser implements SpecialCharmParser {

  private static final String TAG_ESSENCE_FIXED_REPURCHASES = "essenceFixedRepurchases";

  @Override
  public void parse(Element charmElement, SpecialCharmDto overallDto) {
    overallDto.isEssenceRepurchase = true;
   }

  @Override
  public boolean supports(Element charmElement) {
    return charmElement.element(TAG_ESSENCE_FIXED_REPURCHASES) != null;
  }
}