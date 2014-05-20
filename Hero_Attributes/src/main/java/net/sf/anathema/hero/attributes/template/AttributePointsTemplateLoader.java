package net.sf.anathema.hero.attributes.template;

import net.sf.anathema.hero.spiritual.template.EssencePoolTemplate;
import net.sf.anathema.hero.template.ConfigurableTemplateLoader;
import net.sf.anathema.hero.template.TemplateFactory;
import net.sf.anathema.hero.template.TemplateLoader;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

public class AttributePointsTemplateLoader {

  public static AttributePointsTemplate loadTemplate(TemplateFactory templateFactory, String templateName) {
    Identifier templateId = new SimpleIdentifier(templateName);
    TemplateLoader<AttributePointsTemplate> loader = new ConfigurableTemplateLoader<>(AttributePointsTemplate.class);
    return templateFactory.loadModelTemplate(templateId, loader);
  }
}