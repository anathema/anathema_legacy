package net.sf.anathema.charmentry.model;

import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.character.generic.rules.IExaltedSourceBook;
import net.sf.anathema.charmentry.model.data.IConfigurableCharmData;
import net.sf.anathema.charmentry.presenter.model.ISourceEntryModel;

public class SourceEntryModel implements ISourceEntryModel {

  private final IConfigurableCharmData charmData;

  public SourceEntryModel(IConfigurableCharmData charmData) {
    this.charmData = charmData;
  }

  @Override
  public boolean enablePageSelection() {
    return !StringUtilities.isNullOrTrimmedEmpty(charmData.getPrimarySource().getId());
  }

  @Override
  public void setSourceBook(IExaltedSourceBook sourceBook) {
    charmData.setSource(sourceBook);
  }

  @Override
  public void setSourcePage(int newValue) {
    charmData.setPage(newValue);
  }

  @Override
  public IExaltedSourceBook[] getLegalSources() {
    return new IExaltedSourceBook[0];
  }
}