package net.sf.anathema.character.view;

import net.sf.anathema.character.view.overview.IOverviewView;

public interface OverviewContainer {
  IOverviewView addCreationOverviewView();

  IOverviewView addExperienceOverviewView();

  void toogleOverviewView(boolean experienced);
}
