package net.sf.anathema.charmentry.model;

import net.disy.commons.core.util.SimpleBlock;
import net.sf.anathema.character.generic.impl.magic.persistence.ICharmCache;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.charmentry.model.data.IConfigurableCharmData;
import net.sf.anathema.charmentry.presenter.model.ICharmPrerequisitesEntryModel;
import net.sf.anathema.charmentry.presenter.model.IPrerequisitesModel;
import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.gui.wizard.workflow.CheckInputListener;

import java.util.ArrayList;
import java.util.List;

public class CharmPrerequisitesEntryModel implements ICharmPrerequisitesEntryModel {

  private final IConfigurableCharmData charmData;
  private final ICharmCache cache;
  private final ChangeControl control = new ChangeControl();

  public CharmPrerequisitesEntryModel(
      IHeaderDataModel headerModel,
      IPrerequisitesModel prerequisiteModel,
      IConfigurableCharmData charmData,
      ICharmCache cache) {
    this.charmData = charmData;
    this.cache = cache;
    final CheckInputListener changeListener = new CheckInputListener(new SimpleBlock() {
      @Override
      public void execute() {
        control.fireChangedEvent();
      }
    });
    headerModel.addModelListener(changeListener);
    prerequisiteModel.addModelListener(changeListener);
  }

  @Override
  public void addModelListener(IChangeListener inputListener) {
    control.addChangeListener(inputListener);
  }

  @Override
  public ICharm[] getAvailableCharms() throws PersistenceException {
    if (charmData.getCharacterType() == null
        || charmData.getPrimaryTraitType() == null) {
      return new ICharm[0];
    }
    ICharm[] charms = cache.getCharms(charmData.getCharacterType());
    List<ICharm> filterList = new ArrayList<ICharm>();
    for (ICharm charm : charms) {
      if (charm.getPrimaryTraitType() == charmData.getPrimaryTraitType()) {
        filterList.add(charm);
      }
    }
    return filterList.toArray(new ICharm[filterList.size()]);
  }

  @Override
  public void setPrerequisiteCharms(ICharm[] charms) {
    charmData.setParentCharms(charms);
  }

  @Override
  public void setRequiresExcellency(boolean required) {
    charmData.setExcellencyRequired(required);
  }
}