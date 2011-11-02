package net.sf.anathema.character.generic.magic.charms;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.type.CharmType;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AbilityType;

public class ComboRestrictions implements IComboRestrictions {

  private final boolean allAbilities;
  private final String[] selectAbilities;
  private final Boolean combosAllowed;
  private final List<String> restrictedCharmIds = new ArrayList<String>();
  private final List<CharmType> restrictedCharmTypes = new ArrayList<CharmType>();
  private final List<ITraitType> restrictedTraitTypes = new ArrayList<ITraitType>();

  public ComboRestrictions() {
    this(false, null);
  }
  
  public ComboRestrictions(boolean allAbilities, Boolean combosAllowed) {
	    this(allAbilities, "", combosAllowed);
	  }

  public ComboRestrictions(boolean allAbilities, String selectAbilities, Boolean combosAllowed) {
    this.allAbilities = allAbilities;
    this.combosAllowed = combosAllowed;
    this.selectAbilities = selectAbilities.split(",");
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
  
  public boolean combosSelectAbility(AbilityType type)
  {
	  for (String ability : selectAbilities)
		  if (type.getId().equals(ability))
			  return true;
	  return false;
  }

  public ITraitType[] getRestrictedTraitTypes() {
    return restrictedTraitTypes.toArray(new ITraitType[restrictedTraitTypes.size()]);
  }

  public boolean isComboAllowed(boolean isAllowedByDefault) {
    return combosAllowed == null ? isAllowedByDefault : combosAllowed;
  }

  public boolean isRestrictedCharm(ICharm charm) {
    if (restrictedCharmTypes.contains(charm.getCharmTypeModel().getCharmType())) {
      return true;
    }
      return restrictedCharmIds.contains(charm.getId());
  }
}