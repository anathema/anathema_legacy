package net.sf.anathema.hero.intimacies.model;

import net.sf.anathema.character.main.library.selection.IStringEntryTraitModel;
import net.sf.anathema.hero.model.HeroModel;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

public interface IntimaciesModel extends IStringEntryTraitModel<Intimacy>, HeroModel {

  Identifier ID = new SimpleIdentifier("Intimacies");

  int getFreeIntimacies();

  int getCompletionValue();

  int getIntimaciesLimit();

  void addModelChangeListener(ChangeListener listener);

  boolean isCharacterExperienced();
}