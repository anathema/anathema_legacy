package net.sf.anathema.hero.abilities.model;

import net.sf.anathema.character.generic.additionalrules.IAdditionalTraitRules;
import net.sf.anathema.character.generic.caste.ICasteCollection;
import net.sf.anathema.character.generic.template.HeroTemplate;
import net.sf.anathema.character.generic.template.abilities.GroupedTraitType;
import net.sf.anathema.character.generic.traits.TraitType;
import net.sf.anathema.character.generic.traits.groups.IIdentifiedCasteTraitTypeGroup;
import net.sf.anathema.character.impl.model.traits.AbilityTemplateFactory;
import net.sf.anathema.character.impl.model.traits.creation.AbilityTypeGroupFactory;
import net.sf.anathema.character.impl.model.traits.creation.FavorableTraitFactory;
import net.sf.anathema.character.impl.model.traits.creation.FavoredIncrementChecker;
import net.sf.anathema.character.impl.model.traits.creation.TypedTraitTemplateFactory;
import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.character.library.trait.TraitGroup;
import net.sf.anathema.character.library.trait.favorable.IncrementChecker;
import net.sf.anathema.character.library.trait.specialties.SpecialtiesConfiguration;
import net.sf.anathema.character.main.model.abilities.AbilityModel;
import net.sf.anathema.character.main.hero.Hero;
import net.sf.anathema.character.main.hero.HeroModel;
import net.sf.anathema.character.main.hero.InitializationContext;
import net.sf.anathema.character.main.model.traits.DefaultTraitMap;
import net.sf.anathema.character.main.model.traits.MappedTraitGroup;
import net.sf.anathema.character.main.model.traits.TraitMap;
import net.sf.anathema.character.main.model.traits.TraitModel;
import net.sf.anathema.character.main.model.traits.TraitModelFetcher;
import net.sf.anathema.lib.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class AbilityModelImpl extends DefaultTraitMap implements AbilityModel, HeroModel {

  private IIdentifiedCasteTraitTypeGroup[] abilityTraitGroups;
  private SpecialtiesConfiguration specialtyConfiguration;
  private InitializationContext context;

  @Override
  public Identifier getId() {
    return ID;
  }

  @Override
  public void initialize(InitializationContext context, Hero hero) {
    this.context = context;
    HeroTemplate template = context.getTemplate();
    ICasteCollection casteCollection = template.getCasteCollection();
    this.abilityTraitGroups = new AbilityTypeGroupFactory().createTraitGroups(casteCollection, template.getAbilityGroups());
    IncrementChecker incrementChecker = createFavoredAbilityIncrementChecker(template, this);
    addFavorableTraits(incrementChecker, new AbilityTemplateFactory(template.getTraitTemplateCollection().getTraitTemplateFactory()));
    TraitModel traitModel = TraitModelFetcher.fetch(hero);
    traitModel.addTraits(getAll());
    this.specialtyConfiguration = new SpecialtiesConfiguration(this, abilityTraitGroups, context);
  }

  private IncrementChecker createFavoredAbilityIncrementChecker(HeroTemplate template, TraitMap traitMap) {
    int maxFavoredAbilityCount = template.getCreationPoints().getAbilityCreationPoints().getFavorableTraitCount();
    List<TraitType> abilityTypes = new ArrayList<>();
    for (GroupedTraitType traitType : template.getAbilityGroups()) {
      abilityTypes.add(traitType.getTraitType());
    }
    return new FavoredIncrementChecker(maxFavoredAbilityCount, abilityTypes.toArray(new TraitType[abilityTypes.size()]), traitMap);
  }

  public void addFavorableTraits(IncrementChecker incrementChecker, TypedTraitTemplateFactory factory) {
    FavorableTraitFactory favorableTraitFactory = createFactory();
    for (IIdentifiedCasteTraitTypeGroup traitGroup : abilityTraitGroups) {
      Trait[] traits = favorableTraitFactory.createTraits(traitGroup, incrementChecker, factory);
      addTraits(traits);
    }
  }

  private FavorableTraitFactory createFactory() {
    IAdditionalTraitRules traitRules = context.getTemplate().getAdditionalRules().getAdditionalTraitRules();
    return new FavorableTraitFactory(context.getTraitContext(), traitRules, context.getBasicCharacterContext(), context.getCharacterListening());
  }

  @Override
  public TraitGroup[] getTraitGroups() {
    TraitGroup[] groups = new TraitGroup[abilityTraitGroups.length];
    for (int index = 0; index < groups.length; index++) {
      final IIdentifiedCasteTraitTypeGroup typeGroup = abilityTraitGroups[index];
      groups[index] = new MappedTraitGroup(this, typeGroup);
    }
    return groups;
  }

  @Override
  public IIdentifiedCasteTraitTypeGroup[] getAbilityTypeGroups() {
    return abilityTraitGroups;
  }

  @Override
  public SpecialtiesConfiguration getSpecialtyConfiguration() {
    return specialtyConfiguration;
  }
}
