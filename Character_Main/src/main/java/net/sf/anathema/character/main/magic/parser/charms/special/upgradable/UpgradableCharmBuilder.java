package net.sf.anathema.character.main.magic.parser.charms.special.upgradable;

import net.sf.anathema.character.main.magic.charm.special.ISpecialCharm;
import net.sf.anathema.character.main.magic.charm.special.UpgradableCharm;
import net.sf.anathema.character.main.magic.parser.charms.TraitTypeFinder;
import net.sf.anathema.character.main.magic.parser.charms.special.RegisteredSpecialCharmBuilder;
import net.sf.anathema.character.main.magic.parser.charms.special.SpecialCharmBuilder;
import net.sf.anathema.character.main.magic.parser.dto.special.SpecialCharmDto;
import net.sf.anathema.character.main.magic.parser.dto.special.UpgradableDto;
import net.sf.anathema.character.main.traits.TraitType;
import org.dom4j.Element;

import java.util.HashMap;
import java.util.Map;

@RegisteredSpecialCharmBuilder
public class UpgradableCharmBuilder implements SpecialCharmBuilder {
  private static final String TAG_UPGRADABLE = "upgradeable";

  private final TraitTypeFinder traitTypeFinder = new TraitTypeFinder();

  @Override
  public ISpecialCharm readCharm(Element charmElement, String id) {
    SpecialCharmDto overallDto = new SpecialCharmDto();
    overallDto.charmId = id;
    new UpgradableParser().parse(charmElement, overallDto);
    UpgradableDto dto = overallDto.upgradable;
    return new UpgradableCharm(id, createUpgrades(dto), dto.requiresBase, dto.bpCostsByName, dto.xpCostsByName, dto.essenceMinimumsByName,
            dto.traitMinimumsByName, createTraitsMap(dto));
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
  public boolean supports(Element charmElement) {
    return charmElement.element(TAG_UPGRADABLE) != null;
  }
}