package net.sf.anathema.initialization.initialitems;

public interface RepositoryItemInitializationStrategy {
  String getItemPattern();

  String getMessageKey();

  boolean shouldInitializeData();

  void storeInRepository(String itemJSON);
}