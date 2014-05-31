package net.sf.anathema.scribe.perspective.view;

import net.sf.anathema.interaction.AcceleratorMap;
import net.sf.anathema.platform.fx.PerspectivePane;
import net.sf.anathema.scribe.editor.view.FxScrollView;

public class ScribeView {
  public final PerspectivePane perspectivePane = new PerspectivePane("skin/scribe/scribe.css");
  public final FxScrollView scrollView = new FxScrollView();
  public final ScribeNavigation scribeNavigation;

  public ScribeView(AcceleratorMap acceleratorMap) {
    scribeNavigation = new ScribeNavigation(acceleratorMap);
    perspectivePane.addStyleSheetClass("scribe-perspective");
    perspectivePane.setNavigationComponent(scribeNavigation.getNode());
    perspectivePane.setContentComponent(scrollView.getNode());
  }
}
