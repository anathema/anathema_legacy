package net.sf.anathema.character.library.trait.specialty;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.ITraitValueStrategy;
import net.sf.anathema.character.library.trait.ITrait;
import net.sf.anathema.character.library.trait.rules.ITraitRules;
import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;

public class SpecialtiesContainer implements ISubTraitContainer {

  public static final int ALLOWED_SPECIALTY_COUNT = 3;
  private final List<ISubTrait> specialties = new ArrayList<ISubTrait>();
  private final List<ISubTraitListener> specialtyListenerList = new ArrayList<ISubTraitListener>();
  private final IIntValueChangedListener specialtyCreationPointListener = new IIntValueChangedListener() {
    public void valueChanged(int newValue) {
      fireSpecialtyValueChangedEvent();
    }
  };
  private final ITrait trait;
  private final ITraitRules traitRules;
  private final ITraitValueStrategy traitValueStrategy;

  public SpecialtiesContainer(ITrait trait, ITraitRules traitRules, ITraitValueStrategy traitValueStrategy) {
    this.trait = trait;
    this.traitRules = traitRules;
    this.traitValueStrategy = traitValueStrategy;
  }

  public ISubTrait[] getSubTraits() {
    return specialties.toArray(new ISubTrait[specialties.size()]);
  }

  public ISubTrait addSubTrait(String specialtyName) {
    int specialtyCount = getCurrentDotTotal();
    if (specialtyCount < ALLOWED_SPECIALTY_COUNT) {
      ISubTrait specialty = getContainedEquivalent(specialtyName);
      if (specialty == null) {
        specialty = new Specialty(this, trait, specialtyName, traitRules, traitValueStrategy);
        specialties.add(specialty);
        specialty.addCurrentValueListener(specialtyCreationPointListener);
        fireSpecialtyAddedEvent(specialty);
      }
      else {
        int maxAddition = ALLOWED_SPECIALTY_COUNT - specialtyCount;
        int addition = Math.min(specialty.getCurrentValue(), maxAddition);
        specialty.setCurrentValue(specialty.getCurrentValue() + addition);
      }
      return specialty;
    }
    return null;
  }

  private ISubTrait getContainedEquivalent(String name) {
    for (ISubTrait specialty : getSubTraits()) {
      if (specialty.getName().equals(name)) {
        return specialty;
      }
    }
    return null;
  }

  public int getCreationDotTotal() {
    int count = 0;
    for (ISubTrait specialty : getSubTraits()) {
      count += specialty.getCreationValue();
    }
    return count;
  }

  public int getCurrentDotTotal() {
    int count = 0;
    for (ISubTrait specialty : getSubTraits()) {
      count += Math.max(0, specialty.getCurrentValue());
    }
    return count;
  }

  public void removeSubTrait(ISubTrait specialty) {
    specialties.remove(specialty);
    specialty.removeCreationPointListener(specialtyCreationPointListener);
    fireSpecialtyRemovedEvent(specialty);
  }

  private synchronized void fireSpecialtyAddedEvent(ISubTrait specialty) {
    List<ISubTraitListener> cloneList = new ArrayList<ISubTraitListener>(specialtyListenerList);
    for (ISubTraitListener listener : cloneList) {
      listener.specialtyAdded(specialty);
    }
  }

  private synchronized void fireSpecialtyValueChangedEvent() {
    List<ISubTraitListener> cloneList = new ArrayList<ISubTraitListener>(specialtyListenerList);
    for (ISubTraitListener listener : cloneList) {
      listener.specialtyValueChanged();
    }
  }

  private synchronized void fireSpecialtyRemovedEvent(ISubTrait specialty) {
    List<ISubTraitListener> cloneList = new ArrayList<ISubTraitListener>(specialtyListenerList);
    for (ISubTraitListener listener : cloneList) {
      listener.specialtyRemoved(specialty);
    }
  }

  public synchronized void addSubTraitListener(ISubTraitListener listener) {
    specialtyListenerList.add(listener);
  }

  public synchronized void removeSubTraitListener(ISubTraitListener listener) {
    specialtyListenerList.remove(listener);
  }

  public int getExperienceDotTotal() {
    int count = 0;
    for (ISubTrait specialty : getSubTraits()) {
      count += Math.max(0, specialty.getExperiencedValue() - specialty.getCreationValue());
    }
    return count;
  }
}