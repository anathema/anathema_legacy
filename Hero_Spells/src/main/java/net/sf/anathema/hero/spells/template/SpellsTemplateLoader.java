package net.sf.anathema.hero.spells.template;

import net.sf.anathema.hero.template.ConfigurableTemplateLoader;
import net.sf.anathema.hero.template.TemplateFactory;
import net.sf.anathema.hero.template.TemplateLoader;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

public class SpellsTemplateLoader {

  public static SpellsTemplate loadTemplate(TemplateFactory templateFactory, String templateName) {
    Identifier templateId = new SimpleIdentifier(templateName);
    TemplateLoader<SpellsTemplate> loader = new ConfigurableTemplateLoader<>(SpellsTemplate.class);
    return templateFactory.loadModelTemplate(templateId, loader);
  }
}