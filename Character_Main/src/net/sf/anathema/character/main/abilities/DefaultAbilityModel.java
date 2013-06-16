package net.sf.anathema.character.main.abilities;

import net.sf.anathema.character.generic.caste.ICasteCollection;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
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
import net.sf.anathema.character.main.traits.model.HashTraitMap;
import net.sf.anathema.character.main.traits.model.MappedTraitGroup;
import net.sf.anathema.character.main.traits.model.TraitMap;
import net.sf.anathema.character.main.traits.model.TraitModel;

import java.util.ArrayList;
import java.util.List;

public class DefaultAbilityModel implements AbilityModel {
  private final IIdentifiedCasteTraitTypeGroup[] abilityTraitGroups;
  private final SpecialtiesConfiguration specialtyConfiguration;
  private ICharacterTemplate template;
  private ICharacterModelContext modelContext;
  private final HashTraitMap traitMap = new HashTraitMap();

  public DefaultAbilityModel(ICharacterTemplate template, ICharacterModelContext modelContext, TraitModel traitModel) {
    this.template = template;
    this.modelContext = modelContext;
    ICasteCollection casteCollection = template.getCasteCollection();
    this.abilityTraitGroups = new AbilityTypeGroupFactory().createTraitGroups(casteCollection, template.getAbilityGroups());
    IncrementChecker incrementChecker = createFavoredAbilityIncrementChecker(template, traitMap);
    addFavorableTraits(incrementChecker, new AbilityTemplateFactory(template.getTraitTemplateCollection().getTraitTemplateFactory()));
    traitModel.addTraits(getAllAbilities());
    this.specialtyConfiguration = new SpecialtiesConfiguration(traitMap, abilityTraitGroups, modelContext);
  }

  private IncrementChecker createFavoredAbilityIncrementChecker(ICharacterTemplate template, TraitMap traitMap) {
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
    return new FavorableTraitFactory(modelContext.getTraitContext(), template.getAdditionalRules().getAdditionalTraitRules(),
            modelContext.getBasicCharacterContext(), modelContext.getCharacterListening());
  }

  protected final void addTraits(Trait... traits) {
    for (Trait trait : traits) {
      traitMap.addTrait(trait);
    }
  }

  @Override
  public Trait[] getAllAbilities() {
    return traitMap.getAll();
  }

  @Override
  public TraitGroup[] getTraitGroups() {
    TraitGroup[] groups = new TraitGroup[abilityTraitGroups.length];
    for (int index = 0; index < groups.length; index++) {
      final IIdentifiedCasteTraitTypeGroup typeGroup = abilityTraitGroups[index];
      groups[index] = new MappedTraitGroup(traitMap, typeGroup);
    }
    return groups;
  }

  @Override
  public Trait getTrait(TraitType type) {
    return traitMap.getTrait(type);
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
