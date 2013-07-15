package net.sf.anathema.character.main.library.quality.presenter;

public interface IQualityPredicate {

  boolean isFulfilled(IQualitySelection<? extends IQuality>[] selectedQualities);
}