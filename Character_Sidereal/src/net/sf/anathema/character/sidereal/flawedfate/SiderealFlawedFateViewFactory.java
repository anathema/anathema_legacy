package net.sf.anathema.character.sidereal.flawedfate;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.IAdditionalViewFactory;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.library.intvalue.MarkerIntValueDisplayFactory;
import net.sf.anathema.character.library.virtueflaw.presenter.IVirtueFlawModel;
import net.sf.anathema.character.library.virtueflaw.presenter.IVirtueFlawView;
import net.sf.anathema.character.library.virtueflaw.view.VirtueFlawView;
import net.sf.anathema.character.sidereal.flawedfate.presenter.SiderealFlawedFatePresenter;
import net.sf.anathema.lib.gui.IView;
import net.sf.anathema.lib.resources.IResources;

public class SiderealFlawedFateViewFactory implements IAdditionalViewFactory {

  public IView createView(IAdditionalModel model, IResources resources, ICharacterType type) {
    IVirtueFlawView virtueFlawView = new VirtueFlawView(new MarkerIntValueDisplayFactory(resources, type));
    new SiderealFlawedFatePresenter(resources, virtueFlawView, (IVirtueFlawModel) model).initPresentation();
    return virtueFlawView;
  }
}