package net.sf.anathema.character.main.framework.item;

import net.sf.anathema.character.main.itemtype.CharacterItemTypeRetrieval;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.repository.RepositoryIdData;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.lang.StringUtilities;

public class HeroRepositoryData implements RepositoryIdData {

  private Hero hero;

  public HeroRepositoryData(Hero hero) {
    this.hero = hero;
  }

  @Override
  public String getIdProposal() {
    String name = new HeroNameFetcher().getName(hero);
    return StringUtilities.getFileNameRepresentation(name);
  }

  @Override
  public IItemType getItemType() {
    return CharacterItemTypeRetrieval.retrieveCharacterItemType();
  }
}