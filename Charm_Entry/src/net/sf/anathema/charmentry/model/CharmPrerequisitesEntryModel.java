package net.sf.anathema.charmentry.model;

import java.util.ArrayList;
import java.util.List;

import net.disy.commons.core.util.SimpleBlock;
import net.sf.anathema.character.generic.impl.magic.persistence.CharmCache;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.charmentry.model.data.IConfigurableCharmData;
import net.sf.anathema.charmentry.presenter.model.ICharmPrerequisitesEntryModel;
import net.sf.anathema.charmentry.presenter.model.IPrerequisitesModel;
import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.gui.wizard.workflow.CheckInputListener;

public class CharmPrerequisitesEntryModel implements ICharmPrerequisitesEntryModel {

  private final IConfigurableCharmData charmData;
  private final ChangeControl control = new ChangeControl();

  public CharmPrerequisitesEntryModel(
      IHeaderDataModel headerModel,
      IPrerequisitesModel prerequisiteModel,
      IConfigurableCharmData charmData) {
    this.charmData = charmData;
    final CheckInputListener changeListener = new CheckInputListener(new SimpleBlock() {
      public void execute() {
        control.fireChangedEvent();
      }
    });
    headerModel.addModelListener(changeListener);
    prerequisiteModel.addModelListener(changeListener);
  }

  public void addModelListener(IChangeListener inputListener) {
    control.addChangeListener(inputListener);
  }

  public ICharm[] getAvailableCharms() throws PersistenceException {
    if (charmData.getCharacterType() == null
        || charmData.getEdition() == null
        || charmData.getPrimaryTraitType() == null) {
      return new ICharm[0];
    }
    IExaltedRuleSet set = charmData.getEdition().getDefaultRuleset();
    ICharm[] charms = CharmCache.getInstance().getCharms(charmData.getCharacterType(), set);
    List<ICharm> filterList = new ArrayList<ICharm>();
    for (ICharm charm : charms) {
      if (charm.getPrimaryTraitType() == charmData.getPrimaryTraitType()) {
        filterList.add(charm);
      }
    }
    return filterList.toArray(new ICharm[filterList.size()]);
  }

  public void setPrerequisiteCharms(ICharm[] charms) {
    charmData.setParentCharms(charms);
  }

  public void setRequiresExcellency(boolean required) {
    charmData.setExcellencyRequired(required);
  }
}