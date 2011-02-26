package net.sf.anathema.dummy.character;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.character.ICharacterPoints;
import net.sf.anathema.character.generic.character.IConcept;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.equipment.weapon.IArmourStats;
import net.sf.anathema.character.generic.equipment.weapon.IWeaponStats;
import net.sf.anathema.character.generic.health.HealthLevelType;
import net.sf.anathema.character.generic.impl.traits.limitation.StaticTraitLimitation;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IGenericCombo;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.magic.IMagicStats;
import net.sf.anathema.character.generic.magic.charms.special.IMultiLearnableCharm;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.template.ITraitLimitation;
import net.sf.anathema.character.generic.traits.IFavorableGenericTrait;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.INamedGenericTrait;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.lib.exception.NotYetImplementedException;
import net.sf.anathema.lib.resources.IResources;

public class DummyGenericCharacter implements IGenericCharacter {

  private final ICharacterTemplate template;
  private final Map<ITraitType, IGenericTrait> traitsByType = new HashMap<ITraitType, IGenericTrait>();

  public DummyGenericCharacter(ICharacterTemplate template) {
    this.template = template;
  }

  public IGenericTraitCollection getTraitCollection() {
    return new IGenericTraitCollection() {

      public IGenericTrait getTrait(ITraitType type) {
        return traitsByType.get(type);
      }

      public IGenericTrait[] getTraits(ITraitType[] traitTypes) {
        throw new NotYetImplementedException();
      }

      public boolean isFavoredOrCasteTrait(ITraitType type) {
        return getFavorableTrait(type).isCasteOrFavored();
      }

      public IFavorableGenericTrait getFavorableTrait(ITraitType type) {
        return (IFavorableGenericTrait) getTrait(type);
      }
    };
  }

  public void addTrait(IGenericTrait trait) {
    traitsByType.put(trait.getType(), trait);
  }

  public int getLearnCount(IMultiLearnableCharm charm) {
    return 0;
  }

  public boolean isLearned(IMagic magic) {
    return false;
  }

  public boolean isAlienCharm(ICharm charm) {
    return false;
  }

  public ICharacterTemplate getTemplate() {
    return template;
  }

  public INamedGenericTrait[] getSpecialties(ITraitType type) {
    return new INamedGenericTrait[0];
  }

  public INamedGenericTrait[] getSubTraits(ITraitType traitType) {
    return new INamedGenericTrait[0];
  }

  public ICasteType getCasteType() {
    return null;
  }

  public ICharacterPoints getCharacterPoints() {
    return new ICharacterPoints() {
      public int getExperiencePointsTotal() {
        return 0;
      }

      public int getExperiencePointsSpent() {
        return 0;
      }
    };
  }

  public IExaltedRuleSet getRules() {
    return net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet.CoreRules;
  }

  public int getHealthLevelTypeCount(HealthLevelType type) {
    return 0;
  }

  public String getPeripheralPool() {
    return null;
  }

  public String getPersonalPool() {
    return null;
  }

  public IGenericTrait[] getBackgrounds() {
    return new IGenericTrait[0];
  }

  public IAdditionalModel getAdditionalModel(String templateId) {
    return null;
  }

  public IConcept getConcept() {
    return new IConcept() {

      public String getWillpowerRegainingConceptName() {
        return null;
      }

      public ICasteType getCasteType() {
        return ICasteType.NULL_CASTE_TYPE;
      }

      public String getWillpowerRegainingComment(IResources resources) {
        return null;
      }
    };
  }

  public List<IMagic> getAllLearnedMagic() {
    return new ArrayList<IMagic>();
  }

  public int getLearnCount(ICharm charm) {
    return 0;
  }

  public IGenericCombo[] getCombos() {
    return new IGenericCombo[0];
  }

  public boolean isExperienced() {
    return false;
  }

  public String[] getUncompletedCelestialMartialArtsGroups() {
    return new String[0];
  }

  public int getPainTolerance() {
    return 0;
  }

  public ITraitLimitation getEssenceLimitation() {
    return new StaticTraitLimitation(7);
  }

  public void setLearnCount(IMultiLearnableCharm multiLearnableCharm, int newValue) {
    // Nothing to do
  }

  public IIdentifiedTraitTypeGroup[] getAbilityTypeGroups() {
    return new IIdentifiedTraitTypeGroup[0];
  }
  
  public IIdentifiedTraitTypeGroup[] getAttributeTypeGroups() {
	    return new IIdentifiedTraitTypeGroup[0];
	  }

  public IArmourStats[] getPrintArmours() {
    return new IArmourStats[0];
  }

  public IWeaponStats[] getPrintWeapons() {
    return new IWeaponStats[0];
  }

  public int getSpentExperiencePoints() {
    return 0;
  }

  public int getTotalExperiencePoints() {
    return 0;
  }

  public String[] getLearnedEffects(ICharm charm) {
    return new String[0];
  }

  public boolean isMultipleEffectCharm(ICharm magic) {
    return false;
  }

  public boolean isSubeffectCharm(ICharm magic) {
    return false;
  }

  @Override
  public IMagicStats[] getGenericCharmStats() {
    return new IMagicStats[0];
  }

  @Override
  public ICharm[] getLearnedCharms() {
    return new ICharm[0];
  }
}