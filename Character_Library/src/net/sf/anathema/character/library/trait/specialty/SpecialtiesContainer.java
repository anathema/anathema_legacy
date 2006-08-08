package net.sf.anathema.character.library.trait.specialty;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.ITraitValueStrategy;
import net.sf.anathema.character.library.trait.IModifiableTrait;
import net.sf.anathema.character.library.trait.rules.ITraitRules;
import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;

public class SpecialtiesContainer implements ISpecialtiesContainer {

  public static final int ALLOWED_SPECIALTY_COUNT = 3;
  private final List<ISpecialty> specialties = new ArrayList<ISpecialty>();
  private final List<ISpecialtyListener> specialtyListenerList = new ArrayList<ISpecialtyListener>();
  private final IIntValueChangedListener specialtyCreationPointListener = new IIntValueChangedListener() {
    public void valueChanged(int newValue) {
      fireSpecialtyValueChangedEvent();
    }
  };
  private final IModifiableTrait trait;
  private final ITraitRules traitRules;
  private final ITraitValueStrategy traitValueStrategy;

  public SpecialtiesContainer(IModifiableTrait trait, ITraitRules traitRules, ITraitValueStrategy traitValueStrategy) {
    this.trait = trait;
    this.traitRules = traitRules;
    this.traitValueStrategy = traitValueStrategy;

  }

  public ISpecialty[] getSpecialties() {
    return specialties.toArray(new ISpecialty[specialties.size()]);
  }

  public ISpecialty addSpecialty(String specialtyName) {
    int specialtyCount = getCurrentSpecialtyCount();
    if (specialtyCount < ALLOWED_SPECIALTY_COUNT) {
      ISpecialty specialty = getContainedEquivalent(specialtyName);
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

  private ISpecialty getContainedEquivalent(String name) {
    for (ISpecialty specialty : getSpecialties()) {
      if (specialty.getName().equals(name)) {
        return specialty;
      }
    }
    return null;
  }

  public int getCreationSpecialtyCount() {
    int count = 0;
    for (ISpecialty specialty : getSpecialties()) {
      count += specialty.getCreationValue();
    }
    return count;
  }

  public int getCurrentSpecialtyCount() {
    int count = 0;
    for (ISpecialty specialty : getSpecialties()) {
      count += Math.max(0, specialty.getCurrentValue());
    }
    return count;
  }

  public void removeSpecialty(ISpecialty specialty) {
    specialties.remove(specialty);
    specialty.removeCreationPointListener(specialtyCreationPointListener);
    fireSpecialtyRemovedEvent(specialty);
  }

  private synchronized void fireSpecialtyAddedEvent(ISpecialty specialty) {
    List<ISpecialtyListener> cloneList = new ArrayList<ISpecialtyListener>(specialtyListenerList);
    for (ISpecialtyListener listener : cloneList) {
      listener.specialtyAdded(specialty);
    }
  }

  private synchronized void fireSpecialtyValueChangedEvent() {
    List<ISpecialtyListener> cloneList = new ArrayList<ISpecialtyListener>(specialtyListenerList);
    for (ISpecialtyListener listener : cloneList) {
      listener.specialtyValueChanged();
    }
  }

  private synchronized void fireSpecialtyRemovedEvent(ISpecialty specialty) {
    List<ISpecialtyListener> cloneList = new ArrayList<ISpecialtyListener>(specialtyListenerList);
    for (ISpecialtyListener listener : cloneList) {
      listener.specialtyRemoved(specialty);
    }
  }

  public synchronized void addSpecialtyListener(ISpecialtyListener listener) {
    specialtyListenerList.add(listener);
  }

  public synchronized void removeSpecialtyListener(ISpecialtyListener listener) {
    specialtyListenerList.remove(listener);
  }

  public int getExperienceLearnedSpecialtyCount() {
    int count = 0;
    for (ISpecialty specialty : getSpecialties()) {
      count += Math.max(0, specialty.getExperiencedValue() - specialty.getCreationValue());
    }
    return count;
  }
}