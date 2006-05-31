package net.sf.anathema.character.presenter.charm;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.sf.anathema.character.generic.framework.magic.view.AbstractMagicLearnProperties;
import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.model.ICharacterStatistics;
import net.sf.anathema.character.model.ISpellConfiguration;
import net.sf.anathema.character.view.magic.ISpellViewProperties;
import net.sf.anathema.framework.presenter.view.IdentificateListCellRenderer;
import net.sf.anathema.lib.gui.list.LegalityCheckListCellRenderer;
import net.sf.anathema.lib.resources.IResources;

public class SpellViewProperties extends AbstractMagicLearnProperties implements ISpellViewProperties {

  private final ISpellConfiguration spellConfiguration;
  private final ICharacterStatistics statistics;

  public SpellViewProperties(IResources resources, ICharacterStatistics statistics) {
    super(resources);
    this.statistics = statistics;
    this.spellConfiguration = statistics.getSpells();
  }

  public String getCircleString() {
    return getResources().getString("CardView.CharmConfiguration.Spells.Circle"); //$NON-NLS-1$
  }

  public String getLearnedSpellString() {
    return getResources().getString("CardView.CharmConfiguration.Spells.LearnedSpells"); //$NON-NLS-1$
  }

  public ListCellRenderer getAvailableMagicRenderer() {
    return new LegalityCheckListCellRenderer(getResources()) {
      @Override
      protected boolean isLegal(Object object) {
        return spellConfiguration.isSpellAllowed((ISpell) object);
      }
    };
  }

  public ListCellRenderer getCircleSelectionRenderer() {
    return new IdentificateListCellRenderer(getResources());
  }

  public boolean isMagicSelectionAvailable(Object selection) {
    return selection != null && spellConfiguration.isSpellAllowed((ISpell) selection);
  }

  public int getAvailableListSelectionMode() {
    return ListSelectionModel.MULTIPLE_INTERVAL_SELECTION;
  }

  public String getAddButtonToolTip() {
    return getResources().getString("CardView.CharmConfiguration.Spells.AddToolTip"); //$NON-NLS-1$  
  }

  public String getRemoveButtonToolTip() {
    return getResources().getString("CardView.CharmConfiguration.Spells.RemoveToolTip"); //$NON-NLS-1$
  }

  public ListSelectionListener getRemoveButtonEnabledListener(final JButton button, final JList list) {
    return new ListSelectionListener() {
      public void valueChanged(ListSelectionEvent e) {
        boolean enabled = !list.isSelectionEmpty();
        if (statistics.isExperienced()) {
          for (Object spellObject : list.getSelectedValues()) {
            ISpell spell = (ISpell) spellObject;
            if (spellConfiguration.isLearnedOnCreation(spell)) {
              enabled = false;
              break;
            }
          }
        }
        button.setEnabled(enabled);
      }
    };
  }

  public String getDetailTitle() {
    return getResources().getString("CardView.CharmConfiguration.Spells.Details.Title"); //$NON-NLS-1$;
  }

  public String getCostString() {
    return getResources().getString("CardView.CharmConfiguration.Spells.Cost"); //$NON-NLS-1$
  }

  public String getSourceString() {
    return getResources().getString("CardView.CharmConfiguration.Spells.Source"); //$NON-NLS-1$
  }

  public String getSelectionTitle() {
    return getResources().getString("CardView.CharmConfiguration.Spells.Selection.Title"); //$NON-NLS-1$
  }
}