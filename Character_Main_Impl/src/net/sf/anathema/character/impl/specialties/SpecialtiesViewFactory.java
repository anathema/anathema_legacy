package net.sf.anathema.character.impl.specialties;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.IAdditionalViewFactory;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.library.intvalue.MarkerIntValueDisplayFactory;
import net.sf.anathema.character.library.trait.specialties.ISpecialtyConfiguration;
import net.sf.anathema.character.presenter.specialty.ISpecialtiesAdditionalModel;
import net.sf.anathema.character.presenter.specialty.SpecialtiesConfigurationPresenter;
import net.sf.anathema.framework.presenter.view.ISimpleTabView;
import net.sf.anathema.lib.resources.IResources;

public class SpecialtiesViewFactory implements IAdditionalViewFactory {

  public ISimpleTabView createView(IAdditionalModel model, IResources resources, CharacterType type) {
    ISpecialtyConfiguration specialtiesModel = ((ISpecialtiesAdditionalModel) model).getSpecialtiesModel();
    SpecialtiesView view = new SpecialtiesView(new MarkerIntValueDisplayFactory(resources, type));
    new SpecialtiesConfigurationPresenter(specialtiesModel, view, resources).initPresentation();
    return view;
  }
}