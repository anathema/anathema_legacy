package net.sf.anathema.character.library.trait;

import com.google.common.base.Preconditions;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ITraitContext;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ITraitValueStrategy;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.library.trait.favorable.IFavorableTrait;
import net.sf.anathema.character.library.trait.rules.ITraitRules;
import net.sf.anathema.lib.control.IIntValueChangedListener;
import org.jmock.example.announcer.Announcer;

public abstract class AbstractFavorableTrait implements IFavorableTrait {

  private final ITraitRules traitRules;
  private final Announcer<IIntValueChangedListener> creationPointControl = Announcer.to(IIntValueChangedListener.class);
  private final Announcer<IIntValueChangedListener> currentValueControl = Announcer.to(IIntValueChangedListener.class);
  private final ITraitContext traitContext;

  public AbstractFavorableTrait(ITraitRules traitRules, ITraitContext traitContext) {
    Preconditions.checkNotNull(traitRules);
    this.traitRules = traitRules;
    this.traitContext = traitContext;
  }

  @Override
  public boolean isCasteOrFavored() {
    return getFavorization().isCasteOrFavored();
  }

  @Override
  public final ITraitType getType() {
    return getTraitRules().getType();
  }

  @Override
  public final int getMaximalValue() {
    return getTraitRules().getAbsoluteMaximumValue();
  }

  @Override
  public int hashCode() {
    return getType().getId().hashCode();
  }

  public final int getZeroCalculationValue() {
    return getTraitRules().getZeroCalculationCost();
  }

  @Override
  public int getInitialValue() {
    return getTraitRules().getStartValue();
  }

  @Override
  public final void addCreationPointListener(IIntValueChangedListener listener) {
    getCreationPointControl().addListener(listener);
  }

  @Override
  public final void removeCreationPointListener(IIntValueChangedListener listener) {
    getCreationPointControl().removeListener(listener);
  }

  @Override
  public final void addCurrentValueListener(IIntValueChangedListener listener) {
    getCurrentValueControl().addListener(listener);
  }

  @Override
  public final void removeCurrentValueListener(IIntValueChangedListener listener) {
    getCurrentValueControl().removeListener(listener);
  }

  protected ITraitRules getTraitRules() {
    return traitRules;
  }

  protected Announcer<IIntValueChangedListener> getCreationPointControl() {
    return creationPointControl;
  }

  protected Announcer<IIntValueChangedListener> getCurrentValueControl() {
    return currentValueControl;
  }

  protected ITraitValueStrategy getTraitValueStrategy() {
    return traitContext.getTraitValueStrategy();
  }
}