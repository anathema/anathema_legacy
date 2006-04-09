package net.sf.anathema.character.library.quality.model;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.library.quality.presenter.IQuality;
import net.sf.anathema.character.library.quality.presenter.IQualityModel;
import net.sf.anathema.character.library.quality.presenter.IQualitySelection;
import net.sf.anathema.lib.control.ChangeControl;
import net.sf.anathema.lib.control.IChangeListener;

public abstract class AbstractQualityModel<Q extends IQuality> implements IQualityModel<Q> {

  private Q currentQuality;
  private final ICharacterModelContext context;
  private final ChangeControl control = new ChangeControl();
  private final List<IQualitySelection<Q>> selectedQualities = new ArrayList<IQualitySelection<Q>>();

  public AbstractQualityModel(ICharacterModelContext context) {
    this.context = context;
  }

  public void setCurrentQuality(Q quality) {
    this.currentQuality = quality;
  }

  public Q getCurrentQuality() {
    return currentQuality;
  }

  public boolean isSelectable(Q quality) {
    return !isSelected(quality);
  }

  protected final boolean isSelected(Q quality) {
    for (IQualitySelection<Q> selection : getSelectedQualities()) {
      if (selection.getQuality() == quality && isActive(selection)) {
        return true;
      }
    }
    return false;
  }

  public boolean isActive(IQualitySelection<Q> selection) {
    boolean experienceActiveWhileExperienced = isCharacterExperienced() && selection.isExperienceActive();
    boolean creationActiveDuringCreation = !isCharacterExperienced() && selection.isCreationActive();
    return creationActiveDuringCreation || experienceActiveWhileExperienced;
  }

  public final boolean isCharacterExperienced() {
    return context.getBasicCharacterContext().isExperienced();
  }

  protected final ICharacterModelContext getContext() {
    return context;
  }

  protected final void fireModelChangedEvent() {
    control.fireChangedEvent();
  }

  public final void addModelChangeListener(IChangeListener listener) {
    control.addChangeListener(listener);
  }

  public void removeQualitySelection(IQualitySelection<Q> selection) {
    for (IQualitySelection existingSelection : getSelectedQualities()) {
      if (existingSelection.equals(selection)) {
        boolean experienced = isCharacterExperienced();
        if (experienced) {
          existingSelection.setExperienceActive(false);
          if (!existingSelection.isCreationActive()) {
            removeQualityCompletely(existingSelection);
          }
        }
        else {
          if (existingSelection.isCreationActive()) {
            removeQualityCompletely(existingSelection);
          }
        }
        fireModelChangedEvent();
        break;
      }
    }
  }

  private void removeQualityCompletely(IQualitySelection existingSelection) {
    selectedQualities.remove(existingSelection);
  }

  public IQualitySelection<Q>[] getSelectedQualities() {
    List<IQualitySelection<Q>> activeSelectedQualities = new ArrayList<IQualitySelection<Q>>();
    for (IQualitySelection<Q> selection : selectedQualities) {
      activeSelectedQualities.add(selection);
    }
    return activeSelectedQualities.toArray(new IQualitySelection[activeSelectedQualities.size()]);
  }

  public void addQualitySelection(IQualitySelection<Q> selection) {
    if (!selection.isCreationActive() && !selection.isExperienceActive()) {
      return;
    }
    for (IQualitySelection existingSelection : getSelectedQualities()) {
      if (existingSelection.getQuality() == selection.getQuality()) {
        checkForReplacement(selection, existingSelection);
        checkForReacquiredQuality(selection, existingSelection);
        return;
      }
    }
    addQuality(selection);
  }

  private void checkForReacquiredQuality(IQualitySelection<Q> selection, IQualitySelection existingSelection) {
    if (!existingSelection.isExperienceActive() && selection.isExperienceActive()) {
      addQuality(selection);
    }
  }

  private void checkForReplacement(IQualitySelection<Q> selection, IQualitySelection existingSelection) {
    if ((existingSelection.isCreationActive()) || !selection.isCreationActive()) {
      return;
    }
    removeQualityCompletely(existingSelection);
    addQuality(selection);
  }

  private void addQuality(IQualitySelection<Q> selection) {
    selectedQualities.add(selection);
    fireModelChangedEvent();
  }
}