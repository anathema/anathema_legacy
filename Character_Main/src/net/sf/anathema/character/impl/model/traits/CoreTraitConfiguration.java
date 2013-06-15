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
import net.sf.anathema.character.impl.model.traits.creation.AbilityTypeGroupFactory;
import net.sf.anathema.character.impl.model.traits.creation.AttributeTypeGroupFactory;
import net.sf.anathema.character.impl.model.traits.creation.DefaultTraitFactory;
import net.sf.anathema.character.impl.model.traits.creation.FavorableTraitFactory;
import net.sf.anathema.character.impl.model.traits.creation.FavoredIncrementChecker;
import net.sf.anathema.character.impl.model.traits.creation.TypedTraitTemplateFactory;
import net.sf.anathema.character.impl.model.traits.listening.WillpowerListening;
import net.sf.anathema.character.library.trait.AbstractTraitCollection;
import net.sf.anathema.character.library.trait.ITrait;
import net.sf.anathema.character.library.trait.TraitCollectionUtilities;
import net.sf.anathema.character.library.trait.favorable.IFavorableTrait;
import net.sf.anathema.character.library.trait.favorable.IIncrementChecker;
import net.sf.anathema.character.library.trait.specialties.ISpecialtiesConfiguration;
import net.sf.anathema.character.library.trait.specialties.SpecialtiesConfiguration;
import net.sf.anathema.character.library.trait.visitor.IDefaultTrait;
import net.sf.anathema.character.model.traits.ICoreTraitConfiguration;

import java.util.Iterator;

import static java.util.Arrays.asList;

public class CoreTraitConfiguration extends AbstractTraitCollection implements ICoreTraitConfiguration {

  private final FavorableTraitFactory favorableTraitFactory;
  private final IIdentifiedCasteTraitTypeGroup[] abilityTraitGroups;
  private final IIdentifiedCasteTraitTypeGroup[] attributeTraitGroups;
  private final SpecialtiesConfiguration specialtyConfiguration;
  private final ITraitTemplateCollection traitTemplateCollection;

  public CoreTraitConfiguration(ICharacterTemplate template, ICharacterModelContext modelContext) {
    ICasteCollection casteCollection = template.getCasteCollection();
    this.abilityTraitGroups = new AbilityTypeGroupFactory().createTraitGroups(casteCollection, template.getAbilityGroups());
    this.attributeTraitGroups = new AttributeTypeGroupFactory().createTraitGroups(casteCollection, template.getAttributeGroups());
    ITraitContext traitContext = modelContext.getTraitContext();
    traitTemplateCollection = template.getTraitTemplateCollection();
    IAdditionalTraitRules additionalTraitRules = template.getAdditionalRules().getAdditionalTraitRules();
    this.favorableTraitFactory = new FavorableTraitFactory(traitContext, additionalTraitRules, modelContext.getBasicCharacterContext(),
            modelContext.getCharacterListening());
    addEssence(traitContext, traitTemplateCollection, additionalTraitRules);
    addVirtues(traitContext, traitTemplateCollection, additionalTraitRules);
    addWillpower(traitContext, traitTemplateCollection, additionalTraitRules);
    addAttributes(template);
    addAbilities(template);
    IDefaultTrait willpower = TraitCollectionUtilities.getWillpower(this);
    IDefaultTrait[] virtues = TraitCollectionUtilities.getVirtues(this);
    if (additionalTraitRules.isWillpowerVirtueBased()) {
      new WillpowerListening().initListening(willpower, virtues);
    } else {
      willpower.setModifiedCreationRange(5, 10);
    }
    this.specialtyConfiguration = new SpecialtiesConfiguration(this, abilityTraitGroups, modelContext);
    getTrait(OtherTraitType.Essence).addCurrentValueListener(new EssenceLimitationListener(new AllTraits(), modelContext));
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

  private void addAttributes(ICharacterTemplate template) {
    IIncrementChecker incrementChecker = FavoredIncrementChecker.createFavoredAttributeIncrementChecker(template, this);
    addFavorableTraits(attributeTraitGroups, incrementChecker, new AttributeTemplateFactory(traitTemplateCollection.getTraitTemplateFactory()));
  }

  private void addAbilities(ICharacterTemplate template) {
    IIncrementChecker incrementChecker = FavoredIncrementChecker.createFavoredAbilityIncrementChecker(template, this);
    addFavorableTraits(abilityTraitGroups, incrementChecker, new AbilityTemplateFactory(traitTemplateCollection.getTraitTemplateFactory()));
  }

  @Override
  public void addFavorableTraits(IIdentifiedCasteTraitTypeGroup[] traitGroups, IIncrementChecker incrementChecker,
                                 TypedTraitTemplateFactory factory) {
    for (IIdentifiedCasteTraitTypeGroup traitGroup : traitGroups) {
      IFavorableTrait[] traits = favorableTraitFactory.createTraits(traitGroup, incrementChecker, factory);
      addTraits(traits);
    }
  }

  @Override
  public ITrait getTrait(ITraitType traitType) {
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
    return attributeTraitGroups;
  }

  @Override
  public ISpecialtiesConfiguration getSpecialtyConfiguration() {
    return specialtyConfiguration;
  }

  private class AllTraits implements TraitProvider {
    @Override
    public Iterator<ITrait> iterator() {
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