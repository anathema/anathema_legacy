package net.sf.anathema.charmentry.model;

import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.charmentry.presenter.model.ISourceEntryModel;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.gui.wizard.workflow.CheckInputListener;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;

public interface IHeaderDataModel {
  public ICharacterType[] getCharacterTypes();

  public IExaltedEdition[] getEditions();

  public ISourceEntryModel getSourceModel();

  public IExaltedEdition getEdition();

  public ICharacterType getCharacterType();

  public ITextualDescription getName();

  public void setCharacterType(ICharacterType type);

  public void setExaltedEdition(IExaltedEdition edition);

  public void addModelListener(CheckInputListener inputListener);

  public void addChangeListener(IChangeListener inputListener);
}