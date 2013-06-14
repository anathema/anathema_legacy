package net.sf.anathema.character.platform.specialties;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.IAdditionalViewFactory;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.library.intvalue.IntValueDisplayFactoryPrototype;
import net.sf.anathema.character.library.trait.specialties.ISpecialtiesConfiguration;
import net.sf.anathema.character.presenter.specialty.ISpecialtiesAdditionalModel;
import net.sf.anathema.character.presenter.specialty.ISpecialtiesConfigurationView;
import net.sf.anathema.character.presenter.specialty.SpecialtiesConfigurationPresenter;
import net.sf.anathema.framework.swing.IView;
import net.sf.anathema.lib.resources.Resources;

public class SpecialtiesViewFactory implements IAdditionalViewFactory {

  @Override
  public void createView(IAdditionalModel model, Resources resources, ICharacterType type, Object view) {
    ISpecialtiesConfiguration specialtiesModel = ((ISpecialtiesAdditionalModel) model).getSpecialtiesModel();
    ISpecialtiesConfigurationView specialtiesView = (ISpecialtiesConfigurationView) view;
    new SpecialtiesConfigurationPresenter(specialtiesModel, specialtiesView, resources).initPresentation();
  }

  @Override
  public IView createView(IAdditionalModel model, Resources resources, ICharacterType characterType) {
    SpecialtiesView view = new SpecialtiesView(IntValueDisplayFactoryPrototype.createWithMarkerForCharacterType(characterType));
    ISpecialtiesConfiguration specialtiesModel = ((ISpecialtiesAdditionalModel) model).getSpecialtiesModel();
    new SpecialtiesConfigurationPresenter(specialtiesModel, view, resources).initPresentation();
    return view;
  }

  @Override
  public Class getViewClass() {
    return ISpecialtiesConfigurationView.class;
  }
}