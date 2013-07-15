package net.sf.anathema.hero.traits.sheet.content;

public class BulletList {

  public final String header;
  public final String[] items;

  public BulletList(String header, String... items) {
    this.header = header;
    this.items = items;
  }
}
