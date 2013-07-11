package net.sf.anathema.character.main.magic.model.spells;

import net.sf.anathema.character.main.magic.model.magic.ICostList;
import net.sf.anathema.character.main.magic.model.magic.ISourceList;
import net.sf.anathema.character.main.magic.parser.magic.IExaltedSourceBook;
import net.sf.anathema.character.main.template.magic.FavoringTraitType;
import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.traits.TraitModelFetcher;
import net.sf.anathema.lib.util.SimpleIdentifier;

public class Spell extends SimpleIdentifier implements ISpell {
  private final CircleType circleType;
  private final ICostList temporaryCost;
  private ISourceList source;
  private final String target;

  public Spell(String id, CircleType circleType, ICostList temporaryCost, ISourceList source, String target) {
    super(id);
    this.circleType = circleType;
    this.temporaryCost = temporaryCost;
    this.source = source;
    this.target = target;
  }

  @Override
  public String getTarget() {
    return target;
  }

  @Override
  public CircleType getCircleType() {
    return circleType;
  }

  @Override
  public IExaltedSourceBook[] getSources() {
    return new IExaltedSourceBook[]{source.getPrimarySource()};
  }

  @Override
  public IExaltedSourceBook getPrimarySource() {
    return source.getPrimarySource();
  }

  @Override
  public ICostList getTemporaryCost() {
    return temporaryCost;
  }

  @Override
  public boolean isFavored(Hero hero) {
    FavoringTraitType type = hero.getTemplate().getTemplateType().getCharacterType().getFavoringTraitType();
    TraitType spellFavoringType = type.getSpellFavoringType();
    return TraitModelFetcher.fetch(hero).getTrait(spellFavoringType).isCasteOrFavored();
  }
}