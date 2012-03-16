package net.sf.anathema.character.abyssal.additional;

import net.sf.anathema.character.generic.additionalrules.IAdditionalMagicLearnPool;
import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.impl.additional.DefaultAdditionalRules;
import net.sf.anathema.character.generic.impl.additional.GenericMagicLearnPool;
import net.sf.anathema.character.generic.magic.spells.CircleType;
import net.sf.anathema.character.generic.util.IPointModification;

public class AdditionalAbyssalRules extends DefaultAdditionalRules {

  public AdditionalAbyssalRules(String... rejectedBackgrounds) {
    super(new AbyssalAdditionalTraitRules(), rejectedBackgrounds);
  }

  public void addNecromancyRules(IBackgroundTemplate necromancyTemplate) {
    addMagicLearnPool(getPool(necromancyTemplate));
  }

  private IAdditionalMagicLearnPool getPool(IBackgroundTemplate necromancyTemplate) {
    GenericMagicLearnPool pool = new GenericMagicLearnPool(necromancyTemplate, true);
    pool.setMaximumCircle(CircleType.Labyrinth);
    pool.attachCondition(CircleType.Labyrinth, 4);
    pool.setCostModification(new IPointModification() {
      @Override
      public int getAdditionalPoints(int value) {
        return Math.max(0, value - 3);
      }
    });
    return pool;
  }
}