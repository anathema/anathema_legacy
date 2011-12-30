package net.sf.anathema.character.presenter;

import org.junit.Test;
import org.mockito.Mockito;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

public class BackgroundBoxEditorTest {

  @Test
  public void internationalizesBackgroundsInEditor() throws Exception {
    Object input = "MyBackgroundId";
    Object expectation = "My Background Name";
    Displayer displayer = Mockito.mock(Displayer.class);
    when(displayer.getDisplayObject(input)).thenReturn(expectation);
    BackgroundBoxEditor editor = new BackgroundBoxEditor(displayer);
    editor.setItem(input);
    Object result = editor.getItem();
    assertThat(result, is(expectation));
  }
}