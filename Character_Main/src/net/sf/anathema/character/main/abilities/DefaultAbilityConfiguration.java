package net.sf.anathema.character.main.abilities;

import net.sf.anathema.character.generic.caste.ICasteCollection;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.traits.groups.IIdentifiedCasteTraitTypeGroup;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.impl.model.traits.AbilityTemplateFactory;
import net.sf.anathema.character.impl.model.traits.creation.AbilityTypeGroupFactory;
import net.sf.anathema.character.impl.model.traits.creation.FavorableTraitFactory;
import net.sf.anathema.character.impl.model.traits.creation.FavoredIncrementChecker;
import net.sf.anathema.character.impl.model.traits.creation.TypedTraitTemplateFactory;
import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.character.library.trait.TraitGroup;
import net.sf.anathema.character.library.trait.favorable.IIncrementChecker;
import net.sf.anathema.character.library.trait.specialties.SpecialtiesConfiguration;
import net.sf.anathema.character.main.traits.model.HashTraitMap;
import net.sf.anathema.character.main.traits.model.MappedTraitGroup;
import net.sf.anathema.character.main.traits.model.TraitModel;
import net.sf.anathema.character.model.traits.ICoreTraitConfiguration;

public class DefaultAbilityConfiguration implements AbilityConfiguration {
  private final IIdentifiedCasteTraitTypeGroup[] abilityTraitGroups;
  private final SpecialtiesConfiguration specialtyConfiguration;
  private ICharacterTemplate template;
  private ICharacterModelContext modelContext;
  private ICoreTraitConfiguration coreTraitConfiguration;
  private final HashTraitMap traitMap = new HashTraitMap();

  public DefaultAbilityConfiguration(ICharacterTemplate template, ICharacterModelContext modelContext,
                                     ICoreTraitConfiguration coreTraitConfiguration, TraitModel traitModel) {
    this.template = template;
    this.modelContext = modelContext;
    this.coreTraitConfiguration = coreTraitConfiguration;
    ICasteCollection casteCollection = template.getCasteCollection();
    this.abilityTraitGroups = new AbilityTypeGroupFactory().createTraitGroups(casteCollection, template.getAbilityGroups());
    IIncrementChecker incrementChecker = FavoredIncrementChecker.createFavoredAbilityIncrementChecker(template, this.coreTraitConfiguration);
    addFavorableTraits(incrementChecker, new AbilityTemplateFactory(template.getTraitTemplateCollection().getTraitTemplateFactory()));
    traitModel.addTraits(getAllAbilities());
    this.specialtyConfiguration = new SpecialtiesConfiguration(coreTraitConfiguration, abilityTraitGroups, modelContext);
 }


  public void addFavorableTraits(IIncrementChecker incrementChecker,
                                 TypedTraitTemplateFactory factory) {
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
  public Trait getTrait(AttributeType type) {
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
