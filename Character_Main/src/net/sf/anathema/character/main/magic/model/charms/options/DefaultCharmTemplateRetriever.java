package net.sf.anathema.character.main.magic.model.charms.options;

import net.sf.anathema.character.main.template.HeroTemplate;
import net.sf.anathema.character.main.template.ITemplateRegistry;
import net.sf.anathema.character.main.template.magic.CharmTemplate;
import net.sf.anathema.character.main.type.CharacterType;
import net.sf.anathema.hero.model.Hero;

public class DefaultCharmTemplateRetriever implements CharmTemplateRetriever {

  private ITemplateRegistry registry;

  public DefaultCharmTemplateRetriever(ITemplateRegistry registry) {
    this.registry = registry;
  }

  public CharmTemplate getCharmTemplate(CharacterType type) {
    HeroTemplate defaultTemplate = registry.getDefaultTemplate(type);
    if (defaultTemplate == null) {
      return null;
    }
    return getCharmTemplate(defaultTemplate);
  }

  public static CharmTemplate getNativeTemplate(Hero hero) {
    HeroTemplate template = hero.getTemplate();
    return getCharmTemplate(template);
  }

  public static CharmTemplate getCharmTemplate(HeroTemplate defaultTemplate) {
    return defaultTemplate.getMagicTemplate().getCharmTemplate();
  }
}