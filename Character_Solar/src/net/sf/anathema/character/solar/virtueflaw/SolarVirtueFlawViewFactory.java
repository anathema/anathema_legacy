package net.sf.anathema.character.solar.virtueflaw;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.IAdditionalViewFactory;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.library.intvalue.IntValueDisplayFactoryPrototype;
import net.sf.anathema.character.solar.virtueflaw.presenter.ISolarVirtueFlawModel;
import net.sf.anathema.character.solar.virtueflaw.presenter.ISolarVirtueFlawView;
import net.sf.anathema.character.solar.virtueflaw.presenter.SolarVirtueFlawPresenter;
import net.sf.anathema.character.solar.virtueflaw.view.SolarVirtueFlawView;
import net.sf.anathema.lib.gui.IView;
import net.sf.anathema.lib.resources.IResources;

public class SolarVirtueFlawViewFactory implements IAdditionalViewFactory {

  @Override
  public IView createView(IAdditionalModel model, IResources resources, ICharacterType type) {
    ISolarVirtueFlawView virtueFlawView = new SolarVirtueFlawView(
            IntValueDisplayFactoryPrototype.createWithMarkerForCharacterType(resources, type));
    new SolarVirtueFlawPresenter(resources, virtueFlawView, (ISolarVirtueFlawModel) model).initPresentation();
    return virtueFlawView;
  }
}