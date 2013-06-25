package net.sf.anathema.character.presenter.magic;

import net.sf.anathema.character.main.model.charms.CharmsModel;
import net.sf.anathema.character.model.charm.ICharmLearnListener;
import net.sf.anathema.character.model.charm.ILearningCharmGroup;
import net.sf.anathema.charmtree.presenter.CharmInteractionPresenter;
import net.sf.anathema.charmtree.view.ICharmTreeViewProperties;
import net.sf.anathema.charmtree.view.ICharmView;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.platform.tree.presenter.view.NodeInteractionListener;

public class LearnInteractionPresenter implements CharmInteractionPresenter {

  private ICharmView view;
  private CharacterCharmModel model;
  private ICharmTreeViewProperties viewProperties;
  private CharacterCharmDye dye;

  public LearnInteractionPresenter(CharacterCharmModel model, ICharmView view, ICharmTreeViewProperties viewProperties, CharacterCharmDye dye) {
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
