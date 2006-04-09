package net.sf.anathema.character.view.overview;

import net.sf.anathema.lib.workflow.labelledvalue.ILabelledAlotmentView;
import net.sf.anathema.lib.workflow.labelledvalue.ILabelledValueView;

public interface ICreationOverviewView extends IOverviewView {

  public ILabelledAlotmentView addAbilityCreationCategory(String labelText);

  public ILabelledAlotmentView addAdvantageCreationCategory(String labelText);

  public ILabelledAlotmentView addAttributeCreationCategory(String labelText);

  public ILabelledValueView<String> addBonusOverviewCategory(String labelText);

  public ILabelledValueView<String> addCharacterConceptCategory(String titleText);

  public ILabelledValueView<String> addCharmCreationCategory(String labelText);
}