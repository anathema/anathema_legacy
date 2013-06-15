package net.sf.anathema.character.linguistics;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.IAdditionalInitializer;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.linguistics.presenter.ILinguisticsModel;
import net.sf.anathema.character.linguistics.presenter.ILinguisticsView;
import net.sf.anathema.character.linguistics.presenter.LinguisticsPresenter;
import net.sf.anathema.character.view.SectionView;
import net.sf.anathema.lib.resources.Resources;

public class LinguisticsInitializer implements IAdditionalInitializer {

  @Override
  public void initialize(IAdditionalModel model, Resources resources, ICharacterType type, SectionView sectionView) {
    String viewName = resources.getString("AdditionalTemplateView.TabName." + model.getTemplateId());
    ILinguisticsView view = sectionView.addView(viewName, ILinguisticsView.class, type);
    ILinguisticsModel linguisticsModel = ((ILinguisticsAdditionalModel) model).getLinguisticsModel();
    new LinguisticsPresenter(linguisticsModel, view, resources).initPresentation();
  }
}