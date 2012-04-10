package net.sf.anathema.character.equipment.character;

import net.disy.commons.swing.action.SmartAction;
import net.disy.commons.swing.dialog.core.IDialogResult;
import net.disy.commons.swing.dialog.wizard.WizardDialog;
import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.equipment.MaterialComposition;
import net.sf.anathema.character.equipment.character.model.IEquipmentAdditionalModel;
import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.character.equipment.character.preference.AnathemaEquipmentPreferences;
import net.sf.anathema.character.equipment.character.view.IEquipmentAdditionalView;
import net.sf.anathema.character.equipment.character.view.IEquipmentObjectView;
import net.sf.anathema.character.equipment.character.view.IMagicalMaterialView;
import net.sf.anathema.character.equipment.creation.presenter.stats.properties.EquipmentUI;
import net.sf.anathema.character.equipment.item.EquipmentTemplateNameComparator;
import net.sf.anathema.character.equipment.item.personalization.EquipmentPersonalizationModel;
import net.sf.anathema.character.equipment.item.personalization.EquipmentPersonalizationPresenterPage;
import net.sf.anathema.character.generic.framework.configuration.AnathemaCharacterPreferences;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.control.collection.ICollectionListener;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.gui.Presenter;
import net.sf.anathema.lib.gui.selection.IListObjectSelectionView;
import net.sf.anathema.lib.gui.wizard.AnathemaWizardDialog;
import net.sf.anathema.lib.resources.IResources;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import javax.swing.Action;
import javax.swing.DefaultListCellRenderer;
import javax.swing.Icon;

public class EquipmentAdditionalPresenter implements Presenter {

  private final IResources resources;
  private final IEquipmentAdditionalModel model;
  private final IEquipmentAdditionalView view;
  private final Map<IEquipmentItem, IEquipmentObjectView> viewsByItem = new HashMap<IEquipmentItem, IEquipmentObjectView>();

  public EquipmentAdditionalPresenter(IResources resources, final IEquipmentAdditionalModel model,
                                      IEquipmentAdditionalView view) {
    this.resources = resources;
    this.model = model;
    this.view = view;

    model.getCharacterDataProvider().addCharacterSpecialtyListChangeListener(new IChangeListener() {
      @Override
      public void changeOccurred() {
        for (IEquipmentItem item : model.getNaturalWeapons()) {
          initEquipmentObjectPresentation(item);
        }
        for (IEquipmentItem item : model.getEquipmentItems()) {
          initEquipmentObjectPresentation(item);
        }
      }
    });
  }
  
  

  @Override
  public void initPresentation() {
    for (IEquipmentItem item : model.getNaturalWeapons()) {
      initEquipmentObjectPresentation(item);
    }
    for (IEquipmentItem item : model.getEquipmentItems()) {
      initEquipmentObjectPresentation(item);
    }
    final IListObjectSelectionView<String> equipmentTemplatePickList = view.getEquipmentTemplatePickList();
    model.addEquipmentObjectListener(new ICollectionListener<IEquipmentItem>() {
      @Override
      public void itemAdded(IEquipmentItem item) {
        initEquipmentObjectPresentation(item);
      }

      @Override
      public void itemRemoved(IEquipmentItem item) {
        removeItemView(item);
      }
    });
    IMagicalMaterialView magicMaterialView = view.getMagicMaterialView();
    initMaterialView(magicMaterialView);
    equipmentTemplatePickList.setCellRenderer(new EquipmentObjectCellRenderer(model));
    setObjects(equipmentTemplatePickList);
    view.setSelectButtonAction(createTemplateAddAction(equipmentTemplatePickList, magicMaterialView));
    view.setRefreshButtonAction(createRefreshAction(equipmentTemplatePickList));
    equipmentTemplatePickList.addObjectSelectionChangedListener(new IObjectValueChangedListener<String>() {
      @Override
      public void valueChanged(String templateId) {
        MaterialComposition composition = templateId == null ? MaterialComposition.None : model.getMaterialComposition(
                templateId);
        MagicalMaterial magicMaterial = null;
        if (composition == MaterialComposition.Variable) {
          magicMaterial = model.getDefaultMaterial();
        } else if (composition == MaterialComposition.Fixed) {
          magicMaterial = model.getMagicalMaterial(templateId);
        }
        view.getMagicMaterialView().setSelectedMaterial(magicMaterial, composition == MaterialComposition.Variable);
      }
    });
  }

  private void initMaterialView(IMagicalMaterialView magicMaterialView) {
    String label = resources.getString("MagicMaterial.Label") + ":"; //$NON-NLS-1$ //$NON-NLS-2$
    DefaultListCellRenderer renderer = new MagicMaterialCellRenderer(resources);
    magicMaterialView.initView(label, renderer, MagicalMaterial.values());
  }

  private SmartAction createRefreshAction(final IListObjectSelectionView<String> equipmentTemplatePickList) {
    SmartAction refreshAction = new SmartAction(new EquipmentUI(resources).getRefreshIcon()) {
      private static final long serialVersionUID = 1L;

      @Override
      protected void execute(Component parentComponent) {
        setObjects(equipmentTemplatePickList);
        model.refreshItems();
      }
    };
    refreshAction.setToolTipText(
            resources.getString("AdditionalTemplateView.RefreshDatabase.Action.Tooltip")); //$NON-NLS-1$
    return refreshAction;
  }

  private void setObjects(final IListObjectSelectionView<String> equipmentTemplatePickList) {
    String[] templates = model.getAvailableTemplateIds();
    Arrays.sort(templates, new EquipmentTemplateNameComparator());
    equipmentTemplatePickList.setObjects(templates);
  }

  private SmartAction createTemplateAddAction(final IListObjectSelectionView<String> equipmentTemplatePickList,
                                              final IMagicalMaterialView materialView) {
    final SmartAction addAction = new SmartAction(new BasicUi(resources).getRightArrowIcon()) {
      private static final long serialVersionUID = 1L;

      @Override
      protected void execute(Component parentComponent) {
        model.addEquipmentObjectFor(equipmentTemplatePickList.getSelectedObject(), materialView.getSelectedMaterial());
      }
    };
    addAction.setToolTipText(resources.getString("AdditionalTemplateView.AddTemplate.Action.Tooltip")); //$NON-NLS-1$
    equipmentTemplatePickList.addObjectSelectionChangedListener(new IObjectValueChangedListener<String>() {
      @Override
      public void valueChanged(String newValue) {
        addAction.setEnabled(equipmentTemplatePickList.isObjectSelected());
      }
    });
    addAction.setEnabled(equipmentTemplatePickList.isObjectSelected());
    return addAction;
  }

  private void removeItemView(IEquipmentItem item) {
    IEquipmentObjectView objectView = viewsByItem.remove(item);
    view.removeEquipmentObjectView(objectView);
  }

  private void initEquipmentObjectPresentation(final IEquipmentItem selectedObject) {
    IEquipmentObjectView objectView = viewsByItem.get(selectedObject);
    objectView = objectView == null ? view.addEquipmentObjectView() : objectView;
    IEquipmentStringBuilder resourceBuilder = new EquipmentStringBuilder(resources);
    Icon removeIcon = new BasicUi(resources).getRemoveIcon();
    Icon editIcon = new BasicUi(resources).getEditIcon();
    viewsByItem.put(selectedObject, objectView);
    List<Action> actions = new ArrayList<Action>();
    if (model.canBeRemoved(selectedObject)) {
    	if (AnathemaEquipmentPreferences.getDefaultPreferences().getEnablePersonalization()) {
    		actions.add(new SmartAction(resources.getString("AdditionalTemplateView.Personalize.Action.Name"), //$NON-NLS-1$
                        editIcon) {
 	            @Override
 	            protected void execute(Component parentComponent) {
 	            	doPersonalization(selectedObject, parentComponent);
 	            }
             });
    	}
        actions.add(new SmartAction(resources.getString("AdditionalTemplateView.RemoveTemplate.Action.Name"), //$NON-NLS-1$
                       removeIcon) {
	            @Override
	            protected void execute(Component parentComponent) {
	                 model.removeItem(selectedObject);
	            }
            });
    }
    new EquipmentObjectPresenter(selectedObject, objectView, resourceBuilder, model.getCharacterDataProvider(),
            model.getCharacterOptionProvider(), resources, actions.toArray(new Action[0])).initPresentation();
    view.revalidateEquipmentViews();
  }
  
  private void doPersonalization(final IEquipmentItem item, Component component) {
	  EquipmentPersonalizationModel model = new EquipmentPersonalizationModel(item);
	  EquipmentPersonalizationPresenterPage page =
   			new EquipmentPersonalizationPresenterPage(resources, model);
      WizardDialog dialog = new AnathemaWizardDialog(component, page);
      IDialogResult result = dialog.show();
      if (!result.isCanceled()) {
    	  item.setPersonalization(model.getTitle(), model.getDescription());
          initEquipmentObjectPresentation(item);
          this.model.updateItem(item);
      }
  }
}
