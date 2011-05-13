package net.sf.anathema.character.impl.model.traits;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.groups.IIdentifiedCasteTraitTypeGroup;
import net.sf.anathema.character.generic.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.character.impl.model.traits.backgrounds.BackgroundArbitrator;
import net.sf.anathema.character.impl.model.traits.backgrounds.BackgroundConfiguration;
import net.sf.anathema.character.impl.model.traits.creation.AbilityTypeGroupFactory;
import net.sf.anathema.character.impl.model.traits.creation.AttributeTypeGroupFactory;
import net.sf.anathema.character.impl.model.traits.creation.DefaultTraitFactory;
import net.sf.anathema.character.impl.model.traits.creation.FavorableTraitFactory;
import net.sf.anathema.character.impl.model.traits.creation.FavoredIncrementChecker;
import net.sf.anathema.character.impl.model.traits.listening.WillpowerListening;
import net.sf.anathema.character.library.trait.AbstractTraitCollection;
import net.sf.anathema.character.library.trait.ITrait;
import net.sf.anathema.character.library.trait.TraitCollectionUtilities;
import net.sf.anathema.character.library.trait.favorable.IFavorableTrait;
import net.sf.anathema.character.library.trait.favorable.IIncrementChecker;
import net.sf.anathema.character.library.trait.specialties.ISpecialtiesConfiguration;
import net.sf.anathema.character.library.trait.specialties.SpecialtiesConfiguration;
import net.sf.anathema.character.library.trait.visitor.IDefaultTrait;
import net.sf.anathema.character.model.background.IBackgroundConfiguration;
import net.sf.anathema.character.model.traits.ICoreTraitConfiguration;
import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;
import net.sf.anathema.lib.registry.IIdentificateRegistry;
import net.sf.anathema.lib.util.IIdentificate;

public class CoreTraitConfiguration extends AbstractTraitCollection implements ICoreTraitConfiguration {

  private final DefaultTraitFactory traitFactory;
  private final FavorableTraitFactory favorableTraitFactory;
  private final BackgroundConfiguration backgrounds;
  private final IIdentifiedCasteTraitTypeGroup[] abilityTraitGroups;
  private final IIdentifiedCasteTraitTypeGroup[] attributeTraitGroups;
  private final SpecialtiesConfiguration specialtyConfiguration;

  public CoreTraitConfiguration(
      ICharacterTemplate template,
      ICharacterModelContext modelContext,
      IIdentificateRegistry<IBackgroundTemplate> backgroundRegistry) {
    this.abilityTraitGroups = new AbilityTypeGroupFactory().createTraitGroups(
        template.getCasteCollection(),
        template.getAbilityGroups());
    this.attributeTraitGroups = new AttributeTypeGroupFactory().createTraitGroups(
        template.getCasteCollection(),
        template.getAttributeGroups());
    this.traitFactory = new DefaultTraitFactory(
        modelContext.getTraitContext(),
        template.getTraitTemplateCollection(),
        template.getAdditionalRules().getAdditionalTraitRules());
    this.favorableTraitFactory = new FavorableTraitFactory(
        modelContext.getTraitContext(),
        template.getTraitTemplateCollection(),
        modelContext.getAdditionalRules().getAdditionalTraitRules(),
        modelContext.getBasicCharacterContext(),
        modelContext.getCharacterListening());
    addTrait(traitFactory.createTrait(OtherTraitType.Essence));
    addTraits(traitFactory.createTraits(VirtueType.values()));
    addTrait(traitFactory.createTrait(OtherTraitType.Willpower));
    addAttributes(template);
    IDefaultTrait willpower = TraitCollectionUtilities.getWillpower(this);
    IDefaultTrait[] virtues = TraitCollectionUtilities.getVirtues(this);
    if (template.getAdditionalRules().getAdditionalTraitRules().isWillpowerVirtueBased()) {
      new WillpowerListening().initListening(willpower, virtues);
    }
    else {
      willpower.setModifiedCreationRange(5, 10);
    }
    addAbilities(template);
    this.backgrounds = new BackgroundConfiguration(
        new BackgroundArbitrator(template, modelContext.getBasicCharacterContext().getRuleSet().getEdition()),
        template.getTraitTemplateCollection(),
        modelContext.getTraitContext(),
        backgroundRegistry);
    
    IIdentifiedCasteTraitTypeGroup[] specialtyGroup = new IIdentifiedCasteTraitTypeGroup[abilityTraitGroups.length + attributeTraitGroups.length];
    int index = 0;
    for (; index != abilityTraitGroups.length; index++)
    	specialtyGroup[index] = abilityTraitGroups[index];
    for (; index != abilityTraitGroups.length + attributeTraitGroups.length; index++)
    	specialtyGroup[index] = attributeTraitGroups[index - abilityTraitGroups.length];
    this.specialtyConfiguration = new SpecialtiesConfiguration(this, specialtyGroup, modelContext);
    
    getTrait(OtherTraitType.Essence).addCurrentValueListener(new IIntValueChangedListener()
    {
		public void valueChanged(int newValue)
		{
			for (ITrait trait : getAllTraits())
				if (trait instanceof IDefaultTrait)
					((IDefaultTrait)trait).resetCurrentValue();
		}
    });
  }

  private void addAttributes(ICharacterTemplate template) {
	IIncrementChecker incrementChecker = FavoredIncrementChecker.createFavoredAttributeIncrementChecker(template, this);
    addFavorableTraits(attributeTraitGroups, incrementChecker);
  }

  private void addAbilities(ICharacterTemplate template) {
    IIncrementChecker incrementChecker = FavoredIncrementChecker.createFavoredAbilityIncrementChecker(template, this);
    addFavorableTraits(abilityTraitGroups, incrementChecker);
  }

  private void addFavorableTraits(IIdentifiedCasteTraitTypeGroup[] traitGroups, IIncrementChecker incrementChecker) {
    for (IIdentifiedCasteTraitTypeGroup traitGroup : traitGroups) {
      addTraits(favorableTraitFactory.createTraits(traitGroup,
          incrementChecker));
    }
  }

  @Override
  public ITrait getTrait(ITraitType traitType) {
    if (contains(traitType)) {
      return super.getTrait(traitType);
    }
    if (traitType instanceof IBackgroundTemplate) {
      return getBackgrounds().getBackgroundByTemplate((IBackgroundTemplate) traitType);
    }
    throw new UnsupportedOperationException("Unsupported trait type " + traitType); //$NON-NLS-1$
  }

  public IBackgroundConfiguration getBackgrounds() {
    return backgrounds;
  }

  public IIdentifiedTraitTypeGroup[] getAbilityTypeGroups() {
    return abilityTraitGroups;
  }

  public final IIdentifiedCasteTraitTypeGroup[] getAttributeTypeGroups() {
    return attributeTraitGroups;
  }

  public IIdentificate getAbilityGroupId(AbilityType abilityType) {
    for (IIdentifiedTraitTypeGroup group : getAbilityTypeGroups()) {
      if (group.contains(abilityType)) {
        return group.getGroupId();
      }
    }
    throw new IllegalStateException("Ability type in no group: " + abilityType); //$NON-NLS-1$
  }

  public IFavorableTrait[] getAllAbilities() {
    List<ITraitType> abilityTypes = new ArrayList<ITraitType>();
    for (IIdentifiedTraitTypeGroup group : getAbilityTypeGroups()) {
      Collections.addAll(abilityTypes, group.getAllGroupTypes());
    }
    return getFavorableTraits(abilityTypes.toArray(new ITraitType[abilityTypes.size()]));
  }

  public ISpecialtiesConfiguration getSpecialtyConfiguration() {
    return specialtyConfiguration;
  }
}