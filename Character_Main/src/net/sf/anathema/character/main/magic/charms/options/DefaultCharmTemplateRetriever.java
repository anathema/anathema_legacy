package net.sf.anathema.character.main.magic.charms.options;

import net.sf.anathema.character.main.template.HeroTemplate;
import net.sf.anathema.character.main.template.ITemplateRegistry;
import net.sf.anathema.character.main.template.magic.ICharmTemplate;
import net.sf.anathema.character.main.type.ICharacterType;
import net.sf.anathema.hero.model.Hero;

public class DefaultCharmTemplateRetriever implements CharmTemplateRetriever {

  private ITemplateRegistry registry;

  public DefaultCharmTemplateRetriever(ITemplateRegistry registry) {
    this.registry = registry;
  }

  public ICharmTemplate getCharmTemplate(ICharacterType type) {
    HeroTemplate defaultTemplate = registry.getDefaultTemplate(type);
    if (defaultTemplate == null) {
      return null;
    }
    return getCharmTemplate(defaultTemplate);
  }

  public static ICharmTemplate getNativeTemplate(Hero hero) {
    HeroTemplate template = hero.getTemplate();
    return getCharmTemplate(template);
  }

  public static ICharmTemplate getCharmTemplate(HeroTemplate defaultTemplate) {
    return defaultTemplate.getMagicTemplate().getCharmTemplate();
  }
}