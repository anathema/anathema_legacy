package net.sf.anathema.hero.othertraits.template;

import net.sf.anathema.hero.template.ConfigurableTemplateLoader;
import net.sf.anathema.hero.template.TemplateFactory;
import net.sf.anathema.hero.template.TemplateLoader;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

public class OtherTraitsTemplateLoader {

  public static OtherTraitsTemplate loadTemplate(TemplateFactory templateFactory, String templateName) {
    Identifier templateId = new SimpleIdentifier(templateName);
    TemplateLoader<OtherTraitsTemplate> loader = new ConfigurableTemplateLoader<>(OtherTraitsTemplate.class);
    return templateFactory.loadModelTemplate(templateId, loader);
  }
}