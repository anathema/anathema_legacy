package net.sf.anathema.characterengine.persona;

import net.sf.anathema.characterengine.quality.QualityKey;
import net.sf.anathema.characterengine.quality.QualityListener;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ListenerMapTest {
  ListenerMap listenerMap = new ListenerMap();

  @Test
  public void triggersOnlyIfContained() throws Exception {
    QualityKey qualityKey = QualityKey.ForTypeAndName("unknownType", "unknownName");
    listenerMap.triggerFor(qualityKey);
  }

  @Test
  public void triggersAllRegisteredListeners() throws Exception {
    QualityKey key = QualityKey.ForTypeAndName("type", "name");
    QualityListener listener1 = mock(QualityListener.class);
    QualityListener listener2 = mock(QualityListener.class);
    listenerMap.put(key, listener1);
    listenerMap.put(key, listener2);
    listenerMap.triggerFor(key);
    verify(listener1).eventOccurred();
  }

  @Test
  public void removesOnlyGivenListener() throws Exception {
    QualityKey key = QualityKey.ForTypeAndName("type", "name");
    QualityListener listener1 = mock(QualityListener.class);
    QualityListener listener2 = mock(QualityListener.class);
    listenerMap.put(key, listener1);
    listenerMap.put(key, listener2);
    listenerMap.remove(key, listener1);
    listenerMap.triggerFor(key);
    verify(listener2).eventOccurred();
  }
}