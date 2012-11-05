package net.sf.anathema.characterengine.persona;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import net.sf.anathema.characterengine.quality.QualityKey;
import net.sf.anathema.characterengine.quality.QualityListener;

public class ListenerMap {
  private final Multimap<QualityKey, QualityListener> listenerMap = ArrayListMultimap.create();

  public void triggerFor(QualityKey qualityKey) {
    for (QualityListener qualityListener : listenerMap.get(qualityKey)) {
      qualityListener.eventOccurred();
    }
  }

  public void put(QualityKey key, QualityListener listener) {
    listenerMap.put(key, listener);
  }

  public void remove(QualityKey key, QualityListener listener) {
    listenerMap.remove(key, listener);
  }
}