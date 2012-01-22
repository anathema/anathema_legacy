package net.sf.anathema.character.ghost.passions.presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Comparator;

import javax.swing.Icon;

import net.sf.anathema.character.generic.framework.ITraitReference;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.GlobalCharacterChangeAdapter;
import net.sf.anathema.character.generic.framework.resources.TraitInternationalizer;
import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.character.ghost.passions.model.IGhostPassionsModel;
import net.sf.anathema.character.ghost.passions.model.IPassion;
import net.sf.anathema.character.ghost.passions.view.IGhostPassionsConfigurationView;
import net.sf.anathema.character.ghost.passions.view.IPassionView;
import net.sf.anathema.character.library.overview.IOverviewCategory;
import net.sf.anathema.character.library.trait.presenter.TraitPresenter;
import net.sf.anathema.character.library.trait.specialties.ITraitReferencesChangeListener;
import net.sf.anathema.character.library.trait.subtrait.ISubTrait;
import net.sf.anathema.character.library.trait.subtrait.ISubTraitContainer;
import net.sf.anathema.character.library.trait.subtrait.ISubTraitListener;
import net.sf.anathema.framework.presenter.view.IButtonControlledComboEditView;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.framework.view.AbstractSelectCellRenderer;
import net.sf.anathema.lib.collection.IdentityMapping;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.gui.IPresenter;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.workflow.labelledvalue.ILabelledAlotmentView;

public class GhostPassionsPresenter implements IPresenter {

  IButtonControlledComboEditView<ITraitReference> passionSelectionView;
  private ILabelledAlotmentView maxPassionView;
  private ILabelledAlotmentView compassionView;
  private ILabelledAlotmentView convictionView;
  private ILabelledAlotmentView temperanceView;
  private ILabelledAlotmentView valorView;
  
  private final IdentityMapping<ISubTrait, IPassionView> viewsByPassion = new IdentityMapping<ISubTrait, IPassionView>();
  private final TraitInternationalizer i18ner;
  private final Comparator<ITraitReference> comparator = new Comparator<ITraitReference>() {
    public int compare(ITraitReference o1, ITraitReference o2) {
      String name1 = i18ner.getScreenName(o1);
      String name2 = i18ner.getScreenName(o2);
      return name1.compareToIgnoreCase(name2);
    }
  };

  private final ISubTraitListener passionListener = new ISubTraitListener() {
    public void subTraitAdded(ISubTrait passion) {
      addPassionView((IPassion) passion);
    }

    public void subTraitRemoved(ISubTrait passion) {
      IPassionView view = viewsByPassion.get(passion);
      viewsByPassion.remove(passion);
      view.delete();
    }

    public void subTraitValueChanged() {
    	updateOverviewData();
    	setSelectionObjects();
    }
  };

  private final IResources resources;
  private final IGhostPassionsConfigurationView view;
  private final IGhostPassionsModel model;

  public GhostPassionsPresenter(
	  IResources resources,
      IGhostPassionsConfigurationView view,
      IGhostPassionsModel model) {
    this.model = model;
    this.view = view;
    this.resources = resources;
    this.i18ner = new TraitInternationalizer(resources);
  }

  public void initPresentation() {
    initTraitListening();
    final IOverviewCategory overview = view.createOverview(resources.getString("Astrology.Overview.Title")); //$NON-NLS-1$
    maxPassionView = overview.addAlotmentView(resources.getString("Passions.Overview.Max"), 2); //$NON-NLS-1$
    compassionView = overview.addAlotmentView(resources.getString("Compassion"), 2); //$NON-NLS-1$
    convictionView = overview.addAlotmentView(resources.getString("Conviction"), 2); //$NON-NLS-1$
    temperanceView = overview.addAlotmentView(resources.getString("Temperance"), 2); //$NON-NLS-1$
    valorView = overview.addAlotmentView(resources.getString("Valor"), 2); //$NON-NLS-1$
    
    Icon addIcon = new BasicUi(resources).getAddIcon();
    passionSelectionView = view.addPassionSelectionView(
        resources.getString("PassionView.SelectionCombo.Label"), //$NON-NLS-1$
        new AbstractSelectCellRenderer<ITraitReference>(resources) {
			private static final long serialVersionUID = 1L;

		@Override
          protected String getCustomizedDisplayValue(ITraitReference value) {
            return i18ner.getScreenName(value);
          }
        },
        addIcon);
    setSelectionObjects();
    passionSelectionView.addSelectionChangedListener(new IObjectValueChangedListener<ITraitReference>() {
      public void valueChanged(ITraitReference newValue) {
        model.setCurrentTrait(newValue);
        updateOverviewData();
      }
    });
    passionSelectionView.addEditChangedListener(new IObjectValueChangedListener<String>() {
      public void valueChanged(String newPassionName) {
        model.setCurrentPassionName(newPassionName);
      }
    });
    passionSelectionView.addButtonListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        model.commitSelection();
        reset(passionSelectionView);
        updateOverviewData();
      }
    });
    model.addSelectionChangeListener(new IChangeListener() {
      public void changeOccurred() {
    	  passionSelectionView.setButtonEnabled(!model.isExperienced() && model.isEntryComplete());
      }
    });
    model.addTraitListChangeListener(new ITraitReferencesChangeListener() {
      public void referenceAdded(ITraitReference reference) {
        getPassionContainer(reference).addSubTraitListener(passionListener);
        setSelectionObjects();
        updateOverviewData();
      }

      public void referenceRemoved(ITraitReference reference) {
    	  passionSelectionView.setObjects(model.getAllEligibleTraits());
    	  updateOverviewData();
      }
    });
    reset(passionSelectionView);
    for (ITraitReference reference : getAllTraits()) {
      for (ISubTrait passion : getPassionContainer(reference).getSubTraits()) {
        addPassionView((IPassion) passion);
      }
    }
    model.addCharacterChangeListener(new GlobalCharacterChangeAdapter() {
    	
        @Override
        public void characterChanged()
        {
        	setSelectionObjects();
        }
        
        @Override
        public void experiencedChanged(boolean experienced) {
          updatePassionViewButtons();
          if (experienced)
        	  view.removeControls();
        }
      });
    updatePassionViewButtons();
    
    view.setOverview(overview);
    updateOverviewData();
    
    if (model.isExperienced())
    	view.removeControls();
  }
  
  private void updateOverviewData()
 {
	  maxPassionView.setValue(model.getCurrentTotalPassions());
	  maxPassionView.setAlotment(model.getMaxTotalPassions());
	  compassionView.setValue(model.getPassionContainer(VirtueType.Compassion).getCurrentDotTotal());
	  compassionView.setAlotment(model.getCurrentVirtueRating(VirtueType.Compassion));
	  convictionView.setValue(model.getPassionContainer(VirtueType.Conviction).getCurrentDotTotal());
	  convictionView.setAlotment(model.getCurrentVirtueRating(VirtueType.Conviction));
	  temperanceView.setValue(model.getPassionContainer(VirtueType.Temperance).getCurrentDotTotal());
	  temperanceView.setAlotment(model.getCurrentVirtueRating(VirtueType.Temperance));
	  valorView.setValue(model.getPassionContainer(VirtueType.Valor).getCurrentDotTotal());
	  valorView.setAlotment(model.getCurrentVirtueRating(VirtueType.Valor));
	  }

  private void setSelectionObjects()
  {
    ITraitReference[] allTraits = getAllEligibleTraits();
    Arrays.sort(allTraits, comparator);
    passionSelectionView.setObjects(allTraits);
  }

  private void initTraitListening() {
    for (ITraitReference reference : getAllTraits()) {
      getPassionContainer(reference).addSubTraitListener(passionListener);
    }
  }

  private ISubTraitContainer getPassionContainer(ITraitReference reference) {
    return model.getPassionContainer(reference);
  }

  private void reset(final IButtonControlledComboEditView<ITraitReference> passionSelectionView) {
    model.clear();
    passionSelectionView.clear();
  }

  protected void removePassionView(ISubTrait passion) {
    IPassionView view = viewsByPassion.get(passion);
    viewsByPassion.remove(passion);
    view.delete();
  }

  private ITraitReference[] getAllTraits() {
    return model.getAllTraits();
  }
  
  private ITraitReference[] getAllEligibleTraits()
  {
	return model.getAllEligibleTraits();
  }

  private void updatePassionViewButtons() {
    for (ITraitReference trait : getAllTraits()) {
      for (ISubTrait passion : getPassionContainer(trait).getSubTraits()) {
        IPassionView view = viewsByPassion.get(passion);
        view.setDeleteButtonEnabled(passion.getCreationValue() == 0 || !model.isExperienced());
      }
    }
    
  }

  private void addPassionView(final IPassion passion) {
    final ITraitReference traitReference = passion.getTraitReference();
    String traitName = i18ner.getScreenName(traitReference);
    String passionName = passion.getName();
    Icon deleteIcon = new BasicUi(resources).getRemoveIcon();
    final IPassionView passionView = view.addPassionView(
        traitName,
        passionName,
        deleteIcon,
        passion.getCurrentValue(),
        passion.getMaximalValue());
    new TraitPresenter(passion, passionView).initPresentation();
    passionView.addDeleteListener(new IChangeListener() {
      public void changeOccurred() {
        getPassionContainer(traitReference).removeSubTrait(passion);
        updateOverviewData();
      }
    });
    viewsByPassion.put(passion, passionView);
  }
}
