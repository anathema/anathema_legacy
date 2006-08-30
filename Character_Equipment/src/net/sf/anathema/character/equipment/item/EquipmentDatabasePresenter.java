package net.sf.anathema.character.equipment.item;

import javax.swing.Icon;

import net.disy.commons.swing.ui.IObjectUi;
import net.disy.commons.swing.ui.ObjectUiListCellRenderer;
import net.sf.anathema.character.equipment.item.model.IEquipmentDatabaseManagement;
import net.sf.anathema.character.equipment.item.model.IEquipmentTemplateEditModel;
import net.sf.anathema.character.equipment.item.view.IEquipmentDatabaseView;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.gui.IPresenter;
import net.sf.anathema.lib.gui.selection.IObjectSelectionView;
import net.sf.anathema.lib.gui.wizard.workflow.ICondition;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.workflow.container.factory.StandardPanelBuilder;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.lib.workflow.textualdescription.TextualPresentation;

public class EquipmentDatabasePresenter implements IPresenter {

  private static final int COLUMN_COUNT = 45;
  private final IResources resources;
  private final IEquipmentDatabaseView view;
  private final IEquipmentDatabaseManagement model;

  public EquipmentDatabasePresenter(
      IResources resources,
      IEquipmentDatabaseManagement model,
      IEquipmentDatabaseView view) {
    this.resources = resources;
    this.model = model;
    this.view = view;
  }

  public void initPresentation() {
    initTemplateList();
    initDescriptionView();
    initStatsView();
    view.setTemplateListHeader("Available Templates");
    view.setEditTemplateHeader("Edit Template");
  }

  private void initStatsView() {
    final IObjectSelectionView<IExaltedRuleSet> ruleSetView = view.initRuleSetSelectionView(
        "Ruleset",
        new ObjectUiListCellRenderer(new IObjectUi() {
          public Icon getIcon(Object value) {
            return null;
          }

          public String getLabel(Object value) {
            if (value == null) {
              return "Select...";
            }
            return ((IExaltedRuleSet) value).getId();
          }
        }));
    ruleSetView.setObjects(model.getSupportedExaltedRuleSets());
    ruleSetView.addObjectSelectionChangedListener(new IObjectValueChangedListener<IExaltedRuleSet>() {
      public void valueChanged(IExaltedRuleSet newValue) {
        updateStatListContent(newValue);
      }
    });
    view.getStatsListView().setCellRenderer(new ObjectUiListCellRenderer(new IObjectUi() {
      public String getLabel(Object value) {
        return ((IEquipmentStats) value).getName().getId();
      }

      public Icon getIcon(Object value) {
        return null;
      }
    }));
    model.getTemplateEditModel().addStatsChangeListener(new IChangeListener() {
      public void changeOccured() {
        updateStatListContent(ruleSetView.getSelectedObject());
      }
    });
  }

  private void updateStatListContent(IExaltedRuleSet newValue) {
    view.getStatsListView().setObjects(model.getTemplateEditModel().getStats(newValue));
  }

  private void initTemplateList() {
    view.setTemplateListHeader("Available Templates");
    view.getTemplateListView().setObjects(model.getDatabase().getAllAvailableTemplateIds());
    view.getTemplateListView().addSelectionVetor(new DiscardChangesVetor(new ICondition() {
      public boolean isFullfilled() {
        final IEquipmentTemplateEditModel editModel = model.getTemplateEditModel();
        return editModel.isDirty();
      }
    }, view.getTemplateListView().getContent()));
    view.getTemplateListView().addObjectSelectionChangedListener(new IObjectValueChangedListener<String>() {
      public void valueChanged(String newValue) {
        if (newValue == null) {
          return;
        }
        IEquipmentTemplateEditModel editModel = model.getTemplateEditModel();
        editModel.setEditTemplate(newValue);
      }
    });
  }

  private void initDescriptionView() {
    StandardPanelBuilder panelBuilder = new StandardPanelBuilder();
    ITextView nameView = panelBuilder.addLineTextView("Name:", COLUMN_COUNT);
    new TextualPresentation().initView(nameView, model.getTemplateEditModel().getDescription().getName());
    ITextView descriptionView = panelBuilder.addAreaTextView("Description:", 5, COLUMN_COUNT);
    new TextualPresentation().initView(descriptionView, model.getTemplateEditModel().getDescription().getContent());
    view.fillDescriptionPanel(panelBuilder.getTitledContent("Basics"));
  }
}