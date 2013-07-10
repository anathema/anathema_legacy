package net.sf.anathema.hero.concept.template.caste;

import net.sf.anathema.hero.template.ConfigurableTemplateLoader;
import net.sf.anathema.hero.template.TemplateFactory;
import net.sf.anathema.hero.template.TemplateLoader;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

public class CasteTemplateLoader {

  public static CasteTemplate loadTemplate(TemplateFactory templateFactory, String templateName) {
    Identifier templateId = new SimpleIdentifier(templateName);
    TemplateLoader<CasteTemplate> loader = new ConfigurableTemplateLoader<>(CasteTemplate.class);
    return templateFactory.loadModelTemplate(templateId, loader);
  }
}