package net.sf.anathema.character.reporting.pdf.content.general;

public class BulletList {

  public final String header;
  public final String[] items;

  public BulletList(String header, String... items) {
    this.header = header;
    this.items = items;
  }
}
