package net.sf.anathema.character.library.quality.presenter;

import net.sf.anathema.lib.util.IIdentificate;

public interface IQuality extends IIdentificate {

  public IQualityType getType();

  public boolean prerequisitesFulfilled(IQualitySelection< ? extends IQuality>[] selectedQualities);
}