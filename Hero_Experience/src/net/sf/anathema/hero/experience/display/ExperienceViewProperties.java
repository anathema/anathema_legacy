package net.sf.anathema.hero.experience.display;

import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.resources.Resources;

public class ExperienceViewProperties implements IExperienceViewProperties {

  private final BasicUi basicUi;
  private Resources resources;

  public ExperienceViewProperties(Resources resources) {
    this.basicUi = new BasicUi();
    this.resources = resources;
  }

  @Override
  public RelativePath getDeleteIcon() {
    return basicUi.getRemoveIconPath();
  }

  @Override
  public RelativePath getAddIcon() {
    return basicUi.getAddIconPath();
  }

  @Override
  public String getTotalString() {
    return resources.getString("CardView.Experience.Total");
  }

  @Override
  public String getPointHeader() {
    return resources.getString("CardView.Experience.ExperiencePoints");
  }

  @Override
  public String getDescriptionHeader() {
    return resources.getString("CardView.Experience.Description");
  }
}