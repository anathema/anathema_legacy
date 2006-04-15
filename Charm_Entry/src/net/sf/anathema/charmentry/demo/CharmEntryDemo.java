package net.sf.anathema.charmentry.demo;

import net.sf.anathema.charmentry.BasicDataPresenter;
import net.sf.anathema.charmentry.model.CharmEntryModel;
import net.sf.anathema.charmentry.view.BasicDataView;
import net.sf.anathema.framework.resources.AnathemaResources;
import de.jdemo.extensions.SwingDemoCase;

public class CharmEntryDemo extends SwingDemoCase {

  public void demoCharmEntryInterface() {
    BasicDataView view = new BasicDataView();
    CharmEntryModel model = new CharmEntryModel();
    new BasicDataPresenter(model, view, new AnathemaResources()).initPresentation();
    show(view.getContent());
  }
}