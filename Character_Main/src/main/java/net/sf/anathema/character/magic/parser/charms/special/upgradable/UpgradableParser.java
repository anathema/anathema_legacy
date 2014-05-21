package net.sf.anathema.character.magic.parser.charms.special.upgradable;

import net.sf.anathema.character.magic.parser.charms.special.SpecialCharmParser;
import net.sf.anathema.character.magic.parser.dto.special.SpecialCharmDto;
import net.sf.anathema.character.magic.parser.dto.special.UpgradableDto;
import net.sf.anathema.lib.xml.ElementUtilities;
import org.dom4j.Element;

@SuppressWarnings("UnusedDeclaration")
public class UpgradableParser implements SpecialCharmParser {

  private static final String TAG_UPGRADABLE = "upgradeable";
  private static final String TAG_UPGRADE = "upgrade";
  private static final String ATTRIB_BP_COST = "bpCost";
  private static final String ATTRIB_XP_COST = "xpCost";
  private static final String ATTRIB_REQUIRES_BASE = "requiresBase";
  private static final String ATTRIB_TRAIT_VALUE = "traitValue";

  @Override
  public void parse(Element charmElement, SpecialCharmDto overallDto) {
    Element transcendenceElement = charmElement.element(TAG_UPGRADABLE);
    overallDto.upgradable = createUpgradableDto(transcendenceElement, overallDto);
  }

  private UpgradableDto createUpgradableDto(Element upgradableElement, SpecialCharmDto overallDto) {
    UpgradableDto dto = new UpgradableDto();
    dto.requiresBase = ElementUtilities.getBooleanAttribute(upgradableElement, ATTRIB_REQUIRES_BASE, true);
    for (Object upgradeObj : upgradableElement.elements(TAG_UPGRADE)) {
      Element upgrade = (Element) upgradeObj;
      String name = upgrade.attributeValue(ATTRIB_NAME);
      dto.upgrades.add(name);
      try {
        Integer bpCost = ElementUtilities.getIntAttrib(upgrade, ATTRIB_BP_COST, -1);
        if (bpCost != -1) {
          dto.bpCostsByName.put(name, bpCost);
        }

        Integer xpCost = ElementUtilities.getIntAttrib(upgrade, ATTRIB_XP_COST, 1);
        dto.xpCostsByName.put(name, xpCost);

        Integer essenceMin = ElementUtilities.getIntAttrib(upgrade, ATTRIB_ESSENCE, -1);
        if (essenceMin != -1) {
          dto.essenceMinimumsByName.put(name, essenceMin);
        }

        Integer traitMin = ElementUtilities.getIntAttrib(upgrade, ATTRIB_TRAIT_VALUE, -1);
        if (traitMin != -1) {
          dto.traitMinimumsByName.put(name, essenceMin);
        }

        String trait = upgrade.attributeValue(ATTRIB_TRAIT);
        if (trait != null) {
          dto.traitsByName.put(name, trait);
        }
      } catch (Exception ignored) {
      }
    }
    return dto;
  }

  @Override
  public boolean supports(Element charmElement) {
    return charmElement.element(TAG_UPGRADABLE) != null;
  }
}