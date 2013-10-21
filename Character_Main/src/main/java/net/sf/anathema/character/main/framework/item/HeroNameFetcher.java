package net.sf.anathema.character.main.framework.item;

import net.sf.anathema.hero.description.HeroDescription;
import net.sf.anathema.hero.description.HeroDescriptionFetcher;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;

public class HeroNameFetcher {

  public String getName(Hero hero) {
    HeroDescription heroDescription = HeroDescriptionFetcher.fetch(hero);
    if (heroDescription == HeroDescriptionFetcher.NOT_REGISTERED){
      return CharacterItem.DEFAULT_PRINT_NAME;
    }
    ITextualDescription nameDescription = heroDescription.getName();
    if (nameDescription.isEmpty()){
      return CharacterItem.DEFAULT_PRINT_NAME;
    }
    return nameDescription.getText();
  }
}