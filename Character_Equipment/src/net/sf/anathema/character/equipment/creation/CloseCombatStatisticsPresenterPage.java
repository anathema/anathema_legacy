package net.sf.anathema.character.equipment.creation;

import net.disy.commons.core.message.IBasicMessage;
import net.disy.commons.swing.dialog.core.IPageContent;
import net.sf.anathema.character.equipment.creation.model.ICloseCombatStatsticsModel;
import net.sf.anathema.character.equipment.creation.model.IEquipmentStatisticsCreationModel;
import net.sf.anathema.character.equipment.creation.properties.CloseCombatStatisticsProperties;
import net.sf.anathema.character.equipment.creation.view.ICloseCombatStatisticsView;
import net.sf.anathema.lib.gui.widgets.IntegerSpinner;
import net.sf.anathema.lib.gui.wizard.AbstractAnathemaWizardPage;
import net.sf.anathema.lib.gui.wizard.workflow.CheckInputListener;
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

  public CloseCombatStatisticsPresenterPage(
      IResources resources,
      IEquipmentStatisticsCreationModel model,
      IEquipmentStatisticsCreationViewFactory viewFactory) {
    this.properties = new CloseCombatStatisticsProperties(resources);
    this.model = model;
    this.viewFactory = viewFactory;
  }

  public boolean canFinish() {
    return isNameDefined();
  }

  private boolean isNameDefined() {
    return !getPageModel().getName().isEmpty();
  }

  public IPageContent getPageContent() {
    return view;
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
    initIntRow(properties.getSpeedLabel(), getPageModel().getSpeedModel(), 1);
    initIntRow(properties.getAccuracyLabel(), getPageModel().getAccuracyModel());
    initIntRow(properties.getDefenseLabel(), getPageModel().getDefenseModel());
    initIntRow(properties.getRateLabel(), getPageModel().getRateModel(), 1);
  }

  private void initTextRow(String label, ITextualDescription textModel) {
    ITextView textView = view.addLineTextView(label);
    new TextualPresentation().initView(textView, textModel);
  }

  private void initIntRow(String label, IIntValueModel intModel, int minimumValue) {
    final IntegerSpinner spinner = initIntRow(label, intModel);
    spinner.setMinimum(minimumValue);
  }

  private IntegerSpinner initIntRow(String label, IIntValueModel intModel) {
    final IntegerSpinner spinner = view.addIntegerSpinner(label, intModel.getValue());
    new IntValuePresentation().initView(spinner, intModel);
    return spinner;
  }

  private ICloseCombatStatsticsModel getPageModel() {
    return model.getCloseCombatStatsticsModel();
  }

  @Override
  protected void addFollowUpPages(CheckInputListener inputListener) {
    //Nothing to do
  }

  @Override
  protected void initModelListening(CheckInputListener inputListener) {
    getPageModel().getName().addTextChangedListener(inputListener);
  }
}