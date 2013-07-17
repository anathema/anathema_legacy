package net.sf.anathema.hero.charms.compiler.special;

import net.sf.anathema.hero.charms.model.special.ISpecialCharm;
import net.sf.anathema.hero.charms.model.special.upgradable.UpgradableCharm;
import net.sf.anathema.character.main.magic.parser.charms.TraitTypeFinder;
import net.sf.anathema.character.main.magic.parser.dto.special.SpecialCharmDto;
import net.sf.anathema.character.main.magic.parser.dto.special.UpgradableDto;
import net.sf.anathema.character.main.traits.TraitType;

import java.util.HashMap;
import java.util.Map;

@RegisteredSpecialCharmBuilder
public class UpgradableCharmBuilder implements SpecialCharmBuilder {

  private final TraitTypeFinder traitTypeFinder = new TraitTypeFinder();

  @Override
  public ISpecialCharm readCharm(SpecialCharmDto overallDto) {
    UpgradableDto dto = overallDto.upgradable;
    return new UpgradableCharm(overallDto.charmId, createUpgrades(dto), dto.requiresBase, dto.bpCostsByName, dto.xpCostsByName,
            dto.essenceMinimumsByName, dto.traitMinimumsByName, createTraitsMap(dto));
  }

  private Map<String, TraitType> createTraitsMap(UpgradableDto dto) {
    Map<String, TraitType> traits = new HashMap<>();
    for (Map.Entry<String, String> entry : dto.traitsByName.entrySet()) {
      traits.put(entry.getKey(), traitTypeFinder.getTrait(entry.getValue()));
    }
    return traits;
  }

  private String[] createUpgrades(UpgradableDto dto) {
    return dto.upgrades.toArray(new String[dto.upgrades.size()]);
  }

  @Override
  public boolean supports(SpecialCharmDto overallDto) {
    return overallDto.upgradable != null;
  }
}