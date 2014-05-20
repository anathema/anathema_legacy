package net.sf.anathema.character.main.library.trait;

import net.sf.anathema.character.main.library.ITraitFavorization;
import net.sf.anathema.character.main.traits.ValuedTraitType;
import net.sf.anathema.lib.control.IntValueChangedListener;

public interface Trait extends ValuedTraitType {

  int getCreationValue();

  int getExperiencedValue();

  int getAbsoluteMinValue();

  int getCreationCalculationValue();

  int getExperiencedCalculationValue();

  boolean isLowerable();

  int getCalculationValue();

  int getZeroCalculationValue();

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

  void applyCapModifier(int modifier);

  int getModifiedMaximalValue();

  int getUnmodifiedMaximalValue();

  void setUncheckedCreationValue(int value);

  void setUncheckedExperiencedValue(int value);

  void setCurrentValue(int value);

  int getMinimalValue();

  void resetCurrentValue();

  void setModifiedCreationRange(int newInitialValue, int newUpperValue);
}