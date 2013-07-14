package net.sf.anathema.hero.charms.template;

import net.sf.anathema.hero.template.ConfigurableTemplateLoader;
import net.sf.anathema.hero.template.TemplateFactory;
import net.sf.anathema.hero.template.TemplateLoader;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

public class CharmTemplateLoader {

  public static CharmTto loadTemplate(TemplateFactory templateFactory, String templateName) {
    Identifier templateId = new SimpleIdentifier(templateName);
    TemplateLoader<CharmTto> loader = new ConfigurableTemplateLoader<>(CharmTto.class);
    return templateFactory.loadModelTemplate(templateId, loader);
  }
}