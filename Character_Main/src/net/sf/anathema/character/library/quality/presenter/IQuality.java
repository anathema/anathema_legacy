package net.sf.anathema.character.library.quality.presenter;

import net.sf.anathema.lib.util.Identifier;

public interface IQuality extends Identifier {

  IQualityType getType();

  boolean prerequisitesFulfilled(IQualitySelection<? extends IQuality>[] selectedQualities);
}