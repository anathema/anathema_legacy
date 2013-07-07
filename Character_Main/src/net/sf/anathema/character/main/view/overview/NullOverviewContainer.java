package net.sf.anathema.character.main.view.overview;

import net.sf.anathema.character.main.library.overview.OverviewCategory;
import net.sf.anathema.character.main.library.overview.SwingOverviewCategory;

public class NullOverviewContainer implements CategorizedOverview {
  @Override
  public OverviewCategory addOverviewCategory(String borderLabel) {
    return new SwingOverviewCategory(null, null, false);
  }

  @Override
  public void showIn(OverviewDisplay characterPane) {
    //nothing to do
  }
}
