package net.sf.anathema.character.db.virtueflaw;

import net.sf.anathema.character.library.virtueflaw.model.IVirtueFlaw;
import net.sf.anathema.character.library.virtueflaw.presenter.IVirtueFlawModel;
import net.sf.anathema.character.library.virtueflaw.presenter.IVirtueFlawView;
import net.sf.anathema.character.library.virtueflaw.presenter.VirtueFlawPresenter;
import net.sf.anathema.framework.view.NullTextView;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;

public class DbVirtueFlawPresenter extends VirtueFlawPresenter {

  public DbVirtueFlawPresenter(Resources resources, IVirtueFlawView virtueFlawView, IVirtueFlawModel model) {
    super(resources, virtueFlawView, model);
  }

  @Override
  protected ITextView initNamePresentation(IVirtueFlaw virtueFlaw) {
    return new NullTextView();
  }

}