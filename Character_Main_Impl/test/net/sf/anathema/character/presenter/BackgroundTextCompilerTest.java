package net.sf.anathema.character.presenter;

import net.sf.anathema.character.model.background.IBackground;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BackgroundTextCompilerTest {

  public static final String name = "Name";
  private Displayer displayer = Mockito.mock(Displayer.class);
  private IBackground background = mock(IBackground.class);

  @Before
  public void setNameForBackground() throws Exception {
    when(displayer.getDisplayObject(background)).thenReturn(name);
  }

  @Test
  public void rendersBackgroundNameAsPrescribedByDisplayer() throws Exception {
    BackgroundTextCompiler compiler = new BackgroundTextCompiler(displayer);
    String displayedText = compiler.compileDisplayedText(background);
    assertThat(displayedText, is(name));
  }
}