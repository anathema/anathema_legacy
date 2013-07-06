package net.sf.anathema.hero.specialties.display.presenter;

import net.sf.anathema.character.generic.framework.resources.TraitInternationalizer;
import net.sf.anathema.character.generic.traits.TraitType;
import net.sf.anathema.character.library.trait.specialties.DefaultTraitReference;

import java.util.Comparator;

public class TraitReferenceByNameComparator implements Comparator<TraitType> {

  private final TraitInternationalizer i18ner;

  public TraitReferenceByNameComparator(TraitInternationalizer i18ner) {
    this.i18ner = i18ner;
  }

  @Override
  public int compare(TraitType o1, TraitType o2) {
    String name1 = i18ner.getScreenName(new DefaultTraitReference(o1));
    String name2 = i18ner.getScreenName(new DefaultTraitReference(o2));
    return name1.compareToIgnoreCase(name2);
  }
}