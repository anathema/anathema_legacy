package net.sf.anathema.character.main.magic.spells;

import net.sf.anathema.character.main.magic.basic.AbstractMagic;
import net.sf.anathema.character.main.magic.basic.cost.ICostList;
import net.sf.anathema.character.main.magic.basic.source.ISourceList;
import net.sf.anathema.character.main.magic.basic.source.SourceBook;
import net.sf.anathema.character.main.template.magic.FavoringTraitType;
import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.traits.TraitModelFetcher;

public class SpellImpl extends AbstractMagic implements Spell {
  private final CircleType circleType;
  private final ICostList temporaryCost;
  private ISourceList source;
  private final String target;

  public SpellImpl(String id, CircleType circleType, ICostList temporaryCost, ISourceList source, String target) {
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
  public SourceBook[] getSources() {
    return new SourceBook[]{source.getPrimarySource()};
  }

  @Override
  public SourceBook getPrimarySource() {
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