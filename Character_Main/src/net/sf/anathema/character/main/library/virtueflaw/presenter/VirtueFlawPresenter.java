package net.sf.anathema.character.main.library.virtueflaw.presenter;

import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.character.main.traits.types.VirtueType;
import net.sf.anathema.character.main.library.trait.Trait;
import net.sf.anathema.character.main.library.trait.presenter.TraitPresenter;
import net.sf.anathema.character.main.library.virtueflaw.model.VirtueFlaw;
import net.sf.anathema.character.main.library.virtueflaw.model.VirtueFlawModel;
import net.sf.anathema.hero.traits.TraitChangeFlavor;
import net.sf.anathema.character.main.charmtree.presenter.SelectIdentifierConfiguration;
import net.sf.anathema.framework.value.IIntValueView;
import net.sf.anathema.hero.change.ChangeFlavor;
import net.sf.anathema.hero.change.FlavoredChangeListener;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.control.IBooleanValueChangedListener;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.gui.Presenter;
import net.sf.anathema.lib.gui.selection.IObjectSelectionView;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.lib.workflow.textualdescription.TextualPresentation;

public class VirtueFlawPresenter implements Presenter {

  private Hero hero;
  private final Resources resources;
  private final IVirtueFlawView view;
  private final VirtueFlawModel model;

  public VirtueFlawPresenter(Hero hero, Resources resources, IVirtueFlawView virtueFlawView, VirtueFlawModel model) {
    this.hero = hero;
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
    VirtueFlaw virtueFlaw = model.getVirtueFlaw();
    initRootPresentation(virtueFlaw);
    initNamePresentation(virtueFlaw);
  }

  protected void initLimitPresentation(VirtueFlaw virtueFlaw) {
    Trait trait = virtueFlaw.getLimitTrait();
    IIntValueView traitView =
            view.addLimitValueView(getResources().getString(trait.getType().getId()), trait.getCurrentValue(), trait.getMaximalValue());
    new TraitPresenter(trait, traitView).initPresentation();
  }

  protected void initRootPresentation(VirtueFlaw virtueFlaw) {
    initRootPresentation(virtueFlaw, "VirtueFlaw.Root.Name");
  }

  protected void initRootPresentation(final VirtueFlaw virtueFlaw, String nameReference) {
    final IObjectSelectionView<TraitType> rootView = view.addVirtueFlawRootSelectionView(resources.getString(nameReference), new VirtueTypeConfiguration());
    virtueFlaw.addRootChangeListener(new ChangeListener() {
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
    hero.getChangeAnnouncer().addListener(new FlavoredChangeListener() {
      @Override
      public void changeOccurred(ChangeFlavor flavor) {
        if (TraitChangeFlavor.changes(flavor, VirtueType.values())) {
          updateRootView(rootView);
        }
      }
    });
     updateRootView(rootView);
  }

  private void updateRootView(IObjectSelectionView<TraitType> rootView) {
    TraitType rootCopy = model.getVirtueFlaw().getRoot();
    rootView.setObjects(model.getFlawVirtueTypes());
    rootView.setSelectedObject(rootCopy);
  }

  protected ITextView initNamePresentation(VirtueFlaw virtueFlaw) {
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

  protected final VirtueFlawModel getModel() {
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
