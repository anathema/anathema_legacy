package net.sf.anathema.character.main.magic.combos;

import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.hero.charmtree.type.CharmType;
import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.character.main.traits.types.AbilityType;

import java.util.ArrayList;
import java.util.List;

public class ComboRestrictions implements IComboRestrictions {

  private final boolean allAbilities;
  private final String[] selectAbilities;
  private final List<String> restrictedCharmIds = new ArrayList<>();
  private final List<CharmType> restrictedCharmTypes = new ArrayList<>();
  private final List<TraitType> restrictedTraitTypes = new ArrayList<>();

  public ComboRestrictions() {
    this(false, "");
  }

  public ComboRestrictions(boolean allAbilities, String selectAbilities) {
    this.allAbilities = allAbilities;
    this.selectAbilities = selectAbilities.split(",");
  }

  public void addRestrictedCharmId(String charmId) {
    restrictedCharmIds.add(charmId);
  }

  public void addRestrictedCharmType(CharmType charmType) {
    restrictedCharmTypes.add(charmType);
  }

  public void addRestrictedTraitType(TraitType traitType) {
    restrictedTraitTypes.add(traitType);
  }

  @Override
  public boolean combosAllAbilities() {
    return allAbilities;
  }

  @Override
  public boolean combosSelectAbility(AbilityType type) {
    for (String ability : selectAbilities)
      if (type.getId().equals(ability)) return true;
    return false;
  }

  @Override
  public TraitType[] getRestrictedTraitTypes() {
    return restrictedTraitTypes.toArray(new TraitType[restrictedTraitTypes.size()]);
  }

  @Override
  public boolean isRestrictedCharm(Charm charm) {
    if (restrictedCharmTypes.contains(charm.getCharmTypeModel().getCharmType())) {
      return true;
    }
    return restrictedCharmIds.contains(charm.getId());
  }
}