package net.sf.anathema.framework.item.repository.creation;

import java.util.HashMap;
import java.util.Map;

import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.presenter.IWizardFactory;
import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.registry.IRegistry;

public class ItemTypeSelectionWizardModel implements IItemTypeSelectionWizardModel {

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

  public IItemType getSelectedItemType() {
    return value;
  }

  public void setSelectedValue(IItemType value) {
    this.value = value;
    control.fireChangedEvent();
  }

  public IItemType[] getRegisteredItemTypes() {
    return itemTypes;
  }

  public void addListener(IChangeListener inputListener) {
    control.addChangeListener(inputListener);
  }

  public IItemCreationTemplate getTemplate(IItemType type) {
    return templatesByType.get(type);
  }
}