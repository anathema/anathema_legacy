package net.sf.anathema.scribe.scroll.persistence;

public class ScrollReference {

  public final RepositoryId repositoryId;
  public final String printName;

  public ScrollReference(RepositoryId repositoryId, String printName) {
    this.repositoryId = repositoryId;
    this.printName = printName;
  }
}
