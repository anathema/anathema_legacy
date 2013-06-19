package net.sf.anathema.character.model.charm;

import net.sf.anathema.character.generic.health.HealthLevelType;
import net.sf.anathema.character.library.trait.DefaultTraitType;
import net.sf.anathema.character.library.trait.LimitedTrait;
import net.sf.anathema.character.library.trait.favorable.IncrementChecker;
import net.sf.anathema.character.main.hero.Hero;

import static net.sf.anathema.character.generic.impl.traits.SimpleTraitTemplate.createEssenceLimitedTemplate;

public class OxBodyCategory extends LimitedTrait {

  private final String id;
  private final HealthLevelType[] healthLevelTypes;

  public OxBodyCategory(Hero hero, HealthLevelType[] healthLevelTypes, String id, IncrementChecker incrementChecker) {
    super(hero, new DefaultTraitType("OxBodyTechnique"), createEssenceLimitedTemplate(0), incrementChecker);
    this.healthLevelTypes = healthLevelTypes;
    this.id = id;
  }

  public String getId() {
    return id;
  }

  public int getHealthLevelTypeCount(HealthLevelType type) {
    int count = 0;
    for (HealthLevelType categoryType : healthLevelTypes) {
      if (categoryType == type) {
        count++;
      }
    }
    return count;
  }
}