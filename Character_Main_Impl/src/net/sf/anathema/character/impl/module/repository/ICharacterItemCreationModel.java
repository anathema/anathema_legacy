package net.sf.anathema.character.impl.module.repository;

import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.view.repository.ITemplateTypeAggregation;
import net.sf.anathema.lib.control.change.IChangeListener;

public interface ICharacterItemCreationModel {

  public ICharacterType[] getAvailableCharacterTypes();

  public void setCharacterType(ICharacterType type);

  public void addListener(IChangeListener listener);

  public ITemplateTypeAggregation[] getAvailableTemplates();

  public ITemplateTypeAggregation getSelectedTemplate();

  public void setSelectedTemplate(ITemplateTypeAggregation newValue);

  public boolean isSelectionComplete();

  public boolean isCharacterTypeSelected();
}