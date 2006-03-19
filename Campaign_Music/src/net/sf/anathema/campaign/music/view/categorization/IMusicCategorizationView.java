package net.sf.anathema.campaign.music.view.categorization;

import net.sf.anathema.lib.workflow.container.ISelectionContainerView;

public interface IMusicCategorizationView {

  public ISelectionContainerView getMoodsView();

  public ISelectionContainerView getEventsView();

  public ISelectionContainerView getThemesView();
}
