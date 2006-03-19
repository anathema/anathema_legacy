package net.sf.anathema.campaign.presenter.view;

import net.sf.anathema.campaign.presenter.ICampaignContentViewProperties;
import net.sf.anathema.campaign.view.IContentChangeListener;
import net.sf.anathema.framework.presenter.view.ITabView;
import net.sf.anathema.framework.repository.tree.IRepositoryTreeView;


public interface ISeriesContentView extends ITabView<ICampaignContentViewProperties> {

  public IRepositoryTreeView addContentTree();

  public IRepositoryTreeView addRepositoryTree();

  public void addContentChangeListener(IContentChangeListener listener);
}
