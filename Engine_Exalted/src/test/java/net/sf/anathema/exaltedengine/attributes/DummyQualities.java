package net.sf.anathema.exaltedengine.attributes;

import net.sf.anathema.characterengine.persona.Qualities;
import net.sf.anathema.characterengine.persona.QualityClosure;
import net.sf.anathema.characterengine.quality.Quality;
import net.sf.anathema.characterengine.quality.QualityKey;
import net.sf.anathema.characterengine.quality.QualityListener;
import net.sf.anathema.characterengine.quality.Type;
import net.sf.anathema.characterengine.rules.Rule;

import java.util.HashMap;
import java.util.Map;

public class DummyQualities implements Qualities {
  private final Map<QualityKey, Quality> qualityMap = new HashMap<>();


  public void addQuality(QualityKey qualityKey, Quality quality) {
    qualityMap.put(qualityKey, quality);
  }

  @Override
  public void addQuality(QualityKey qualityKey) {
    //nothing to do
  }

  @Override
  public void defineRule(Type type, Rule rule) {
    //nothing to do
  }

  @Override
  public void doFor(QualityKey qualityKey, QualityClosure closure) {
    Quality quality = qualityMap.get(qualityKey);
    closure.execute(quality);
  }

  @Override
  public void doForEach(Type type, QualityClosure closure) {
    //nothing to do
  }

  @Override
  public void observe(QualityKey key, QualityListener listener) {
    //nothing to do
  }

  @Override
  public void stopObservation(QualityKey key, QualityListener listener) {
    //nothing to do
  }
}