package net.sf.anathema.character.reporting.demo;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.character.ICharacterPoints;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.health.HealthLevelType;
import net.sf.anathema.character.generic.impl.testing.DummyGenericTrait;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IGenericCombo;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.magic.charms.ICharmAttributeRequirement;
import net.sf.anathema.character.generic.magic.charms.special.IMultiLearnableCharm;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.template.ITraitLimitation;
import net.sf.anathema.character.generic.traits.IFavorableGenericTrait;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.INamedGenericTrait;
import net.sf.anathema.character.generic.traits.ITraitType;

public class DemoGenericCharacter implements IGenericCharacter {

  private final List<IMagic> allLearnedMagic = new ArrayList<IMagic>();
  private final List<IGenericTrait> allBackgrounds = new ArrayList<IGenericTrait>();
  private final List<IGenericCombo> allCombos = new ArrayList<IGenericCombo>();
  private final DemoCharacterTemplate characterTemplate = new DemoCharacterTemplate();
  private final DemoGenericDescription description = new DemoGenericDescription();
  private final DemoConcept concept = new DemoConcept();

  public IAdditionalModel getAdditionalModel(String templateId) {
    return null;
  }

  public List<IMagic> getAllLearnedMagic() {
    return allLearnedMagic;
  }

  public IGenericTrait[] getBackgrounds() {
    return allBackgrounds.toArray(new IGenericTrait[allBackgrounds.size()]);
  }

  public ICharacterPoints getCharacterPoints() {
    return null;
  }

  public IGenericCombo[] getCombos() {
    return allCombos.toArray(new IGenericCombo[allCombos.size()]);
  }

  public DemoConcept getConcept() {
    return concept;
  }

  public int getHealthLevelTypeCount(HealthLevelType type) {
    return 0;
  }

  public int getLearnCount(ICharm charm) {
    return 0;
  }

  public int getPainTolerance() {
    return 0;
  }

  public String getPeripheralPool() {
    return "25"; //$NON-NLS-1$
  }

  public String getPersonalPool() {
    return "10"; //$NON-NLS-1$
  }

  public IExaltedRuleSet getRules() {
    return null;
  }

  public INamedGenericTrait[] getSpecialties(ITraitType traitType) {
    return new INamedGenericTrait[0];
  }

  public DemoCharacterTemplate getTemplate() {
    return characterTemplate;
  }

  public String[] getUncompletedCelestialMartialArtsGroups() {
    return new String[0];
  }

  public boolean isAlienCharm(ICharm charm) {
    return false;
  }

  public boolean isExperienced() {
    return false;
  }

  public boolean isFavoredOrCasteTrait(ITraitType type) {
    return false;
  }

  public boolean isLearned(IMagic magic) {
    return false;
  }

  public boolean isRequirementFulfilled(ICharmAttributeRequirement requirement) {
    return false;
  }

  public ICasteType getCasteType() {
    return null;
  }

  public ITraitLimitation getEssenceLimitation() {
    return null;
  }

  public IFavorableGenericTrait getFavorableTrait(ITraitType type) {
    return null;
  }

  public IGenericTrait getTrait(ITraitType type) {
    return new DummyGenericTrait(type, 1);
  }

  public int getLearnCount(IMultiLearnableCharm multiLearnableCharm) {
    return 0;
  }

  public void setLearnCount(IMultiLearnableCharm multiLearnableCharm, int newValue) {
  }
  
  public DemoGenericDescription getDescription() {
    return description ;
  }
}