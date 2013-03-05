package net.sf.anathema.scribe.perspective.view;

import javafx.application.Platform;
import net.sf.anathema.platform.fx.FxThreading;
import net.sf.anathema.platform.fx.PerspectivePane;
import net.sf.anathema.scribe.editor.view.ScrollView;

public class ScribeView {
  public final PerspectivePane perspectivePane = new PerspectivePane("skin/anathema/scribe.css");
  public final ScrollView scrollView = new ScrollView();
  public final ScribeNavigation scribeNavigation = new ScribeNavigation();

  public ScribeView() {
    FxThreading.assertNotOnFxThread();
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        perspectivePane.addStyleSheetClass("scribe-perspective");
        perspectivePane.setNavigationComponent(scribeNavigation.getNode());
        perspectivePane.setContentComponent(scrollView.getNode());
      }
    });
  }
}
