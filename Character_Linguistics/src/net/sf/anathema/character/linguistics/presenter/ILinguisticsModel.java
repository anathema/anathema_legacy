package net.sf.anathema.character.linguistics.presenter;

import net.sf.anathema.character.library.removableentry.presenter.IRemovableEntryModel;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.util.Identified;

public interface ILinguisticsModel extends IRemovableEntryModel<Identified> {

  Identified[] getPredefinedLanguages();

  boolean isPredefinedLanguage(Object object);

  Identified getPredefinedLanguageById(String displayName);

  void selectBarbarianLanguage(String customName);

  void selectLanguage(Identified language);

  boolean isEntryAllowed();

  int getPredefinedLanguageCount();

  int getBarbarianLanguageCount();

  int getLanguagePointsSpent();

  void addCharacterChangedListener(IChangeListener listener);

  int getLanguagePointsAllowed();
}