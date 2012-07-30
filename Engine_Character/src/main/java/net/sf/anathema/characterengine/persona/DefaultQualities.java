package net.sf.anathema.characterengine.persona;

import net.sf.anathema.characterengine.engine.Engine;
import net.sf.anathema.characterengine.quality.Quality;
import net.sf.anathema.characterengine.quality.QualityKey;
import net.sf.anathema.characterengine.quality.Type;

import java.util.List;

public class DefaultQualities implements Qualities {
  private final QualityMap qualityMap = new QualityMap();
  private final Engine engine;

  public DefaultQualities(Engine engine) {
    this.engine = engine;
  }

  @Override
  public void addQuality(final QualityKey qualityKey) {
    Quality quality = engine.createQuality(qualityKey);
    qualityMap.put(qualityKey, quality);
  }

  @Override
  public void doFor(QualityKey qualityKey, QualityClosure closure) {
    if (qualityMap.contains(qualityKey)) {
      Quality quality = qualityMap.get(qualityKey);
      closure.execute(quality);
    }
  }

  @Override
  public void doForEach(final Type type, final QualityClosure closure) {
    List<Quality> qualityList = qualityMap.getAllWithType(type);
    for (Quality quality : qualityList) {
      closure.execute(quality);
    }
  }
}