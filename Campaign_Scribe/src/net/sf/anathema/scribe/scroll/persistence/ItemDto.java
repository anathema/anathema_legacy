package net.sf.anathema.scribe.scroll.persistence;

public class ItemDto {

  public final String repositoryId;
  public final String printName;

  public ItemDto(String repositoryId, String printName) {
    this.printName = printName;
    this.repositoryId = repositoryId;
  }
}
