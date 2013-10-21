package net.sf.anathema.character.main.framework.item;

import net.sf.anathema.hero.description.HeroDescription;
import net.sf.anathema.hero.description.HeroDescriptionFetcher;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;

public class HeroNameFetcher {

  public String getName(Hero hero) {
    HeroDescription heroDescription = HeroDescriptionFetcher.fetch(hero);
    ITextualDescription nameDescription = heroDescription.getName();
    return nameDescription.getText();
  }
}