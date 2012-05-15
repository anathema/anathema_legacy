package net.sf.anathema.character.linguistics.presenter;

import net.sf.anathema.character.library.removableentry.presenter.IRemovableEntryModel;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.util.IIdentificate;

public interface ILinguisticsModel extends IRemovableEntryModel<IIdentificate> {

  IIdentificate[] getPredefinedLanguages();

  boolean isPredefinedLanguage(Object object);

  IIdentificate getPredefinedLanguageById(String displayName);

  void selectBarbarianLanguage(String customName);

  void selectLanguage(IIdentificate language);

  boolean isEntryAllowed();

  int getPredefinedLanguageCount();

  int getBarbarianLanguageCount();

  int getLanguagePointsSpent();

  void addCharacterChangedListener(IChangeListener listener);

  int getLanguagePointsAllowed();
}