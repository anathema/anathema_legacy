package net.sf.anathema.characterengine;

import org.junit.Test;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

public class DefaultQualitiesTest {
  private DefaultQualities qualities = new DefaultQualities();
  private Closure closure = mock(Closure.class);

  @Test
  public void discoversPreviouslyAddedQualities() throws Exception {
    Type type = new Type("type");
    Name name = new Name("name");
    qualities.addQuality(type, name);
    qualities.doFor(type, name, closure);
    verify(closure).execute(isA(Quality.class));
  }

  @Test
  public void ignoresUnknownQualities() throws Exception {
    Type type = new Type("unknownType");
    Name name = new Name("unknownName");
    qualities.doFor(type, name, closure);
    verifyZeroInteractions(closure);
  }
}
