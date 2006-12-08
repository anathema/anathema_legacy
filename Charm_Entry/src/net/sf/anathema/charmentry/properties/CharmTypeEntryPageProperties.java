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

  public CharmTypeEntryPageProperties(IResources resources) {
    this.resources = resources;
    reflexiveMessage = new BasicMessage("CharmEntry.CharmType.Message.Reflexive"); //$NON-NLS-1$
    simpleMessage = new BasicMessage(resources.getString("CharmEntry.CharmType.Message.SimpleData")); //$NON-NLS-1$
  }

  public String getCharmTypeMessage() {
    return "Select the Charm's Type";
  }

  public String getPageHeader() {
    return "Charm Type";
  }

  public String getSpecialModelLabel() {
    return "Charm type is annotated";
  }

  public String getTypeLabel() {
    return "Charm Type";
  }

  public String getDefaultStepLabel() {
    return "Default/Offensive";
  }

  public String getDefensiveStepLabel() {
    return "Defensive";
  }

  public String getReflexiveStepLabel() {
    return "Step (Reflexive)";
  }

  public String getSplitStepLabel() {
    return "Split Offense/Defense";
  }

  public String getDefenseLabel() {
    return "DV";
  }

  public String getModifiersLabel() {
    return "Modifiers (Simple)";
  }

  public String getSpeedLabel() {
    return "Speed";
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

}