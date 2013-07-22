package net.sf.anathema.character.main.magic.parser.charms.special.traitcap;

import net.sf.anathema.character.main.magic.parser.charms.special.RegisteredSpecialCharmParser;
import net.sf.anathema.character.main.magic.parser.charms.special.SpecialCharmParser;
import net.sf.anathema.character.main.magic.parser.dto.special.SpecialCharmDto;
import net.sf.anathema.character.main.magic.parser.dto.special.TraitCapModifierDto;
import org.dom4j.Element;

@RegisteredSpecialCharmParser
public class TraitCapModifierParser implements SpecialCharmParser {

  private static final String TAG_TRAIT_CAP_MODIFIER = "traitCapModifier";
  private static final String ATTRIB_MODIFIER = "modifier";
  private static final String ATTRIB_TRAIT = "trait";

  @Override
  public void parse(Element charmElement, SpecialCharmDto overallDto) {
    Element traitCapElement = charmElement.element(TAG_TRAIT_CAP_MODIFIER);
    overallDto.traitCapModifier = createCapModifierDto(traitCapElement, overallDto);
  }

  private TraitCapModifierDto createCapModifierDto(Element element, SpecialCharmDto overallDto) {
    TraitCapModifierDto dto = new TraitCapModifierDto();
    dto.trait = readTraitString(overallDto.charmId, element);
    dto.modifier = Integer.parseInt(element.attributeValue(ATTRIB_MODIFIER));
    return dto;
  }

  private String readTraitString(String id, Element traitCapModifierElement) {
    String traitString = traitCapModifierElement.attributeValue(ATTRIB_TRAIT);
    if (traitString == null) {
      traitString = id.split("\\.")[2];
    }
    return traitString;
  }

  @Override
  public boolean supports(Element charmElement) {
    return charmElement.element(TAG_TRAIT_CAP_MODIFIER) != null;
  }
}