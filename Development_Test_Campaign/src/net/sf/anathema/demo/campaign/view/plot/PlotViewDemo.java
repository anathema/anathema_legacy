package net.sf.anathema.demo.campaign.view.plot;

import net.sf.anathema.campaign.concrete.plot.PlotModel;
import net.sf.anathema.campaign.presenter.plot.PlotPresenter;
import net.sf.anathema.campaign.presenter.view.plot.IPlotView;
import net.sf.anathema.campaign.view.plot.PlotView;
import net.sf.anathema.framework.resources.AnathemaResources;
import net.sf.anathema.lib.resources.IResources;
import de.jdemo.extensions.SwingDemoCase;

public class PlotViewDemo extends SwingDemoCase {

  public void demo() {
    final IPlotView view = new PlotView("Plot"); //$NON-NLS-1$
    IResources resources = new AnathemaResources();
    new PlotPresenter(resources, view, new PlotModel()).initPresentation();
    show(view.getComponent());
  }
}