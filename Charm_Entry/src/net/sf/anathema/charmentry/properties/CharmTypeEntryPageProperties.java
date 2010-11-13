package net.sf.anathema.charmentry.properties;

import javax.swing.ListCellRenderer;

import net.disy.commons.core.message.BasicMessage;
import net.disy.commons.core.message.IBasicMessage;
import net.sf.anathema.framework.view.IdentificateSelectCellRenderer;
import net.sf.anathema.lib.resources.IResources;

public class CharmTypeEntryPageProperties {

  private final IResources resources;
  private final IBasicMessage reflexiveMessage;
  private final IBasicMessage simpleMessage;
  private final BasicMessage defaultMessage;

  public CharmTypeEntryPageProperties(IResources resources) {
    this.resources = resources;
    reflexiveMessage = new BasicMessage("CharmEntry.CharmType.Message.Reflexive"); //$NON-NLS-1$
    simpleMessage = new BasicMessage(resources.getString("CharmEntry.CharmType.Message.SimpleData")); //$NON-NLS-1$
    defaultMessage = new BasicMessage(resources.getString("CharmEntry.CharmType.Message.Default")); //$NON-NLS-1$
  }

  public String getPageHeader() {
    return resources.getString("CharmEntry.CharmType.Title"); //$NON-NLS-1$
  }

  public String getSpecialModelLabel() {
    return resources.getString("CharmEntry.CharmType.Annotated"); //$NON-NLS-1$
  }

  public String getTypeLabel() {
    return resources.getString("CharmEntry.CharmType.Type"); //$NON-NLS-1$
  }

  public String getDefaultStepLabel() {
    return resources.getString("CharmEntry.CharmType.ReflexiveDefaultStep"); //$NON-NLS-1$
  }

  public String getDefensiveStepLabel() {
    return resources.getString("CharmEntry.CharmType.ReflexiveDefenseStep"); //$NON-NLS-1$
  }

  public String getSplitStepLabel() {
    return resources.getString("CharmEntry.CharmType.ReflexiveSplitStep"); //$NON-NLS-1$
  }

  public String getDefenseLabel() {
    return resources.getString("CharmEntry.CharmType.SimpleDV"); //$NON-NLS-1$
  }

  public String getSpeedLabel() {
    return resources.getString("CharmEntry.CharmType.SimpleSpeed"); //$NON-NLS-1$;
  }

  public ListCellRenderer getDefaultIdentificateRenderer() {
    return new IdentificateSelectCellRenderer("", resources); //$NON-NLS-1$
  }

  public String getReflexiveSpecialsTitle() {
    return resources.getString("CharmEntry.CharmType.ReflexiveSpecialsTitle"); //$NON-NLS-1$
  }

  public IBasicMessage getReflexiveSpecialsMessage() {
    return reflexiveMessage;
  }

  public String getSimpleSpecialsTitle() {
    return resources.getString("CharmEntry.CharmType.SimpleSpecialsTitle"); //$NON-NLS-1$
  }

  public IBasicMessage getSimpleSpecialsMessage() {
    return simpleMessage;
  }

  public String getTurnTypeLabel() {
    return resources.getString("CharmEntry.CharmType.TurnType"); //$NON-NLS-1$
  }

  public IBasicMessage getCharmTypeDefaultMessage() {
    return defaultMessage;
  }
}