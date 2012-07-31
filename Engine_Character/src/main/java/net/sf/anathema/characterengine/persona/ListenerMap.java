package net.sf.anathema.characterengine.persona;

import net.sf.anathema.characterengine.quality.QualityKey;
import net.sf.anathema.characterengine.quality.QualityListener;

import java.util.HashMap;
import java.util.Map;

public class ListenerMap {
  private final Map<QualityKey, QualityListener> listenerMap = new HashMap<QualityKey, QualityListener>();

  public boolean contains(QualityKey qualityKey) {
    return listenerMap.containsKey(qualityKey);
  }

  public void triggerFor(QualityKey qualityKey) {
    if (contains(qualityKey)) {
      listenerMap.get(qualityKey).eventOccurred();
    }
  }

  public void put(QualityKey key, QualityListener listener) {
    listenerMap.put(key, listener);
  }

  public void remove(QualityKey key, QualityListener listener) {
    listenerMap.remove(key);
  }

  public QualityListener get(QualityKey qualityKey) {
    return listenerMap.get(qualityKey);
  }
}
