package net.sf.anathema.magic.description.presenter;

import net.sf.anathema.character.presenter.charm.detail.MagicDetailModel;
import net.sf.anathema.character.presenter.charm.detail.CharmDetailPresenter;
import net.sf.anathema.magic.description.model.MagicDescriptionEditDetailModel;
import net.sf.anathema.magic.description.model.MagicDescriptionEditModel;
import net.sf.anathema.magic.description.view.MagicDescriptionEditView;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.gui.IView;

import static java.text.MessageFormat.format;

public class MagicDescriptionEditPresenter implements CharmDetailPresenter {

  private final MagicDescriptionEditView view;
  private final MagicDescriptionEditModel model;

  public MagicDescriptionEditPresenter(MagicDescriptionEditView view, MagicDescriptionEditModel model) {
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
  public MagicDetailModel getModel() {
    return new MagicDescriptionEditDetailModel(model);
  }

  @Override
  public String getDetailTitle() {
    return format("Edit detail for ''{0}''", model.getEditId());
  }
}
