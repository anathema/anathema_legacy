package net.sf.anathema.character.main.magic.persistence.builder.special;

import net.sf.anathema.character.main.magic.model.charm.special.UpgradableCharm;
import net.sf.anathema.character.main.magic.persistence.builder.TraitTypeFinder;
import net.sf.anathema.character.main.magic.model.charm.special.ISpecialCharm;
import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.lib.xml.ElementUtilities;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpecialCharmParser
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
    boolean requiresBase = ElementUtilities.getBooleanAttribute(upgradableElement, ATTRIB_REQUIRES_BASE, true);
    List<String> upgrades = new ArrayList<>();
    Map<String, Integer> bpCosts = new HashMap<>();
    Map<String, Integer> xpCosts = new HashMap<>();
    Map<String, Integer> essenceMins = new HashMap<>();
    Map<String, Integer> traitMins = new HashMap<>();
    Map<String, TraitType> traits = new HashMap<>();

    for (Object upgradeObj : upgradableElement.elements(TAG_UPGRADE)) {
      Element upgrade = (Element) upgradeObj;
      String name = upgrade.attributeValue(ATTRIB_NAME);
      upgrades.add(name);

      try {
        Integer bpCost = ElementUtilities.getIntAttrib(upgrade, ATTRIB_BP_COST, -1);
        if (bpCost != -1) {
          bpCosts.put(name, bpCost);
        }

        Integer xpCost = ElementUtilities.getIntAttrib(upgrade, ATTRIB_XP_COST, 1);
        xpCosts.put(name, xpCost);

        Integer essenceMin = ElementUtilities.getIntAttrib(upgrade, ATTRIB_ESSENCE, -1);
        if (essenceMin != -1) {
          essenceMins.put(name, essenceMin);
        }

        Integer traitMin = ElementUtilities.getIntAttrib(upgrade, ATTRIB_TRAIT_VALUE, -1);
        if (traitMin != -1) {
          traitMins.put(name, essenceMin);
        }

        String trait = upgrade.attributeValue(ATTRIB_TRAIT);
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

  @Override
  public boolean willReadCharm(Element charmElement) {
    Element upgradableElement = charmElement.element(TAG_UPGRADABLE);
    return upgradableElement != null;
  }
}