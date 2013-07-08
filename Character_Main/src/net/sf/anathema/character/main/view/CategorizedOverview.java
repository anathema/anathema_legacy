package net.sf.anathema.character.main.view;

import net.sf.anathema.character.main.library.overview.OverviewCategory;

public interface CategorizedOverview {

  OverviewCategory addOverviewCategory(String borderLabel);

  void showIn(OverviewDisplay characterPane);
}