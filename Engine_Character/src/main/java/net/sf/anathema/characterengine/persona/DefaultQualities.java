package net.sf.anathema.characterengine.persona;

import net.sf.anathema.characterengine.engine.Engine;
import net.sf.anathema.characterengine.quality.Quality;
import net.sf.anathema.characterengine.quality.QualityKey;
import net.sf.anathema.characterengine.quality.QualityListener;
import net.sf.anathema.characterengine.quality.Type;

import java.util.List;

public class DefaultQualities implements Qualities {
  private final QualityMap qualityMap = new QualityMap();
  private final ListenerMap listenerMap = new ListenerMap();
  private final Engine engine;

  public DefaultQualities(Engine engine) {
    this.engine = engine;
  }

  @Override
  public void addQuality(final QualityKey qualityKey) {
    Quality quality = engine.createQuality(qualityKey);
    qualityMap.put(qualityKey, quality);
    quality.registerObserver(new MapTriggeringListener(qualityKey));
    listenerMap.triggerFor(qualityKey);
  }

  @Override
  public void doFor(QualityKey qualityKey, QualityClosure closure) {
    if (qualityMap.contains(qualityKey)) {
      Quality quality = qualityMap.get(qualityKey);
      closure.execute(quality);
    }
  }

  @Override
  public void doForEach(Type type, QualityClosure closure) {
    List<Quality> qualitiesWithType = qualityMap.getAllWithType(type);
    for (Quality quality : qualitiesWithType) {
      closure.execute(quality);
    }
  }

  @Override
  public void observe(QualityKey key, QualityListener listener) {
    listenerMap.put(key, listener);
  }

  @Override
  public void stopObservation(QualityKey key, QualityListener listener) {
    listenerMap.remove(key, listener);
  }

  private class MapTriggeringListener implements QualityListener {
    private final QualityKey qualityKey;

    public MapTriggeringListener(QualityKey qualityKey) {
      this.qualityKey = qualityKey;
    }

    @Override
    public void eventOccurred() {
      listenerMap.triggerFor(qualityKey);
    }
  }
}