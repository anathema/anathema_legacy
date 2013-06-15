package net.sf.anathema.character.main.concept.model;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.ICharacterChangeListener;
import net.sf.anathema.character.impl.model.IntegerDescription;
import net.sf.anathema.character.impl.model.TypedDescription;
import net.sf.anathema.character.model.IIntegerDescription;
import net.sf.anathema.character.model.ITypedDescription;
import net.sf.anathema.lib.util.Identified;
import net.sf.anathema.lib.util.Identifier;

public class CharacterConceptImpl implements CharacterConcept {

  public static final Identified ID = new Identifier("Concept");
  private final ITypedDescription<ICasteType> caste = new TypedDescription<>(ICasteType.NULL_CASTE_TYPE);
  private final IIntegerDescription age = new IntegerDescription(0);

  @Override
  public ITypedDescription<ICasteType> getCaste() {
    return caste;
  }

  @Override
  public IIntegerDescription getAge() {
    return age;
  }

  @Override
  public Identified getId() {
    return ID;
  }

  @Override
  public void addChangeListener(ICharacterChangeListener changeListener) {
    caste.addChangeListener(changeListener);
    age.addChangeListener(changeListener);
  }
}