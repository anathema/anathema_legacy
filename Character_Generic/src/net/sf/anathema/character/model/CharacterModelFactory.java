package net.sf.anathema.character.model;

public interface CharacterModelFactory {

  <M extends CharacterModel> M create(ModelCreationContext context, Hero hero);
}
