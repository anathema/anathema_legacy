package net.sf.anathema.character.model.background;

import net.sf.anathema.character.library.trait.IModifiableTrait;

public interface IBackgroundPointsCalculator {

  int calculateBackgroundPoints(IModifiableTrait[] backgrounds);

  int calculateBonusPoints(IModifiableTrait[] backgrounds);
}