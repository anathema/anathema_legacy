package net.sf.anathema.scribe.perspective.view;

import javafx.application.Platform;
import net.sf.anathema.platform.fx.FxThreading;
import net.sf.anathema.platform.fx.PerspectivePane;
import net.sf.anathema.scribe.editor.view.ScrollEditor;

public class ScribeView {
  public final PerspectivePane perspectivePane = new PerspectivePane();
  public final ScrollEditor scrollEditor = new ScrollEditor();
  public final ScribeNavigation scribeNavigation = new ScribeNavigation();

  public ScribeView() {
    FxThreading.assertNotOnFxThread();
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        perspectivePane.setNavigationComponent(scribeNavigation.getNode());
        perspectivePane.setContentComponent(scrollEditor.getNode());
      }
    });
  }
}
