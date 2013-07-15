package net.sf.anathema.character.equipment.creation.presenter.stats.properties;

import net.sf.anathema.lib.message.BasicMessage;
import net.sf.anathema.lib.message.IBasicMessage;
import net.sf.anathema.lib.resources.Resources;

public class ArtifactStatisticsProperties extends AbstractEquipmentStatisticsProperties {

  private final IBasicMessage defaultMessage;

  public ArtifactStatisticsProperties(Resources resources) {
    super(resources);
    this.defaultMessage = new BasicMessage(getString("Equipment.Creation.Artifact.DefaultMessage"));
  }

  public String getAttuneCostLabel() {
    return getLabelString("Equipment.Stats.Long.AttuneCost");
  }
  
  public String getRequireAttuneLabel() {
	    return getLabelString("Equipment.Stats.Long.RequireAttune");
	  }
  
  public String getForeignAttuneLabel() {
	    return getLabelString("Equipment.Stats.Long.ForeignAttune");
	  }

  @Override
  public IBasicMessage getDefaultMessage() {
    return defaultMessage;
  }

  @Override
  public String getPageDescription() {
    return getString("Equipment.Creation.Artifact.PageTitle");
  }

  @Override
  public String getDefaultName() {
    return getString("Equipment.Creation.Artifact.DefaultName");
  }
}