package net.sf.anathema.character.presenter.specialty;

import net.sf.anathema.character.generic.additionaltemplate.AbstractAdditionalModelAdapter;
import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;
import net.sf.anathema.character.library.trait.specialties.ISpecialtiesConfiguration;
import net.sf.anathema.character.library.trait.specialties.SpecialtyModelFetcher;
import net.sf.anathema.hero.display.HeroModelGroup;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.control.IChangeListener;

public class SpecialtiesAdditionalModel extends AbstractAdditionalModelAdapter implements ISpecialtiesAdditionalModel {

  private final IAdditionalTemplate additionalTemplate;
  private final ISpecialtiesConfiguration model;

  public SpecialtiesAdditionalModel(Hero hero, IAdditionalTemplate additionalTemplate) {
    this.additionalTemplate = additionalTemplate;
    // todo (sandra): move specialty configuration into specialty module
    this.model = SpecialtyModelFetcher.fetch(hero);
  }

  @Override
  public HeroModelGroup getAdditionalModelType() {
    return HeroModelGroup.NaturalTraits;
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