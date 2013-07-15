package net.sf.anathema.scribe.scroll.persistence;

public class ScrollDto {

  public final String wikiText;
  public final String title;

  public ScrollDto(String title, String wikiText) {
    this.title = title;
    this.wikiText = wikiText;
  }
}
