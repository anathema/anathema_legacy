package net.sf.anathema.charm.description.presenter;

import net.sf.anathema.character.presenter.charm.detail.CharmDetailModel;
import net.sf.anathema.character.presenter.charm.detail.CharmDetailPresenter;
import net.sf.anathema.charm.description.model.CharmDescriptionEditDetailModel;
import net.sf.anathema.charm.description.model.CharmDescriptionEditModel;
import net.sf.anathema.charm.description.view.CharmDescriptionEditView;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.gui.IView;

import static java.text.MessageFormat.format;

public class CharmDescriptionEditPresenter implements CharmDetailPresenter {

  private final CharmDescriptionEditView view;
  private final CharmDescriptionEditModel model;

  public CharmDescriptionEditPresenter(CharmDescriptionEditView view, CharmDescriptionEditModel model) {
    this.view = view;
    this.model = model;
  }


  @Override
  public void initPresentation() {
    model.addDescriptionChangedListener(new IChangeListener() {

      @Override
      public void changeOccurred() {
        view.setDescription(model.getCurrentDescription());
      }
    });
    view.addDescriptionChangeListener(new IObjectValueChangedListener<String>() {
      @Override
      public void valueChanged(String newValue) {
        model.updateCurrentDescription(newValue);
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
