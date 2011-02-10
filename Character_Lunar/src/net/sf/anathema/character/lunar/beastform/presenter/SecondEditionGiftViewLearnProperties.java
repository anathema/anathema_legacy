package net.sf.anathema.character.lunar.beastform.presenter;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.sf.anathema.character.generic.framework.magic.view.AbstractMagicLearnProperties;
import net.sf.anathema.character.library.quality.presenter.IQualitySelection;
import net.sf.anathema.character.lunar.beastform.model.gift.IGift;
import net.sf.anathema.character.lunar.beastform.model.gift.IGiftModel;
import net.sf.anathema.character.lunar.beastform.view.IGiftLearnViewProperties;
import net.sf.anathema.lib.gui.list.LegalityCheckListCellRenderer;
import net.sf.anathema.lib.resources.IResources;

public class SecondEditionGiftViewLearnProperties extends AbstractMagicLearnProperties implements IGiftLearnViewProperties {

  private final IGiftModel model;

  public SecondEditionGiftViewLearnProperties(IResources resources, IGiftModel model) {
    super(resources);
    this.model = model;
  }

  public String getAddButtonToolTip() {
    return getResources().getString("Lunar.DeadlyBeastmanTransformation.Gifts.AddTooltip_2nd"); //$NON-NLS-1$
  }

  public String getRemoveButtonToolTip() {
    return getResources().getString("Lunar.DeadlyBeastmanTransformation.Gifts.RemoveTooltip_2nd"); //$NON-NLS-1$
  }

  public ListCellRenderer getAvailableMagicRenderer() {
    return new LegalityCheckListCellRenderer(getResources()) {
      @Override
      protected boolean isLegal(Object object) {
        return model.isSelectable((IGift) object);
      }

      @Override
      protected String getPrintName(IResources res, Object value) {
        return getGiftString((IGift) value);
      }
    };
  }

  @Override
  public ListCellRenderer getLearnedMagicRenderer() {
    return new DefaultListCellRenderer() {
      @SuppressWarnings("unchecked")
      @Override
      public Component getListCellRendererComponent(
          JList list,
          Object value,
          int index,
          boolean isSelected,
          boolean cellHasFocus) {
        IQualitySelection<IGift> selection = (IQualitySelection<IGift>) value;
        IGift gift = selection.getQuality();
        String printName = getGiftString(gift);
        Component renderComponent = super.getListCellRendererComponent(list, printName, index, isSelected, cellHasFocus);
        renderComponent.setEnabled(model.isActive(selection));
        return renderComponent;
      }
    };
  }

  public boolean isMagicSelectionAvailable(Object selectedValue) {
    return model.isSelectable((IGift) selectedValue);
  }

  public int getAvailableListSelectionMode() {
    return ListSelectionModel.SINGLE_SELECTION;
  }

  private String getGiftString(IGift gift) {
    return getResources().getString("DeadlyBeastmanTransformation.Gift." + gift.getId()); //$NON-NLS-1$
  }

  public ListSelectionListener getRemoveButtonEnabledListener(final JButton button, final JList list) {
    return new ListSelectionListener() {
      public void valueChanged(ListSelectionEvent e) {
        boolean enabled = !list.isSelectionEmpty();
        for (Object object : list.getSelectedValues()) {
          IQualitySelection<IGift> selection = (IQualitySelection<IGift>) object;
          if (model.isCreationLearnedSelectionInExperiencedCharacter(selection)) {
            enabled = false;
            break;
          }
        }
        button.setEnabled(enabled);
      }
    };
  }
}