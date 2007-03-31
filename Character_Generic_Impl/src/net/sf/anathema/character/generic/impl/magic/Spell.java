package net.sf.anathema.character.generic.impl.magic;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.magic.IMagicVisitor;
import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.generic.magic.general.ICostList;
import net.sf.anathema.character.generic.magic.general.IPermanentCostList;
import net.sf.anathema.character.generic.magic.general.ISourceList;
import net.sf.anathema.character.generic.magic.spells.CircleType;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
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
  private final IPermanentCostList permanentCost;
  private ISourceList source;
  private final String target;

  public Spell(
      String id,
      CircleType circleType,
      ICostList temporaryCost,
      IPermanentCostList permanentCost,
      ISourceList source,
      String target) {
    super(id);
    this.circleType = circleType;
    this.temporaryCost = temporaryCost;
    this.permanentCost = permanentCost;
    this.source = source;
    this.target = target;
  }

  public String getTarget() {
    return target;
  }

  public CircleType getCircleType() {
    return circleType;
  }

  public IPermanentCostList getPermanentCost() {
    return permanentCost;
  }

  public IExaltedSourceBook getSource() {
    return source.getPrimarySource();
  }

  public IExaltedSourceBook getSource(IExaltedEdition edition) {
    return source.getSource(edition);
  }

  public ICostList getTemporaryCost() {
    return temporaryCost;
  }

  public void accept(IMagicVisitor visitor) {
    visitor.visitSpell(this);
  }

  public boolean isFavored(IBasicCharacterData basicCharacter, IGenericTraitCollection traitCollection) {
    final ITraitType[] spellFavoringType = new ITraitType[1];
    basicCharacter.getCharacterType().getFavoringTraitType().accept(new IFavoringTraitTypeVisitor() {
      public void visitAbilityType(FavoringTraitType visitedType) {
        spellFavoringType[0] = AbilityType.Occult;
      }

      public void visitAttributeType(FavoringTraitType visitedType) {
        spellFavoringType[0] = AttributeType.Intelligence;
      }
    });
    return traitCollection.getFavorableTrait(spellFavoringType[0]).isCasteOrFavored();
  }
}