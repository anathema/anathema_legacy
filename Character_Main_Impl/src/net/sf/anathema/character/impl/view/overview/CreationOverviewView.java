package net.sf.anathema.character.impl.view.overview;

import net.disy.commons.swing.layout.grid.GridDialogPanel;
import net.sf.anathema.character.impl.view.LabelledOverviewStringValueView;
import net.sf.anathema.character.view.overview.ICreationOverviewView;
import net.sf.anathema.character.view.overview.IOverviewViewProperties;
import net.sf.anathema.lib.workflow.labelledvalue.ILabelledAlotmentView;
import net.sf.anathema.lib.workflow.labelledvalue.ILabelledValueView;

public class CreationOverviewView extends AbstractOverviewView implements ICreationOverviewView {
  private final GridDialogPanel abilityCategoryPanel = new GridDialogPanel(false);
  private final GridDialogPanel advantageCategoryPanel = new GridDialogPanel(false);
  private final GridDialogPanel attributeCategoryPanel = new GridDialogPanel(false);
  private final GridDialogPanel bonusPointOverviewPanel = new GridDialogPanel(false);
  private final GridDialogPanel characterConceptPanel = new GridDialogPanel(false);
  private final GridDialogPanel charmCategoryPanel = new GridDialogPanel(false);

  public ILabelledAlotmentView addAbilityCreationCategory(String labelText) {
    return addCategoryView(labelText, 0, 0, abilityCategoryPanel);
  }

  public ILabelledAlotmentView addAdvantageCreationCategory(String labelText) {
    return addCategoryView(labelText, 0, 0, advantageCategoryPanel);
  }

  public ILabelledAlotmentView addAttributeCreationCategory(String labelText) {
    return addCategoryView(labelText, 0, 0, attributeCategoryPanel);
  }

  public ILabelledValueView<String> addBonusOverviewCategory(String labelText) {
    return addStringValueView(bonusPointOverviewPanel, labelText, ""); //$NON-NLS-1$
  }

  public ILabelledValueView<String> addCharacterConceptCategory(String titleText) {
    return addStringValueView(characterConceptPanel, titleText, ""); //$NON-NLS-1$
  }

  public ILabelledValueView<String> addCharmCreationCategory(String labelText) {
    return addStringValueView(charmCategoryPanel, labelText, ""); //$NON-NLS-1$
  }

  public ILabelledValueView<Integer> addDerivedAdvantageCreationCategory(String labelText) {
    return addDerivedView(labelText, 0, advantageCategoryPanel);
  }

  public ILabelledValueView<String> addVirtueFlawCategory(String titleText) {
    return addStringValueView(advantageCategoryPanel, titleText, ""); //$NON-NLS-1$
  }

  private ILabelledValueView<String> addStringValueView(GridDialogPanel panel, String titleText, String valueText) {
    LabelledOverviewStringValueView flawView = new LabelledOverviewStringValueView(titleText, valueText);
    flawView.addComponents(panel);
    return flawView;
  }

  public void initGui(IOverviewViewProperties properties) {
    addOverviewPanel(properties.getConceptTitle(), characterConceptPanel);
    addOverviewPanel(properties.getAttributeTitle(), attributeCategoryPanel);
    addOverviewPanel(properties.getAbilitiesTtile(), abilityCategoryPanel);
    addOverviewPanel(properties.getAdvantagesTitle(), advantageCategoryPanel);
    if (properties.showCharms()) {
      addOverviewPanel(properties.getCharmsTitle(), charmCategoryPanel);
    }
    addOverviewPanel(properties.getBonusPointsTitle(), bonusPointOverviewPanel);
  }
}