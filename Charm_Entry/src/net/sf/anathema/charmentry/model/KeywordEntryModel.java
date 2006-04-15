package net.sf.anathema.charmentry.model;

import net.sf.anathema.character.generic.impl.magic.CharmAttribute;
import net.sf.anathema.character.generic.magic.charms.ICharmAttribute;
import net.sf.anathema.character.library.removableentry.model.AbstractRemovableEntryModel;
import net.sf.anathema.lib.util.IIdentificate;

public class KeywordEntryModel extends AbstractRemovableEntryModel<ICharmAttribute> implements IKeywordEntryModel {

  private IIdentificate keyword;

  @Override
  protected ICharmAttribute createEntry() {
    return new CharmAttribute(keyword.getId(), true);
  }

  @Override
  public ICharmAttribute commitSelection() {
    ICharmAttribute charmAttribute = super.commitSelection();
    fireEntryChanged();
    return charmAttribute;
  }

  @Override
  protected boolean isEntryAllowed() {
    if (keyword == null) {
      return false;
    }
    for (ICharmAttribute attribute : getEntries()) {
      if (attribute.getId() == keyword.getId()) {
        return false;
      }
    }
    return true;
  }

  public void setCurrentKeyword(IIdentificate keyword) {
    this.keyword = keyword;
    fireEntryChanged();
  }

  public void clear() {
    for (ICharmAttribute entry : getEntries()) {
      removeEntry(entry);
    }
  }
}