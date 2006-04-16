package net.sf.anathema.character.db.additional;

import net.sf.anathema.character.generic.additionalrules.IAdditionalMagicLearnPool;
import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.magic.IMagicVisitor;
import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.generic.magic.spells.CircleType;
import net.sf.anathema.character.generic.traits.IGenericTrait;

public abstract class AbstractSorceryLearnPool implements IAdditionalMagicLearnPool {

  private final IBackgroundTemplate sorceryTemplate;

  public AbstractSorceryLearnPool(IBackgroundTemplate sorceryTemplate) {
    this.sorceryTemplate = sorceryTemplate;
  }

  public int getAdditionalMagicCount(IGenericCharacter traitCollection) {
    IGenericTrait sorceryBackground = traitCollection.getTrait(sorceryTemplate);
    if (sorceryBackground == null) {
      return 0;
    }
    return sorceryBackground.getCurrentValue();
  }

  public boolean isAllowedFor(IGenericCharacter character, IMagic magic) {
    final boolean[] isAllowed = new boolean[1];
    magic.accept(new IMagicVisitor() {
      public void visitSpell(ISpell spell) {
        if (spell.getCircleType() == CircleType.Terrestrial) {
          isAllowed[0] = isTerrestrialSpellAllowed(spell);
        }
        else {
          isAllowed[0] = false;
        }
      }

      public void visitCharm(ICharm charm) {
        isAllowed[0] = false;
      }
    });
    return isAllowed[0];
  }

  protected abstract boolean isTerrestrialSpellAllowed(ISpell spell);
}