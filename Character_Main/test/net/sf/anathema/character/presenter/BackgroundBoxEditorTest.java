package net.sf.anathema.character.presenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

public class BackgroundBoxEditorTest {
  private static final Object id = "MyBackgroundId";
  private static final Object displayedText = "My Background Name";
  private Displayer displayer = Mockito.mock(Displayer.class);
  private BackgroundBoxEditor editor = new BackgroundBoxEditor(displayer);

  @Before
  public void linkIdToDisplayedText() {
    when(displayer.getDisplayObject(id)).thenReturn(displayedText);
  }

  @Test
  public void internationalizesBackgroundsInEditor() throws Exception {
    editor.setItem(id);
    Object result = editor.getItem();
    assertThat(result, is(displayedText));
  }
}