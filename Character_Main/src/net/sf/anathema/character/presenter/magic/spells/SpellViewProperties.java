package net.sf.anathema.character.presenter.magic.spells;

import net.sf.anathema.character.generic.framework.magic.view.AbstractMagicLearnProperties;
import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.generic.magic.description.MagicDescriptionProvider;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.model.ISpellConfiguration;
import net.sf.anathema.character.view.magic.ISpellViewProperties;
import net.sf.anathema.framework.presenter.view.IdentificateListCellRenderer;
import net.sf.anathema.lib.gui.list.LegalityCheckListCellRenderer;
import net.sf.anathema.lib.resources.Resources;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.Component;

public class SpellViewProperties extends AbstractMagicLearnProperties implements ISpellViewProperties {

  private final ISpellConfiguration spellConfiguration;
  private final ICharacter character;
  private final SpellTooltipBuilder tooltipBuilder;

  public SpellViewProperties(Resources resources, ICharacter character, MagicDescriptionProvider magicDescriptionProvider) {
    super(resources);
    this.character = character;
    this.spellConfiguration = character.getSpells();
    this.tooltipBuilder = new SpellTooltipBuilder(resources, this, magicDescriptionProvider);
  }

  @Override
  public String getCircleLabel() {
    return getResources().getString("CardView.CharmConfiguration.Spells.Circle");
  }

  @Override
  public ListCellRenderer getAvailableMagicRenderer() {
    return new LegalityCheckListCellRenderer(getResources()) {

      @Override
      public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        JComponent rendererComponent = (JComponent) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        String tooltip = tooltipBuilder.createTooltip((ISpell) value);
        rendererComponent.setToolTipText(tooltip);
        return rendererComponent;
      }

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
    return getResources().getString("CardView.CharmConfiguration.Spells.AddToolTip");
  }

  @Override
  public String getRemoveButtonToolTip() {
    return getResources().getString("CardView.CharmConfiguration.Spells.RemoveToolTip");
  }

  @Override
  public ListSelectionListener getRemoveButtonEnabledListener(final JButton button, final JList list) {
    return new ListSelectionListener() {
      @Override
      public void valueChanged(ListSelectionEvent e) {
        boolean enabled = !list.isSelectionEmpty();
        if (enabled && character.isExperienced()) {
          for (Object spellObject : list.getSelectedValuesList()) {
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
}