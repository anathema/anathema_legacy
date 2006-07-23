package net.sf.anathema.campaign.presenter;

import javax.swing.Icon;

import net.sf.anathema.campaign.module.PlotUI;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.lib.resources.IResources;

public class CampaignContentViewProperties implements ICampaignContentViewProperties {

  private final IResources resources;

  public CampaignContentViewProperties(IResources resources) {
    this.resources = resources;
  }

  public Icon getAddButtonIcon() {
    return new PlotUI(resources).getAddButtonIcon();
  }

  public Icon getRemoveButtonIcon() {
    return new BasicUi(resources).getMediumRemoveIcon();
  }

  public String getAddToolTip() {
    return resources.getString("CampaignContent.AddToolTip"); //$NON-NLS-1$
  }

  public String getRemoveToolTip() {
    return resources.getString("CampaignContent.RemoveToolTip"); //$NON-NLS-1$  
  }
}