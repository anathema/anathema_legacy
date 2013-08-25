package net.sf.anathema.magic.description.display;

import net.sf.anathema.lib.gui.Presenter;

public class MagicAndDetailPresenter implements Presenter {

  private class OpenCharmDetailListener implements ShowMagicDetailListener {

    @Override
    public void showDetail(String magicId) {
      MagicDetailModel model = detailPresenter.getModel();
      if (!model.isActive(magicId)) {
        return;
      }
      model.setDetailFor(magicId);
      //collapsibleView.setCollapsibleTitle(detailPresenter.getDetailTitle());
      //collapsibleView.expandCollapsible();
    }
  }

  private final DetailDemandingMagicPresenter mainPresenter;
  private final MagicDetailPresenter detailPresenter;
  //private final CollapsibleView collapsibleView = new CollapsibleView();

  public MagicAndDetailPresenter(MagicDetailPresenter detailPresenter, DetailDemandingMagicPresenter mainPresenter) {
    this.detailPresenter = detailPresenter;
    this.mainPresenter = mainPresenter;
  }

  @Override
  public void initPresentation() {
    mainPresenter.initPresentation();
    detailPresenter.initPresentation();
    initDetailPresentation();
  }

  private void initDetailPresentation() {
    mainPresenter.addShowDetailListener(new OpenCharmDetailListener());
    //collapsibleView.setMainContent(mainPresenter.getView().getComponent());
    //collapsibleView.setCollapsibleContainer(detailPresenter.getView().getComponent());
  }
}
