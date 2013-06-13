package net.sf.anathema.character.model;

public interface CharacterModelFactory {

  <M> M create(ModelCreationContext context, Character character);
}
