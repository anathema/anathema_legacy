package net.sf.anathema.character.solar.virtueflaw;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.IAdditionalInitializer;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.library.virtueflaw.presenter.IDescriptiveVirtueFlawModel;
import net.sf.anathema.character.library.virtueflaw.presenter.IDescriptiveVirtueFlawView;
import net.sf.anathema.character.solar.virtueflaw.presenter.SolarVirtueFlawPresenter;
import net.sf.anathema.character.view.SectionView;
import net.sf.anathema.lib.resources.Resources;

public class SolarVirtueFlawInitializer implements IAdditionalInitializer {

  @Override
  public void initialize(IAdditionalModel model, Resources resources, ICharacterType type, SectionView sectionView) {
    String viewName = resources.getString("AdditionalTemplateView.TabName." + model.getTemplateId());
    IDescriptiveVirtueFlawView view = sectionView.addView(viewName, IDescriptiveVirtueFlawView.class, type);
    SolarVirtueFlawPresenter presenter = new SolarVirtueFlawPresenter(resources, view, (IDescriptiveVirtueFlawModel) model);
    presenter.initPresentation();
  }
}