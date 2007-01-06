package net.sf.anathema.character.generic.impl.additional;

import net.sf.anathema.character.generic.additionalrules.IAdditionalMagicLearnPool;
import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.magic.IMagicVisitor;
import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.generic.magic.spells.CircleType;
import net.sf.anathema.character.generic.traits.IGenericTrait;

public class GenericMagicLearnPool implements IAdditionalMagicLearnPool {

  private final IBackgroundTemplate template;

  public GenericMagicLearnPool(IBackgroundTemplate template) {
    this.template = template;
  }

  public int getAdditionalMagicCount(IGenericTraitCollection traitCollection) {
    IGenericTrait background = traitCollection.getTrait(template);
    if (background == null) {
      return 0;
    }
    return background.getCurrentValue();
  }

  public boolean isAllowedFor(IGenericTraitCollection characterAbstraction, IMagic magic) {
    final boolean[] isAllowed = new boolean[1];
    magic.accept(new IMagicVisitor() {
      public void visitSpell(ISpell spell) {
        if (spell.getCircleType() == CircleType.Terrestrial) {
          isAllowed[0] = true;
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
}