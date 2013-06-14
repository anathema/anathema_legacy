package net.sf.anathema.character.solar.virtueflaw;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.IAdditionalViewFactory;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.library.intvalue.IntValueDisplayFactoryPrototype;
import net.sf.anathema.character.library.virtueflaw.presenter.IDescriptiveVirtueFlawModel;
import net.sf.anathema.character.library.virtueflaw.presenter.IDescriptiveVirtueFlawView;
import net.sf.anathema.character.library.virtueflaw.view.DescriptiveVirtueFlawView;
import net.sf.anathema.character.solar.virtueflaw.presenter.SolarVirtueFlawPresenter;
import net.sf.anathema.framework.swing.IView;
import net.sf.anathema.framework.value.IntegerViewFactory;
import net.sf.anathema.lib.resources.Resources;

public class SolarVirtueFlawViewFactory implements IAdditionalViewFactory {

  @Override
  public void createView(IAdditionalModel model, Resources resources, ICharacterType type, Object view) {
    IDescriptiveVirtueFlawView virtueFlawView = (IDescriptiveVirtueFlawView) view;
    SolarVirtueFlawPresenter presenter = new SolarVirtueFlawPresenter(resources, virtueFlawView, (IDescriptiveVirtueFlawModel) model);
    presenter.initPresentation();
  }

  @Override
  public IView createView(IAdditionalModel model, Resources resources, ICharacterType characterType) {
    IntegerViewFactory viewFactory = IntValueDisplayFactoryPrototype.createWithMarkerForCharacterType(characterType);
    IDescriptiveVirtueFlawView virtueFlawView = new DescriptiveVirtueFlawView(viewFactory);
    SolarVirtueFlawPresenter presenter = new SolarVirtueFlawPresenter(resources, virtueFlawView, (IDescriptiveVirtueFlawModel) model);
    presenter.initPresentation();
    return virtueFlawView;
  }

  @Override
  public Class getViewClass() {
    return IDescriptiveVirtueFlawView.class;
  }
}