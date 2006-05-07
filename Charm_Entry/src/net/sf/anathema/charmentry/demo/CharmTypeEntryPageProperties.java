package net.sf.anathema.charmentry.demo;

import net.sf.anathema.lib.resources.IResources;

public class CharmTypeEntryPageProperties implements ICharmTypeEntryPageProperties {

  private final IResources resources;

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
}