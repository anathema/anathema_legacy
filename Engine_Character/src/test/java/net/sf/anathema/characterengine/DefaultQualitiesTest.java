package net.sf.anathema.characterengine;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class DefaultQualitiesTest {
  private Engine engine = mock(Engine.class);
  private DefaultQualities qualities = new DefaultQualities(engine);
  private QualityClosure closure = mock(QualityClosure.class);

  @Test
  public void createsQualitiesViaEngine() throws Exception {
    Type type = new Type("type");
    Name name = new Name("name");
    NumericQuality quality = new NumericQuality(new NumericValue(0));
    when(engine.createQuality(type)).thenReturn(quality);
    qualities.addQuality(new QualityKey(type, name));
    qualities.doFor(new QualityKey(type, name), closure);
    verify(closure).execute(quality);
  }

  @Test
  public void ignoresUnknownQualities() throws Exception {
    Type type = new Type("unknownType");
    Name name = new Name("unknownName");
    qualities.doFor(new QualityKey(type, name), closure);
    verifyZeroInteractions(closure);
  }
}
