package net.sf.anathema.campaign.presenter;

import net.sf.anathema.lib.resources.Resources;

public class CampaignContentViewProperties implements ICampaignContentViewProperties {

  private final Resources resources;

  public CampaignContentViewProperties(Resources resources) {
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