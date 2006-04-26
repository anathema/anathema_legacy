package net.sf.anathema.character.equipment;

import javax.swing.JComponent;

import net.sf.anathema.character.equipment.model.IEquipmentItemData;
import net.sf.anathema.character.equipment.view.IEquipmentItemDataView;
import net.sf.anathema.lib.gui.IPresenter;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.workflow.container.factory.StandardPanelBuilder;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.lib.workflow.textualdescription.TextualPresentation;

public class EquipmentItemDataPresenter implements IPresenter {

  private static int COLUMN_COUNT = 45;
  private final IResources resources;
  private final IEquipmentItemData model;
  private final IEquipmentItemDataView view;

  public EquipmentItemDataPresenter(IResources resources, IEquipmentItemData model, IEquipmentItemDataView view) {
    this.resources = resources;
    this.model = model;
    this.view = view;
  }

  public void initPresentation() {
    view.fillDescriptionPanel(createDescriptionPanel());
  }

  private JComponent createDescriptionPanel() {
    StandardPanelBuilder builder = new StandardPanelBuilder();
    ITextView nameView = builder.addLineTextView("Name:", COLUMN_COUNT);
    ITextView descriptionView = builder.addAreaTextView("Description:", 5, COLUMN_COUNT);
    new TextualPresentation().initView(nameView, model.getDescription().getName());
    new TextualPresentation().initView(descriptionView, model.getDescription().getContent());
    return builder.getTitledContent("Basics");
  }
}