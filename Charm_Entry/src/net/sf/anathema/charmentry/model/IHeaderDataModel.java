package net.sf.anathema.charmentry.model;

import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.charmentry.presenter.model.ISourceEntryModel;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.gui.wizard.workflow.CheckInputListener;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;

public interface IHeaderDataModel {
  ICharacterType[] getCharacterTypes();

  ISourceEntryModel getSourceModel();

  ICharacterType getCharacterType();

  ITextualDescription getName();

  void setCharacterType(ICharacterType type);

  void addModelListener(CheckInputListener inputListener);

  void addChangeListener(IChangeListener inputListener);
}