package net.sf.anathema.character.library.trait.subtrait;

public interface ISubTraitListener {

  void subTraitValueChanged();

  void subTraitAdded(ISubTrait subTrait);

  void subTraitRemoved(ISubTrait subTrait);
}