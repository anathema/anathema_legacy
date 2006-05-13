package net.sf.anathema.charmentry.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.charmentry.model.data.IConfigurableCharmData;
import net.sf.anathema.charmentry.presenter.model.ISourceEntryModel;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

public class SourceEntryModel implements ISourceEntryModel {

  private final IConfigurableCharmData charmData;

  public SourceEntryModel(IConfigurableCharmData charmData) {
    this.charmData = charmData;
  }

  public boolean enablePageSelection() {
    return !StringUtilities.isNullOrTrimEmpty(charmData.getSource().getSource());
  }

  public IIdentificate[] getPredefinedSources() {
    List<IIdentificate> sources = new ArrayList<IIdentificate>();
    Collections.addAll(sources, ExaltedSourceBooks.values());
    sources.add(new Identificate("Custom"));
    return sources.toArray(new IIdentificate[sources.size()]);
  }

  public void setSourceBook(IIdentificate identificate) {
    charmData.getSource().setSource(identificate.getId());
  }

  public void setSourcePage(int newValue) {
    charmData.getSource().setPage(newValue);
  }
}