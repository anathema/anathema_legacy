package net.sf.anathema.character.model.background;

import net.sf.anathema.character.library.trait.ITrait;

public interface IBackgroundPointsCalculator {

  int calculateBackgroundPoints(ITrait[] backgrounds);

  int calculateBonusPoints(ITrait[] backgrounds);
}