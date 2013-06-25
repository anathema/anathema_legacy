package net.sf.anathema.character.library.virtueflaw.model;

import net.sf.anathema.character.generic.traits.TraitType;
import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;

public interface VirtueFlaw {
  TraitType getRoot();

  void setRoot(TraitType root);

  ITextualDescription getName();

  boolean isFlawComplete();

  Trait getLimitTrait();

  void addRootChangeListener(ChangeListener listener);
}