package net.sf.anathema.charmentry.properties;

import net.disy.commons.core.message.BasicMessage;
import net.disy.commons.core.message.IBasicMessage;
import net.sf.anathema.lib.resources.IResources;

public class PrerequisiteCharmsPageProperties implements IPrerequisiteCharmsPageProperties {

  private final IResources resources;
  private final IBasicMessage defaultMessage;

  public PrerequisiteCharmsPageProperties(IResources resources) {
    this.resources = resources;
    defaultMessage = new BasicMessage(resources.getString("CharmEntry.PrerequisiteCharms.Message.Default")); //$NON-NLS-1$
  }

  public IBasicMessage getDefaultMessage() {
    return defaultMessage;
  }

  public String getPageTitle() {
    return resources.getString("CharmEntry.PrerequisiteCharms.Title"); //$NON-NLS-1$
  }

  public String getExcellencyString() {
    return resources.getString("CharmEntry.PrerequisiteCharms.AnyExcellency"); //$NON-NLS-1$ 
  }
}