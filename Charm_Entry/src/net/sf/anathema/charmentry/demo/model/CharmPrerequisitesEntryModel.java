package net.sf.anathema.charmentry.demo.model;

import java.util.ArrayList;
import java.util.List;

import net.disy.commons.core.util.ISimpleBlock;
import net.sf.anathema.character.generic.impl.magic.persistence.CharmCache;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.charmentry.demo.ICharmPrerequisitesEntryModel;
import net.sf.anathema.charmentry.model.IConfigurableCharmData;
import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.gui.wizard.workflow.CheckInputListener;

public class CharmPrerequisitesEntryModel implements ICharmPrerequisitesEntryModel {

  private final IConfigurableCharmData charmData;
  private final ChangeControl control = new ChangeControl();

  public CharmPrerequisitesEntryModel(IHeaderDataModel headerModel, IConfigurableCharmData charmData) {
    this.charmData = charmData;
    headerModel.addModelListener(new CheckInputListener(new ISimpleBlock() {
      public void execute() {
        control.fireChangedEvent();
      }
    }));
  }

  public void addModelListener(IChangeListener inputListener) {
    control.addChangeListener(inputListener);
  }

  public ICharm[] getAvailableCharms() throws PersistenceException {
    if (charmData.getCharacterType() == null || charmData.getEdition() == null) {
      return new ICharm[0];
    }
    IExaltedRuleSet set = charmData.getEdition().getDefaultRuleset();
    ICharm[] charms = CharmCache.getInstance().getCharms(charmData.getCharacterType(), set);
    List<ICharm> filterList = new ArrayList<ICharm>();
    for (ICharm charm : charms) {
      if (charm.getPrerequisites()[0].getType() == charmData.getPrimaryPrerequiste().getType()) {
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