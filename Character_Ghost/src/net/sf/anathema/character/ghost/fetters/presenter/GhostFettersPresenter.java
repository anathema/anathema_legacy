package net.sf.anathema.character.ghost.fetters.presenter;

import net.sf.anathema.character.generic.framework.additionaltemplate.listening.DedicatedCharacterChangeAdapter;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.GlobalCharacterChangeAdapter;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.ghost.fetters.model.Fetter;
import net.sf.anathema.character.ghost.fetters.model.IGhostFettersModel;
import net.sf.anathema.character.ghost.fetters.view.ButtonControlledEditView;
import net.sf.anathema.character.ghost.fetters.view.IFetterView;
import net.sf.anathema.character.ghost.fetters.view.IGhostFettersConfigurationView;
import net.sf.anathema.character.library.overview.IOverviewCategory;
import net.sf.anathema.character.library.trait.presenter.TraitPresenter;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.lib.collection.IdentityMapping;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.control.IIntValueChangedListener;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.gui.Presenter;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.workflow.labelledvalue.ILabelledAlotmentView;
import net.sf.anathema.lib.workflow.labelledvalue.IValueView;
import org.jmock.example.announcer.Announcer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GhostFettersPresenter implements Presenter {

  ButtonControlledEditView fetterSelectionView;
  private ILabelledAlotmentView creationMaxFetterView;
  private ILabelledAlotmentView experienceMaxFetterView;
  private ILabelledAlotmentView freeFetterView;
  private IValueView<Integer> bonusView;
  private IValueView<Integer> experienceView;

  private final Announcer<IChangeListener> control = Announcer.to(IChangeListener.class);
  private final IdentityMapping<Fetter, IFetterView> viewsByFetter = new IdentityMapping<>();

  private final IResources resources;
  private final IGhostFettersConfigurationView view;
  private final IGhostFettersModel model;

  public GhostFettersPresenter(IResources resources, IGhostFettersConfigurationView view, IGhostFettersModel model) {
    this.model = model;
    this.view = view;
    this.resources = resources;
  }

  @Override
  public void initPresentation() {
    final IOverviewCategory creationOverview = view.createOverview(resources.getString("Overview.Title")); //$NON-NLS-1$
    final IOverviewCategory experienceOverview = view.createOverview(resources.getString("Overview.Title"));
    creationMaxFetterView = creationOverview.addAlotmentView(resources.getString("Fetters.Overview.Max"), 2);
    experienceMaxFetterView = experienceOverview.addAlotmentView(resources.getString("Fetters.Overview.Max"), 2);
    freeFetterView = creationOverview.addAlotmentView(resources.getString("Fetters.Overview.Dots"), 1); //$NON-NLS-1$
    bonusView = creationOverview.addIntegerValueView(resources.getString("Fetters.Overview.Bonus"), 2);
    experienceView = experienceOverview.addIntegerValueView(resources.getString("Fetters.Overview.Experience"), 2);


    Icon addIcon = new BasicUi(resources).getAddIcon();
    fetterSelectionView = view.addFetterSelectionView(resources.getString("View.SelectionCombo.Label"), addIcon);
    fetterSelectionView.addEditChangedListener(new ObjectValueListener<String>() {
      @Override
      public void valueChanged(String newFetterName) {
        model.setCurrentFetterName(newFetterName);
      }
    });
    fetterSelectionView.addButtonListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        addFetterView(model.commitSelection());
        fetterSelectionView.clear();
        updateOverviewData();
      }
    });
    model.addSelectionChangeListener(new IChangeListener() {
      @Override
      public void changeOccurred() {
        fetterSelectionView.setButtonEnabled(model.isEntryComplete());
      }
    });
    model.addCharacterChangeListener(new DedicatedCharacterChangeAdapter() {
      @Override
      public void traitChanged(ITraitType type) {
        updateOverviewData();
      }
    });
    model.setValueChangedListener(new IIntValueChangedListener() {
      @Override
      public void valueChanged(int newValue) {
        updateOverviewData();
        control.announce().changeOccurred();
      }

    });

    for (Fetter fetter : model.getFetters())
      addFetterView(fetter);
    model.addCharacterChangeListener(new GlobalCharacterChangeAdapter() {
      @Override
      public void experiencedChanged(boolean experienced) {
        updateFetterViewButtons();
      }
    });
    fetterSelectionView.setButtonEnabled(model.isEntryComplete());
    updateFetterViewButtons();

    view.setOverview(creationOverview);
    updateOverviewData();
    model.addCharacterChangeListener(new DedicatedCharacterChangeAdapter() {
      @Override
      public void experiencedChanged(boolean experienced) {
        if (experienced) {
          view.setOverview(experienceOverview);
        } else {
          view.setOverview(creationOverview);
        }
      }
    });
  }

  private void updateOverviewData() {
    creationMaxFetterView.setValue(model.getCurrentFetterDots());
    creationMaxFetterView.setAlotment(model.getMaxFetterDots());
    experienceMaxFetterView.setValue(model.getCurrentFetterDots());
    experienceMaxFetterView.setAlotment(model.getMaxFetterDots());
    freeFetterView.setValue(model.getFreeDotsSpent());
    freeFetterView.setAlotment(model.getFreeDotAllotment());
    bonusView.setValue(model.getBonusPointsSpent());
    experienceView.setValue(model.getXPSpent());
  }

  private void updateFetterViewButtons() {
    for (Fetter fetter : model.getFetters()) {
      IFetterView view = viewsByFetter.get(fetter);
      view.setDeleteButtonEnabled(fetter.getCreationValue() == 0 || !model.isExperienced());
    }
  }

  private void addFetterView(final Fetter fetter) {
    String fetterName = fetter.getName();
    Icon deleteIcon = new BasicUi(resources).getRemoveIcon();
    IFetterView fetterView = view.addFetterView(fetterName, deleteIcon, fetter.getCurrentValue(),
            fetter.getMaximalValue());
    new TraitPresenter(fetter, fetterView).initPresentation();
    fetterView.addDeleteListener(new IChangeListener() {
      @Override
      public void changeOccurred() {
        model.removeFetter(fetter);
        viewsByFetter.get(fetter).delete();
        updateOverviewData();
      }
    });
    viewsByFetter.put(fetter, fetterView);
  }
}
