package net.sf.anathema.character.library.trait.specialties;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.sf.anathema.character.library.trait.subtrait.ISubTrait;
import net.sf.anathema.character.library.trait.subtrait.ISubTraitContainer;
import net.sf.anathema.character.library.trait.subtrait.ISubTraitListener;
import net.sf.anathema.lib.control.GenericControl;
import net.sf.anathema.lib.control.IClosure;

public class AggregatedSpecialtiesContainer implements ISubTraitContainer {

  private final List<SpecialtiesContainer> containers = new ArrayList<SpecialtiesContainer>();
  private final GenericControl<ISubTraitListener> listeners = new GenericControl<ISubTraitListener>();
  private final ISubTraitListener listener = new ISubTraitListener() {
    public void subTraitAdded(final ISubTrait subTrait) {
      listeners.forAllDo(new IClosure<ISubTraitListener>() {
        public void execute(ISubTraitListener input) {
          input.subTraitAdded(subTrait);
        }
      });
    }

    public void subTraitRemoved(final ISubTrait subTrait) {
      listeners.forAllDo(new IClosure<ISubTraitListener>() {
        public void execute(ISubTraitListener input) {
          input.subTraitRemoved(subTrait);
        }
      });
    }

    public void subTraitValueChanged() {
      listeners.forAllDo(new IClosure<ISubTraitListener>() {
        public void execute(ISubTraitListener input) {
          input.subTraitValueChanged();
        }
      });
    }
  };

  public void addSubTraitListener(ISubTraitListener newListener) {
    listeners.addListener(newListener);
  }

  public int getCreationDotTotal() {
    int total = 0;
    for (ISubTraitContainer container : containers) {
      total += container.getCreationDotTotal();
    }
    return total;
  }

  public int getCurrentDotTotal() {
    int total = 0;
    for (ISubTraitContainer container : containers) {
      total += container.getCurrentDotTotal();
    }
    return total;
  }

  public int getExperienceDotTotal() {
    int total = 0;
    for (ISubTraitContainer container : containers) {
      total += container.getExperienceDotTotal();
    }
    return total;
  }

  public ISubTrait[] getSubTraits() {
    List<ISubTrait> traits = new ArrayList<ISubTrait>();
    for (ISubTraitContainer container : containers) {
      Collections.addAll(traits, container.getSubTraits());
    }
    return traits.toArray(new ISubTrait[traits.size()]);
  }

  public void removeSubTraitListener(ISubTraitListener newListener) {
    listeners.removeListener(newListener);
  }

  public void addContainer(SpecialtiesContainer subContainer) {
    subContainer.addSubTraitListener(listener);
    containers.add(subContainer);
  }

  public void removeContainer(ISubTraitContainer subContainer) {
    subContainer.removeSubTraitListener(listener);
    containers.remove(subContainer);
  }

  public ISubTrait addSubTrait(String subName) {
    throw new UnsupportedOperationException("Add to subcontainers instead."); //$NON-NLS-1$
  }

  public boolean isRemovable(ISubTrait subTrait) {
    throw new UnsupportedOperationException("Ask subcontainers instead."); //$NON-NLS-1$
  }

  public void removeSubTrait(ISubTrait specialty) {
    throw new UnsupportedOperationException("Remove from subcontainers instead."); //$NON-NLS-1$
  }

  public void dispose() {
    throw new UnsupportedOperationException("Dispose subcontainers instead."); //$NON-NLS-1$
  }
}