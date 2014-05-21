package net.sf.anathema.hero.health.model;

import net.sf.anathema.hero.abilities.model.AbilitiesModel;
import net.sf.anathema.hero.attributes.model.AttributeModel;
import net.sf.anathema.hero.experience.ExperienceModel;
import net.sf.anathema.hero.initialization.SimpleModelTreeEntry;
import net.sf.anathema.hero.model.HeroModelFactory;
import net.sf.anathema.hero.template.TemplateFactory;
import net.sf.anathema.hero.traits.model.TraitModel;

@SuppressWarnings("UnusedDeclaration")
public class HealthModelFactory extends SimpleModelTreeEntry implements HeroModelFactory {

  public HealthModelFactory() {
    super(HealthModel.ID, AbilitiesModel.ID, AttributeModel.ID, TraitModel.ID, ExperienceModel.ID);
  }

  @SuppressWarnings("unchecked")
  @Override
  public HealthModelImpl create(TemplateFactory templateFactory, String templateId) {
    return new HealthModelImpl();
  }
}
