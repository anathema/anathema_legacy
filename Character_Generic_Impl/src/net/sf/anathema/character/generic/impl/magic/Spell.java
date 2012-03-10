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
import net.sf.anathema.character.generic.template.magic.IFavoringTraitTypeVisitor;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.lib.util.Identificate;

public class Spell extends Identificate implements ISpell {
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
    final ITraitType[] spellFavoringType = new ITraitType[1];
    basicCharacter.getCharacterType().getFavoringTraitType().accept(new IFavoringTraitTypeVisitor() {
      @Override
      public void visitAbilityType(FavoringTraitType visitedType) {
        spellFavoringType[0] = AbilityType.Occult;
      }

      @Override
      public void visitAttributeType(FavoringTraitType visitedType) {
        spellFavoringType[0] = AttributeType.Intelligence;
      }

      @Override
      public void visitYoziType(FavoringTraitType visitedType) {
        spellFavoringType[0] = AbilityType.Occult;
      }

      @Override
      public void visitVirtueType(FavoringTraitType visitedType) {
        // ghosts don't get sorcery?
      }
    });
    return traitCollection.getFavorableTrait(spellFavoringType[0]).isCasteOrFavored();
  }
}