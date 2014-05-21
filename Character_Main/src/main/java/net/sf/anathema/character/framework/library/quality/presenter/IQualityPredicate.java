package net.sf.anathema.character.framework.library.quality.presenter;

public interface IQualityPredicate {

  boolean isFulfilled(IQualitySelection<? extends IQuality>[] selectedQualities);
}