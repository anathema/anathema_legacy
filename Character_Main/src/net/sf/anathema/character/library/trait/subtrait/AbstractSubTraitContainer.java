package net.sf.anathema.character.library.trait.subtrait;

import com.google.common.base.Preconditions;
import net.sf.anathema.character.library.trait.specialties.Specialty;
import net.sf.anathema.lib.control.IIntValueChangedListener;
import org.jmock.example.announcer.Announcer;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSubTraitContainer implements ISubTraitContainer {

  private final List<Specialty> unremovableSubTraits = new ArrayList<>();
  private final List<Specialty> subtraits = new ArrayList<>();
  private final Announcer<ISpecialtyListener> subTraitListeners = Announcer.to(ISpecialtyListener.class);
  private final IIntValueChangedListener subTraitCreationPointListener = new IIntValueChangedListener() {
    @Override
    public void valueChanged(int newValue) {
      fireSubTraitValueChangedEvent();
    }
  };

  @Override
  public boolean isRemovable(Specialty subTrait) {
    return !unremovableSubTraits.contains(subTrait);
  }

  private void fireSubTraitAddedEvent(Specialty subTrait) {
    subTraitListeners.announce().subTraitAdded(subTrait);
  }

  private void fireSubTraitValueChangedEvent() {
    subTraitListeners.announce().subTraitValueChanged();
  }

  private void fireSubTraitRemovedEvent(Specialty subTrait) {
    subTraitListeners.announce().subTraitRemoved(subTrait);
  }

  @Override
  public void addSubTraitListener(ISpecialtyListener listener) {
    subTraitListeners.addListener(listener);
  }

  @Override
  public final void removeSubTraitListener(ISpecialtyListener listener) {
    subTraitListeners.removeListener(listener);
  }

  @Override
  public Specialty getSubTrait(String name) {
    for (Specialty subtrait : getSubTraits()) {
      if (subtrait.getName().equals(name)) {
        return subtrait;
      }
    }
    return null;
  }

  @Override
  public final int getCreationDotTotal() {
    int count = 0;
    for (Specialty specialty : getSubTraits()) {
      count += specialty.getCreationValue();
    }
    return count;
  }

  @Override
  public final int getCurrentDotTotal() {
    int count = 0;
    for (Specialty specialty : getSubTraits()) {
      count += Math.max(0, specialty.getCurrentValue());
    }
    return count;
  }

  @Override
  public void removeSubTrait(Specialty subtrait) {
    subtraits.remove(subtrait);
    subtrait.removeCreationPointListener(subTraitCreationPointListener);
    fireSubTraitRemovedEvent(subtrait);
  }

  @Override
  public final int getExperienceDotTotal() {
    int count = 0;
    for (Specialty specialty : getSubTraits()) {
      count += Math.max(0, specialty.getExperiencedValue() - specialty.getCreationValue());
    }
    return count;
  }

  @Override
  public final Specialty[] getSubTraits() {
    return subtraits.toArray(new Specialty[subtraits.size()]);
  }

  protected abstract Specialty createSubTrait(String name);

  protected abstract void handleAdditionOfContainedEquivalent(Specialty subTrait);

  @Override
  public final Specialty addSubTrait(String traitName) {
    Preconditions.checkNotNull(traitName);
    if (isNewSubTraitAllowed()) {
      Specialty subTrait = getSubTrait(traitName);
      if (subTrait == null) {
        subTrait = createSubTrait(traitName);
        subtraits.add(subTrait);
        subTrait.addCurrentValueListener(subTraitCreationPointListener);
        fireSubTraitAddedEvent(subTrait);
      } else {
        handleAdditionOfContainedEquivalent(subTrait);
      }
      return subTrait;
    }
    return null;
  }

  @Override
  public void dispose() {
    for (Specialty trait : getSubTraits()) {
      removeSubTrait(trait);
    }
  }
}