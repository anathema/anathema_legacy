package net.sf.anathema.character.main.magic.parser.charms.special.oxbody;

import net.sf.anathema.character.main.magic.model.charm.special.ISpecialCharm;
import net.sf.anathema.character.main.magic.model.charm.special.OxBodyTechniqueCharm;
import net.sf.anathema.character.main.magic.parser.charms.TraitTypeFinder;
import net.sf.anathema.character.main.magic.parser.charms.special.SpecialCharmBuilder;
import net.sf.anathema.character.main.magic.parser.charms.special.SpecialCharmParser;
import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.health.HealthLevelType;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@SpecialCharmParser
public class OxBodyCharmBuilder implements SpecialCharmBuilder {
  private static final String TAG_OXBODY_CHARM = "oxbody";
  private static final String TAG_OXBODY_PICK = "pick";
  private static final String TAG_ZERO_HEALTH = "zeroHealthLevel";
  private static final String TAG_ONE_HEALTH = "oneHealthLevel";
  private static final String TAG_TWO_HEALTH = "twoHealthLevel";
  private static final String TAG_FOUR_HEALTH = "fourHealthLevel";
  private static final String TAG_INCAP_HEALTH = "incapHealthLevel";
  private static final String TAG_DYING_HEALTH = "dyingHealthLevel";

  private final TraitTypeFinder traitTypeFinder = new TraitTypeFinder();

  @Override
  public ISpecialCharm readCharm(Element charmElement, String id) {
    OxBodyTechniqueDto dto = createDto(charmElement);
    TraitType[] traitList = new TraitType[dto.traits.size()];
    for (int i = 0; i != traitList.length; i++) {
      traitList[i] = traitTypeFinder.getTrait(dto.traits.get(i));
    }

    LinkedHashMap<String, HealthLevelType[]> healthPicks = new LinkedHashMap<>();
    for (OxBodyPickDto pickDto : dto.picks) {
      String name = pickDto.id;
      List<HealthLevelType> healthLevels = new ArrayList<>();
      Map<String, HealthLevelType> healthTypeByString = getHealthTypeMap();
      for (String healthLevel : pickDto.healthLevels) {
        healthLevels.add(healthTypeByString.get(healthLevel));
       }
       healthPicks.put(name, healthLevels.toArray(new HealthLevelType[healthLevels.size()]));
    }
    return new OxBodyTechniqueCharm(id, traitList, healthPicks);
  }

  private Map<String, HealthLevelType> getHealthTypeMap() {
    Map<String, HealthLevelType> healthTypeByString = new HashMap<>();
    healthTypeByString.put(TAG_ZERO_HEALTH, HealthLevelType.ZERO);
    healthTypeByString.put(TAG_ONE_HEALTH, HealthLevelType.ONE);
    healthTypeByString.put(TAG_TWO_HEALTH, HealthLevelType.TWO);
    healthTypeByString.put(TAG_FOUR_HEALTH, HealthLevelType.FOUR);
    healthTypeByString.put(TAG_INCAP_HEALTH, HealthLevelType.INCAPACITATED);
    healthTypeByString.put(TAG_DYING_HEALTH, HealthLevelType.DYING);
    return healthTypeByString;
  }

  private OxBodyTechniqueDto createDto(Element charmElement) {
    OxBodyTechniqueDto dto = new OxBodyTechniqueDto();
    Element oxBodyElement = charmElement.element(TAG_OXBODY_CHARM);
    dto.traits.addAll(Arrays.asList(oxBodyElement.attributeValue(ATTRIB_TRAIT).split(",")));
    for (Object pickObj : oxBodyElement.elements(TAG_OXBODY_PICK)) {
      Element pick = (Element) pickObj;
      createPickDto(dto, pick);
    }
    return dto;
  }

  private void createPickDto(OxBodyTechniqueDto dto, Element pick) {
    OxBodyPickDto category = new OxBodyPickDto();
    category.id = pick.attributeValue(ATTRIB_NAME);
    for (Object levelObj : pick.elements()) {
      Element levelElement = (Element) levelObj;
      category.healthLevels.add(levelElement.getName());
    }
    dto.picks.add(category);
  }

  @Override
  public boolean willReadCharm(Element charmElement) {
    Element oxbodyElement = charmElement.element(TAG_OXBODY_CHARM);
    return oxbodyElement != null;
  }
}