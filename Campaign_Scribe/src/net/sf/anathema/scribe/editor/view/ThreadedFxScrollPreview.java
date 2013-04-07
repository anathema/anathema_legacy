package net.sf.anathema.scribe.editor.view;

import javafx.application.Platform;
import javafx.scene.Node;
import net.sf.anathema.platform.fx.FxThreading;
import net.sf.anathema.scribe.editor.model.HtmlText;
import net.sf.anathema.scribe.editor.presenter.ScrollPreview;

public class ThreadedFxScrollPreview implements ScrollPreview {

  private FxScrollPreview preview;

  public ThreadedFxScrollPreview() {
    FxThreading.assertNotOnFxThread();
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        preview = new FxScrollPreview();
      }
    });
  }

  @Override
  public void setHtmlText(final HtmlText text) {
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        preview.setHtmlText(text);
      }
    });
  }

  @Override
  public void setTitle(final String text) {
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        preview.setTitle(text);
      }
    });
  }

  public Node getNode() {
    return preview.getNode();
  }
}