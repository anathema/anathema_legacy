package net.sf.anathema.character.library.virtueflaw.model;

import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.library.trait.IDefaultTrait;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;

public interface IVirtueFlaw {
  ITraitType getRoot();

  void setRoot(ITraitType root);

  ITextualDescription getName();

  boolean isFlawComplete();

  IDefaultTrait getLimitTrait();

  void addRootChangeListener(IChangeListener listener);
}