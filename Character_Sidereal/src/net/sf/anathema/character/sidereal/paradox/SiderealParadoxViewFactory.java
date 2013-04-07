package net.sf.anathema.character.sidereal.paradox;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.IAdditionalViewFactory;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.library.intvalue.IntValueDisplayFactoryPrototype;
import net.sf.anathema.character.library.virtueflaw.presenter.IVirtueFlawModel;
import net.sf.anathema.character.library.virtueflaw.presenter.IVirtueFlawView;
import net.sf.anathema.character.library.virtueflaw.view.VirtueFlawView;
import net.sf.anathema.character.sidereal.paradox.presenter.SiderealParadoxPresenter;
import net.sf.anathema.framework.swing.IView;
import net.sf.anathema.lib.resources.Resources;

public class SiderealParadoxViewFactory implements IAdditionalViewFactory {

  @Override
  public IView createView(IAdditionalModel model, Resources resources, ICharacterType type) {
    IVirtueFlawView virtueFlawView = new VirtueFlawView(
            IntValueDisplayFactoryPrototype.createWithMarkerForCharacterType(type));
    new SiderealParadoxPresenter(resources, virtueFlawView, (IVirtueFlawModel) model).initPresentation();
    return virtueFlawView;
  }
}