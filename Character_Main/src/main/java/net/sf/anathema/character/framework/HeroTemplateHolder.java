package net.sf.anathema.character.framework;

import net.sf.anathema.hero.template.HeroTemplate;

public class HeroTemplateHolder {

  private HeroTemplate template;

  public HeroTemplate getTemplate() {
    return template;
  }

  public void setTemplate(HeroTemplate template) {
    this.template = template;
  }

  public boolean isCurrentlySelected(HeroTemplate newValue) {
    return newValue == template;
  }
}