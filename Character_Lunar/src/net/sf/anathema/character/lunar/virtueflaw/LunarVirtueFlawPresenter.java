package net.sf.anathema.character.lunar.virtueflaw;

import net.sf.anathema.character.library.virtueflaw.model.IVirtueFlaw;
import net.sf.anathema.character.library.virtueflaw.presenter.IVirtueFlawModel;
import net.sf.anathema.character.library.virtueflaw.presenter.IVirtueFlawView;
import net.sf.anathema.character.library.virtueflaw.presenter.VirtueFlawPresenter;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;

public class LunarVirtueFlawPresenter extends VirtueFlawPresenter {

  public LunarVirtueFlawPresenter(IResources resources, IVirtueFlawView virtueFlawView, IVirtueFlawModel model) {
    super(resources, virtueFlawView, model);
  }

  @Override
  protected ITextView initNamePresentation(final IVirtueFlaw virtueFlaw) {
    ITextView nameView = super.initNamePresentation(virtueFlaw);
    nameView.setEnabled(false);
    virtueFlaw.addRootChangeListener(new IChangeListener() {
      public void changeOccured() {
        virtueFlaw.getName().setText(getResources().getString("Lunar.VirtueFlaw.Name." + virtueFlaw.getRoot().getId())); //$NON-NLS-1$
      }
    });
    return nameView;
  }
}