package net.sf.anathema.characterengine.persona;

import net.sf.anathema.characterengine.engine.Engine;
import net.sf.anathema.characterengine.quality.Name;
import net.sf.anathema.characterengine.quality.Quality;
import net.sf.anathema.characterengine.quality.QualityKey;
import net.sf.anathema.characterengine.quality.QualityListener;
import net.sf.anathema.characterengine.quality.Type;
import net.sf.anathema.characterengine.support.NumericQuality;
import net.sf.anathema.characterengine.support.NumericValue;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class DefaultQualities_ListenerTest {
  private Engine engine = mock(Engine.class);
  private DefaultQualities qualities = new DefaultQualities(engine);

  @Test
  public void notifiesListenerWhenTheQualityIsAdded() throws Exception {
    Type type = new Type("type");
    Name name = new Name("name");
    QualityKey key = new QualityKey(type, name);
    NumericQuality quality = new NumericQuality(new NumericValue(0));
    configureEngineToCreate(key, quality);
    QualityListener listener = mock(QualityListener.class);
    qualities.observe(key, listener);
    qualities.addQuality(key);
    verify(listener).eventOccurred();
  }

  @Test
  public void doesNotNotifyRemovedListener() throws Exception {
    Type type = new Type("type");
    Name name = new Name("name");
    QualityKey key = new QualityKey(type, name);
    NumericQuality quality = new NumericQuality(new NumericValue(0));
    configureEngineToCreate(key, quality);
    QualityListener listener = mock(QualityListener.class);
    qualities.observe(key, listener);
    qualities.stopObservation(key, listener);
    qualities.addQuality(key);
    verifyZeroInteractions(listener);
  }

  @Test
  public void notifiesListenerWhenTheQualityIsModified() throws Exception {
    Type type = new Type("type");
    Name name = new Name("name");
    QualityKey key = new QualityKey(type, name);
    final NumericQuality quality = new NumericQuality(new NumericValue(0));
    configureEngineToCreate(key, quality);
    QualityListener listener = mock(QualityListener.class);
    qualities.observe(key, listener);
    qualities.addQuality(key);
    qualities.doFor(key, new QualityClosure() {
      @Override
      public void execute(Quality quality) {
        ((NumericQuality) quality).changeBy(new NumericValue(1));
      }
    });
    verify(listener, times(2)).eventOccurred();
  }

  @Test
  public void notifiesListenersRegisteredAfterCreationWhenTheQualityIsModified() throws Exception {
    Type type = new Type("type");
    Name name = new Name("name");
    QualityKey key = new QualityKey(type, name);
    final NumericQuality quality = new NumericQuality(new NumericValue(0));
    configureEngineToCreate(key, quality);
    QualityListener listener = mock(QualityListener.class);
    qualities.addQuality(key);
    qualities.observe(key, listener);
    qualities.doFor(key, new QualityClosure() {
      @Override
      public void execute(Quality quality) {
        ((NumericQuality) quality).changeBy(new NumericValue(1));
      }
    });
    verify(listener).eventOccurred();
  }

  private void configureEngineToCreate(QualityKey key, NumericQuality quality) {
    when(engine.createQuality(key)).thenReturn(quality);
  }
}
