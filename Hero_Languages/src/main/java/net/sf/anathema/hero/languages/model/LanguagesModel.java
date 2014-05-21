package net.sf.anathema.hero.languages.model;

import net.sf.anathema.character.framework.library.removableentry.RemovableEntryModel;
import net.sf.anathema.hero.model.HeroModel;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

public interface LanguagesModel extends RemovableEntryModel<Identifier>, HeroModel {

  Identifier ID = new SimpleIdentifier("Languages");

  Identifier[] getPredefinedLanguages();

  boolean isPredefinedLanguage(Object object);

  Identifier getPredefinedLanguageById(String displayName);

  void selectBarbarianLanguage(String customName);

  void selectLanguage(Identifier language);

  boolean isEntryAllowed();

  int getPredefinedLanguageCount();

  int getBarbarianLanguageCount();

  int getLanguagePointsSpent();

  void addCharacterChangedListener(ChangeListener listener);

  int getLanguagePointsAllowed();

  Identifier getSelectedEntry();
}