package net.sf.anathema.character.main.library.trait.rules;

import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.lib.data.Range;

public interface ITraitRules {

  int UNEXPERIENCED = -1;

  int getAbsoluteMaximumValue();

  int getAbsoluteMinimumValue();

  int getCurrentMaximumValue(boolean modified);

  int getStartValue();

  boolean isReducible();

  TraitType getType();

  int getExperiencedValue(int creationValue, int demandedValue);

  int getCreationValue(int demandedValue);

  void setCapModifier(int modifier);

  void setModifiedCreationRange(Range range);
}