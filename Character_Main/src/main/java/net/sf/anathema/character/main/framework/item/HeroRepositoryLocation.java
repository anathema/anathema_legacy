package net.sf.anathema.character.main.framework.item;

import com.google.common.base.Preconditions;
import net.sf.anathema.character.main.itemtype.CharacterItemTypeRetrieval;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.hero.description.HeroDescription;
import net.sf.anathema.hero.description.HeroDescriptionFetcher;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.lang.StringUtilities;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;

public class HeroRepositoryLocation implements ItemRepositoryLocation {

  private final Hero hero;
  private String id;

  public HeroRepositoryLocation(Hero hero) {
    this.hero = hero;
  }

  @Override
  public String getIdProposal() {
    HeroDescription heroDescription = HeroDescriptionFetcher.fetch(hero);
    ITextualDescription nameDescription = heroDescription.getName();
    String name = nameDescription.getText();
    return StringUtilities.getFileNameRepresentation(name);
  }

  @Override
  public synchronized void setId(String id) {
    Preconditions.checkArgument(this.id == null, "Hero's id must not be changed.");
    this.id = id;
  }

  @Override
  public synchronized String getId() {
    return id;
  }

  @Override
  public IItemType getItemType() {
    return CharacterItemTypeRetrieval.retrieveCharacterItemType();
  }
}