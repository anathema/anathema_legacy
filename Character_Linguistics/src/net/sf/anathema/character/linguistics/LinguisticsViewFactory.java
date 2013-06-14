package net.sf.anathema.character.linguistics;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.IAdditionalViewFactory;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.linguistics.presenter.ILinguisticsModel;
import net.sf.anathema.character.linguistics.presenter.ILinguisticsView;
import net.sf.anathema.character.linguistics.presenter.LinguisticsPresenter;
import net.sf.anathema.character.linguistics.view.LinguisticsView;
import net.sf.anathema.framework.swing.IView;
import net.sf.anathema.lib.resources.Resources;

public class LinguisticsViewFactory implements IAdditionalViewFactory {

  @Override
  public void createView(IAdditionalModel model, Resources resources, ICharacterType type, Object view) {
    ILinguisticsModel linguisticsModel = ((ILinguisticsAdditionalModel) model).getLinguisticsModel();
    ILinguisticsView linguisticsView = (ILinguisticsView) view;
    new LinguisticsPresenter(linguisticsModel, linguisticsView, resources).initPresentation();
  }

  @Override
  public IView createView(IAdditionalModel model, Resources resources, ICharacterType characterType) {
    LinguisticsView linguisticsView = new LinguisticsView();
    ILinguisticsModel linguisticsModel = ((ILinguisticsAdditionalModel) model).getLinguisticsModel();
    new LinguisticsPresenter(linguisticsModel, linguisticsView, resources).initPresentation();
    return linguisticsView;
  }

  @Override
  public Class getViewClass() {
    return ILinguisticsView.class;
  }
}