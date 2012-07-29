package net.sf.anathema.characterengine;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AddQualityTest {

  @Test
  public void addsQualityToQualities() throws Exception {
    Name name = new Name("name");
    Type type = new Type("type");
    Qualities qualities = mock(Qualities.class);
    new AddQuality(type, name).execute(qualities);
    verify(qualities).addQuality(type, name);
  }
}
