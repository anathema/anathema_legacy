package net.sf.anathema.character.impl.model.traits;

import net.sf.anathema.character.generic.additionalrules.IAdditionalTraitRules;
import net.sf.anathema.character.generic.caste.ICasteCollection;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ITraitContext;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.template.ITraitTemplateCollection;
import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.groups.IIdentifiedCasteTraitTypeGroup;
import net.sf.anathema.character.generic.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.character.impl.model.temporary.InternalAttributeConfiguration;
import net.sf.anathema.character.impl.model.traits.creation.AbilityTypeGroupFactory;
import net.sf.anathema.character.impl.model.traits.creation.DefaultTraitFactory;
import net.sf.anathema.character.impl.model.traits.creation.FavorableTraitFactory;
import net.sf.anathema.character.impl.model.traits.creation.FavoredIncrementChecker;
import net.sf.anathema.character.impl.model.traits.creation.TypedTraitTemplateFactory;
import net.sf.anathema.character.impl.model.traits.listening.WillpowerListening;
import net.sf.anathema.character.library.trait.AbstractTraitCollection;
import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.character.library.trait.TraitCollectionUtilities;
import net.sf.anathema.character.library.trait.favorable.IIncrementChecker;
import net.sf.anathema.character.library.trait.specialties.ISpecialtiesConfiguration;
import net.sf.anathema.character.library.trait.specialties.SpecialtiesConfiguration;
import net.sf.anathema.character.model.traits.ICoreTraitConfiguration;

import java.util.Iterator;

import static java.util.Arrays.asList;

public class CoreTraitConfiguration extends AbstractTraitCollection implements ICoreTraitConfiguration {

  private final FavorableTraitFactory favorableTraitFactory;
  private final IIdentifiedCasteTraitTypeGroup[] abilityTraitGroups;
  private final SpecialtiesConfiguration specialtyConfiguration;
  private final ITraitTemplateCollection traitTemplateCollection;
  private InternalAttributeConfiguration attributeConfiguration;

  public CoreTraitConfiguration(ICharacterTemplate template, ICharacterModelContext modelContext,
                                InternalAttributeConfiguration attributeConfiguration) {
    this.attributeConfiguration = attributeConfiguration;
    ICasteCollection casteCollection = template.getCasteCollection();
    this.abilityTraitGroups = new AbilityTypeGroupFactory().createTraitGroups(casteCollection, template.getAbilityGroups());
    traitTemplateCollection = template.getTraitTemplateCollection();
    this.favorableTraitFactory = createFactory(template, modelContext);
    addEssence(modelContext.getTraitContext(), traitTemplateCollection, template.getAdditionalRules().getAdditionalTraitRules());
    addVirtues(modelContext.getTraitContext(), traitTemplateCollection, template.getAdditionalRules().getAdditionalTraitRules());
    addWillpower(modelContext.getTraitContext(), traitTemplateCollection, template.getAdditionalRules().getAdditionalTraitRules());
    addTraits(attributeConfiguration.getAllAttributes());
    addAbilities(template);
    Trait willpower = TraitCollectionUtilities.getWillpower(this);
    Trait[] virtues = TraitCollectionUtilities.getVirtues(this);
    if (template.getAdditionalRules().getAdditionalTraitRules().isWillpowerVirtueBased()) {
      new WillpowerListening().initListening(willpower, virtues);
    } else {
      willpower.setModifiedCreationRange(5, 10);
    }
    this.specialtyConfiguration = new SpecialtiesConfiguration(this, abilityTraitGroups, modelContext);
    getTrait(OtherTraitType.Essence).addCurrentValueListener(new EssenceLimitationListener(new AllTraits(), modelContext));
  }

  private FavorableTraitFactory createFactory(ICharacterTemplate template, ICharacterModelContext modelContext) {
    return new FavorableTraitFactory(modelContext.getTraitContext(), template.getAdditionalRules().getAdditionalTraitRules(),
            modelContext.getBasicCharacterContext(), modelContext.getCharacterListening());
  }

  private void addEssence(ITraitContext traitContext, ITraitTemplateCollection traitTemplateCollection, IAdditionalTraitRules additionalTraitRules) {
    TypedTraitTemplateFactory templateFactory = new EssenceTemplateFactory(traitTemplateCollection.getTraitTemplateFactory());
    DefaultTraitFactory traitFactory = new DefaultTraitFactory(traitContext, additionalTraitRules, templateFactory);
    addTraits(traitFactory.createTrait(OtherTraitType.Essence));
  }

  private void addVirtues(ITraitContext traitContext, ITraitTemplateCollection traitTemplateCollection, IAdditionalTraitRules additionalTraitRules) {
    TypedTraitTemplateFactory templateFactory = new VirtueTemplateFactory(traitTemplateCollection.getTraitTemplateFactory());
    DefaultTraitFactory traitFactory = new DefaultTraitFactory(traitContext, additionalTraitRules, templateFactory);
    addTraits(traitFactory.createTraits(VirtueType.values()));
  }

  private void addWillpower(ITraitContext traitContext, ITraitTemplateCollection traitTemplateCollection,
                            IAdditionalTraitRules additionalTraitRules) {
    TypedTraitTemplateFactory templateFactory = new WillpowerTemplateFactory(traitTemplateCollection.getTraitTemplateFactory());
    DefaultTraitFactory traitFactory = new DefaultTraitFactory(traitContext, additionalTraitRules, templateFactory);
    addTraits(traitFactory.createTrait(OtherTraitType.Willpower));
  }

  private void addAbilities(ICharacterTemplate template) {
    IIncrementChecker incrementChecker = FavoredIncrementChecker.createFavoredAbilityIncrementChecker(template, this);
    addFavorableTraits(abilityTraitGroups, incrementChecker, new AbilityTemplateFactory(traitTemplateCollection.getTraitTemplateFactory()));
  }

  @Override
  public void addFavorableTraits(IIdentifiedCasteTraitTypeGroup[] traitGroups, IIncrementChecker incrementChecker,
                                 TypedTraitTemplateFactory factory) {
    for (IIdentifiedCasteTraitTypeGroup traitGroup : traitGroups) {
      Trait[] traits = favorableTraitFactory.createTraits(traitGroup, incrementChecker, factory);
      addTraits(traits);
    }
  }

  @Override
  public Trait getTrait(ITraitType traitType) {
    if (contains(traitType)) {
      return super.getTrait(traitType);
    }
    throw new UnsupportedOperationException("Unsupported trait type " + traitType);
  }

  @Override
  public IIdentifiedTraitTypeGroup[] getAbilityTypeGroups() {
    return abilityTraitGroups;
  }

  @Override
  public final IIdentifiedCasteTraitTypeGroup[] getAttributeTypeGroups() {
    return attributeConfiguration.getGroups();
  }

  @Override
  public ISpecialtiesConfiguration getSpecialtyConfiguration() {
    return specialtyConfiguration;
  }

  private class AllTraits implements TraitProvider {
    @Override
    public Iterator<Trait> iterator() {
      return asList(getAllTraits()).iterator();
    }
  }

  public class GenericTraitTemplateFactory implements TypedTraitTemplateFactory {
    @Override
    public ITraitTemplate create(ITraitType type) {
      return traitTemplateCollection.getTraitTemplate(type);
    }
  }
}