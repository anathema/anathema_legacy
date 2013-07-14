package net.sf.anathema.magic.description.presenter;

import net.sf.anathema.magic.description.display.MagicDetailModel;
import net.sf.anathema.magic.description.display.MagicDetailPresenter;
import net.sf.anathema.framework.swing.IView;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.magic.description.model.MagicDescriptionEditDetailModel;
import net.sf.anathema.magic.description.model.MagicDescriptionEditModel;
import net.sf.anathema.magic.description.view.MagicDescriptionEditView;

public class MagicDescriptionEditPresenter implements MagicDetailPresenter {

  private final MagicDescriptionEditView view;
  private final MagicDescriptionEditModel model;
  private Resources resources;

  public MagicDescriptionEditPresenter(MagicDescriptionEditView view, MagicDescriptionEditModel model, Resources resources) {
    this.view = view;
    this.model = model;
    this.resources = resources;
  }


  @Override
  public void initPresentation() {
    model.addDescriptionChangedListener(new ChangeListener() {

      @Override
      public void changeOccurred() {
        view.setDescription(model.getCurrentDescription());
      }
    });
    view.addDescriptionChangeListener(new ObjectValueListener<String>() {
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
    String magicName = resources.getString(model.getEditId());
    return resources.getString("MagicDescription.EditTitle", magicName);
  }
}
