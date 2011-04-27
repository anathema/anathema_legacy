package net.sf.anathema.character.sidereal.paradox.presenter;

import net.sf.anathema.character.library.virtueflaw.presenter.IVirtueFlawModel;
import net.sf.anathema.character.library.virtueflaw.presenter.IVirtueFlawView;
import net.sf.anathema.character.library.virtueflaw.presenter.VirtueFlawPresenter;
import net.sf.anathema.lib.resources.IResources;

public class SiderealParadoxPresenter extends VirtueFlawPresenter {

  public SiderealParadoxPresenter(IResources resources, IVirtueFlawView virtueFlawView, IVirtueFlawModel model) {
    super(resources, virtueFlawView, model);
  }
  
  @Override
  protected void initBasicPresentation()
  {
  }
}