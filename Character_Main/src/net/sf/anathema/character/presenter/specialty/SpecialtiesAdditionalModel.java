package net.sf.anathema.character.presenter.specialty;

import net.sf.anathema.character.generic.additionaltemplate.AbstractAdditionalModelAdapter;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;
import net.sf.anathema.character.library.trait.specialties.ISpecialtiesConfiguration;
import net.sf.anathema.character.model.CharacterModelGroup;
import net.sf.anathema.lib.control.IChangeListener;

public class SpecialtiesAdditionalModel extends AbstractAdditionalModelAdapter implements ISpecialtiesAdditionalModel {

  private final IAdditionalTemplate additionalTemplate;
  private final ISpecialtiesConfiguration model;

  public SpecialtiesAdditionalModel(IAdditionalTemplate additionalTemplate, ICharacterModelContext context) {
    this.additionalTemplate = additionalTemplate;
    // todo: move specialty configuration into specialty module
    this.model = context.getHero().getAbilities().getSpecialtyConfiguration();
  }

  @Override
  public CharacterModelGroup getAdditionalModelType() {
    return CharacterModelGroup.NaturalTraits;
  }

  @Override
  public String getTemplateId() {
    return additionalTemplate.getId();
  }

  @Override
  public ISpecialtiesConfiguration getSpecialtiesModel() {
    return model;
  }

  @Override
  public void addChangeListener(IChangeListener listener) {
    // Dirty/Changed-state handled via abilities.
  }
}