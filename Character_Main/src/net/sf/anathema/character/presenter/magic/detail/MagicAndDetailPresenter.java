package net.sf.anathema.character.presenter.magic.detail;

import net.sf.anathema.character.presenter.magic.IContentPresenter;
import net.sf.anathema.framework.presenter.view.IViewContent;
import net.sf.anathema.framework.presenter.view.SimpleViewContent;
import net.sf.anathema.framework.view.CollapsibleView;
import net.sf.anathema.framework.view.util.ContentProperties;

public class MagicAndDetailPresenter implements IContentPresenter {

  private String tabTitle;

  private class OpenCharmDetailListener implements ShowMagicDetailListener {

    @Override
    public void showDetail(String magicId) {
      MagicDetailModel model = detailPresenter.getModel();
      if (!model.isActive(magicId)) {
        return;
      }
      model.setDetailFor(magicId);
      collapsibleView.setCollapsibleTitle(detailPresenter.getDetailTitle());
      collapsibleView.expandCollapsible();
    }
  }

  private final DetailDemandingMagicPresenter mainPresenter;
  private final MagicDetailPresenter detailPresenter;
  private final CollapsibleView collapsibleView = new CollapsibleView();

  public MagicAndDetailPresenter(String tabTitle, MagicDetailPresenter detailPresenter,
          DetailDemandingMagicPresenter mainPresenter) {
    this.tabTitle = tabTitle;
    this.detailPresenter = detailPresenter;
    this.mainPresenter = mainPresenter;
  }

  @Override
  public IViewContent getTabContent() {
    return new SimpleViewContent(new ContentProperties(tabTitle), collapsibleView);
  }

  @Override
  public void initPresentation() {
    mainPresenter.initPresentation();
    detailPresenter.initPresentation();
    initDetailPresentation();
  }

  private void initDetailPresentation() {
    mainPresenter.addShowDetailListener(new OpenCharmDetailListener());
    collapsibleView.setMainContent(mainPresenter.getView().getComponent());
    collapsibleView.setCollapsibleContainer(detailPresenter.getView().getComponent());
  }
}
