package net.sf.anathema.hero.traits.model;

import net.sf.anathema.lib.control.IntValueChangedListener;

public interface Trait extends ValuedTraitType {

  int getCreationValue();

  int getExperiencedValue();

  int getAbsoluteMinValue();

  boolean isLowerable();

  void resetCreationValue();

  void resetExperiencedValue();

  void setCreationValue(int value);

  void setExperiencedValue(int value);

  ITraitFavorization getFavorization();

  int getInitialValue();

  int getMaximalValue();

  void addCreationPointListener(IntValueChangedListener listener);

  void removeCreationPointListener(IntValueChangedListener listener);

  void addCurrentValueListener(IntValueChangedListener listener);

  int getModifiedMaximalValue();

  int getUnmodifiedMaximalValue();

  void setUncheckedCreationValue(int value);

  void setUncheckedExperiencedValue(int value);

  void setCurrentValue(int value);

  int getMinimalValue();

  void resetCurrentValue();

  void setModifiedCreationRange(int newInitialValue, int newUpperValue);
}