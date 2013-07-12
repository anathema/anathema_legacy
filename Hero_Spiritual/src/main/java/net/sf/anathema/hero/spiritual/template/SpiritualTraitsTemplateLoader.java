package net.sf.anathema.hero.spiritual.template;

import net.sf.anathema.hero.template.ConfigurableTemplateLoader;
import net.sf.anathema.hero.template.TemplateFactory;
import net.sf.anathema.hero.template.TemplateLoader;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

public class SpiritualTraitsTemplateLoader {

  public static SpiritualTraitsTemplate loadTemplate(TemplateFactory templateFactory, String templateName) {
    Identifier templateId = new SimpleIdentifier(templateName);
    TemplateLoader<SpiritualTraitsTemplate> loader = new ConfigurableTemplateLoader<>(SpiritualTraitsTemplate.class);
    return templateFactory.loadModelTemplate(templateId, loader);
  }
}