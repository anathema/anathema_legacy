package net.sf.anathema.character.db.additional;

import net.sf.anathema.character.db.DbCharacterModule;
import net.sf.anathema.character.generic.additionalrules.ITraitCostModifier;
import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.framework.xml.rules.TraitTypeAdapter;
import net.sf.anathema.character.generic.impl.additional.DefaultTraitCostModifier;
import net.sf.anathema.character.generic.traits.ITraitType;

public class AdditionalOutcasteDbRules extends AdditionalDbRules {

  @Override
  public ITraitCostModifier getCostModifier(ITraitType type) {
    final ITraitCostModifier[] modifier = new ITraitCostModifier[] { super.getCostModifier(type) };
    type.accept(new TraitTypeAdapter() {
      @Override
      public void visitBackground(IBackgroundTemplate template) {
        if (template.getId().equals(DbCharacterModule.BACKGROUND_ID_BREEDING)) {
          modifier[0] = new DefaultTraitCostModifier() {

            @Override
            public int getAdditionalDotsToSpend(int traitValue) {
              return traitValue;
            }
          };
        }
      }
    });
    return modifier[0];
  }
}