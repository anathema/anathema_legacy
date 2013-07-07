package net.sf.anathema.hero.intimacies.model;

import net.sf.anathema.character.main.library.removableentry.IRemovableEntryModel;
import net.sf.anathema.hero.change.FlavoredChangeListener;
import net.sf.anathema.hero.model.HeroModel;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

public interface IntimaciesModel extends IRemovableEntryModel<Intimacy>, HeroModel {

  Identifier ID = new SimpleIdentifier("Intimacies");

  void setCurrentName(String newValue);

  void addChangeListener(FlavoredChangeListener listener);

  int getFreeIntimacies();

  int getCompletionValue();

  int getIntimaciesLimit();

  void addModelChangeListener(ChangeListener listener);

  boolean isCharacterExperienced();
}