package net.sf.anathema.charmentry.model;

import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.character.generic.impl.rules.ExaltedSourceBook;
import net.sf.anathema.character.generic.rules.IExaltedSourceBook;
import net.sf.anathema.charmentry.model.data.IConfigurableCharmData;
import net.sf.anathema.charmentry.presenter.model.ISourceEntryModel;

public class SourceEntryModel implements ISourceEntryModel {

  private final IConfigurableCharmData charmData;

  public SourceEntryModel(IConfigurableCharmData charmData) {
    this.charmData = charmData;
  }

  public boolean enablePageSelection() {
    return !StringUtilities.isNullOrTrimEmpty(charmData.getSource().getId());
  }

  public IExaltedSourceBook[] getPredefinedSources() {
    return ExaltedSourceBook.values();
  }

  public void setSourceBook(IExaltedSourceBook sourceBook) {
    charmData.setSource(sourceBook);
  }

  public void setSourcePage(int newValue) {
    charmData.setPage(newValue);
  }
}