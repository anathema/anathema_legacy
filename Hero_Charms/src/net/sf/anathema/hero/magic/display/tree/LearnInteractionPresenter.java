package net.sf.anathema.hero.magic.display.tree;

import net.sf.anathema.character.main.presenter.magic.CharacterCharmModel;
import net.sf.anathema.character.main.presenter.magic.CharmLearnVisualizer;
import net.sf.anathema.hero.charms.CharmsModel;
import net.sf.anathema.character.main.magic.charms.ICharmLearnListener;
import net.sf.anathema.character.main.magic.charms.ILearningCharmGroup;
import net.sf.anathema.character.main.magic.charmtree.presenter.CharmDye;
import net.sf.anathema.character.main.magic.charmtree.presenter.CharmInteractionPresenter;
import net.sf.anathema.character.main.magic.charmtree.view.ICharmTreeViewProperties;
import net.sf.anathema.character.main.magic.charmtree.view.ICharmView;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.platform.tree.presenter.view.NodeInteractionListener;

public class LearnInteractionPresenter implements CharmInteractionPresenter {

  private ICharmView view;
  private CharacterCharmModel model;
  private ICharmTreeViewProperties viewProperties;
  private CharmDye dye;

  public LearnInteractionPresenter(CharacterCharmModel model, ICharmView view, ICharmTreeViewProperties viewProperties, CharmDye dye) {
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
