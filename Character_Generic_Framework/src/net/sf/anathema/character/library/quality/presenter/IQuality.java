package net.sf.anathema.character.library.quality.presenter;

import net.sf.anathema.lib.util.Identified;

public interface IQuality extends Identified {

  IQualityType getType();

  boolean prerequisitesFulfilled(IQualitySelection<? extends IQuality>[] selectedQualities);
}