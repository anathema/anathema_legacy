package net.sf.anathema.character.impl.generic;

import net.disy.commons.core.util.ContractFailedException;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.character.IConcept;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.equipment.IEquipmentModifiers;
import net.sf.anathema.character.generic.framework.ITraitReference;
import net.sf.anathema.character.generic.health.HealthLevelType;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IGenericCombo;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.magic.IMagicVisitor;
import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.generic.magic.charms.special.IMultiLearnableCharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharmConfiguration;
import net.sf.anathema.character.generic.magic.charms.special.ISubeffect;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.template.ITraitLimitation;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.INamedGenericTrait;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.impl.model.advance.ExperiencePointManagement;
import net.sf.anathema.character.library.trait.specialties.ISpecialtiesConfiguration;
import net.sf.anathema.character.library.trait.subtrait.ISubTrait;
import net.sf.anathema.character.library.trait.subtrait.ISubTraitListener;
import net.sf.anathema.character.library.trait.visitor.IAggregatedTrait;
import net.sf.anathema.character.library.trait.visitor.IDefaultTrait;
import net.sf.anathema.character.library.trait.visitor.ITraitVisitor;
import net.sf.anathema.character.model.ICharacterStatistics;
import net.sf.anathema.character.model.charm.ICharmConfiguration;
import net.sf.anathema.character.model.charm.ICombo;
import net.sf.anathema.character.model.charm.special.IMultiLearnableCharmConfiguration;
import net.sf.anathema.character.model.charm.special.IMultipleEffectCharmConfiguration;
import net.sf.anathema.character.model.charm.special.ISubeffectCharmConfiguration;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.util.IdentifiedInteger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenericCharacter implements IGenericCharacter {

  private final ICharacterStatistics statistics;

  public GenericCharacter(ICharacterStatistics statistics) {
    this.statistics = statistics;
  }

  @Override
  public IGenericTraitCollection getTraitCollection() {
    return statistics.getTraitConfiguration();
  }

  @Override
  public int getLearnCount(IMultiLearnableCharm charm) {
    return getLearnCount(charm.getCharmId());
  }

  @Override
  public int getLearnCount(String charmName) {
    ICharmConfiguration charms = statistics.getCharms();
    try {
      IMultiLearnableCharmConfiguration configuration = (IMultiLearnableCharmConfiguration) charms.getSpecialCharmConfiguration(
              charmName);
      return configuration.getCurrentLearnCount();
    } catch (IllegalArgumentException e) {
      return 0;
    }
  }

  @Override
  public void setLearnCount(IMultiLearnableCharm multiLearnableCharm, int newValue) {
    setLearnCount(multiLearnableCharm.getCharmId(), newValue);
  }

  @Override
  public void setLearnCount(String charmName, int newValue) {
    ICharmConfiguration charms = statistics.getCharms();
    IMultiLearnableCharmConfiguration configuration = (IMultiLearnableCharmConfiguration) charms.getSpecialCharmConfiguration(
            charmName);
    configuration.setCurrentLearnCount(newValue);
  }

  @Override
  public boolean isLearned(IMagic magic) {
    final boolean[] isLearned = new boolean[1];
    magic.accept(new IMagicVisitor() {
      @Override
      public void visitSpell(ISpell spell) {
        isLearned[0] = statistics.getSpells().isLearned(spell);
      }

      @Override
      public void visitCharm(ICharm charm) {
        isLearned[0] = statistics.getCharms().isLearned(charm);
      }
    });
    return isLearned[0];
  }

  @Override
  public boolean isAlienCharm(ICharm charm) {
    return statistics.getCharms().isAlienCharm(charm);
  }

  @Override
  public ICharacterTemplate getTemplate() {
    return statistics.getCharacterTemplate();
  }

  @Override
  public INamedGenericTrait[] getSpecialties(ITraitType traitType) {
    ISpecialtiesConfiguration specialtyConfiguration = statistics.getTraitConfiguration().getSpecialtyConfiguration();
    return specialtyConfiguration.getSpecialtiesContainer(traitType).getSubTraits();
  }

  @Override
  public INamedGenericTrait[] getSubTraits(ITraitType traitType) {
    class CollectSubTraitVisitor implements ITraitVisitor {
      INamedGenericTrait[] subtraits;

      @Override
      public void visitAggregatedTrait(IAggregatedTrait visitedTrait) {
        subtraits = visitedTrait.getSubTraits().getSubTraits();
      }

      @Override
      public void visitDefaultTrait(IDefaultTrait visitedTrait) {
        subtraits = new INamedGenericTrait[0];
      }
    }
    CollectSubTraitVisitor collectVisitor = new CollectSubTraitVisitor();
    statistics.getTraitConfiguration().getTrait(traitType).accept(collectVisitor);
    return collectVisitor.subtraits;
  }

  @Override
  public ICasteType getCasteType() {
    return statistics.getCharacterConcept().getCaste().getType();
  }

  @Override
  public IExaltedRuleSet getRules() {
    return statistics.getRules();
  }

  @Override
  public int getHealthLevelTypeCount(HealthLevelType type) {
    return statistics.getHealth().getHealthLevelTypeCount(type);
  }

  @Override
  public String getPeripheralPool() {
    try {
      return getTemplate().getEssenceTemplate().isEssenceUser() ? statistics.getEssencePool().getPeripheralPool() : null;
    } catch (ContractFailedException e) {
      return null;
    }
  }

  @Override
  public int getPeripheralPoolValue() {
    return getTemplate().getEssenceTemplate().isEssenceUser() ? statistics.getEssencePool().getPeripheralPoolValue() : 0;
  }

  @Override
  public String getPersonalPool() {
    try {
      return getTemplate().getEssenceTemplate().isEssenceUser() ? statistics.getEssencePool().getPersonalPool() : null;
    } catch (ContractFailedException e) {
      return null;
    }
  }

  @Override
  public int getPersonalPoolValue() {
    return getTemplate().getEssenceTemplate().isEssenceUser() ? statistics.getEssencePool().getPersonalPoolValue() : 0;
  }

  @Override
  public int getOverdrivePoolValue() {
    return getTemplate().getEssenceTemplate().isEssenceUser() ? statistics.getEssencePool().getOverdrivePoolValue() : 0;
  }

  @Override
  public IdentifiedInteger[] getComplexPools() {
    if (getTemplate().getEssenceTemplate().isEssenceUser()) {
      return statistics.getEssencePool().getComplexPools();
    } else {
      return new IdentifiedInteger[0];
    }
  }

  @Override
  public int getAttunedPoolValue() {
    return getTemplate().getEssenceTemplate().isEssenceUser() ? statistics.getEssencePool().getAttunedPoolValue() : 0;
  }

  @Override
  public IGenericTrait[] getBackgrounds() {
    return statistics.getTraitConfiguration().getBackgrounds().getBackgrounds();
  }

  @Override
  public IAdditionalModel getAdditionalModel(String id) {
    return statistics.getExtendedConfiguration().getAdditionalModel(id);
  }

  @Override
  public IEquipmentModifiers getEquipmentModifiers() {
    return new EquipmentModifiers(statistics);
  }

  @Override
  public IConcept getConcept() {
    return new GenericConcept(statistics.getCharacterConcept());
  }

  @Override
  public List<IMagic> getAllLearnedMagic() {
    List<IMagic> magicList = new ArrayList<IMagic>();
    magicList.addAll(Arrays.asList(getLearnedCharms()));
    magicList.addAll(Arrays.asList(statistics.getSpells().getLearnedSpells(statistics.isExperienced())));
    return magicList;
  }

  @Override
  public int getLearnCount(ICharm charm) {
    return getLearnCount(charm, statistics.getCharms());
  }

  private int getLearnCount(ICharm charm, ICharmConfiguration configuration) {
    ISpecialCharmConfiguration specialCharmConfiguration = configuration.getSpecialCharmConfiguration(charm.getId());
    if (specialCharmConfiguration != null) {
      return specialCharmConfiguration.getCurrentLearnCount();
    }
    return configuration.isLearned(charm) ? 1 : 0;
  }

  @Override
  public IGenericCombo[] getCombos() {
    List<IGenericCombo> genericCombos = new ArrayList<IGenericCombo>();
    for (ICombo combo : statistics.getCombos().getAllCombos()) {
      genericCombos.add(new GenericCombo(combo));
    }
    return genericCombos.toArray(new IGenericCombo[genericCombos.size()]);
  }

  @Override
  public boolean isExperienced() {
    return statistics.isExperienced();
  }

  @Override
  public String[] getUncompletedCelestialMartialArtsGroups() {
    return statistics.getCharms().getUncompletedCelestialMartialArtsGroups();
  }

  @Override
  public int getPainTolerance() {
    return statistics.getHealth().getPainToleranceLevel();
  }

  @Override
  public ITraitLimitation getEssenceLimitation() {
    return getTemplate().getTraitTemplateCollection().getTraitTemplate(OtherTraitType.Essence).getLimitation();
  }

  @Override
  public int getEssenceCap(boolean modified) {
    IDefaultTrait essence = (IDefaultTrait) statistics.getTraitConfiguration().getTrait(OtherTraitType.Essence);
    return modified ? essence.getModifiedMaximalValue() : essence.getUnmodifiedMaximalValue();
  }

  @Override
  public int getAge() {
    return statistics.getCharacterConcept().getAge().getValue();
  }

  @Override
  public IIdentifiedTraitTypeGroup[] getAbilityTypeGroups() {
    return statistics.getTraitConfiguration().getAbilityTypeGroups();
  }

  @Override
  public IIdentifiedTraitTypeGroup[] getAttributeTypeGroups() {
    return statistics.getTraitConfiguration().getAttributeTypeGroups();
  }

  @Override
  public IIdentifiedTraitTypeGroup[] getYoziTypeGroups() {
    return statistics.getTraitConfiguration().getYoziTypeGroups();
  }

  @Override
  public int getSpentExperiencePoints() {
    return new ExperiencePointManagement(statistics).getTotalCosts();
  }

  @Override
  public int getTotalExperiencePoints() {
    return statistics.getExperiencePoints().getTotalExperiencePoints();
  }

  @Override
  public boolean isSubeffectCharm(ICharm charm) {
    ISpecialCharmConfiguration charmConfiguration = statistics.getCharms().getSpecialCharmConfiguration(charm);
    return charmConfiguration instanceof ISubeffectCharmConfiguration;
  }

  @Override
  public boolean isMultipleEffectCharm(ICharm charm) {
    ISpecialCharmConfiguration charmConfiguration = statistics.getCharms().getSpecialCharmConfiguration(charm);
    return charmConfiguration instanceof IMultipleEffectCharmConfiguration && !(charmConfiguration instanceof ISubeffectCharmConfiguration);
  }

  @Override
  public String[] getLearnedEffects(ICharm charm) {
    ISpecialCharmConfiguration charmConfiguration = statistics.getCharms().getSpecialCharmConfiguration(charm);
    if (!(charmConfiguration instanceof IMultipleEffectCharmConfiguration)) {
      return new String[0];
    }
    IMultipleEffectCharmConfiguration configuration = (IMultipleEffectCharmConfiguration) charmConfiguration;
    List<String> learnedEffectIds = new ArrayList<String>();
    for (ISubeffect effect : configuration.getEffects()) {
      if (effect.isLearned()) {
        learnedEffectIds.add(effect.getId());
      }
    }
    return learnedEffectIds.toArray(new String[learnedEffectIds.size()]);
  }

  @Override
  public ICharm[] getLearnedCharms() {
    return statistics.getCharms().getLearnedCharms(statistics.isExperienced());
  }

  @Override
  public void addSpecialtyListChangeListener(final IChangeListener listener) {
    ISpecialtiesConfiguration config = statistics.getTraitConfiguration().getSpecialtyConfiguration();
    for (ITraitReference trait : config.getAllTraits())
      config.getSpecialtiesContainer(trait).addSubTraitListener(new ISubTraitListener() {
        @Override
        public void subTraitValueChanged() {
        }

        @Override
        public void subTraitAdded(ISubTrait subTrait) {
          listener.changeOccurred();
        }

        @Override
        public void subTraitRemoved(ISubTrait subTrait) {
          listener.changeOccurred();
        }
      });
  }
}