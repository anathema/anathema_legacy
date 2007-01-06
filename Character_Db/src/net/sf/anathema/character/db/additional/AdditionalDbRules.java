package net.sf.anathema.character.db.additional;

import net.sf.anathema.character.db.DbCharacterModule;
import net.sf.anathema.character.generic.additionalrules.ITraitCostModifier;
import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.framework.module.IBackgroundIds;
import net.sf.anathema.character.generic.impl.additional.AdditionalEssencePool;
import net.sf.anathema.character.generic.impl.additional.BackgroundPool;
import net.sf.anathema.character.generic.impl.additional.DefaultAdditionalRules;
import net.sf.anathema.character.generic.impl.additional.DefaultTraitCostModifier;
import net.sf.anathema.character.generic.traits.ITraitType;

public class AdditionalDbRules extends DefaultAdditionalRules {

  public AdditionalDbRules() {
    super(
        IBackgroundIds.BACKGROUND_ID_CONTACTS,
        IBackgroundIds.BACKGROUND_ID_INFLUENCE,
        IBackgroundIds.BACKGROUND_ID_FOLLOWERS);
  }

  public void addSorceryRules(IBackgroundTemplate sorceryTemplate) {
    addMagicLearnPool(new DefaultSorceryLearnPool(sorceryTemplate));
  }

  public void addBreedingRules(IBackgroundTemplate breedingTemplate) {
    AdditionalEssencePool peripheralPool = new AdditionalEssencePool(0);
    peripheralPool.setFixedValue(0, 0);
    peripheralPool.setFixedValue(1, 2);
    peripheralPool.setFixedValue(2, 3);
    peripheralPool.setFixedValue(3, 5);
    peripheralPool.setFixedValue(4, 7);
    peripheralPool.setFixedValue(5, 9);
    peripheralPool.setFixedValue(6, 11);
    addEssencePool(new BackgroundPool(breedingTemplate, new AdditionalEssencePool(1), peripheralPool));
  }

  @Override
  public ITraitCostModifier getCostModifier(ITraitType type) {
    final ITraitCostModifier[] modifier = new ITraitCostModifier[] { super.getCostModifier(type) };
    type.accept(new TraitTypeAdapter() {
      @Override
      public void visitBackground(IBackgroundTemplate template) {
        if (template.getId().equals(DbCharacterModule.BACKGROUND_ID_SORCERY)) {
          modifier[0] = new DefaultTraitCostModifier() {
            @Override
            public int getAdditionalBonusPointsToSpend(int traitValue) {
              return Math.max(0, (traitValue - 3) * 2);
            }
          };
        }
      }
    });
    return modifier[0];
  }
}