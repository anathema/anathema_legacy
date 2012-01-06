package net.sf.anathema.character.generic.impl.magic.persistence.builder;

import net.sf.anathema.character.generic.health.HealthLevelType;
import net.sf.anathema.character.generic.impl.magic.charm.special.OxBodyTechniqueCharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.character.generic.traits.ITraitType;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static net.sf.anathema.character.generic.impl.magic.persistence.builder.SpecialCharmBuilder.ATTRIB_NAME;
import static net.sf.anathema.character.generic.impl.magic.persistence.builder.SpecialCharmBuilder.ATTRIB_TRAIT;

public class OxBodyCharmBuilder {
  private static final String TAG_OXBODY_CHARM = "oxbody";
  private static final String TAG_OXBODY_PICK = "pick";
  private static final String TAG_ZERO_HEALTH = "zeroHealthLevel";
  private static final String TAG_ONE_HEALTH = "oneHealthLevel";
  private static final String TAG_TWO_HEALTH = "twoHealthLevel";
  private static final String TAG_FOUR_HEALTH = "fourHealthLevel";
  private static final String TAG_INCAP_HEALTH = "incapHealthLevel";
  private static final String TAG_DYING_HEALTH = "dyingHealthLevel";

  private TraitTypeFinder traitTypeFinder = new TraitTypeFinder();

  public ISpecialCharm readOxBodyCharm(Element charmElement, String id) {
    Element oxbodyElement = charmElement.element(TAG_OXBODY_CHARM);
    if (oxbodyElement == null)
      return null;
    String[] traitNameList = oxbodyElement.attributeValue(ATTRIB_TRAIT).split(",");
    ITraitType[] traitList = new ITraitType[traitNameList.length];
    for (int i = 0; i != traitList.length; i++)
      traitList[i] = traitTypeFinder.getTrait(traitNameList[i]);
    LinkedHashMap<String, HealthLevelType[]> healthPicks = new LinkedHashMap<String, HealthLevelType[]>();
    for (Object pickObj : oxbodyElement.elements(TAG_OXBODY_PICK)) {
      Element pick = (Element) pickObj;
      String name = pick.attributeValue(ATTRIB_NAME);
      List<HealthLevelType> healthLevels = new ArrayList<HealthLevelType>();
      for (Object levelObj : pick.elements()) {
        Element levelElement = (Element) levelObj;
        if (levelElement.getName().equals(TAG_ZERO_HEALTH))
          healthLevels.add(HealthLevelType.ZERO);
        if (levelElement.getName().equals(TAG_ONE_HEALTH))
          healthLevels.add(HealthLevelType.ONE);
        if (levelElement.getName().equals(TAG_TWO_HEALTH))
          healthLevels.add(HealthLevelType.TWO);
        if (levelElement.getName().equals(TAG_FOUR_HEALTH))
          healthLevels.add(HealthLevelType.FOUR);
        if (levelElement.getName().equals(TAG_INCAP_HEALTH))
          healthLevels.add(HealthLevelType.INCAPACITATED);
        if (levelElement.getName().equals(TAG_DYING_HEALTH))
          healthLevels.add(HealthLevelType.DYING);
      }
      HealthLevelType[] levels = new HealthLevelType[healthLevels.size()];
      healthLevels.toArray(levels);
      healthPicks.put(name, levels);
    }
    return new OxBodyTechniqueCharm(id, traitList, healthPicks);
  }
}