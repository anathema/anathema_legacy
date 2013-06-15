package net.sf.anathema.character.solar.virtueflaw;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.IAdditionalInitializer;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.library.virtueflaw.presenter.IDescriptiveVirtueFlawModel;
import net.sf.anathema.character.library.virtueflaw.presenter.IDescriptiveVirtueFlawView;
import net.sf.anathema.character.solar.virtueflaw.presenter.SolarVirtueFlawPresenter;
import net.sf.anathema.lib.resources.Resources;

public class SolarVirtueFlawInitializer implements IAdditionalInitializer {

  @Override
  public void initialize(IAdditionalModel model, Resources resources, ICharacterType type, Object view) {
    IDescriptiveVirtueFlawView virtueFlawView = (IDescriptiveVirtueFlawView) view;
    SolarVirtueFlawPresenter presenter = new SolarVirtueFlawPresenter(resources, virtueFlawView, (IDescriptiveVirtueFlawModel) model);
    presenter.initPresentation();
  }

  @Override
  public Class getViewClass() {
    return IDescriptiveVirtueFlawView.class;
  }
}