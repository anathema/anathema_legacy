package net.sf.anathema.character.platform.specialties;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.IAdditionalViewFactory;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.library.intvalue.IntValueDisplayFactoryPrototype;
import net.sf.anathema.character.library.trait.specialties.ISpecialtiesConfiguration;
import net.sf.anathema.character.presenter.specialty.ISpecialtiesAdditionalModel;
import net.sf.anathema.character.presenter.specialty.SpecialtiesConfigurationPresenter;
import net.sf.anathema.lib.gui.IView;
import net.sf.anathema.lib.resources.Resources;

public class SpecialtiesViewFactory implements IAdditionalViewFactory {

  @Override
  public IView createView(IAdditionalModel model, Resources resources, ICharacterType type) {
    ISpecialtiesConfiguration specialtiesModel = ((ISpecialtiesAdditionalModel) model).getSpecialtiesModel();
    SpecialtiesView view = new SpecialtiesView(IntValueDisplayFactoryPrototype.createWithMarkerForCharacterType(type));
    new SpecialtiesConfigurationPresenter(specialtiesModel, view, resources).initPresentation();
    return view;
  }
}