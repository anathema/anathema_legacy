package net.sf.anathema.charmentry.model;

import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.charmentry.presenter.model.ISourceEntryModel;
import net.sf.anathema.lib.gui.wizard.workflow.CheckInputListener;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;

public interface IHeaderDataModel {
  public CharacterType[] getCharacterTypes();

  public IExaltedEdition[] getEditions();

  public ISourceEntryModel getSourceModel();

  public IExaltedEdition getEdition();

  public CharacterType getCharacterType();

  public ITextualDescription getName();

  public void setCharacterType(CharacterType type);

  public void setExaltedEdition(IExaltedEdition edition);

  public void addModelListener(CheckInputListener inputListener);
}