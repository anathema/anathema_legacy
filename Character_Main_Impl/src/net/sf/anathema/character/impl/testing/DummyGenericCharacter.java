package net.sf.anathema.character.impl.testing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.character.ICharacterPoints;
import net.sf.anathema.character.generic.character.IConcept;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.health.HealthLevelType;
import net.sf.anathema.character.generic.impl.traits.limitation.StaticTraitLimitation;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IGenericCombo;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.magic.charms.ICharmAttributeRequirement;
import net.sf.anathema.character.generic.magic.charms.special.IMultiLearnableCharm;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.template.ITraitLimitation;
import net.sf.anathema.character.generic.traits.IFavorableGenericTrait;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.INamedGenericTrait;
import net.sf.anathema.character.generic.traits.ITraitType;

public class DummyGenericCharacter implements IGenericCharacter {

  private final ICharacterTemplate template;
  private final Map<ITraitType, IGenericTrait> traitsByType = new HashMap<ITraitType, IGenericTrait>();

  public DummyGenericCharacter(ICharacterTemplate template) {
    this.template = template;
  }

  public boolean isFavoredOrCasteTrait(ITraitType type) {
    return getFavorableTrait(type).isCasteOrFavored();
  }

  public void addTrait(IGenericTrait trait) {
    traitsByType.put(trait.getType(), trait);
  }

  public IGenericTrait getTrait(ITraitType type) {
    return traitsByType.get(type);
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
      public String getWillpowerCondition() {
        return null;
      }

      public String getWillpowerRegainingConceptName() {
        return null;
      }

      public String getConceptText() {
        return null;
      }

      public ICasteType getCasteType() {
        return ICasteType.NULL_CASTE_TYPE;
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

  public boolean isRequirementFulfilled(ICharmAttributeRequirement requirement) {
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

  public IFavorableGenericTrait getFavorableTrait(ITraitType type) {
    return (IFavorableGenericTrait) getTrait(type);
  }

  public void setLearnCount(IMultiLearnableCharm multiLearnableCharm, int newValue) {
    // Nothing to do
  }
}