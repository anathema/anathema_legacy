package net.sf.anathema.character.impl.module;

import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.view.repository.ITemplateTypeAggregation;
import net.sf.anathema.lib.control.change.IChangeListener;

public interface ICharacterItemCreationModel {

  public boolean isCharacterTypeSelected();

  public CharacterType[] getAvailableCharacterTypes();

  public void setCharacterType(CharacterType type);

  public void addListener(IChangeListener listener);

  public ITemplateTypeAggregation[] getAvailableTemplates();

  public ITemplateTypeAggregation getSelectedTemplate();

  public void setSelectedTemplate(ITemplateTypeAggregation newValue);

  public IExaltedRuleSet[] getAvailableRulesets();

  public IExaltedRuleSet getSelectedRuleset();

  public void setSelectedRuleset(IExaltedRuleSet newValue);

}
