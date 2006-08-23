package net.sf.anathema.character.library.trait.specialties;

import java.util.HashMap;
import java.util.Map;

import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.ICharacterChangeListener;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.groups.ITraitTypeGroup;
import net.sf.anathema.character.generic.traits.groups.TraitTypeGroup;
import net.sf.anathema.character.library.trait.ITrait;
import net.sf.anathema.character.library.trait.ITraitCollection;
import net.sf.anathema.character.library.trait.subtrait.ISubTraitContainer;
import net.sf.anathema.character.library.trait.visitor.IAggregatedTrait;
import net.sf.anathema.character.library.trait.visitor.IDefaultTrait;
import net.sf.anathema.character.library.trait.visitor.ITraitVisitor;
import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.change.IChangeListener;

public class SpecialtiesConfiguration implements ISpecialtiesConfiguration {

  private final Map<ITraitType, ISubTraitContainer> specialtiesByTrait = new HashMap<ITraitType, ISubTraitContainer>();
  private final ITraitType[] traitTypes;
  private String currentName;
  private ITraitType currentType;
  private final ChangeControl control = new ChangeControl();
  private final ICharacterModelContext context;

  public SpecialtiesConfiguration(
      ITraitCollection traitCollection,
      ITraitTypeGroup[] groups,
      final ICharacterModelContext context) {
    this.context = context;
    this.traitTypes = TraitTypeGroup.getAllTraitTypes(groups);
    for (ITrait trait : traitCollection.getTraits(getAllTraitTypes())) {
      trait.accept(new ITraitVisitor() {
        public void visitAggregatedTrait(IAggregatedTrait visitedTrait) {
          initializeAggregatedTrait(visitedTrait);
        }

        public void visitDefaultTrait(IDefaultTrait visitedTrait) {
          ITraitType traitType = visitedTrait.getType();
          specialtiesByTrait.put(traitType, new SpecialtiesContainer(traitType, context.getTraitContext()));
        }
      });
    }
  }

  private void initializeAggregatedTrait(IAggregatedTrait trait) {
    ITraitType traitType = trait.getType();
//    specialtiesByTrait.put(traitType, new AggregatedSpecialtiesContainer());
//    trait.getSubTraits().addSubTraitListener(new ISubTraitListener() {
//      public void subTraitAdded(ISubTrait subTrait) {
//        // TODO Auto-generated method stub
//      }
//
//      public void subTraitRemoved(ISubTrait subTrait) {
//        // TODO Auto-generated method stub
//      }
//
//      public void subTraitValueChanged() {
//        // nothing to do
//      }
//    });
    specialtiesByTrait.put(traitType, new SpecialtiesContainer(traitType, context.getTraitContext()));
  }

  public ISubTraitContainer getSpecialtiesContainer(ITraitType traitType) {
    return specialtiesByTrait.get(traitType);
  }

  public ITraitType[] getAllTraitTypes() {
    return traitTypes;
  }

  public void setCurrentSpecialtyName(String newSpecialtyName) {
    this.currentName = newSpecialtyName;
    control.fireChangedEvent();
  }

  public void setCurrentTraitType(ITraitType newValue) {
    this.currentType = newValue;
    control.fireChangedEvent();
  }

  public void commitSelection() {
    specialtiesByTrait.get(currentType).addSubTrait(currentName);
  }

  public void clear() {
    currentName = null;
    currentType = null;
    control.fireChangedEvent();
  }

  public void addCurrentSelectionListener(IChangeListener listener) {
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
}