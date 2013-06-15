package net.sf.anathema.character.presenter;

import net.sf.anathema.character.view.IAdvantageViewProperties;
import net.sf.anathema.lib.resources.Resources;

public class BasicAdvantageViewProperties implements IAdvantageViewProperties {
  private Resources resources;

  public BasicAdvantageViewProperties(Resources resources) {
    this.resources = resources;
  }

  @Override
  public String getVirtueTitle() {
    return resources.getString("AdvantagesView.Virtues.Title");
  }

  @Override
  public String getWillpowerTitle() {
    return resources.getString("AdvantagesView.Willpower.Title");
  }

  @Override
  public String getEssenceTitle() {
    return resources.getString("AdvantagesView.Essence.Title");
  }

  public String getOverallHeader() {
    return getVirtueTitle() + ", " + getWillpowerTitle() + " & " + getEssenceTitle();
  }
}