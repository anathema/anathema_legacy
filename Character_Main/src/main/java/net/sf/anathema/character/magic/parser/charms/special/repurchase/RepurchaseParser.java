package net.sf.anathema.character.magic.parser.charms.special.repurchase;

import net.sf.anathema.character.magic.parser.charms.special.SpecialCharmParser;
import net.sf.anathema.character.magic.parser.dto.special.RepurchaseDto;
import net.sf.anathema.character.magic.parser.dto.special.RequirementDto;
import net.sf.anathema.character.magic.parser.dto.special.SpecialCharmDto;
import net.sf.anathema.character.magic.parser.dto.special.StaticRepurchaseDto;
import net.sf.anathema.character.magic.parser.dto.special.TierDto;
import net.sf.anathema.character.magic.parser.dto.special.TierRepurchaseDto;
import net.sf.anathema.character.magic.parser.dto.special.TraitRepurchaseDto;
import net.sf.anathema.hero.traits.model.SystemConstants;
import net.sf.anathema.lib.xml.ElementUtilities;
import org.dom4j.Element;

@SuppressWarnings("UnusedDeclaration")
public class RepurchaseParser implements SpecialCharmParser {

  private static final String TAG_REPURCHASES = "repurchases";
  private static final String TAG_REPURCHASE = "repurchase";
  private static final String ATTRIB_ABSOLUTE_MAX = "absoluteMax";
  private static final String ATTRIB_LIMITING_TRAIT = "limitingTrait";
  private static final String ATTRIB_LIMIT = "limit";

  @Override
  public void parse(Element charmElement, SpecialCharmDto overallDto) {
    Element repurchasesElement = charmElement.element(TAG_REPURCHASES);
    overallDto.repurchase = createRepurchaseDto(repurchasesElement);
  }

  private RepurchaseDto createRepurchaseDto(Element repurchasesElement) {
    RepurchaseDto dto = new RepurchaseDto();
    parseTraitRepurchase(dto, repurchasesElement);
    if (dto.traitRepurchase != null) {
      return dto;
    }
    parseStaticRepurchase(dto, repurchasesElement);
    if (dto.staticRepurchase != null) {
      return dto;
    }
    parseTierRepurchaseDto(dto, repurchasesElement);
    return dto;
  }

  private void parseTraitRepurchase(RepurchaseDto dto, Element repurchasesElement) {
    String limitingTraitString = repurchasesElement.attributeValue(ATTRIB_LIMITING_TRAIT);
    if (limitingTraitString != null) {
      TraitRepurchaseDto traitRepurchase = new TraitRepurchaseDto();
      traitRepurchase.limitingTrait = limitingTraitString;
      traitRepurchase.modifier = parseModifier(repurchasesElement);
      traitRepurchase.absoluteMax = parseAbsoluteMaximum(repurchasesElement, traitRepurchase.modifier);
      dto.traitRepurchase = traitRepurchase;
    }
  }

  private void parseStaticRepurchase(RepurchaseDto dto, Element repurchasesElement) {
    String limitString = repurchasesElement.attributeValue(ATTRIB_LIMIT);
    if (limitString != null) {
      StaticRepurchaseDto staticRepurchase = new StaticRepurchaseDto();
      staticRepurchase.limit = Integer.parseInt(limitString);
      dto.staticRepurchase = staticRepurchase;
    }
  }

  private void parseTierRepurchaseDto(RepurchaseDto dto, Element repurchasesElement) {
    TierRepurchaseDto repurchaseDto = new TierRepurchaseDto();
    String trait = repurchasesElement.attributeValue(ATTRIB_TRAIT);
    for (Element repurchaseElement : ElementUtilities.elements(repurchasesElement, TAG_REPURCHASE)) {
      TierDto tierDto = parseTierDto(trait, repurchaseElement);
      repurchaseDto.tiers.add(tierDto);
    }
    dto.tierRepurchase = repurchaseDto;
  }

  private TierDto parseTierDto(String traitString, Element repurchase) {
    TierDto tierDto = new TierDto();
    int essence = ElementUtilities.getRequiredIntAttrib(repurchase, ATTRIB_ESSENCE);
    tierDto.requirements.add(createRequirementDto("Essence", essence));
    if (traitString != null) {
      int traitValue = ElementUtilities.getRequiredIntAttrib(repurchase, ATTRIB_TRAIT);
      tierDto.requirements.add(createRequirementDto(traitString, traitValue));
    }
    return tierDto;
  }

  private RequirementDto createRequirementDto(String traitType, int traitValue) {
    RequirementDto dto = new RequirementDto();
    dto.traitType = traitType;
    dto.traitValue = traitValue;
    return dto;
  }

  private int parseAbsoluteMaximum(Element repurchaseElement, int modifier) {
    int absoluteMax = SystemConstants.SYSTEM_ESSENCE_MAX + modifier;
    String maxString = repurchaseElement.attributeValue(ATTRIB_ABSOLUTE_MAX);
    try {
      absoluteMax = Integer.parseInt(maxString);
    } catch (Exception ignored) {
    }
    return absoluteMax;
  }

  private int parseModifier(Element repurchaseElement) {
    int modifier = 0;
    String modifierString = repurchaseElement.attributeValue(ATTRIB_MODIFIER);
    try {
      modifier = Integer.parseInt(modifierString);
    } catch (Exception ignored) {
    }
    return modifier;
  }

  @Override
  public boolean supports(Element charmElement) {
    return charmElement.element(TAG_REPURCHASES) != null;
  }
}