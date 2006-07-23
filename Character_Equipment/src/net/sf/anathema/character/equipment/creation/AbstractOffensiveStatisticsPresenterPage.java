package net.sf.anathema.character.equipment.creation;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JPanel;

import net.disy.commons.core.message.IBasicMessage;
import net.disy.commons.core.util.Ensure;
import net.disy.commons.swing.dialog.core.IPageContent;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.disy.commons.swing.layout.grid.IDialogComponent;
import net.sf.anathema.character.equipment.creation.model.IEquipmentStatisticsCreationModel;
import net.sf.anathema.character.equipment.creation.model.IOffensiveStatisticsModel;
import net.sf.anathema.character.equipment.creation.model.IWeaponDamageModel;
import net.sf.anathema.character.equipment.creation.properties.OffensiveStatisticsProperties;
import net.sf.anathema.character.equipment.creation.view.IWeaponDamageView;
import net.sf.anathema.character.equipment.creation.view.IWeaponStatisticsView;
import net.sf.anathema.lib.gui.widgets.IntegerSpinner;
import net.sf.anathema.lib.gui.wizard.AbstractAnathemaWizardPage;
import net.sf.anathema.lib.gui.wizard.workflow.CheckInputListener;
import net.sf.anathema.lib.gui.wizard.workflow.ICondition;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.workflow.booleanvalue.BooleanValueModel;
import net.sf.anathema.lib.workflow.booleanvalue.BooleanValuePresentation;
import net.sf.anathema.lib.workflow.intvalue.IIntValueModel;
import net.sf.anathema.lib.workflow.intvalue.IntValuePresentation;
import net.sf.anathema.lib.workflow.textualdescription.ICheckableTextView;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;
import net.sf.anathema.lib.workflow.textualdescription.TextualPresentation;

public abstract class AbstractOffensiveStatisticsPresenterPage<O extends IOffensiveStatisticsModel, P extends OffensiveStatisticsProperties> extends
    AbstractAnathemaWizardPage {

  private final O pageModel;
  private final IEquipmentStatisticsCreationModel overallModel;
  private final IEquipmentStatisticsCreationViewFactory viewFactory;
  private final P properties;
  private IWeaponStatisticsView view;
  private final IResources resources;

  public AbstractOffensiveStatisticsPresenterPage(
      IResources resources,
      P properties,
      IEquipmentStatisticsCreationModel overallModel,
      O pageModel,
      IEquipmentStatisticsCreationViewFactory viewFactory) {
    this.resources = resources;
    this.properties = properties;
    this.pageModel = pageModel;
    this.overallModel = overallModel;
    this.viewFactory = viewFactory;
  }

  protected final P getProperties() {
    return properties;
  }

  protected final O getPageModel() {
    return pageModel;
  }

  public final boolean canFinish() {
    return isNameCorrectlyDefined();
  }

  private boolean isNameCorrectlyDefined() {
    return !pageModel.getNameSpecified().getValue() || !pageModel.getName().isEmpty();
  }

  public final IBasicMessage getMessage() {
    if (!isNameCorrectlyDefined()) {
      return properties.getUndefinedNameMessage();
    }
    return properties.getDefaultMessage();
  }

  public final String getDescription() {
    return properties.getPageDescription();
  }

  @Override
  protected final void initPageContent() {
    this.view = viewFactory.createCloseCombatStatisticsView();
    initNameRow(properties.getNameLabel(), pageModel.getName(), pageModel.getNameSpecified());
    addIndividualRows();
    initAccuracyAndRateRow();
    initWeaponDamageRow(pageModel.getWeaponDamageModel());
  }

  protected abstract void addIndividualRows();

  private void initAccuracyAndRateRow() {
    addLabelledComponentRow(new String[] { properties.getAccuracyLabel(), properties.getRateLabel() }, new Component[] {
        initIntegerSpinner(pageModel.getAccuracyModel()).getComponent(),
        initIntegerSpinner(pageModel.getRateModel(), 1).getComponent() });
  }

  protected final void addLabelledComponentRow(final String[] labels, final Component[] contents) {
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

  private void initNameRow(String label, ITextualDescription textModel, BooleanValueModel isNameDefinedModel) {
    ICheckableTextView textView = view.addCheckableLineTextView(label);
    new TextualPresentation().initView(textView.getTextView(), textModel);
    new BooleanValuePresentation().initPresentation(textView.getBooleanValueView(), isNameDefinedModel);
  }

  protected final IntegerSpinner initIntegerSpinner(IIntValueModel intModel) {
    final IntegerSpinner spinner = new IntegerSpinner(intModel.getValue());
    new IntValuePresentation().initView(spinner, intModel);
    return spinner;
  }

  protected final IntegerSpinner initIntegerSpinner(IIntValueModel intModel, int minimumValue) {
    final IntegerSpinner spinner = initIntegerSpinner(intModel);
    spinner.setMinimum(minimumValue);
    return spinner;
  }

  @Override
  protected void addFollowUpPages(CheckInputListener inputListener) {
    addFollowupPage(new WeaponTagsPresenterPage(resources, overallModel, viewFactory), inputListener, new ICondition() {
      public boolean isFullfilled() {
        return isNameCorrectlyDefined();
      }
    });
  }

  @Override
  protected final void initModelListening(CheckInputListener inputListener) {
    pageModel.getName().addTextChangedListener(inputListener);
    pageModel.getNameSpecified().addChangeListener(inputListener);
  }

  public final IPageContent getPageContent() {
    return view;
  }
}