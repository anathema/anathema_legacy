package net.sf.anathema.scribe.editor.model;

import net.sf.anathema.scribe.scroll.persistence.InMemoryScrollPersister;
import org.junit.Test;

import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

public class ScrollModelTest {

  private final ScrollChangedListener mockListener = mock(ScrollChangedListener.class);
  private final ScrollModel model = new ScrollModel(new InMemoryScrollPersister());

  @Test
  public void notifiesScrollChangeListenerOnContentChange() {
    model.whenContentChanges(mockListener);
    model.setText(new WikiText("new"));
    verify(mockListener).contentChanged(isA(WikiText.class), isA(HtmlText.class));
  }

  @Test
  public void doesNotNotifyScrollChangeListenerWhenSettingEqualContent() {
    model.setText(new WikiText("content"));
    model.whenContentChanges(mockListener);
    model.setText(new WikiText("content"));
    verifyZeroInteractions(mockListener);
  }

  @Test
  public void notifiesScrollChangeListenerWithConvertedHtmlText() {
    model.whenContentChanges(mockListener);
    model.setText(new WikiText("**new**"));
    verify(mockListener).contentChanged(isA(WikiText.class), eq(new HtmlText("<p><strong>new</strong></p>\n")));
  }

  @Test
  public void notifiesScrollChangeListenerWithNewWikiText() {
    model.whenContentChanges(mockListener);
    model.setText(new WikiText("**new**"));
    verify(mockListener).contentChanged(eq(new WikiText("**new**")), isA(HtmlText.class));
  }
}
