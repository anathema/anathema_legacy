package net.sf.anathema.hero.concept.model.concept;

import com.google.common.base.Preconditions;
import net.sf.anathema.hero.concept.CasteTemplate;
import net.sf.anathema.hero.concept.ConfigurableCasteCollection;
import net.sf.anathema.hero.concept.DefaultCasteModel;
import net.sf.anathema.hero.concept.DefaultCasteSelection;
import net.sf.anathema.hero.concept.HeroConcept;
import net.sf.anathema.hero.concept.template.caste.CasteTemplateLoader;
import net.sf.anathema.hero.experience.ExperienceModel;
import net.sf.anathema.hero.initialization.SimpleModelTreeEntry;
import net.sf.anathema.hero.model.HeroModelAutoCollector;
import net.sf.anathema.hero.model.HeroModelFactory;
import net.sf.anathema.hero.template.TemplateFactory;

@HeroModelAutoCollector
public class CharacterConceptFactory extends SimpleModelTreeEntry implements HeroModelFactory {

  public CharacterConceptFactory() {
    super(HeroConcept.ID, ExperienceModel.ID);
  }

  @SuppressWarnings("unchecked")
  @Override
  public DefaultHeroConcept create(TemplateFactory templateFactory, String templateId) {
    Preconditions.checkNotNull(templateId, "The Character Concept requires a configured template.");
    CasteTemplate template = CasteTemplateLoader.loadTemplate(templateFactory, templateId);
    DefaultCasteModel casteModel = new DefaultCasteModel(new DefaultCasteSelection(), new ConfigurableCasteCollection(template));
    return new DefaultHeroConcept(casteModel);
  }
}
