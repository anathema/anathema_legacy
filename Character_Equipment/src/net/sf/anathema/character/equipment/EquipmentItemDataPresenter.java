package net.sf.anathema.character.equipment;

import net.sf.anathema.character.equipment.model.IEquipmentItemData;
import net.sf.anathema.character.equipment.view.IEquipmentItemDataView;
import net.sf.anathema.lib.gui.IPresenter;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.lib.workflow.textualdescription.TextualPresentation;

public class EquipmentItemDataPresenter implements IPresenter {

  private final IResources resources;
  private final IEquipmentItemData model;
  private final IEquipmentItemDataView view;

  public EquipmentItemDataPresenter(IResources resources, IEquipmentItemData model, IEquipmentItemDataView view) {
    this.resources = resources;
    this.model = model;
    this.view = view;
  }

  public void initPresentation() {
    ITextView nameView = null;//view.addLineTextView("Name:", 15);
    ITextView descriptionView = null;//view.addAreaTextView("Description:", 15, 10);
    new TextualPresentation().initView(nameView, model.getDescription().getName());
    new TextualPresentation().initView(descriptionView, model.getDescription().getContent());
  }
}