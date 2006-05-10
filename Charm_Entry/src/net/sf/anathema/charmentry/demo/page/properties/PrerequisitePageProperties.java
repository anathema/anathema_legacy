package net.sf.anathema.charmentry.demo.page.properties;

import net.disy.commons.core.message.BasicMessage;
import net.disy.commons.core.message.IBasicMessage;
import net.sf.anathema.charmentry.demo.IPrerequisitePageProperties;
import net.sf.anathema.lib.resources.IResources;

public class PrerequisitePageProperties implements IPrerequisitePageProperties {

  private final IResources resources;
  private IBasicMessage defaultMessage = new BasicMessage("Please enter the Charm's trait minima.");
  private IBasicMessage primaryMissingMessage = new BasicMessage("Please select the primary trait.");

  public PrerequisitePageProperties(IResources resources) {
    this.resources = resources;
  }

  public IBasicMessage getPrerequisitesMessage() {
    return defaultMessage;
  }

  public IBasicMessage getPrimaryMissingMessage() {
    return primaryMissingMessage;
  }

  public String getPageTitle() {
    return "Trait Minima";
  }

  public String getEssenceLabel() {
    return "Essence prerequisite";
  }

  public String getPrimaryPrerequisiteLabel() {
    return "Primary prerequisite";
  }

  public String getEssenceMinimumLabel() {
    return ""; //$NON-NLS-1$
  }

}
