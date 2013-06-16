package net.sf.anathema.character.generic.impl.magic;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.magic.IMagicVisitor;
import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.generic.magic.general.ICostList;
import net.sf.anathema.character.generic.magic.general.ISourceList;
import net.sf.anathema.character.generic.magic.spells.CircleType;
import net.sf.anathema.character.generic.rules.IExaltedSourceBook;
import net.sf.anathema.character.generic.template.magic.FavoringTraitType;
import net.sf.anathema.character.generic.traits.TraitType;
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
  public void accept(IMagicVisitor visitor) {
    visitor.visitSpell(this);
  }

  @Override
  public boolean isFavored(IBasicCharacterData basicCharacter, IGenericTraitCollection traitCollection) {
    FavoringTraitType type = basicCharacter.getCharacterType().getFavoringTraitType();
    TraitType spellFavoringType = type.getSpellFavoringType();
    return traitCollection.getTrait(spellFavoringType).isCasteOrFavored();
  }
}