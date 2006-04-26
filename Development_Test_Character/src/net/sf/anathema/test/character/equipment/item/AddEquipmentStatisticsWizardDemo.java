package net.sf.anathema.test.character.equipment.item;

import net.disy.commons.swing.dialog.wizard.WizardDialog;
import net.sf.anathema.character.equipment.creation.EquipmentTypeChoicePresenterPage;
import net.sf.anathema.character.equipment.creation.model.IAddEquipmentStatisticsModel;
import net.sf.anathema.character.equipment.creation.view.IEquipmentTypeChoiceView;
import net.sf.anathema.character.equipment.impl.creation.model.AddEquipmentStatisticsModel;
import net.sf.anathema.character.equipment.impl.creation.view.EquipmentTypeChoiceView;
import net.sf.anathema.framework.resources.AnathemaResources;
import net.sf.anathema.lib.gui.wizard.AnathemaWizardDialog;
import de.jdemo.extensions.SwingDemoCase;

public class AddEquipmentStatisticsWizardDemo extends SwingDemoCase {

  public void demo() {
    AnathemaResources resources = new AnathemaResources();
    IAddEquipmentStatisticsModel model = new AddEquipmentStatisticsModel();
    IEquipmentTypeChoiceView view = new EquipmentTypeChoiceView();
    EquipmentTypeChoicePresenterPage startPage = new EquipmentTypeChoicePresenterPage(resources, model, view);
    startPage.initPresentation();
    WizardDialog dialog = new AnathemaWizardDialog(createParentComponent(), startPage);
    show(dialog.getConfiguredDialog().getWindow());
  }
}