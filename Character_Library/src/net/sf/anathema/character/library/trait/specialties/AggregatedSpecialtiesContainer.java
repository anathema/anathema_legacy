package net.sf.anathema.character.library.trait.specialties;

import net.sf.anathema.character.library.trait.subtrait.ISubTrait;
import net.sf.anathema.character.library.trait.subtrait.ISubTraitContainer;
import net.sf.anathema.character.library.trait.subtrait.ISubTraitListener;
import org.jmock.example.announcer.Announcer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AggregatedSpecialtiesContainer implements ISubTraitContainer {

  private final List<SpecialtiesContainer> containers = new ArrayList<>();
  private final Announcer<ISubTraitListener> listeners =Announcer.to(ISubTraitListener.class);
  private final ISubTraitListener listener = new ISubTraitListener() {
    @Override
    public void subTraitAdded(ISubTrait subTrait) {
      listeners.announce().subTraitAdded(subTrait);
    }

    @Override
    public void subTraitRemoved(ISubTrait subTrait) {
      listeners.announce().subTraitRemoved(subTrait);
    }

    @Override
    public void subTraitValueChanged() {
      listeners.announce().subTraitValueChanged();
    }
  };

  @Override
  public void addSubTraitListener(ISubTraitListener newListener) {
    listeners.addListener(newListener);
  }

  @Override
  public int getCreationDotTotal() {
    int total = 0;
    for (ISubTraitContainer container : containers) {
      total += container.getCreationDotTotal();
    }
    return total;
  }

  @Override
  public int getCurrentDotTotal() {
    int total = 0;
    for (ISubTraitContainer container : containers) {
      total += container.getCurrentDotTotal();
    }
    return total;
  }

  @Override
  public int getExperienceDotTotal() {
    int total = 0;
    for (ISubTraitContainer container : containers) {
      total += container.getExperienceDotTotal();
    }
    return total;
  }

  @Override
  public ISubTrait[] getSubTraits() {
    List<ISubTrait> traits = new ArrayList<>();
    for (ISubTraitContainer container : containers) {
      Collections.addAll(traits, container.getSubTraits());
    }
    return traits.toArray(new ISubTrait[traits.size()]);
  }

  @Override
  public void removeSubTraitListener(ISubTraitListener newListener) {
    listeners.removeListener(newListener);
  }

  public void addContainer(SpecialtiesContainer subContainer) {
    subContainer.addSubTraitListener(listener);
    containers.add(subContainer);
  }

  @SuppressWarnings("SuspiciousMethodCalls")
  public void removeContainer(ISubTraitContainer subContainer) {
    subContainer.removeSubTraitListener(listener);
    containers.remove(subContainer);
  }

  @Override
  public ISubTrait addSubTrait(String subName) {
    throw new UnsupportedOperationException("Add to subcontainers instead.");
  }

  @Override
  public ISubTrait getSubTrait(String traitName) {
    throw new UnsupportedOperationException("Get from subcontainers instead.");
  }

  @Override
  public boolean isRemovable(ISubTrait subTrait) {
    throw new UnsupportedOperationException("Ask subcontainers instead.");
  }

  @Override
  public void removeSubTrait(ISubTrait specialty) {
    throw new UnsupportedOperationException("Remove from subcontainers instead.");
  }

  @Override
  public void dispose() {
    throw new UnsupportedOperationException("Dispose subcontainers instead.");
  }

	@Override
	public boolean isNewSubTraitAllowed()
	{
		return true;
	}
}