package net.sf.anathema.hero.framework.perspective;

import net.sf.anathema.character.main.framework.item.HeroNameFetcher;
import net.sf.anathema.character.main.framework.item.Item;
import net.sf.anathema.character.main.template.ITemplateType;
import net.sf.anathema.hero.concept.HeroConceptFetcher;
import net.sf.anathema.hero.framework.perspective.model.CharacterIdentifier;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.util.Identifier;

public class LoadedDescriptiveFeatures implements DescriptiveFeatures {

  private CharacterIdentifier identifier;
  private Item characterItem;

  public LoadedDescriptiveFeatures(CharacterIdentifier identifier, Item characterItem) {
    this.identifier = identifier;
    this.characterItem = characterItem;
  }

  @Override
  public String getPrintName() {
    return new HeroNameFetcher().getName(getHero());
  }

  @Override
  public CharacterIdentifier getIdentifier() {
    return identifier;
  }

  @Override
  public ITemplateType getTemplateType() {
    Hero hero = getHero();
    return hero.getTemplate().getTemplateType();
  }

  @Override
  public Identifier getCasteType() {
    Hero hero = getHero();
    return HeroConceptFetcher.fetch(hero).getCaste().getType();
  }

  @Override
  public boolean isDirty() {
    return characterItem.getChangeManagement().isDirty();
  }

  private Hero getHero() {
    return (Hero) characterItem.getItemData();
  }
}
