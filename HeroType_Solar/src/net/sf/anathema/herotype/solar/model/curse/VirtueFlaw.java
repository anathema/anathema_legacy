package net.sf.anathema.herotype.solar.model.curse;

import net.sf.anathema.character.main.library.trait.Trait;
import net.sf.anathema.character.main.traits.TraitType;
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