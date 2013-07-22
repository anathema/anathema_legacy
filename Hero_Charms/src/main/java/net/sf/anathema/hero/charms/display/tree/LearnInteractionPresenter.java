package net.sf.anathema.hero.charms.display.tree;

import net.sf.anathema.hero.charms.display.coloring.CharmDye;
import net.sf.anathema.hero.charms.display.model.CharmDisplayModel;
import net.sf.anathema.hero.charms.display.view.FunctionalNodeProperties;
import net.sf.anathema.hero.charms.model.CharmsModel;
import net.sf.anathema.hero.charms.model.learn.ICharmLearnListener;
import net.sf.anathema.hero.charms.model.learn.ILearningCharmGroup;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.platform.tree.display.NodeInteractionListener;
import net.sf.anathema.platform.tree.display.TreeView;

public class LearnInteractionPresenter implements CharmInteractionPresenter {

  private CharmDisplayModel model;
  private FunctionalNodeProperties viewProperties;
  private CharmDye dye;
  private TreeView treeView;

  public LearnInteractionPresenter(CharmDisplayModel model, FunctionalNodeProperties viewProperties, CharmDye dye) {
    this.model = model;
    this.viewProperties = viewProperties;
    this.dye = dye;
  }

  @Override
  public void initPresentation() {
    CharmsModel charms = model.getCharmModel();
    treeView.addNodeInteractionListener(new NodeInteractionListener() {
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
    charms.addLearnableListener(new ChangeListener() {
      @Override
      public void changeOccurred() {
        dye.setCharmVisuals();
      }
    });
  }

  private void initCharmLearnListening(CharmsModel charmConfiguration) {
    ICharmLearnListener charmLearnListener = new CharmLearnVisualizer(dye);
    for (ILearningCharmGroup group : charmConfiguration.getAllGroups()) {
      group.addCharmLearnListener(charmLearnListener);
    }
  }

  @Override
  public void operateOn(TreeView treeView) {
    this.treeView = treeView;
  }
}
