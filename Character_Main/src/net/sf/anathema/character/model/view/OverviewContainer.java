package net.sf.anathema.character.model.view;

import net.sf.anathema.character.model.view.overview.CategorizedOverview;

public interface OverviewContainer {
  CategorizedOverview addCreationOverviewView();

  CategorizedOverview addExperienceOverviewView();

  void toggleOverviewView(boolean experienced);
}
