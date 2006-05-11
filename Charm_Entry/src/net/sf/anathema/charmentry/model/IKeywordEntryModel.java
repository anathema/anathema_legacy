package net.sf.anathema.charmentry.model;

import net.sf.anathema.character.generic.magic.charms.ICharmAttribute;
import net.sf.anathema.character.library.removableentry.presenter.IRemovableEntryModel;
import net.sf.anathema.lib.util.IIdentificate;

public interface IKeywordEntryModel extends IRemovableEntryModel<ICharmAttribute> {

  public void setCurrentKeyword(IIdentificate keyword);

  public void clear();

  public IIdentificate[] getKeywords();
}