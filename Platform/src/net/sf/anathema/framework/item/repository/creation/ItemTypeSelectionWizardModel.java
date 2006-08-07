package net.sf.anathema.framework.item.repository.creation;

import java.util.HashMap;
import java.util.Map;

import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.workflow.wizard.selection.IItemCreationTemplate;
import net.sf.anathema.lib.workflow.wizard.selection.IObjectSelectionWizardModel;
import net.sf.anathema.lib.workflow.wizard.selection.IWizardFactory;

public class ItemTypeSelectionWizardModel implements IObjectSelectionWizardModel<IItemType> {

  private IItemType value;
  private final IItemType[] itemTypes;
  private final ChangeControl control = new ChangeControl();
  private final Map<IItemType, IItemCreationTemplate> templatesByType = new HashMap<IItemType, IItemCreationTemplate>();

  public ItemTypeSelectionWizardModel(IItemType[] allItemTypes, IRegistry<IItemType, IWizardFactory> registry) {
    this.itemTypes = allItemTypes;
    for (IItemType type : itemTypes) {
      templatesByType.put(type, registry.get(type).createTemplate());
    }
  }

  public IItemType getSelectedObject() {
    return value;
  }

  public void setSelectedObject(IItemType value) {
    this.value = value;
    control.fireChangedEvent();
  }

  public IItemType[] getRegisteredObjects() {
    return itemTypes;
  }

  public void addListener(IChangeListener inputListener) {
    control.addChangeListener(inputListener);
  }

  public IItemCreationTemplate getTemplate(IItemType type) {
    return templatesByType.get(type);
  }
}