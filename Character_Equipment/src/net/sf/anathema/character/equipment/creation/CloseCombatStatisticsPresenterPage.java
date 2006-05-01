package net.sf.anathema.character.equipment.creation;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JPanel;

import net.disy.commons.core.message.IBasicMessage;
import net.disy.commons.core.util.Ensure;
import net.disy.commons.swing.dialog.core.IPageContent;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.disy.commons.swing.layout.grid.IDialogComponent;
import net.sf.anathema.character.equipment.creation.model.ICloseCombatStatsticsModel;
import net.sf.anathema.character.equipment.creation.model.IEquipmentStatisticsCreationModel;
import net.sf.anathema.character.equipment.creation.model.IWeaponDamageModel;
import net.sf.anathema.character.equipment.creation.properties.CloseCombatStatisticsProperties;
import net.sf.anathema.character.equipment.creation.view.ICloseCombatStatisticsView;
import net.sf.anathema.character.equipment.creation.view.IWeaponDamageView;
import net.sf.anathema.lib.gui.widgets.IntegerSpinner;
import net.sf.anathema.lib.gui.wizard.AbstractAnathemaWizardPage;
import net.sf.anathema.lib.gui.wizard.workflow.CheckInputListener;
import net.sf.anathema.lib.gui.wizard.workflow.ICondition;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.workflow.intvalue.IIntValueModel;
import net.sf.anathema.lib.workflow.intvalue.IntValuePresentation;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;
import net.sf.anathema.lib.workflow.textualdescription.TextualPresentation;

public class CloseCombatStatisticsPresenterPage extends AbstractAnathemaWizardPage {

  private final IEquipmentStatisticsCreationModel model;
  private final IEquipmentStatisticsCreationViewFactory viewFactory;
  private final CloseCombatStatisticsProperties properties;
  private ICloseCombatStatisticsView view;
  private final IResources resources;

  public CloseCombatStatisticsPresenterPage(
      IResources resources,
      IEquipmentStatisticsCreationModel model,
      IEquipmentStatisticsCreationViewFactory viewFactory) {
    this.properties = new CloseCombatStatisticsProperties(resources);
    this.resources = resources;
    this.model = model;
    this.viewFactory = viewFactory;
  }

  public boolean canFinish() {
    return isNameDefined();
  }

  private boolean isNameDefined() {
    return !getPageModel().getName().isEmpty();
  }

  public IBasicMessage getMessage() {
    if (!isNameDefined()) {
      return properties.getUndefinedNameMessage();
    }
    return properties.getDefaultMessage();
  }

  public String getDescription() {
    return properties.getPageDescription();
  }

  @Override
  protected void initPageContent() {
    this.view = viewFactory.createCloseCombatStatisticsView();
    initTextRow(properties.getNameLabel(), getPageModel().getName());
    initSpeedAndDefenseRow();
    initAccuracyAndRateRow();
    initWeaponDamageRow(getPageModel().getWeaponDamageModel());
  }

  private void initAccuracyAndRateRow() {
    addLabelledComponentRow(new String[] { properties.getAccuracyLabel(), properties.getRateLabel() }, new Component[] {
        initIntegerSpinner(getPageModel().getAccuracyModel()).getComponent(),
        initIntegerSpinner(getPageModel().getRateModel(), 1).getComponent() });
  }

  private void initSpeedAndDefenseRow() {
    addLabelledComponentRow(new String[] { properties.getSpeedLabel(), properties.getDefenseLabel() }, new Component[] {
        initIntegerSpinner(getPageModel().getSpeedModel(), 1).getComponent(),
        initIntegerSpinner(getPageModel().getDefenseModel()).getComponent() });
  }

  private void addLabelledComponentRow(final String[] labels, final Component[] contents) {
    Ensure.ensureArgumentTrue("Same number of labels required", labels.length == contents.length); //$NON-NLS-1$
    view.addDialogComponent(new IDialogComponent() {
      public void fillInto(JPanel panel, int columnCount) {
        for (int index = 0; index < contents.length; index++) {
          panel.add(new JLabel(labels[index]));
          panel.add(contents[index], GridDialogLayoutData.FILL_HORIZONTAL);
        }
      }

      public int getColumnCount() {
        return contents.length * 2;
      }
    });
  }

  private void initWeaponDamageRow(final IWeaponDamageModel damageModel) {
    final IWeaponDamageView damageView = viewFactory.createWeaponDamageView();
    new WeaponDamagePresenter(resources, damageModel, damageView).initPresentation();
    view.addDialogComponent(damageView);
  }

  private void initTextRow(String label, ITextualDescription textModel) {
    ITextView textView = view.addLineTextView(label);
    new TextualPresentation().initView(textView, textModel);
  }

  private IntegerSpinner initIntegerSpinner(IIntValueModel intModel) {
    final IntegerSpinner spinner = new IntegerSpinner(intModel.getValue());
    new IntValuePresentation().initView(spinner, intModel);
    return spinner;
  }

  private IntegerSpinner initIntegerSpinner(IIntValueModel intModel, int minimumValue) {
    final IntegerSpinner spinner = initIntegerSpinner(intModel);
    spinner.setMinimum(minimumValue);
    return spinner;
  }

  private ICloseCombatStatsticsModel getPageModel() {
    return model.getCloseCombatStatsticsModel();
  }

  @Override
  protected void addFollowUpPages(CheckInputListener inputListener) {
    addFollowupPage(new WeaponTagsPresenterPage(resources, model, viewFactory), inputListener, new ICondition() {
      public boolean isFullfilled() {
        return isNameDefined();
      }
    });
  }

  @Override
  protected void initModelListening(CheckInputListener inputListener) {
    getPageModel().getName().addTextChangedListener(inputListener);
  }

  public IPageContent getPageContent() {
    return view;
  }
}