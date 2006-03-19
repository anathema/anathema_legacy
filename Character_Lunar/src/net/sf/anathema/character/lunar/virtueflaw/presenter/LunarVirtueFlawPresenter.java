package net.sf.anathema.character.lunar.virtueflaw.presenter;

import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.character.library.virtueflaw.model.IVirtueFlaw;
import net.sf.anathema.character.library.virtueflaw.presenter.IVirtueFlawModel;
import net.sf.anathema.character.library.virtueflaw.presenter.IVirtueFlawView;
import net.sf.anathema.character.library.virtueflaw.presenter.VirtueFlawPresenter;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;

public class LunarVirtueFlawPresenter extends VirtueFlawPresenter {

  public LunarVirtueFlawPresenter(IResources resources, IVirtueFlawView virtueFlawView, IVirtueFlawModel model) {
    super(resources, virtueFlawView, model);
  }

  @Override
  protected ITextView initNamePresentation(IVirtueFlaw virtueFlaw) {
    ITextView nameView = super.initNamePresentation(virtueFlaw);
    nameView.setEnabled(false);
    getModel().getVirtueFlaw().addRootListener(new IObjectValueChangedListener<VirtueType>() {
      public void valueChanged(VirtueType oldValue, VirtueType newValue) {
        getModel().getVirtueFlaw().getName().setText(
            getResources().getString("Lunar.VirtueFlaw.Name." + newValue.getId())); //$NON-NLS-1$
      }
    });
    return nameView;
  }
}