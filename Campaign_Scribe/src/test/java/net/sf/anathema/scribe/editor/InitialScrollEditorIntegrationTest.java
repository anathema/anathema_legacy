package net.sf.anathema.scribe.editor;

import net.sf.anathema.scribe.editor.model.ScrollModel;
import net.sf.anathema.scribe.editor.presenter.ScrollPresenter;
import net.sf.anathema.scribe.scroll.persistence.InMemoryScrollPersister;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class InitialScrollEditorIntegrationTest {

  public static final String NEW_SCROLL_TITLE = "New Scroll";
  private ResourcesDummy resources = new ResourcesDummy();
  private ScrollEditorDummy editor = new ScrollEditorDummy();
  private ScrollPreviewDummy preview = new ScrollPreviewDummy();
  private ScrollModel model = new ScrollModel(new InMemoryScrollPersister());

  @Before
  public void initPresentation() {
    configureUnnamedScrollTitle();
    new ScrollPresenter(model, editor, preview, resources).initPresentation();
  }

  private void configureUnnamedScrollTitle() {
    resources.patternsByKey.put("Scribe.UnnamedScroll.Title", NEW_SCROLL_TITLE);
  }

  @Test
  public void showsUnnamedScrollNameInEditorTitle() {
    assertThat(editor.title, is(""));
  }

  @Test
  public void showsUnnamedScrollNameInPreviewTitle() {
    assertThat(preview.title, is(NEW_SCROLL_TITLE));
  }

  @Test
  public void updatesTitleInPreview() {
    model.setName("Important Scroll");
    assertThat(preview.title, is("Important Scroll"));
  }

  @Test
  public void updatesTitleInEditor() {
    model.setName("Other Important Scroll");
    assertThat(editor.title, is("Other Important Scroll"));
  }

  @Test
  public void updatesPreviewTitleOnTypingInEditor() {
    editor.titleChangedAnnouncer.announce().textChanged("Typed title");
    assertThat(preview.title, is("Typed title"));
  }

  @Test
  public void updatesEditorTitleOnTypingInEditor() {
    editor.titleChangedAnnouncer.announce().textChanged("Typed title");
    assertThat(editor.title, is("Typed title"));
  }
}
