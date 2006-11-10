package net.sf.anathema.charmentry.properties;

import net.disy.commons.core.message.BasicMessage;
import net.disy.commons.core.message.IBasicMessage;
import net.sf.anathema.lib.resources.IResources;

public class PrerequisiteCharmsPageProperties implements IPrerequisiteCharmsPageProperties {

  private final IResources resources;
  private final IBasicMessage defaultMessage = new BasicMessage("Please select the prerequisite Charms, if any.");

  public PrerequisiteCharmsPageProperties(IResources resources) {
    this.resources = resources;
  }

  public IBasicMessage getDefaultMessage() {
    return defaultMessage;
  }

  public String getPageTitle() {
    return "Prerequisite Charms";
  }

  public String getExcellencyString() {
    return "Requires \"Any Excellency\"";
  }
}