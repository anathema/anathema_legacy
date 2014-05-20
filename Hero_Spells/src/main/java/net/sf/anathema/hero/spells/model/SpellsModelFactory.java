package net.sf.anathema.hero.spells.model;

import net.sf.anathema.hero.abilities.model.AbilitiesModel;
import net.sf.anathema.hero.abilities.template.AbilitiesTemplate;
import net.sf.anathema.hero.abilities.template.AbilitiesTemplateLoader;
import net.sf.anathema.hero.attributes.model.AttributeModel;
import net.sf.anathema.hero.charms.advance.MagicPointsModel;
import net.sf.anathema.hero.charms.model.CharmsModel;
import net.sf.anathema.hero.concept.HeroConcept;
import net.sf.anathema.hero.experience.ExperienceModel;
import net.sf.anathema.hero.health.model.HealthModel;
import net.sf.anathema.hero.initialization.SimpleModelTreeEntry;
import net.sf.anathema.hero.model.HeroModelFactory;
import net.sf.anathema.hero.spells.template.SpellsTemplate;
import net.sf.anathema.hero.spells.template.SpellsTemplateLoader;
import net.sf.anathema.hero.spiritual.SpiritualTraitModel;
import net.sf.anathema.hero.template.TemplateFactory;
import net.sf.anathema.hero.traits.TraitModel;

@SuppressWarnings("UnusedDeclaration")
public class SpellsModelFactory extends SimpleModelTreeEntry implements HeroModelFactory {

  public SpellsModelFactory() {
    super(SpellsModel.ID, CharmsModel.ID, AttributeModel.ID, AbilitiesModel.ID, SpiritualTraitModel.ID, TraitModel.ID, ExperienceModel.ID,
            HeroConcept.ID, HealthModel.ID, MagicPointsModel.ID);
  }

  @SuppressWarnings("unchecked")
  @Override
  public SpellsModel create(TemplateFactory templateFactory, String templateId) {
    SpellsTemplate template = SpellsTemplateLoader.loadTemplate(templateFactory, templateId);
    return new SpellsModelImpl(template);
  }
}
