package net.sf.anathema.charmentry.demo.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.sf.anathema.charmentry.demo.ISourceEntryModel;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

public class SourceEntryModel implements ISourceEntryModel {

  public boolean enablePageSelection() {
    // TODO Auto-generated method stub
    return false;
  }

  public IIdentificate[] getPredefinedSources() {
    List<IIdentificate> sources = new ArrayList<IIdentificate>();
    Collections.addAll(sources, ExaltedSourceBooks.values());
    sources.add(new Identificate("Custom"));
    return sources.toArray(new IIdentificate[sources.size()]);
  }

  public void setSourceBook(IIdentificate identificate) {
    // TODO Auto-generated method stub

  }

  public void setSourcePage(int newValue) {
    // TODO Auto-generated method stub

  }

}
