package net.sf.anathema.character.library.virtueflaw.presenter;

import net.sf.anathema.character.generic.framework.additionaltemplate.listening.VirtueChangeListener;
import net.sf.anathema.character.generic.traits.TraitType;
import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.character.library.trait.presenter.TraitPresenter;
import net.sf.anathema.character.library.virtueflaw.model.IVirtueFlaw;
import net.sf.anathema.charmtree.presenter.SelectIdentifierConfiguration;
import net.sf.anathema.framework.value.IIntValueView;
import net.sf.anathema.framework.view.AbstractSelectCellRenderer;
import net.sf.anathema.lib.control.IBooleanValueChangedListener;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.gui.Presenter;
import net.sf.anathema.lib.gui.selection.IObjectSelectionView;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.lib.workflow.textualdescription.TextualPresentation;

import javax.swing.JList;
import java.awt.Component;

public class VirtueFlawPresenter implements Presenter {

  private final Resources resources;
  private final IVirtueFlawView view;
  private final IVirtueFlawModel model;

  public VirtueFlawPresenter(Resources resources, IVirtueFlawView virtueFlawView, IVirtueFlawModel model) {
    this.resources = resources;
    this.view = virtueFlawView;
    this.model = model;
  }

  @Override
  public void initPresentation() {
    initBasicPresentation();
    initAdditionalPresentation();
    initChangeableListening();
    initLimitPresentation(model.getVirtueFlaw());
  }

  protected void initAdditionalPresentation() {
    // Nothing to do
  }

  protected void initBasicPresentation() {
    IVirtueFlaw virtueFlaw = model.getVirtueFlaw();
    initRootPresentation(virtueFlaw);
    initNamePresentation(virtueFlaw);
  }

  protected void initLimitPresentation(IVirtueFlaw virtueFlaw) {
    Trait trait = virtueFlaw.getLimitTrait();
    IIntValueView traitView =
            view.addLimitValueView(getResources().getString(trait.getType().getId()), trait.getCurrentValue(), trait.getMaximalValue());
    new TraitPresenter(trait, traitView).initPresentation();
  }

  protected void initRootPresentation(IVirtueFlaw virtueFlaw) {
    initRootPresentation(virtueFlaw, "VirtueFlaw.Root.Name");
  }

  protected void initRootPresentation(final IVirtueFlaw virtueFlaw, String nameReference) {
    final IObjectSelectionView<TraitType> rootView = view.addVirtueFlawRootSelectionView(resources.getString(nameReference), new VirtueTypeConfiguration());
    virtueFlaw.addRootChangeListener(new IChangeListener() {
      @Override
      public void changeOccurred() {
        rootView.setSelectedObject(virtueFlaw.getRoot());
      }
    });
    rootView.addObjectSelectionChangedListener(new ObjectValueListener<TraitType>() {
      @Override
      public void valueChanged(TraitType newValue) {
        virtueFlaw.setRoot(newValue);
      }
    });
    model.addVirtueChangeListener(new VirtueChangeListener() {
      @Override
      public void configuredChangeOccured() {
        updateRootView(rootView);
      }
    });
    updateRootView(rootView);
  }

  private void updateRootView(IObjectSelectionView<TraitType> rootView) {
    TraitType rootCopy = model.getVirtueFlaw().getRoot();
    rootView.setObjects(model.getFlawVirtueTypes());
    rootView.setSelectedObject(rootCopy);
  }

  protected ITextView initNamePresentation(IVirtueFlaw virtueFlaw) {
    ITextView titleView = view.addTextView(resources.getString("VirtueFlaw.Name.Name"), 30);
    new TextualPresentation().initView(titleView, virtueFlaw.getName());
    return titleView;
  }

  protected void initChangeableListening() {
    model.addVirtueFlawChangableListener(new IBooleanValueChangedListener() {
      @Override
      public void valueChanged(boolean newValue) {
        view.setEnabled(newValue);
      }
    });
    view.setEnabled(model.isVirtueFlawChangable());
  }

  protected final IVirtueFlawModel getModel() {
    return model;
  }

  protected final Resources getResources() {
    return resources;
  }

  private class VirtueTypeConfiguration extends SelectIdentifierConfiguration {
    public VirtueTypeConfiguration() {
      super(VirtueFlawPresenter.this.resources);
    }

    @Override
    protected String getKeyForObject(Identifier value) {
      return "VirtueType.Name." + value.getId();
    }
  }
}
