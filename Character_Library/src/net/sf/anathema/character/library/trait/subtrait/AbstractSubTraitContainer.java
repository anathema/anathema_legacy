package net.sf.anathema.character.library.trait.subtrait;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.lib.control.GenericControl;
import net.sf.anathema.lib.control.IClosure;
import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;

public abstract class AbstractSubTraitContainer implements ISubTraitContainer {

  private final List<ISubTrait> unremovableSubTraits = new ArrayList<ISubTrait>();
  private final List<ISubTrait> subtraits = new ArrayList<ISubTrait>();
  private final GenericControl<ISubTraitListener> subTraitListeners = new GenericControl<ISubTraitListener>();
  private final IIntValueChangedListener subTraitCreationPointListener = new IIntValueChangedListener() {
    public void valueChanged(int newValue) {
      fireSubTraitValueChangedEvent();
    }
  };
  
  public AbstractSubTraitContainer(String... unremovableSubTraitNames) {
    for (String traitName : unremovableSubTraitNames) {
      ISubTrait subTrait = addSubTrait(traitName);
      unremovableSubTraits.add(subTrait);
    }
  }
  
  public boolean isRemovable(ISubTrait subTrait) {
    return !unremovableSubTraits.contains(subTrait);
  }

  private void fireSubTraitAddedEvent(final ISubTrait subTrait) {
    subTraitListeners.forAllDo(new IClosure<ISubTraitListener>() {
      public void execute(ISubTraitListener input) {
        input.subTraitAdded(subTrait);
      }
    });
  }

  private void fireSubTraitValueChangedEvent() {
    subTraitListeners.forAllDo(new IClosure<ISubTraitListener>() {
      public void execute(ISubTraitListener input) {
        input.subTraitValueChanged();
      }
    });
  }

  private void fireSubTraitRemovedEvent(final ISubTrait subTrait) {
    subTraitListeners.forAllDo(new IClosure<ISubTraitListener>() {
      public void execute(ISubTraitListener input) {
        input.subTraitRemoved(subTrait);
      }
    });
  }

  public void addSubTraitListener(ISubTraitListener listener) {
    subTraitListeners.addListener(listener);
  }

  public final void removeSubTraitListener(ISubTraitListener listener) {
    subTraitListeners.removeListener(listener);
  }

  private ISubTrait getContainedEquivalent(String name) {
    for (ISubTrait specialty : getSubTraits()) {
      if (specialty.getName().equals(name)) {
        return specialty;
      }
    }
    return null;
  }

  public final int getCreationDotTotal() {
    int count = 0;
    for (ISubTrait specialty : getSubTraits()) {
      count += specialty.getCreationValue();
    }
    return count;
  }

  public final int getCurrentDotTotal() {
    int count = 0;
    for (ISubTrait specialty : getSubTraits()) {
      count += Math.max(0, specialty.getCurrentValue());
    }
    return count;
  }

  public final void removeSubTrait(ISubTrait specialty) {
    subtraits.remove(specialty);
    specialty.removeCreationPointListener(subTraitCreationPointListener);
    fireSubTraitRemovedEvent(specialty);
  }

  public final int getExperienceDotTotal() {
    int count = 0;
    for (ISubTrait specialty : getSubTraits()) {
      count += Math.max(0, specialty.getExperiencedValue() - specialty.getCreationValue());
    }
    return count;
  }

  public final ISubTrait[] getSubTraits() {
    return subtraits.toArray(new ISubTrait[subtraits.size()]);
  }

  protected abstract ISubTrait createSubTrait(String name);

  protected abstract boolean isNewSubTraitAllowed();

  protected abstract void handleAdditionOfContainedEquivalent(ISubTrait subTrait);

  public final ISubTrait addSubTrait(String specialtyName) {
    if (isNewSubTraitAllowed()) {
      ISubTrait subTrait = getContainedEquivalent(specialtyName);
      if (subTrait == null) {
        subTrait = createSubTrait(specialtyName);
        subtraits.add(subTrait);
        subTrait.addCurrentValueListener(subTraitCreationPointListener);
        fireSubTraitAddedEvent(subTrait);
      }
      else {
        handleAdditionOfContainedEquivalent(subTrait);
      }
      return subTrait;
    }
    return null;
  }
}