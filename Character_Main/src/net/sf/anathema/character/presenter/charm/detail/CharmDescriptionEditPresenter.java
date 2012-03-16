package net.sf.anathema.character.presenter.charm.detail;

import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.gui.IView;

import static java.text.MessageFormat.format;

public class CharmDescriptionEditPresenter implements CharmDetailPresenter {

  private final CharmDescriptionEditView view = new CharmDescriptionEditView();
  private CharmDescriptionEditModel model = new AutoSaveCharmDescriptionEditModel();

  @Override
  public void initPresentation() {
    model.addDescriptionChangedListener(new IChangeListener() {

      @Override
      public void changeOccurred() {
        view.setDescription(model.getCurrentDescription());
      }
    });
  }

  @Override
  public IView getView() {
    return view;
  }

  @Override
  public CharmDetailModel getModel() {
    return new CharmDescriptionEditDetailModel(model);
  }

  @Override
  public String getDetailTitle() {
    return format("Edit detail for ''{0}''", model.getEditId());
  }
}
