package net.sf.anathema.character.magic.spells;

import net.sf.anathema.character.magic.basic.AbstractMagic;
import net.sf.anathema.character.magic.basic.cost.ICostList;
import net.sf.anathema.character.magic.basic.source.ISourceList;
import net.sf.anathema.character.magic.basic.source.SourceBook;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.spells.model.SpellsModelFetcher;
import net.sf.anathema.hero.traits.model.TraitModelFetcher;
import net.sf.anathema.hero.traits.model.TraitType;

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
    TraitType traitType = SpellsModelFetcher.fetch(hero).getFavoringTraitType();
    return TraitModelFetcher.fetch(hero).getTrait(traitType).isCasteOrFavored();
  }
}