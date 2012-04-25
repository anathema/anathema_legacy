package net.sf.anathema.campaign.presenter;

import net.sf.anathema.lib.resources.IResources;

public class CampaignContentViewProperties implements ICampaignContentViewProperties {

  private final IResources resources;

  public CampaignContentViewProperties(IResources resources) {
    this.resources = resources;
  }

  @Override
  public String getAddToolTip() {
    return resources.getString("CampaignContent.AddToolTip"); //$NON-NLS-1$
  }

  @Override
  public String getRemoveToolTip() {
    return resources.getString("CampaignContent.RemoveToolTip"); //$NON-NLS-1$  
  }
}