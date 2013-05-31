package net.sf.anathema.character.equipment.impl.module;

public interface RepositoryItemInitializationStrategy {
  String getItemPattern();

  String getMessageKey();

  boolean shouldInitializeData();

  void storeInRepository(String itemJSON);
}