package net.sf.anathema.hero.specialties.display.presenter;

import net.sf.anathema.character.main.TraitTypeInternationalizer;
import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.lib.gui.AbstractUIConfiguration;

public class TraitTypeUiConfiguration extends AbstractUIConfiguration<TraitType> {
  private TraitTypeInternationalizer i18ner;

  TraitTypeUiConfiguration(TraitTypeInternationalizer i18ner) {
    this.i18ner = i18ner;
  }

  @Override
  protected String labelForExistingValue(TraitType value) {
    return i18ner.getScreenName(value);
  }
}
