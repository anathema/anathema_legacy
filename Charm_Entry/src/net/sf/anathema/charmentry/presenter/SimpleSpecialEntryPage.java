package net.sf.anathema.charmentry.presenter;

import net.disy.commons.core.message.IBasicMessage;
import net.disy.commons.swing.dialog.core.IPageContent;
import net.sf.anathema.character.generic.magic.charms.type.TurnType;
import net.sf.anathema.charmentry.module.ICharmEntryViewFactory;
import net.sf.anathema.charmentry.presenter.model.ICharmEntryModel;
import net.sf.anathema.charmentry.presenter.model.ISimpleSpecialsEntryModel;
import net.sf.anathema.charmentry.presenter.view.ISimpleSpecialsView;
import net.sf.anathema.charmentry.properties.CharmTypeEntryPageProperties;
import net.sf.anathema.framework.value.IIntValueView;
import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.gui.selection.IObjectSelectionView;
import net.sf.anathema.lib.gui.wizard.AbstractAnathemaWizardPage;
import net.sf.anathema.lib.gui.wizard.workflow.CheckInputListener;
import net.sf.anathema.lib.gui.wizard.workflow.ICondition;
import net.sf.anathema.lib.resources.IResources;

public class SimpleSpecialEntryPage extends AbstractAnathemaWizardPage {

  private final IResources resources;
  private final ICharmEntryModel model;
  private final ICharmEntryViewFactory viewFactory;
  private final CharmTypeEntryPageProperties properties;
  private ISimpleSpecialsView view;

  public SimpleSpecialEntryPage(IResources resources, ICharmEntryModel model, ICharmEntryViewFactory viewFactory) {
    this.resources = resources;
    this.properties = new CharmTypeEntryPageProperties(resources);
    this.model = model;
    this.viewFactory = viewFactory;
  }

  @Override
  protected void addFollowUpPages(CheckInputListener inputListener) {
    addFollowupPage(new DurationEntryPage(resources, model, viewFactory), inputListener, new ICondition() {
      public boolean isFullfilled() {
        return true;
      }
    });
  }

  @Override
  protected void initModelListening(CheckInputListener inputListener) {
    // TODO Auto-generated method stub
  }

  @Override
  protected void initPageContent() {
    this.view = viewFactory.createSimpleSpecialsView();
    IIntValueView speedView = view.addIntegerSelectionView(properties.getSpeedLabel(), 10);
    speedView.addIntValueChangedListener(new IIntValueChangedListener() {
      public void valueChanged(int newValue) {
        getPageModel().setSpeed(newValue);
      }
    });
    speedView.setValue(getPageModel().getSpeed());
    IObjectSelectionView<TurnType> turnView = view.addObjectSelectionView(
        properties.getTurnTypeLabel(),
        properties.getDefaultIdentificateRenderer(),
        getPageModel().getTurnTypes());
    turnView.addObjectSelectionChangedListener(new IObjectValueChangedListener<TurnType>() {
      public void valueChanged(TurnType newValue) {
        getPageModel().setTurnType(newValue);
      }
    });
    turnView.setSelectedObject(getPageModel().getTurnType());
    IIntValueView defenseView = view.addIntegerSelectionView(properties.getDefenseLabel(), -10);
    defenseView.addIntValueChangedListener(new IIntValueChangedListener() {
      public void valueChanged(int newValue) {
        getPageModel().setDefenseValue(newValue);
      }
    });
    defenseView.setValue(getPageModel().getDefenseModifier());
  }

  private ISimpleSpecialsEntryModel getPageModel() {
    return model.getCharmTypeModel().getSimpleSpecialsModel();
  }

  public boolean canFinish() {
    return false;
  }

  public String getDescription() {
    return properties.getSimpleSpecialsTitle();
  }

  public IBasicMessage getMessage() {
    return properties.getSimpleSpecialsMessage();
  }

  public IPageContent getPageContent() {
    return view;
  }
}