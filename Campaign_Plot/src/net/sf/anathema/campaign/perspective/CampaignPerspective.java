package net.sf.anathema.campaign.perspective;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.view.perspective.Container;
import net.sf.anathema.framework.view.perspective.Perspective;
import net.sf.anathema.framework.view.perspective.PerspectiveAutoCollector;
import net.sf.anathema.framework.view.perspective.PerspectiveToggle;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.resources.IResources;

@PerspectiveAutoCollector
@Weight(weight = 9999)
public class CampaignPerspective implements Perspective {

  @Override
  public void configureToggle(PerspectiveToggle toggle) {
    toggle.setIcon("TabSeries16.png");
    toggle.setTooltip("Campaign.Perspective.Tooltip");
  }

  @Override
  public void initContent(Container container, IAnathemaModel model, IResources resources) {
    CampaignPerspectiveView view = new CampaignPerspectiveView();
    new CampaignPerspectivePresenter(model, view, resources).initPresentation();
    container.setSwingContent(view.createContent());
  }
}
