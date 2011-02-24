package net.sf.anathema.character.library.trait.specialties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.character.generic.framework.ITraitReference;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.ICharacterChangeListener;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.groups.ITraitTypeGroup;
import net.sf.anathema.character.generic.traits.groups.TraitTypeGroup;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.library.trait.ITrait;
import net.sf.anathema.character.library.trait.ITraitCollection;
import net.sf.anathema.character.library.trait.subtrait.ISubTrait;
import net.sf.anathema.character.library.trait.subtrait.ISubTraitContainer;
import net.sf.anathema.character.library.trait.subtrait.ISubTraitListener;
import net.sf.anathema.character.library.trait.visitor.IAggregatedTrait;
import net.sf.anathema.character.library.trait.visitor.IDefaultTrait;
import net.sf.anathema.character.library.trait.visitor.ITraitVisitor;
import net.sf.anathema.lib.control.GenericControl;
import net.sf.anathema.lib.control.IClosure;
import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.change.IChangeListener;

public class SpecialtiesConfiguration implements ISpecialtiesConfiguration {

  private final Map<ITraitType, ISubTraitContainer> specialtiesByType = new HashMap<ITraitType, ISubTraitContainer>();
  private final Map<ITraitReference, ISubTraitContainer> specialtiesByTrait = new HashMap<ITraitReference, ISubTraitContainer>();
  private final ChangeControl control = new ChangeControl();
  private final GenericControl<ITraitReferencesChangeListener> traitControl = new GenericControl<ITraitReferencesChangeListener>();
  private final ICharacterModelContext context;
  private String currentName;
  private ITraitReference currentType;
  
  //reference to the Lunar generic charm which grants the ability to take attribute specialties
  private final String flawlessFocus = "Lunar.FlawlessFocus.";

  public SpecialtiesConfiguration(
      ITraitCollection traitCollection,
      ITraitTypeGroup[] groups,
      final ICharacterModelContext context) {
    this.context = context;
    ITraitType[] traitTypes = TraitTypeGroup.getAllTraitTypes(groups);
    for (ITrait trait : traitCollection.getTraits(traitTypes)) {
      trait.accept(new ITraitVisitor() {
        public void visitAggregatedTrait(IAggregatedTrait visitedTrait) {
          initializeAggregatedTrait(visitedTrait);
        }

        public void visitDefaultTrait(IDefaultTrait visitedTrait) {
          ITraitReference reference = new DefaultTraitReference(visitedTrait);
          SpecialtiesContainer container = addSpecialtiesContainer(reference);
          specialtiesByType.put(visitedTrait.getType(), container);
        }
      });
    }
  }

  private void initializeAggregatedTrait(final IAggregatedTrait visitedTrait) {
    visitedTrait.getSubTraits().addSubTraitListener(new ISubTraitListener() {
      public void subTraitAdded(ISubTrait subTrait) {
        ISubTraitContainer container = specialtiesByType.get(visitedTrait.getType());
        addSubTraitSpecialtiesContainer(subTrait, (AggregatedSpecialtiesContainer) container);
      }

      public void subTraitRemoved(final ISubTrait subTrait) {
        ISubTraitContainer container = specialtiesByType.get(visitedTrait.getType());
        removeSubTraitSpecialtiesContainer(subTrait, (AggregatedSpecialtiesContainer) container);
      }

      public void subTraitValueChanged() {
        // nothing to do
      }
    });
    AggregatedSpecialtiesContainer container = new AggregatedSpecialtiesContainer();
    for (ISubTrait subTrait : visitedTrait.getSubTraits().getSubTraits()) {
      addSubTraitSpecialtiesContainer(subTrait, container);
    }
    specialtiesByType.put(visitedTrait.getType(), container);
  }

  private void removeSubTraitSpecialtiesContainer(final ISubTrait subTrait, AggregatedSpecialtiesContainer container) {
    ITraitReference reference = new SubTraitReference(subTrait);
    ISubTraitContainer subContainer = specialtiesByTrait.remove(reference);
    subContainer.dispose();
    container.removeContainer(subContainer);
    traitControl.forAllDo(new IClosure<ITraitReferencesChangeListener>() {
      public void execute(ITraitReferencesChangeListener input) {
        input.referenceRemoved(new SubTraitReference(subTrait));
      }
    });
  }

  private void addSubTraitSpecialtiesContainer(ISubTrait subTrait, AggregatedSpecialtiesContainer container) {
    final SubTraitReference reference = new SubTraitReference(subTrait);
    SpecialtiesContainer subContainer = addSpecialtiesContainer(reference);
    container.addContainer(subContainer);
    traitControl.forAllDo(new IClosure<ITraitReferencesChangeListener>() {
      public void execute(ITraitReferencesChangeListener input) {
        input.referenceAdded(reference);
      }
    });
  }

  private SpecialtiesContainer addSpecialtiesContainer(ITraitReference reference) {
    SpecialtiesContainer specialtiesContainer = new SpecialtiesContainer(reference, context.getTraitContext());
    specialtiesByTrait.put(reference, specialtiesContainer);
    return specialtiesContainer;
  }

  public ISubTraitContainer getSpecialtiesContainer(ITraitReference trait) {
    return specialtiesByTrait.get(trait);
  }

  public ISubTraitContainer getSpecialtiesContainer(ITraitType traitType) {
    return specialtiesByType.get(traitType);
  }

  public ITraitReference[] getAllTraits() {
    Set<ITraitReference> keySet = specialtiesByTrait.keySet();
    return keySet.toArray(new ITraitReference[keySet.size()]);
  }
  
  public ITraitReference[] getAllEligibleTraits()
  {
	   List<ITraitReference> keySet = new ArrayList<ITraitReference>(specialtiesByTrait.keySet());
	   List<ITraitReference> toRemove = new ArrayList<ITraitReference>();
	   
	   for (ITraitReference trait : keySet)
	   {
		   if (trait.getTraitType() instanceof AttributeType)
		   {
			   boolean hasFocus = false;
			   for (ICharm charm : context.getCharmContext().getCharmConfiguration().getLearnedCharms())
				   if (charm.getId().equals(flawlessFocus + trait))
					   hasFocus = true;
			   if (!hasFocus)
			   {
				   toRemove.add(trait);
				   getSpecialtiesContainer(trait.getTraitType()).dispose();
			   }
		   }
		   if (!getSpecialtiesContainer(trait.getTraitType()).isNewSubTraitAllowed() &&
			   !toRemove.contains(trait))
			   toRemove.add(trait);
			   
	   }
	   keySet.removeAll(toRemove);
	   
	   return keySet.toArray(new ITraitReference[keySet.size()]);
  }

  public void setCurrentSpecialtyName(String newSpecialtyName) {
    this.currentName = newSpecialtyName;
    control.fireChangedEvent();
  }

  public void setCurrentTrait(ITraitReference newValue) {
    this.currentType = newValue;
    control.fireChangedEvent();
  }

  public void commitSelection() {
    ISubTrait specialty = specialtiesByTrait.get(currentType).addSubTrait(currentName);
    if (specialty != null && specialty.getCurrentValue() == 0) {
      specialty.setCurrentValue(1);
    }
  }

  public void clear() {
    currentName = null;
    currentType = null;
    control.fireChangedEvent();
  }

  public void addSelectionChangeListener(IChangeListener listener) {
    control.addChangeListener(listener);
  }

  public boolean isEntryComplete() {
    return !StringUtilities.isNullOrEmpty(currentName) && currentType != null;
  }

  public boolean isExperienced() {
    return context.getBasicCharacterContext().isExperienced();
  }

  public void addCharacterChangeListener(ICharacterChangeListener listener) {
    context.getCharacterListening().addChangeListener(listener);
  }

  public void addTraitListChangeListener(ITraitReferencesChangeListener listener) {
    traitControl.addListener(listener);
  }
}