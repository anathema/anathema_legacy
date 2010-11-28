package net.sf.anathema.character.library.quality.presenter;

public interface IQualityPredicate {

  boolean isFulfilled(IQualitySelection< ? extends IQuality>[] selectedQualities);
}