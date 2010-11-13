package net.sf.anathema.charmentry.properties;

import net.disy.commons.core.message.BasicMessage;
import net.disy.commons.core.message.IBasicMessage;
import net.sf.anathema.lib.resources.IResources;

public class DurationPageProperties {

  private final IResources resources;
  private final IBasicMessage defaultMessage;

  public DurationPageProperties(IResources resources) {
    this.resources = resources;
    defaultMessage = new BasicMessage(resources.getString("CharmEntry.Duration.Message.Default")); //$NON-NLS-1$
  }

  public IBasicMessage getBasicMessage() {
    return defaultMessage;
  }

  public String getDurationPageTitle() {
    return resources.getString("CharmEntry.Duration.Title"); //$NON-NLS-1$
  }

  public String getDurationLabel() {
    return resources.getString("CharmEntry.Duration.Duration"); //$NON-NLS-1$
  }

  public String getInstantString() {
    return resources.getString("CharmEntry.Duration.Instant"); //$NON-NLS-1$
  }

  public String getSimpleDurationString() {
    return resources.getString("CharmEntry.Duration.Simple"); //$NON-NLS-1$
  }

  public String getUntilString() {
    return resources.getString("CharmEntry.Duration.Until"); //$NON-NLS-1$
  }

  public String getQualifiedAmountDurationString() {
    return resources.getString("CharmEntry.Duration.QualifiedAmount"); //$NON-NLS-1$;
  }

  public String getAbilityString() {
    return resources.getString("CharmEntry.Duration.Ability"); //$NON-NLS-1$
  }

  public String getAttributeString() {
    return resources.getString("CharmEntry.Duration.Attribute"); //$NON-NLS-1$
  }

  public String getVirtueString() {
    return resources.getString("CharmEntry.Duration.Virtue"); //$NON-NLS-1$
  }

  public String getOtherString() {
    return resources.getString("CharmEntry.Duration.Other"); //$NON-NLS-1$
  }
}