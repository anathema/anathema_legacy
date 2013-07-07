package net.sf.anathema.character.main.view.overview;

import net.sf.anathema.character.main.library.overview.IOverviewCategory;

public interface CategorizedOverview {

  IOverviewCategory addOverviewCategory(String borderLabel);

  void showIn(OverviewDisplay characterPane);
}