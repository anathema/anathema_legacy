package net.sf.anathema.hero.traits.template;

import net.sf.anathema.hero.template.ConfigurableTemplateLoader;
import net.sf.anathema.hero.template.TemplateFactory;
import net.sf.anathema.hero.template.TemplateLoader;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

public class GroupedTraitsTemplateLoader {

  public static GroupedTraitsTemplate loadTemplate(TemplateFactory templateFactory, String templateName) {
    Identifier templateId = new SimpleIdentifier(templateName);
    TemplateLoader<GroupedTraitsTemplate> loader = new ConfigurableTemplateLoader<>(GroupedTraitsTemplate.class);
    return templateFactory.loadModelTemplate(templateId, loader);
  }
}