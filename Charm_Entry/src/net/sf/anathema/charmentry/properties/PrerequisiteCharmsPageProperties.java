package net.sf.anathema.charmentry.properties;

import net.sf.anathema.lib.message.BasicMessage;
import net.sf.anathema.lib.message.IBasicMessage;
import net.sf.anathema.lib.resources.IResources;

public class PrerequisiteCharmsPageProperties implements IPrerequisiteCharmsPageProperties {

  private final IResources resources;
  private final IBasicMessage defaultMessage;

  public PrerequisiteCharmsPageProperties(IResources resources) {
    this.resources = resources;
    defaultMessage = new BasicMessage(resources.getString("CharmEntry.PrerequisiteCharms.Message.Default")); //$NON-NLS-1$
  }

  @Override
  public IBasicMessage getDefaultMessage() {
    return defaultMessage;
  }

  @Override
  public String getPageTitle() {
    return resources.getString("CharmEntry.PrerequisiteCharms.Title"); //$NON-NLS-1$
  }

  @Override
  public String getExcellencyString() {
    return resources.getString("CharmEntry.PrerequisiteCharms.AnyExcellency"); //$NON-NLS-1$ 
  }
}