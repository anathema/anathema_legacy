package net.sf.anathema.character.linguistics.presenter;

import net.sf.anathema.character.library.removableentry.presenter.IRemovableEntryModel;
import net.sf.anathema.lib.util.IIdentificate;

public interface ILinguisticsModel extends IRemovableEntryModel<IIdentificate> {

  public IIdentificate[] getPredefinedLanguages();

  public boolean isPredefinedLanguage(Object object);

  public IIdentificate getPredefinedLanguageById(String displayName);

  public void selectBarbarianLanguage(String customName);

  public void selectLanguage(IIdentificate language);

  public boolean isEntryAllowed();
}