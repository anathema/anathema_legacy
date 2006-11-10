package net.sf.anathema.character.lunar.virtueflaw;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.IAdditionalViewFactory;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.library.virtueflaw.presenter.IVirtueFlawModel;
import net.sf.anathema.character.library.virtueflaw.presenter.IVirtueFlawView;
import net.sf.anathema.character.library.virtueflaw.view.VirtueFlawView;
import net.sf.anathema.character.lunar.virtueflaw.presenter.LunarVirtueFlawPresenter;
import net.sf.anathema.lib.gui.IView;
import net.sf.anathema.lib.resources.IResources;

public class LunarVirtueFlawViewFactory implements IAdditionalViewFactory {

  public IView createView(IAdditionalModel model, IResources resources, CharacterType type) {
    IVirtueFlawView virtueFlawView = new VirtueFlawView();
    new LunarVirtueFlawPresenter(resources, virtueFlawView, (IVirtueFlawModel) model).initPresentation();
    return virtueFlawView;
  }
}