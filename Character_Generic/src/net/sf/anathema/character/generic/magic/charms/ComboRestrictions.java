package net.sf.anathema.character.generic.magic.charms;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.type.CharmType;
import net.sf.anathema.character.generic.traits.ITraitType;

public class ComboRestrictions implements IComboRestrictions {

  private final boolean allAbilities;
  private final Boolean combosAllowed;
  private final List<String> restrictedCharmIds = new ArrayList<String>();
  private final List<CharmType> restrictedCharmTypes = new ArrayList<CharmType>();
  private final List<ITraitType> restrictedTraitTypes = new ArrayList<ITraitType>();

  public ComboRestrictions() {
    this(false, null);
  }

  public ComboRestrictions(boolean allAbilities, Boolean combosAllowed) {
    this.allAbilities = allAbilities;
    this.combosAllowed = combosAllowed;
  }

  public void addRestrictedCharmId(String charmId) {
    restrictedCharmIds.add(charmId);
  }

  public void addRestrictedCharmType(CharmType charmType) {
    restrictedCharmTypes.add(charmType);
  }

  public void addRestrictedTraitType(ITraitType traitType) {
    restrictedTraitTypes.add(traitType);
  }

  public boolean combosAllAbilities() {
    return allAbilities;
  }

  public ITraitType[] getRestrictedTraitTypes() {
    return restrictedTraitTypes.toArray(new ITraitType[0]);
  }

  public boolean isComboAllowed(boolean isAllowedByDefault) {
    return combosAllowed == null ? isAllowedByDefault : combosAllowed.booleanValue();
  }

  public boolean isRestrictedCharm(ICharm charm) {
    if (restrictedCharmTypes.contains(charm.getCharmTypeModel().getCharmType())) {
      return true;
    }
    if (restrictedCharmIds.contains(charm.getId())) {
      return true;
    }
    return false;
  }
}