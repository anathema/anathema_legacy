package net.sf.anathema.character.db.reporting.rendering;

import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import org.junit.Before;
import org.junit.Test;

import static net.sf.anathema.character.generic.type.CharacterType.DB;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GreatCurseEncoderFactoryTest {

  private GreatCurseEncoderFactory factory;

  @Before
  public void setUp() throws Exception {
    factory = new GreatCurseEncoderFactory();
  }

  @Test
  public void activatesForSecondEditionCharacters() throws Exception {
    BasicContent content = mock(BasicContent.class);
    when(content.isOfType(DB)).thenReturn(true);
    boolean supports = factory.supports(content);
    assertThat(supports, is(true));
  }
}
