package net.sf.anathema.hero.concept.display.description;

import net.sf.anathema.hero.concept.HeroConcept;
import net.sf.anathema.hero.description.HeroDescription;

public class DescriptionDetails {
  private final HeroDescription description;
  private final HeroConcept heroConcept;
  private final boolean hasAnima;

  public DescriptionDetails(HeroDescription description, HeroConcept heroConcept, boolean hasAnima) {
    this.description = description;
    this.heroConcept = heroConcept;
    this.hasAnima = hasAnima;
  }

  public HeroDescription getDescription() {
    return description;
  }

  public HeroConcept getHeroConcept() {
    return heroConcept;
  }

  public boolean isHasAnima() {
    return hasAnima;
  }
}
