package net.sf.anathema.scribe.scroll.persistence;

public interface IdGenerator {

  String create(RepositoryId id, String printName);
}
