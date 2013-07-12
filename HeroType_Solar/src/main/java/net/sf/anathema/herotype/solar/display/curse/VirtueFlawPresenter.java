package net.sf.anathema.herotype.solar.display.curse;

import net.sf.anathema.character.main.library.trait.Trait;
import net.sf.anathema.character.main.library.trait.presenter.TraitPresenter;
import net.sf.anathema.hero.advance.overview.presenter.SelectIdentifierConfiguration;
import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.character.main.traits.types.VirtueType;
import net.sf.anathema.framework.value.IntValueView;
import net.sf.anathema.hero.model.change.ChangeFlavor;
import net.sf.anathema.hero.model.change.FlavoredChangeListener;
import net.sf.anathema.hero.display.configurableview.ConfigurableCharacterView;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.traits.TraitChangeFlavor;
import net.sf.anathema.herotype.solar.model.curse.VirtueFlaw;
import net.sf.anathema.herotype.solar.model.curse.VirtueFlawModel;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.gui.Presenter;
import net.sf.anathema.lib.gui.selection.IObjectSelectionView;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.lib.workflow.textualdescription.TextualPresentation;

public class VirtueFlawPresenter implements Presenter {

  private Hero hero;
  private final Resources resources;
  private final ConfigurableCharacterView view;
  private final VirtueFlawModel model;

  public VirtueFlawPresenter(Hero hero, Resources resources, ConfigurableCharacterView virtueFlawView, VirtueFlawModel model) {
    this.hero = hero;
    this.resources = resources;
    this.view = virtueFlawView;
    this.model = model;
  }

  @Override
  public void initPresentation() {
    initBasicPresentation();
    initAdditionalPresentation();
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
    IntValueView traitView =
            view.addDotSelector(getResources().getString(trait.getType().getId()), trait.getMaximalValue());
    new TraitPresenter(trait, traitView).initPresentation();
  }

  protected void initRootPresentation(final VirtueFlaw virtueFlaw) {
    final IObjectSelectionView<TraitType> rootView = view.addSelectionView(resources.getString("VirtueFlaw.Root.Name"), new VirtueTypeConfiguration());
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
    TraitType root = model.getVirtueFlaw().getRoot();
    rootView.setObjects(model.getFlawVirtueTypes());
    rootView.setSelectedObject(root);
  }

  protected ITextView initNamePresentation(VirtueFlaw virtueFlaw) {
    ITextView titleView = view.addLineView(resources.getString("VirtueFlaw.Name.Name"));
    new TextualPresentation().initView(titleView, virtueFlaw.getName());
    return titleView;
  }

  protected final Resources getResources() {
    return resources;
  }

  private class VirtueTypeConfiguration extends SelectIdentifierConfiguration<TraitType> {
    public VirtueTypeConfiguration() {
      super(VirtueFlawPresenter.this.resources);
    }

    @Override
    protected String getKeyForObject(TraitType value) {
      return "VirtueType.Name." + value.getId();
    }
  }
}