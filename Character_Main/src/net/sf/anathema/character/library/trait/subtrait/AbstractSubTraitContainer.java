package net.sf.anathema.character.library.trait.subtrait;

import com.google.common.base.Preconditions;
import net.sf.anathema.character.library.trait.specialties.ISpecialty;
import net.sf.anathema.lib.control.IIntValueChangedListener;
import org.jmock.example.announcer.Announcer;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSubTraitContainer implements ISubTraitContainer {

  private final List<ISubTrait> unremovableSubTraits = new ArrayList<>();
  private final List<ISubTrait> subtraits = new ArrayList<>();
  private final Announcer<ISubTraitListener> subTraitListeners = Announcer.to(ISubTraitListener.class);
  private final IIntValueChangedListener subTraitCreationPointListener = new IIntValueChangedListener() {
    @Override
    public void valueChanged(int newValue) {
      fireSubTraitValueChangedEvent();
    }
  };

  protected final void addUnremovableSubTraits(String... unremovableSubTraitNames) {
    for (String traitName : unremovableSubTraitNames) {
      ISubTrait subTrait = addSubTrait(traitName);
      unremovableSubTraits.add(subTrait);
    }
  }

  @Override
  public boolean isRemovable(ISubTrait subTrait) {
    return !unremovableSubTraits.contains(subTrait);
  }

  private void fireSubTraitAddedEvent(ISubTrait subTrait) {
    subTraitListeners.announce().subTraitAdded(subTrait);
  }

  private void fireSubTraitValueChangedEvent() {
    subTraitListeners.announce().subTraitValueChanged();
  }

  private void fireSubTraitRemovedEvent(ISubTrait subTrait) {
    subTraitListeners.announce().subTraitRemoved(subTrait);
  }

  @Override
  public void addSubTraitListener(ISubTraitListener listener) {
    subTraitListeners.addListener(listener);
  }

  @Override
  public final void removeSubTraitListener(ISubTraitListener listener) {
    subTraitListeners.removeListener(listener);
  }

  @Override
  public ISpecialty getSubTrait(String name) {
    for (ISpecialty subtrait : getSubTraits()) {
      if (subtrait.getName().equals(name)) {
        return subtrait;
      }
    }
    return null;
  }

  @Override
  public final int getCreationDotTotal() {
    int count = 0;
    for (ISubTrait specialty : getSubTraits()) {
      count += specialty.getCreationValue();
    }
    return count;
  }

  @Override
  public final int getCurrentDotTotal() {
    int count = 0;
    for (ISubTrait specialty : getSubTraits()) {
      count += Math.max(0, specialty.getCurrentValue());
    }
    return count;
  }

  @Override
  public void removeSubTrait(ISubTrait subtrait) {
    subtraits.remove(subtrait);
    subtrait.removeCreationPointListener(subTraitCreationPointListener);
    fireSubTraitRemovedEvent(subtrait);
  }

  @Override
  public final int getExperienceDotTotal() {
    int count = 0;
    for (ISubTrait specialty : getSubTraits()) {
      count += Math.max(0, specialty.getExperiencedValue() - specialty.getCreationValue());
    }
    return count;
  }

  @Override
  public final ISpecialty[] getSubTraits() {
    return subtraits.toArray(new ISpecialty[subtraits.size()]);
  }

  protected abstract ISpecialty createSubTrait(String name);

  protected abstract void handleAdditionOfContainedEquivalent(ISubTrait subTrait);

  @Override
  public final ISpecialty addSubTrait(String traitName) {
    Preconditions.checkNotNull(traitName);
    if (isNewSubTraitAllowed()) {
      ISpecialty subTrait = getSubTrait(traitName);
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
    for (ISubTrait trait : getSubTraits()) {
      removeSubTrait(trait);
    }
  }
}