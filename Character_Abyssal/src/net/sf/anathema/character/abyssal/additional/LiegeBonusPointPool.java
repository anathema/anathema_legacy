package net.sf.anathema.character.abyssal.additional;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.abyssal.AbyssalCharacterModule;
import net.sf.anathema.character.generic.additionalrules.IAdditionalBonusPointPool;
import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.framework.module.BasicExaltCharacterModule;
import net.sf.anathema.character.generic.framework.module.IBackgroundIds;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.AbstractTraitTypeVisitor;

public class LiegeBonusPointPool implements IAdditionalBonusPointPool {

  private final IBackgroundTemplate liegeTemplate;

  public LiegeBonusPointPool(IBackgroundTemplate liegeTemplate) {
    this.liegeTemplate = liegeTemplate;
  }

  private List<String> allowedBackgroundIds = new ArrayList<String>() {
    {
      add(AbyssalCharacterModule.BACKGROUND_ID_ABYSSAL_COMMAND);
      add(BasicExaltCharacterModule.BACKGROUND_ID_ARTIFACT);
      add(IBackgroundIds.BACKGROUND_ID_FOLLOWERS);
      add(IBackgroundIds.BACKGROUND_ID_INFLUENCE);
      add(BasicExaltCharacterModule.BACKGROUND_ID_MANSE);
      add(AbyssalCharacterModule.BACKGROUND_ID_NECROMANCY);
      add(IBackgroundIds.BACKGROUND_ID_RESOURCES);
      add(AbyssalCharacterModule.BACKGROUND_ID_UNDERWORLD_MANSE);
      add(AbyssalCharacterModule.BACKGROUND_ID_WHISPERS);
    }
  };

  public int getAmount(IGenericTraitCollection traitCollection) {
    IGenericTrait liegeBackground = traitCollection.getTrait(liegeTemplate);
    if (liegeBackground == null || liegeBackground.getCurrentValue() == 0) {
      return 0;
    }
    int currentValue = liegeBackground.getCurrentValue();
    if (currentValue < 4) {
      return currentValue * 2;
    }
    return (currentValue) * 3 - 3;
  }

  public boolean isAllowedForTrait(final IGenericTraitCollection traitCollection, final IGenericTrait trait) {
    final boolean[] mayBeSpent = new boolean[1];
    trait.getType().accept(new AbstractTraitTypeVisitor() {
      @Override
      public void visitBackground(IBackgroundTemplate template) {
        mayBeSpent[0] = allowedBackgroundIds.contains(template.getId());
      }

      @Override
      public void visitAbility(AbilityType type) {
        mayBeSpent[0] = traitCollection.isFavoredOrCasteTrait(type);
      }

    });
    return mayBeSpent[0];
  }

  public boolean isAllowedForMagic(IGenericTraitCollection traitCollection, IMagic magic) {
    return true;
  }
}