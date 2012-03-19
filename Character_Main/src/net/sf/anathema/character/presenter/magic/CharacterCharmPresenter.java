package net.sf.anathema.character.presenter.magic;

import net.sf.anathema.character.presenter.magic.detail.MagicDetailModel;
import net.sf.anathema.character.presenter.magic.detail.MagicDetailPresenter;
import net.sf.anathema.character.presenter.magic.tree.CharacterCharmTreePresenter;
import net.sf.anathema.framework.presenter.view.IViewContent;
import net.sf.anathema.framework.presenter.view.SimpleViewContent;
import net.sf.anathema.framework.view.CollapsibleView;
import net.sf.anathema.framework.view.util.ContentProperties;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.platform.svgtree.presenter.view.CharmInteractionListener;

public class CharacterCharmPresenter implements IContentPresenter {

  private class OpenCharmDetailListener implements CharmInteractionListener {
    @Override
    public void nodeSelected(String nodeId) {
      // nothing to do
    }

    @Override
    public void nodeDetailsDemanded(String nodeId) {
      MagicDetailModel model = detailPresenter.getModel();
      if (!model.isActive(nodeId)) {
        return;
      }
      model.setDetailFor(nodeId);
      collapsibleView.setCollapsibleTitle(detailPresenter.getDetailTitle());
      collapsibleView.expandCollapsible();
    }
  }

  private final IResources resources;
  private final CharacterCharmTreePresenter treePresenter;
  private final MagicDetailPresenter detailPresenter;
  private final CollapsibleView collapsibleView = new CollapsibleView();

  public CharacterCharmPresenter(IResources resources, MagicDetailPresenter detailPresenter,
                                 CharacterCharmTreePresenter treePresenter) {
    this.detailPresenter = detailPresenter;
    this.resources = resources;
    this.treePresenter = treePresenter;
  }

  @Override
  public IViewContent getTabContent() {
    String header = resources.getString("CardView.CharmConfiguration.CharmSelection.Title"); //$NON-NLS-1$
    return new SimpleViewContent(new ContentProperties(header), collapsibleView);
  }

  @Override
  public void initPresentation() {
    treePresenter.initPresentation();
    detailPresenter.initPresentation();
    initDetailPresentation();
  }

  private void initDetailPresentation() {
    treePresenter.getView().addCharmInteractionListener(new OpenCharmDetailListener());
    collapsibleView.setMainContent(treePresenter.getView().getComponent());
    collapsibleView.setCollapsibleContainer(detailPresenter.getView().getComponent());
  }
}
