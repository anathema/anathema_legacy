package net.sf.anathema.hero.framework.perspective.model;

import net.sf.anathema.framework.repository.access.printname.RepositoryId;

public class CharacterReference {

  public final RepositoryId repositoryId;
  public final String printName;

  public CharacterReference(RepositoryId repositoryId, String printName) {
    this.repositoryId = repositoryId;
    this.printName = printName;
  }
}