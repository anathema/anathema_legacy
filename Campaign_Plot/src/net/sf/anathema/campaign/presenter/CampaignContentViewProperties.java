package net.sf.anathema.campaign.presenter;

import javax.swing.Icon;

import net.sf.anathema.lib.resources.IResources;

public class CampaignContentViewProperties implements ICampaignContentViewProperties {

  private final IResources resources;

  public CampaignContentViewProperties(IResources resources) {
    this.resources = resources;
  }

  public Icon getAddButtonIcon() {
    return resources.getImageIcon("GreenArrowLeft20.png"); //$NON-NLS-1$
  }

  public Icon getRemoveButtonIcon() {
    return resources.getImageIcon("tools/RedX20.png"); //$NON-NLS-1$
  }

  public String getAddToolTip() {
    return resources.getString("CampaignContent.AddToolTip"); //$NON-NLS-1$
  }

  public String getRemoveToolTip() {
    return resources.getString("CampaignContent.RemoveToolTip"); //$NON-NLS-1$  
  }
}