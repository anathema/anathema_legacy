package net.sf.anathema.character.linguistics.presenter;

import net.sf.anathema.character.library.removableentry.presenter.IRemovableEntryModel;
import net.sf.anathema.lib.util.IIdentificate;

public interface ILinguisticsModel extends IRemovableEntryModel<IIdentificate> {

  public IIdentificate[] getPredefinedLanguages();

  public boolean isPredefinedLanguage(Object object);

  public void languageSelected(IIdentificate identificate);

  public void barbarianLanguageSelected(String string);

}
