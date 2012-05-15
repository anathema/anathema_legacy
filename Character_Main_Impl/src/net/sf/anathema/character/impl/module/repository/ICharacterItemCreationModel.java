package net.sf.anathema.character.impl.module.repository;

import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.view.repository.ITemplateTypeAggregation;
import net.sf.anathema.lib.control.IChangeListener;

public interface ICharacterItemCreationModel {

  ICharacterType[] getAvailableCharacterTypes();

  void setCharacterType(ICharacterType type);

  void addListener(IChangeListener listener);

  ITemplateTypeAggregation[] getAvailableTemplates();

  ITemplateTypeAggregation getSelectedTemplate();

  void setSelectedTemplate(ITemplateTypeAggregation newValue);

  boolean isSelectionComplete();

  boolean isCharacterTypeSelected();
}