package net.sf.anathema.hero.charms.compiler.special;

import net.sf.anathema.hero.charms.model.special.ISpecialCharm;
import net.sf.anathema.hero.charms.model.special.oxbody.OxBodyTechniqueCharm;
import net.sf.anathema.character.main.magic.parser.charms.TraitTypeFinder;
import net.sf.anathema.character.main.magic.parser.dto.special.OxBodyPickDto;
import net.sf.anathema.character.main.magic.parser.dto.special.OxBodyTechniqueDto;
import net.sf.anathema.character.main.magic.parser.dto.special.SpecialCharmDto;
import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.health.HealthLevelType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("UnusedDeclaration")
public class OxBodyCharmBuilder implements SpecialCharmBuilder {
  private static final String TAG_ZERO_HEALTH = "zeroHealthLevel";
  private static final String TAG_ONE_HEALTH = "oneHealthLevel";
  private static final String TAG_TWO_HEALTH = "twoHealthLevel";
  private static final String TAG_FOUR_HEALTH = "fourHealthLevel";
  private static final String TAG_INCAP_HEALTH = "incapHealthLevel";
  private static final String TAG_DYING_HEALTH = "dyingHealthLevel";

  private final TraitTypeFinder traitTypeFinder = new TraitTypeFinder();

  @Override
  public ISpecialCharm readCharm(SpecialCharmDto overallDto) {
    return createSpecialCharm(overallDto.charmId, overallDto.oxBodyTechnique);
  }

  private ISpecialCharm createSpecialCharm(String id, OxBodyTechniqueDto dto) {
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

  @Override
  public boolean supports(SpecialCharmDto overallDto) {
    return overallDto.oxBodyTechnique != null;
  }
}