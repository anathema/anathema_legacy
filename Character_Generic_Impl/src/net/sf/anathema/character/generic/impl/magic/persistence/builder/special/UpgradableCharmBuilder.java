package net.sf.anathema.character.generic.impl.magic.persistence.builder.special;

import net.sf.anathema.character.generic.impl.magic.charm.special.UpgradableCharm;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.AllSpecialCharmBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.SpecialCharmBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.TraitTypeFinder;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.lib.xml.ElementUtilities;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UpgradableCharmBuilder implements SpecialCharmBuilder {
  private static final String TAG_UPGRADABLE = "upgradeable";
  private static final String TAG_UPGRADE = "upgrade";
  private static final String ATTRIB_BP_COST = "bpCost";
  private static final String ATTRIB_XP_COST = "xpCost";
  private static final String ATTRIB_REQUIRES_BASE = "requiresBase";
  private static final String ATTRIB_TRAIT_VALUE = "traitValue";

  private final TraitTypeFinder traitTypeFinder = new TraitTypeFinder();

  @Override
  public ISpecialCharm readCharm(Element charmElement, String id) {
    Element upgradableElement = charmElement.element(TAG_UPGRADABLE);
    if (upgradableElement == null) {
      return null;
    }
    boolean requiresBase = ElementUtilities.getBooleanAttribute(upgradableElement, ATTRIB_REQUIRES_BASE, true);
    List<String> upgrades = new ArrayList<>();
    Map<String, Integer> bpCosts = new HashMap<>();
    Map<String, Integer> xpCosts = new HashMap<>();
    Map<String, Integer> essenceMins = new HashMap<>();
    Map<String, Integer> traitMins = new HashMap<>();
    Map<String, ITraitType> traits = new HashMap<>();

    for (Object upgradeObj : upgradableElement.elements(TAG_UPGRADE)) {
      Element upgrade = (Element) upgradeObj;
      String name = upgrade.attributeValue(AllSpecialCharmBuilder.ATTRIB_NAME);
      upgrades.add(name);

      try {
        Integer bpCost = ElementUtilities.getIntAttrib(upgrade, ATTRIB_BP_COST, -1);
        if (bpCost != -1) {
          bpCosts.put(name, bpCost);
        }

        Integer xpCost = ElementUtilities.getIntAttrib(upgrade, ATTRIB_XP_COST, 1);
        xpCosts.put(name, xpCost);

        Integer essenceMin = ElementUtilities.getIntAttrib(upgrade, AllSpecialCharmBuilder.ATTRIB_ESSENCE, -1);
        if (essenceMin != -1) {
          essenceMins.put(name, essenceMin);
        }

        Integer traitMin = ElementUtilities.getIntAttrib(upgrade, ATTRIB_TRAIT_VALUE, -1);
        if (traitMin != -1) {
          traitMins.put(name, essenceMin);
        }

        String trait = upgrade.attributeValue(AllSpecialCharmBuilder.ATTRIB_TRAIT);
        if (trait != null) {
          traits.put(name, traitTypeFinder.getTrait(trait));
        }

      } catch (Exception ignored) {
      }
    }
    String[] upgradeArray = new String[upgrades.size()];
    upgrades.toArray(upgradeArray);
    return new UpgradableCharm(id, upgradeArray, requiresBase, bpCosts, xpCosts, essenceMins, traitMins, traits);
  }
}
