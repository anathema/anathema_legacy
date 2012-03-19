package net.sf.anathema.character.presenter.magic.detail;

import net.sf.anathema.lib.gui.IView;

import javax.swing.JComponent;
import javax.swing.JLabel;

public class NullCharmDetailPresenter implements CharmDetailPresenter {

  @Override
  public MagicDetailModel getModel() {
    return new NullCharmDetailModel();
  }

  @Override
  public String getDetailTitle() {
    return "No details available";
  }

  @Override
  public IView getView() {
    return new NullCharmDetailView();
  }

  @Override
  public void initPresentation() {
    //nothing to do
  }

  private static class NullCharmDetailView implements IView {
    @Override
    public JComponent getComponent() {
      return new JLabel("No details available");
    }
  }
}
