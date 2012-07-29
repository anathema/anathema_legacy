package net.sf.anathema.characterengine;

public interface Qualities {

  void addQuality(QualityKey qualityKey);

  void doFor(QualityKey qualityKey, QualityClosure closure);
}