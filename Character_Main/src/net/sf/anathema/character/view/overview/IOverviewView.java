package net.sf.anathema.character.view.overview;

import net.sf.anathema.character.library.overview.IOverviewCategory;

public interface IOverviewView {

  public void initGui(IOverviewViewProperties properties);

  public IOverviewCategory addOverviewCategory(String borderLabel);
}