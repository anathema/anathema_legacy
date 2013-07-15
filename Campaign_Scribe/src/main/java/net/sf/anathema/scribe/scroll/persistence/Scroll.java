package net.sf.anathema.scribe.scroll.persistence;

public class Scroll {

  public final ScrollDto dto;
  public final RepositoryId repositoryId;

  public Scroll(ScrollDto dto, RepositoryId repositoryId) {
    this.dto = dto;
    this.repositoryId = repositoryId;
  }
}
