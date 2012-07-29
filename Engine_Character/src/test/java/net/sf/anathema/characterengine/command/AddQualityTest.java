package net.sf.anathema.characterengine.command;

import net.sf.anathema.characterengine.persona.Qualities;
import net.sf.anathema.characterengine.quality.Name;
import net.sf.anathema.characterengine.quality.QualityKey;
import net.sf.anathema.characterengine.quality.Type;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AddQualityTest {

  @Test
  public void addsQualityToQualities() throws Exception {
    Name name = new Name("name");
    Type type = new Type("type");
    Qualities qualities = mock(Qualities.class);
    new AddQuality(new QualityKey(type, name)).execute(qualities);
    verify(qualities).addQuality(new QualityKey(type, name));
  }
}
