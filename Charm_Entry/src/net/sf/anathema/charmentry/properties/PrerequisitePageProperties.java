package net.sf.anathema.charmentry.properties;

import net.disy.commons.core.message.BasicMessage;
import net.disy.commons.core.message.IBasicMessage;
import net.sf.anathema.lib.resources.IResources;

public class PrerequisitePageProperties {

  private final IResources resources;
  private final IBasicMessage defaultMessage;
  private final IBasicMessage primaryMissingMessage;

  public PrerequisitePageProperties(IResources resources) {
    this.resources = resources;
    defaultMessage = new BasicMessage(resources.getString("CharmEntry.PrerequisiteTraits.Message.Default")); //$NON-NLS-1$
    primaryMissingMessage = new BasicMessage(
        resources.getString("CharmEntry.PrerequisiteTraits.Message.PrimaryMissing")); //$NON-NLS-1$
  }

  public IBasicMessage getPrerequisitesMessage() {
    return defaultMessage;
  }

  public IBasicMessage getPrimaryMissingMessage() {
    return primaryMissingMessage;
  }

  public String getPageTitle() {
    return resources.getString("CharmEntry.PrerequisiteTraits.Title"); //$NON-NLS-1$
  }

  public String getEssenceLabel() {
    return resources.getString("CharmEntry.PrerequisiteTraits.Essence"); //$NON-NLS-1$
  }

  public String getPrimaryPrerequisiteLabel() {
    return resources.getString("CharmEntry.PrerequisiteTraits.Primary"); //$NON-NLS-1$
  }

  public String getEssenceMinimumLabel() {
    return ""; //$NON-NLS-1$
  }

}
