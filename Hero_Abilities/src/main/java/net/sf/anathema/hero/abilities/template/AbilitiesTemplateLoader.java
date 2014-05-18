package net.sf.anathema.hero.abilities.template;

import net.sf.anathema.hero.template.ConfigurableTemplateLoader;
import net.sf.anathema.hero.template.TemplateFactory;
import net.sf.anathema.hero.template.TemplateLoader;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

public class AbilitiesTemplateLoader {

  public static AbilitiesTemplate loadTemplate(TemplateFactory templateFactory, String templateName) {
    Identifier templateId = new SimpleIdentifier(templateName);
    TemplateLoader<AbilitiesTemplate> loader = new ConfigurableTemplateLoader<>(AbilitiesTemplate.class);
    return templateFactory.loadModelTemplate(templateId, loader);
  }
}