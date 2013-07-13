package net.sf.anathema.hero.charms.display.tree;

import net.sf.anathema.character.main.magic.display.view.charmtree.CharmView;
import net.sf.anathema.character.main.magic.display.view.charmtree.ICharmTreeViewProperties;
import net.sf.anathema.character.main.magic.model.charm.ICharmLearnListener;
import net.sf.anathema.character.main.magic.model.charms.ILearningCharmGroup;
import net.sf.anathema.hero.charms.display.coloring.CharmDye;
import net.sf.anathema.hero.charms.model.CharacterCharmModel;
import net.sf.anathema.hero.charms.model.CharmsModel;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.platform.tree.display.NodeInteractionListener;

public class LearnInteractionPresenter implements CharmInteractionPresenter {

  private CharmView view;
  private CharacterCharmModel model;
  private ICharmTreeViewProperties viewProperties;
  private CharmDye dye;

  public LearnInteractionPresenter(CharacterCharmModel model, CharmView view, ICharmTreeViewProperties viewProperties, CharmDye dye) {
    this.model = model;
    this.view = view;
    this.viewProperties = viewProperties;
    this.dye = dye;
  }

  @Override
  public void initPresentation() {
    CharmsModel charms = model.getCharmConfiguration();
    view.addCharmInteractionListener(new NodeInteractionListener() {
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
}
