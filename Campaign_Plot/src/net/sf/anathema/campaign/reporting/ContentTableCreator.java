package net.sf.anathema.campaign.reporting;

import java.util.HashMap;
import java.util.Map;

import com.lowagie.text.Section;

public class ContentTableCreator {

  private final Map<Section, Integer> tableOfContents = new HashMap<Section, Integer>();
  private int page;

  public void setCurrentPage(int page) {
    this.page = page;
  }

  public void addSection(Section section) {
    tableOfContents.put(section, page);
  }

  public int getPage(Section section) {
    return tableOfContents.get(section);
  }
}