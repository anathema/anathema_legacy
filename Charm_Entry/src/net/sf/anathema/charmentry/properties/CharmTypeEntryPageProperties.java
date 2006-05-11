package net.sf.anathema.charmentry.properties;

import javax.swing.ListCellRenderer;

import net.disy.commons.core.message.BasicMessage;
import net.disy.commons.core.message.IBasicMessage;
import net.sf.anathema.framework.view.IdentificateSelectCellRenderer;
import net.sf.anathema.lib.resources.IResources;

public class CharmTypeEntryPageProperties {

  private final IResources resources;
  private final IBasicMessage reflexiveMessage = new BasicMessage("Enter the Charm's step data.");
  private final IBasicMessage simpleMessage = new BasicMessage("Enter the Charm's speed data.");

  public CharmTypeEntryPageProperties(IResources resources) {
    this.resources = resources;
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
    return new IdentificateSelectCellRenderer("", resources);
  }

  public String getReflexiveSpecialsTitle() {
    return "Step Data";
  }

  public IBasicMessage getReflexiveSpecialsMessage() {
    return reflexiveMessage;
  }

  public String getSimpleSpecialsTitle() {
    return "Speed Data";
  }

  public IBasicMessage getSimpleSpecialsMessage() {
    return simpleMessage;
  }

  public String getTurnTypeLabel() {
    return "Speed is measured in";
  }

}