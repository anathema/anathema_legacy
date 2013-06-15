package net.sf.anathema.character.linguistics.presenter;

import net.sf.anathema.character.library.removableentry.presenter.IRemovableEntryModel;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.util.Identifier;

public interface ILinguisticsModel extends IRemovableEntryModel<Identifier> {

  Identifier[] getPredefinedLanguages();

  boolean isPredefinedLanguage(Object object);

  Identifier getPredefinedLanguageById(String displayName);

  void selectBarbarianLanguage(String customName);

  void selectLanguage(Identifier language);

  boolean isEntryAllowed();

  int getPredefinedLanguageCount();

  int getBarbarianLanguageCount();

  int getLanguagePointsSpent();

  void addCharacterChangedListener(IChangeListener listener);

  int getLanguagePointsAllowed();
}