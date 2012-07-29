package net.sf.anathema.characterengine.engine;

import net.sf.anathema.characterengine.quality.Quality;
import net.sf.anathema.characterengine.quality.Type;
import net.sf.anathema.characterengine.support.NumericQuality;
import net.sf.anathema.characterengine.support.NumericValue;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DefaultEngineTest {

  private DefaultEngine engine = new DefaultEngine();

  @Test
  public void createsQualityFromFactory() throws Exception {
    QualityFactory factory = mock(QualityFactory.class);
    Quality expected = new NumericQuality(new NumericValue(0));
    when(factory.create()).thenReturn(expected);
    Type type = new Type("type");
    engine.setFactory(type, factory);
    Quality quality = engine.createQuality(type);
    assertThat(quality, is(expected));
  }

  @Test(expected = UnknownQualityTypeException.class)
  public void throwsUnknownQualityType() throws Exception {
    Type type = new Type("unknownType");
    engine.createQuality(type);
  }
}