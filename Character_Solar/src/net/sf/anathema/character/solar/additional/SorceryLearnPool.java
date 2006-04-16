package net.sf.anathema.character.solar.additional;

import net.sf.anathema.character.generic.additionalrules.IAdditionalMagicLearnPool;
import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.magic.IMagicVisitor;
import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.generic.magic.spells.CircleType;
import net.sf.anathema.character.generic.traits.IGenericTrait;

public class SorceryLearnPool implements IAdditionalMagicLearnPool {

  private final IBackgroundTemplate sorceryTemplate;

  public SorceryLearnPool(IBackgroundTemplate sorceryTemplate) {
    this.sorceryTemplate = sorceryTemplate;
  }

  public boolean isAllowedFor(IGenericCharacter traitCollection, IMagic magic) {
    final IGenericTrait necromancyBackground = traitCollection.getTrait(sorceryTemplate);
    final boolean[] isAllowed = new boolean[1];
    magic.accept(new IMagicVisitor() {
      public void visitSpell(ISpell spell) {
        if (spell.getCircleType() == CircleType.Terrestrial) {
          isAllowed[0] = true;
        }
        else if (spell.getCircleType() == CircleType.Celestial) {
          isAllowed[0] = necromancyBackground.getCurrentValue() > 3;
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

  public int getAdditionalMagicCount(IGenericCharacter traitCollection) {
    IGenericTrait necromancyBackground = traitCollection.getTrait(sorceryTemplate);
    if (necromancyBackground == null) {
      return 0;
    }
    int currentValue = necromancyBackground.getCurrentValue();
    if (currentValue < 4) {
      return currentValue;
    }
    return (currentValue - 3) * 2 + 3;
  }
}