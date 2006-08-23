package net.sf.anathema.character.equipment.item;

import net.sf.anathema.character.equipment.item.model.IEquipmentDatabase;
import net.sf.anathema.character.equipment.item.view.IEquipmentDatabaseView;
import net.sf.anathema.lib.gui.IPresenter;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.workflow.container.factory.StandardPanelBuilder;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.lib.workflow.textualdescription.TextualPresentation;

public class EquipmentDatabasePresenter implements IPresenter {

  private static final int COLUMN_COUNT = 45;
  private final IResources resources;
  private final IEquipmentDatabaseView view;
  private final IEquipmentDatabase model;

  public EquipmentDatabasePresenter(IResources resources, IEquipmentDatabase model, IEquipmentDatabaseView view) {
    this.resources = resources;
    this.model = model;
    this.view = view;
  }

  public void initPresentation() {
    initTemplateList();
    initDescriptionView();
    view.setTemplateListHeader("Available Templates");
    view.setEditTemplateHeader("Edit Template");
  }

  private void initTemplateList() {
    view.setTemplateListHeader("Available Templates");
    view.getTemplateView().setObjects(model.getAllAvailableTemplates());
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