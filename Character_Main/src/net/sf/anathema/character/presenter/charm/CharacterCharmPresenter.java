package net.sf.anathema.character.presenter.charm;

import net.sf.anathema.character.presenter.charm.detail.CharmDescriptionEditPresenter;
import net.sf.anathema.character.presenter.charm.detail.DetailModel;
import net.sf.anathema.character.presenter.charm.tree.CharacterCharmTreePresenter;
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
      DetailModel<String> model = detailPresenter.getModel();
      if (!model.isActive()) {
        return;
      }
      model.setDetailFor(nodeId);
      collapsibleView.setCollapsibleTitle(detailPresenter.getDetailTitle());
      collapsibleView.expandCollapsible();
    }
  }

  private IResources resources;
  private CharacterCharmTreePresenter treePresenter;
  private CharmDescriptionEditPresenter detailPresenter;
  private final CollapsibleView collapsibleView = new CollapsibleView();

  public CharacterCharmPresenter(IResources resources, CharmDescriptionEditPresenter detailPresenter,
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
