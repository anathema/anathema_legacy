package net.sf.anathema.character.model.background;

import net.sf.anathema.character.library.trait.IDefaultTrait;

public interface IBackgroundPointsCalculator {

  int calculateBackgroundPoints(IDefaultTrait[] backgrounds);

  int calculateBonusPoints(IDefaultTrait[] backgrounds);
}