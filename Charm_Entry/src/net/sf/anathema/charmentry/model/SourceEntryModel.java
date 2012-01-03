package net.sf.anathema.charmentry.model;

import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.character.generic.impl.rules.ExaltedSourceBook;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.rules.IExaltedSourceBook;
import net.sf.anathema.charmentry.model.data.IConfigurableCharmData;
import net.sf.anathema.charmentry.presenter.model.ISourceEntryModel;

public class SourceEntryModel implements ISourceEntryModel {

  private final IConfigurableCharmData charmData;

  public SourceEntryModel(IConfigurableCharmData charmData) {
    this.charmData = charmData;
  }

  public boolean enablePageSelection() {
    return !StringUtilities.isNullOrTrimmedEmpty(charmData.getSource().getId());
  }

  public void setSourceBook(IExaltedSourceBook sourceBook) {
    charmData.setSource(sourceBook);
  }

  public void setSourcePage(int newValue) {
    charmData.setPage(newValue);
  }

  public IExaltedSourceBook[] getLegalSources() {
    IExaltedEdition edition = charmData.getEdition();
    if (edition == null) {
      return ExaltedSourceBook.values();
    }
    return ExaltedSourceBook.getSourcesForEdition(edition);
  }
}