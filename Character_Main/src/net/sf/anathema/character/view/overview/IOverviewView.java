package net.sf.anathema.character.view.overview;

import net.sf.anathema.character.library.overview.IOverviewCategory;

public interface IOverviewView {

  IOverviewCategory addOverviewCategory(String borderLabel);
}