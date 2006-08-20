package net.sf.anathema.character.craft.model;

import java.util.Arrays;
import java.util.List;

import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.character.craft.presenter.ICraftModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.library.removableentry.presenter.IRemovableEntryListener;
import net.sf.anathema.character.library.trait.subtrait.ISubTrait;
import net.sf.anathema.character.library.trait.subtrait.ISubTraitListener;
import net.sf.anathema.character.library.trait.visitor.IAggregatedTrait;
import net.sf.anathema.lib.control.GenericControl;
import net.sf.anathema.lib.control.IClosure;

public class CraftModel implements ICraftModel {

  private final GenericControl<IRemovableEntryListener<ISubTrait>> control = new GenericControl<IRemovableEntryListener<ISubTrait>>();
  private String currentName;
  private final IAggregatedTrait trait;

  public CraftModel(ICharacterModelContext context) {
    this.trait = (IAggregatedTrait) context.getTraitCollection().getFavorableTrait(AbilityType.Craft);
    trait.getSubTraits().addSubTraitListener(new ISubTraitListener() {
      public void subTraitAdded(final ISubTrait subTrait) {
        control.forAllDo(new IClosure<IRemovableEntryListener<ISubTrait>>() {
          public void execute(IRemovableEntryListener<ISubTrait> input) {
            input.entryAdded(subTrait);
          }
        });
      }

      public void subTraitRemoved(final ISubTrait subTrait) {
        control.forAllDo(new IClosure<IRemovableEntryListener<ISubTrait>>() {
          public void execute(IRemovableEntryListener<ISubTrait> input) {
            input.entryRemoved(subTrait);
          }
        });
      }

      public void subTraitValueChanged() {
        //nothing to do; 
      }
    });
    for (ISubTrait subTrait : trait.getSubTraits().getSubTraits()) {
      addSubTraitEntry(subTrait);
    }
  }

  private void addSubTraitEntry(ISubTrait subTrait) {
    currentName = subTrait.getName();
    commitSelection();
  }

  public int getAbsoluteMaximum() {
    return trait.getMaximalValue();
  }

  public boolean isRemovable(ISubTrait craft) {
    return trait.getSubTraits().isRemovable(craft);
  }

  public void setCurrentName(String newValue) {
    this.currentName = newValue;
    fireEntryChanged();
  }

  public void removeEntry(final ISubTrait entry) {
    trait.getSubTraits().removeSubTrait(entry);   
  }

  private boolean isEntryAllowed() {
    return !StringUtilities.isNullOrEmpty(currentName);
  }

  public ISubTrait commitSelection() {
    final ISubTrait subTrait = trait.getSubTraits().addSubTrait(currentName);

    return subTrait;
  }

  public List<ISubTrait> getEntries() {
    return Arrays.asList(trait.getSubTraits().getSubTraits());
  }

  private void fireEntryChanged() {
    control.forAllDo(new IClosure<IRemovableEntryListener<ISubTrait>>() {
      public void execute(IRemovableEntryListener<ISubTrait> input) {
        input.entryAllowed(isEntryAllowed());
      }
    });
  }

  public void addModelChangeListener(IRemovableEntryListener<ISubTrait> listener) {
    control.addListener(listener);
  }
}