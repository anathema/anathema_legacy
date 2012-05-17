package net.sf.anathema.character.lunar.virtueflaw;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.IAdditionalViewFactory;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.library.intvalue.IntValueDisplayFactoryPrototype;
import net.sf.anathema.character.library.virtueflaw.model.DescriptiveVirtueFlawModel;
import net.sf.anathema.character.library.virtueflaw.presenter.IDescriptiveVirtueFlawModel;
import net.sf.anathema.character.library.virtueflaw.presenter.IDescriptiveVirtueFlawView;
import net.sf.anathema.character.library.virtueflaw.presenter.IVirtueFlawModel;
import net.sf.anathema.character.library.virtueflaw.presenter.IVirtueFlawView;
import net.sf.anathema.character.library.virtueflaw.view.DescriptiveVirtueFlawView;
import net.sf.anathema.character.library.virtueflaw.view.VirtueFlawView;
import net.sf.anathema.character.lunar.virtueflaw.presenter.ComplexLunarVirtueFlawPresenter;
import net.sf.anathema.framework.value.IntegerViewFactory;
import net.sf.anathema.lib.gui.IView;
import net.sf.anathema.lib.resources.IResources;

public class LunarVirtueFlawViewFactory implements IAdditionalViewFactory {

  @Override
  public IView createView(IAdditionalModel model, IResources resources, ICharacterType type) {
    IView virtueFlawView = null;
    IntegerViewFactory factory = IntValueDisplayFactoryPrototype.createWithMarkerForCharacterType(resources, type);
    if (model instanceof DescriptiveVirtueFlawModel) {
      virtueFlawView = new DescriptiveVirtueFlawView(factory);
      new ComplexLunarVirtueFlawPresenter(resources, (IDescriptiveVirtueFlawView) virtueFlawView, (IDescriptiveVirtueFlawModel) model).initPresentation();
    } else {
      virtueFlawView = new VirtueFlawView(factory);
      new LunarVirtueFlawPresenter(resources, (IVirtueFlawView) virtueFlawView, (IVirtueFlawModel) model).initPresentation();
    }
    return virtueFlawView;
  }
}