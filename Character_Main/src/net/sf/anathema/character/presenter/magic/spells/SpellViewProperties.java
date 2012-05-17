package net.sf.anathema.character.presenter.magic.spells;

import net.sf.anathema.character.generic.framework.magic.view.AbstractMagicLearnProperties;
import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.model.ISpellConfiguration;
import net.sf.anathema.character.view.magic.ISpellViewProperties;
import net.sf.anathema.framework.presenter.view.IdentificateListCellRenderer;
import net.sf.anathema.lib.gui.list.LegalityCheckListCellRenderer;
import net.sf.anathema.lib.resources.IResources;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class SpellViewProperties extends AbstractMagicLearnProperties implements ISpellViewProperties {

  private final ISpellConfiguration spellConfiguration;
  private final ICharacter character;

  public SpellViewProperties(IResources resources, ICharacter character) {
    super(resources);
    this.character = character;
    this.spellConfiguration = character.getSpells();
  }

  @Override
  public String getCircleString() {
    return getResources().getString("CardView.CharmConfiguration.Spells.Circle"); //$NON-NLS-1$
  }

  @Override
  public String getLearnedSpellString() {
    return getResources().getString("CardView.CharmConfiguration.Spells.LearnedSpells"); //$NON-NLS-1$
  }

  @Override
  public ListCellRenderer getAvailableMagicRenderer() {
    return new LegalityCheckListCellRenderer(getResources()) {
      private static final long serialVersionUID = 7840060419690645799L;

      @Override
      protected boolean isLegal(Object object) {
        return spellConfiguration.isSpellAllowed((ISpell) object);
      }
    };
  }

  @Override
  public ListCellRenderer getCircleSelectionRenderer() {
    return new IdentificateListCellRenderer(getResources());
  }

  @Override
  public boolean isMagicSelectionAvailable(Object selection) {
    return selection != null && spellConfiguration.isSpellAllowed((ISpell) selection);
  }

  @Override
  public int getAvailableListSelectionMode() {
    return ListSelectionModel.MULTIPLE_INTERVAL_SELECTION;
  }

  @Override
  public String getAddButtonToolTip() {
    return getResources().getString("CardView.CharmConfiguration.Spells.AddToolTip"); //$NON-NLS-1$  
  }

  @Override
  public String getRemoveButtonToolTip() {
    return getResources().getString("CardView.CharmConfiguration.Spells.RemoveToolTip"); //$NON-NLS-1$
  }

  @Override
  public ListSelectionListener getRemoveButtonEnabledListener(final JButton button, final JList list) {
    return new ListSelectionListener() {
      @Override
      public void valueChanged(ListSelectionEvent e) {
        boolean enabled = !list.isSelectionEmpty();
        if (enabled && character.isExperienced()) {
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

  @Override
  public String getDetailTitle() {
    return getResources().getString("CardView.CharmConfiguration.Spells.Details.Title"); //$NON-NLS-1$;
  }

  @Override
  public String getCostString() {
    return getResources().getString("CardView.CharmConfiguration.Spells.Cost"); //$NON-NLS-1$
  }

  @Override
  public String getSourceString() {
    return getResources().getString("CardView.CharmConfiguration.Spells.Source"); //$NON-NLS-1$
  }

  @Override
  public String getSelectionTitle() {
    return getResources().getString("CardView.CharmConfiguration.Spells.Selection.Title"); //$NON-NLS-1$
  }

  public String getTargetString() {
    return getResources().getString("CardView.CharmConfiguration.Spells.Target"); //$NON-NLS-1$
  }

  public String getUndefinedString() {
    return getResources().getString("CardView.CharmConfiguration.Spells.Target.Undefined"); //$NON-NLS-1$
  }
}
