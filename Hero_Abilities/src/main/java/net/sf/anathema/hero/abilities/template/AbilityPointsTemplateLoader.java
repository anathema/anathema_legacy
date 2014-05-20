package net.sf.anathema.hero.abilities.template;

import net.sf.anathema.hero.template.ConfigurableTemplateLoader;
import net.sf.anathema.hero.template.TemplateFactory;
import net.sf.anathema.hero.template.TemplateLoader;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

public class AbilityPointsTemplateLoader {

  public static AbilityPointsTemplate loadTemplate(TemplateFactory templateFactory, String templateName) {
    Identifier templateId = new SimpleIdentifier(templateName);
    TemplateLoader<AbilityPointsTemplate> loader = new ConfigurableTemplateLoader<>(AbilityPointsTemplate.class);
    return templateFactory.loadModelTemplate(templateId, loader);
  }
}