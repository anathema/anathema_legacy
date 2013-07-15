package net.sf.anathema.hero.charms.display.tree;

import net.sf.anathema.hero.charms.display.view.CascadeSelectionView;
import net.sf.anathema.hero.charms.display.presenter.ICharmGroupChangeListener;
import net.sf.anathema.character.main.magic.model.charm.ICharmGroup;
import net.sf.anathema.character.main.magic.model.charmtree.GroupCharmTree;
import net.sf.anathema.hero.advance.overview.presenter.SelectIdentifierConfiguration;
import net.sf.anathema.hero.charms.display.coloring.CharmDye;
import net.sf.anathema.hero.charms.display.special.NullSpecialCharmPresenter;
import net.sf.anathema.hero.charms.display.special.SpecialCharmViewPresenter;
import net.sf.anathema.hero.charms.model.CharmGroupCollection;
import net.sf.anathema.hero.charms.display.model.CharmTypes;
import net.sf.anathema.lib.compare.I18nedIdentificateSorter;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.platform.tree.display.CascadeLoadedListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AbstractCascadePresenter implements ICascadeSelectionPresenter {

  private final Resources resources;
  private ICharmGroupChangeListener changeListener;
  private CascadeSelectionView view;
  private CharmDye dye;
  private CharmTypes charmTypes;
  protected CharmGroupCollection charmGroups;
  private SpecialCharmViewPresenter specialCharmPresenter = new NullSpecialCharmPresenter();
  private AlienCharmPresenter alienPresenter = new NullAlienCharmPresenter();
  private CharmInteractionPresenter interactionPresenter = new NullInteractionPresenter();

  public AbstractCascadePresenter(Resources resources) {
    this.resources = resources;
  }

  public void initPresentation() {
    createCharmTypeSelector();
    listenForCascadeLoading();
    initCharmTypeSelectionListening();
    specialCharmPresenter.initPresentation();
    view.whenCursorLeavesCharmAreaResetAllPopups();
    createCharmGroupSelector();
    createHelpText();
    alienPresenter.initPresentation();
    interactionPresenter.initPresentation();
  }

  private void initCharmTypeSelectionListening() {
    view.addCharmTypeSelectionListener(new ObjectValueListener<Identifier>() {
      @Override
      public void valueChanged(Identifier cascadeType) {
        handleTypeSelectionChange(cascadeType);
      }
    });
  }

  private void listenForCascadeLoading() {
    view.addCascadeLoadedListener(new CascadeLoadedListener() {
      @Override
      public void cascadeLoaded() {
        dye.setCharmVisuals();
      }
    });
    view.addCascadeLoadedListener(new CascadeLoadedListener() {
      @Override
      public void cascadeLoaded() {
        specialCharmPresenter.showSpecialViews();
      }
    });
  }

  protected Resources getResources() {
    return resources;
  }

  protected void createCharmGroupSelector() {
    ICharmGroup[] allGroups = charmGroups.getCharmGroups();
    AgnosticUIConfiguration config = new SelectIdentifierConfiguration(resources);
    view.addCharmGroupSelector(getResources().getString("CardView.CharmConfiguration.AlienCharms.CharmGroup"), config, changeListener,
            allGroups);
  }

  protected void createCharmTypeSelector() {
    Identifier[] types = charmTypes.getCurrentCharmTypes();
    view.addCharmTypeSelector(getResources().getString("CharmTreeView.GUI.CharmType"), types, new SelectIdentifierConfiguration(resources));
  }

  private void createHelpText() {
    view.addCharmCascadeHelp(resources.getString("CharmTreeView.GUI.HelpText"));
  }

  private List<ICharmGroup> sortCharmGroups(ICharmGroup[] originalGroups) {
    ArrayList<ICharmGroup> filteredGroups = new ArrayList<>();
    Collections.addAll(filteredGroups, originalGroups);
    return new I18nedIdentificateSorter<ICharmGroup>().sortAscending(filteredGroups, resources);
  }

  protected void setSpecialPresenter(SpecialCharmViewPresenter presenter) {
    this.specialCharmPresenter = presenter;
  }

  protected void setView(CascadeSelectionView view) {
    this.view = view;
  }

  protected void setChangeListener(ICharmGroupChangeListener charmGroupChangeListener) {
    this.changeListener = charmGroupChangeListener;
  }

  protected void setCharmDye(CharmDye dye) {
    this.dye = dye;
  }

  protected void setCharmTypes(CharmTypes types) {
    this.charmTypes = types;
  }

  private void handleTypeSelectionChange(Identifier cascadeType) {
    if (cascadeType == null) {
      view.fillCharmGroupBox(new Identifier[0]);
      return;
    }
    GroupCharmTree charmTree = getCharmTree(cascadeType);
    if (charmTree == null) {
      view.fillCharmGroupBox(new Identifier[0]);
      return;
    }
    ICharmGroup[] allCharmGroups = charmTree.getAllCharmGroups();
    List<ICharmGroup> sortedGroups = sortCharmGroups(allCharmGroups);
    view.fillCharmGroupBox(sortedGroups.toArray(new ICharmGroup[sortedGroups.size()]));
    specialCharmPresenter.showSpecialViews();
  }

  protected abstract GroupCharmTree getCharmTree(Identifier type);

  protected void setAlienCharmPresenter(AlienCharmPresenter presenter) {
    this.alienPresenter = presenter;
  }

  protected void setInteractionPresenter(CharmInteractionPresenter presenter) {
    this.interactionPresenter = presenter;
  }

  public void setCharmGroups(CharmGroupCollection charmGroups) {
    this.charmGroups = charmGroups;
  }
}