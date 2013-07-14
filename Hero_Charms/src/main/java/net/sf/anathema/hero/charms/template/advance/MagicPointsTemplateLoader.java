package net.sf.anathema.hero.charms.template.advance;

import net.sf.anathema.hero.template.ConfigurableTemplateLoader;
import net.sf.anathema.hero.template.TemplateFactory;
import net.sf.anathema.hero.template.TemplateLoader;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

public class MagicPointsTemplateLoader {

  public static MagicPointsTemplate loadTemplate(TemplateFactory templateFactory, String templateName) {
    Identifier templateId = new SimpleIdentifier(templateName);
    TemplateLoader<MagicPointsTemplate> loader = new ConfigurableTemplateLoader<>(MagicPointsTemplate.class);
    return templateFactory.loadModelTemplate(templateId, loader);
  }
}