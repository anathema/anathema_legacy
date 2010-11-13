package net.sf.anathema.campaign.reporting;

public class ContentEntry {

  private final String title;
  private final int page;

  public ContentEntry(String title, int page) {
    this.title = title;
    this.page = page;
  }

  public String getText() {
    return title;
  }

  public String getPageAsString() {
    return String.valueOf(page);
  }
}