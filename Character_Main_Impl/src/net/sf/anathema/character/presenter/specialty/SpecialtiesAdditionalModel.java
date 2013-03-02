package net.sf.anathema.character.presenter.specialty;

import net.sf.anathema.character.generic.additionaltemplate.AbstractAdditionalModelAdapter;
import net.sf.anathema.character.generic.additionaltemplate.AdditionalModelType;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;
import net.sf.anathema.character.library.trait.specialties.ISpecialtiesConfiguration;
import net.sf.anathema.character.model.traits.ICoreTraitConfiguration;
import net.sf.anathema.lib.control.IChangeListener;

public class SpecialtiesAdditionalModel extends AbstractAdditionalModelAdapter implements ISpecialtiesAdditionalModel {

  private final IAdditionalTemplate additionalTemplate;
  private final ISpecialtiesConfiguration model;

  public SpecialtiesAdditionalModel(IAdditionalTemplate additionalTemplate, ICharacterModelContext context) {
    this.additionalTemplate = additionalTemplate;
    IGenericTraitCollection traitCollection = context.getTraitCollection();
    this.model = ((ICoreTraitConfiguration) traitCollection).getSpecialtyConfiguration();
  }

  @Override
  public AdditionalModelType getAdditionalModelType() {
    return AdditionalModelType.Abilities;
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