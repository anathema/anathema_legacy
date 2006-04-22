package net.sf.anathema.campaign.reporting;

import java.util.ArrayList;
import java.util.List;

public class TableOfContents {

  private final List<ContentEntry> entryList = new ArrayList<ContentEntry>();

  public void addEntry(String entry, int page) {
    entryList.add(new ContentEntry(entry, page));
  }

  public ContentEntry[] getEntries() {
    return entryList.toArray(new ContentEntry[entryList.size()]);
  }
}