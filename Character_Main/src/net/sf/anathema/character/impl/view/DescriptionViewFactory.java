package net.sf.anathema.character.impl.view;

public class DescriptionViewFactory implements SubViewFactory {
  @Override
  public <T> T create() {
    return (T) new CharacterDescriptionView();
  }
}
