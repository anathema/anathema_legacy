package net.sf.anathema.charmentry.demo;

import net.disy.commons.core.message.IBasicMessage;
import net.sf.anathema.lib.resources.IResources;

public class PrerequisitePageProperties implements IPrerequisitePageProperties {

  private final IResources resources;

  public PrerequisitePageProperties(IResources resources) {
    this.resources = resources;
  }

  public IBasicMessage getPrerequisitesMessage() {
    return null;
  }

  public String getPageTitle() {
    // TODO Auto-generated method stub
    return null;
  }

  public String getEssenceLabel() {
    // TODO Auto-generated method stub
    return null;
  }

  public String getEssenceMinimumLabel() {
    // TODO Auto-generated method stub
    return null;
  }

}
