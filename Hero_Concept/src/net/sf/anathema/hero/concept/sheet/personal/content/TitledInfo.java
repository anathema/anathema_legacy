package net.sf.anathema.hero.concept.sheet.personal.content;

public class TitledInfo {

  public String title;
  public String info;
  public int columnCount;

  public TitledInfo(String title, String info) {
    this(title, info, 1);
  }

  public TitledInfo(String title, String info, int columnCount) {
    this.title = title;
    this.info = info;
    this.columnCount = columnCount;
  }
}
