package net.sf.anathema.character.impl.specialties;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.IAdditionalViewFactory;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.library.intvalue.MarkerIntValueDisplayFactory;
import net.sf.anathema.character.library.trait.specialties.ISpecialtiesConfiguration;
import net.sf.anathema.character.presenter.specialty.ISpecialtiesAdditionalModel;
import net.sf.anathema.character.presenter.specialty.SpecialtiesConfigurationPresenter;
import net.sf.anathema.lib.gui.IView;
import net.sf.anathema.lib.resources.IResources;

public class SpecialtiesViewFactory implements IAdditionalViewFactory {

  public IView createView(IAdditionalModel model, IResources resources, CharacterType type) {
    ISpecialtiesConfiguration specialtiesModel = ((ISpecialtiesAdditionalModel) model).getSpecialtiesModel();
    SpecialtiesView view = new SpecialtiesView(new MarkerIntValueDisplayFactory(resources, type));
    new SpecialtiesConfigurationPresenter(specialtiesModel, view, resources).initPresentation();
    return view;
  }
}