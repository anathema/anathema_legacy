package net.sf.anathema.character.main.magic.parser.charms.special.subeffect;

import net.sf.anathema.character.main.magic.parser.charms.special.SpecialCharmParser;
import net.sf.anathema.character.main.magic.parser.dto.special.SpecialCharmDto;
import net.sf.anathema.character.main.magic.parser.dto.special.SubEffectDto;
import net.sf.anathema.lib.xml.ElementUtilities;
import org.dom4j.Element;

@SuppressWarnings("UnusedDeclaration")
public class SubEffectParser implements SpecialCharmParser {

  private static final String TAG_SUB_EFFECTS = "subeffects";
  private static final String TAG_SUB_EFFECT = "subeffect";
  private static final String TAG_BP_COST = "bpCost";

  @Override
  public void parse(Element charmElement, SpecialCharmDto overallDto) {
    Element subEffectsElement = charmElement.element(TAG_SUB_EFFECTS);
    overallDto.subEffect = createSubEffectDto(subEffectsElement);
  }

  private SubEffectDto createSubEffectDto(Element subEffectsElement) {
    SubEffectDto dto = new SubEffectDto();
    dto.cost = Double.parseDouble(subEffectsElement.attributeValue(TAG_BP_COST));
    for (Element subEffect : ElementUtilities.elements(subEffectsElement, TAG_SUB_EFFECT)) {
      dto.subEffects.add(subEffect.attributeValue(ATTRIB_NAME));
    }
    return dto;
  }

  @Override
  public boolean supports(Element charmElement) {
    return charmElement.element(TAG_SUB_EFFECTS) != null;
  }
}