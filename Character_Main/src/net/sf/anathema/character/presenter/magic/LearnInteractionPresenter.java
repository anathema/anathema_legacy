package net.sf.anathema.character.presenter.magic;

import net.sf.anathema.character.model.charm.ICharmConfiguration;
import net.sf.anathema.character.model.charm.ICharmLearnListener;
import net.sf.anathema.character.model.charm.ILearningCharmGroup;
import net.sf.anathema.charmtree.presenter.CharmInteractionPresenter;
import net.sf.anathema.charmtree.presenter.view.ICharmTreeViewProperties;
import net.sf.anathema.charmtree.presenter.view.ICharmView;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.platform.svgtree.presenter.view.CharmInteractionListener;

import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;

public class LearnInteractionPresenter implements CharmInteractionPresenter {

  private ICharmView view;
  private CharacterCharmModel model;
  private CharacterCharmGroupChangeListener groupChangeListener;
  private ICharmTreeViewProperties viewProperties;
  private CharacterCharmDye dye;

  public LearnInteractionPresenter(CharacterCharmModel model, ICharmView view, CharacterCharmGroupChangeListener charmGroupChangeListener,
                                   ICharmTreeViewProperties viewProperties, CharacterCharmDye dye) {
    this.model = model;
    this.view = view;
    this.groupChangeListener = charmGroupChangeListener;
    this.viewProperties = viewProperties;
    this.dye = dye;
  }

  @Override
  public void initPresentation() {
    ICharmConfiguration charms = model.getCharmConfiguration();
    view.addCharmInteractionListener(new CharmInteractionListener() {
      @Override
      public void nodeSelected(String charmId) {
        if (viewProperties.isRequirementNode(charmId)) {
          return;
        }
        model.toggleLearned(charmId);
      }

      @Override
      public void nodeDetailsDemanded(String nodeId) {
        // nothing to do
      }
    });
    initCharmLearnListening(charms);
    charms.addLearnableListener(new IChangeListener() {
      @Override
      public void changeOccurred() {
        dye.setCharmVisuals();
      }
    });
    ensureRefreshAfterAutomaticUnlearn();
  }

  private void ensureRefreshAfterAutomaticUnlearn() {
    view.getCharmComponent().addHierarchyListener(new HierarchyListener() {
      @Override
      public void hierarchyChanged(HierarchyEvent e) {
        groupChangeListener.reselect();
      }
    });
  }

  private void initCharmLearnListening(ICharmConfiguration charmConfiguration) {
    ICharmLearnListener charmLearnListener = new CharmLearnVisualizer(dye);
    for (ILearningCharmGroup group : charmConfiguration.getAllGroups()) {
      group.addCharmLearnListener(charmLearnListener);
    }
  }
}
