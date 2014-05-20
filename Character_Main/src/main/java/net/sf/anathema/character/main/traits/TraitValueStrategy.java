package net.sf.anathema.character.main.traits;

import net.sf.anathema.character.main.library.trait.Trait;
import net.sf.anathema.lib.control.IntValueChangedListener;
import org.jmock.example.announcer.Announcer;

public interface TraitValueStrategy {

  int getMinimalValue(Trait trait);

  int getCurrentValue(Trait trait);

  void setValue(Trait trait, int value);

  void notifyOnCreationValueChange(int value, Announcer<IntValueChangedListener> currentValueControl);

  void notifyOnLearnedValueChange(int value, Announcer<IntValueChangedListener> currentValueControl);

  void resetCurrentValue(Trait trait);
}