package net.sf.anathema.characterengine.persona;

import net.sf.anathema.characterengine.engine.Engine;
import net.sf.anathema.characterengine.quality.Name;
import net.sf.anathema.characterengine.quality.QualityKey;
import net.sf.anathema.characterengine.quality.Type;
import net.sf.anathema.characterengine.support.NumericQuality;
import net.sf.anathema.characterengine.support.NumericValue;
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
    configureEngineToCreate(type, quality);
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

  @Test
  public void executesClosureOnEachQualityOfGivenType() throws Exception {
    Type type = new Type("type");
    NumericQuality firstQuality = new NumericQuality(new NumericValue(0));
    NumericQuality secondQuality = new NumericQuality(new NumericValue(0));
    configureEngineToCreate(type, firstQuality, secondQuality);
    qualities.addQuality(new QualityKey(type, new Name("firstName")));
    qualities.addQuality(new QualityKey(type, new Name("secondName")));
    qualities.doForEach(type, closure);
    verify(closure).execute(firstQuality);
    verify(closure).execute(secondQuality);
  }

  private void configureEngineToCreate(Type type, NumericQuality quality, NumericQuality... moreQualities) {
    when(engine.createQuality(type)).thenReturn(quality, moreQualities);
  }
}
