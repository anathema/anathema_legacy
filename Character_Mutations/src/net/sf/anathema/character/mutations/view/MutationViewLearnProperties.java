package net.sf.anathema.character.mutations.view;

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
import net.sf.anathema.character.mutations.model.IMutation;
import net.sf.anathema.character.mutations.model.IMutationsModel;
import net.sf.anathema.lib.gui.list.LegalityCheckListCellRenderer;
import net.sf.anathema.lib.resources.IResources;

public class MutationViewLearnProperties extends AbstractMagicLearnProperties implements IMutationLearnViewProperties {

  private final IMutationsModel model;

  public MutationViewLearnProperties(IResources resources, IMutationsModel model) {
    super(resources);
    this.model = model;
  }

  public String getAddButtonToolTip() {
    return getResources().getString("Mutations.Tooltips.AddTooltip"); //$NON-NLS-1$
  }

  public String getRemoveButtonToolTip() {
    return getResources().getString("Mutations.Tooltips.RemoveTooltip"); //$NON-NLS-1$
  }

  public ListCellRenderer getAvailableMagicRenderer() {
    return new LegalityCheckListCellRenderer(getResources()) {
		private static final long serialVersionUID = 1L;

	@Override
      protected boolean isLegal(Object object) {
        return model.isSelectable((IMutation) object);
      }

      @Override
      protected String getPrintName(IResources res, Object value) {
        return getMutationString((IMutation) value, true);
      }
    };
  }

  @Override
  public ListCellRenderer getLearnedMagicRenderer() {
    return new DefaultListCellRenderer() {
		private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
      @Override
      public Component getListCellRendererComponent(
          JList list,
          Object value,
          int index,
          boolean isSelected,
          boolean cellHasFocus) {
        IQualitySelection<IMutation> selection = (IQualitySelection<IMutation>) value;
        IMutation gift = selection.getQuality();
        String printName = getMutationString(gift, false);
        Component renderComponent = super.getListCellRendererComponent(list, printName, index, isSelected, cellHasFocus);
        renderComponent.setEnabled(model.isActive(selection));
        return renderComponent;
      }
    };
  }

  public boolean isMagicSelectionAvailable(Object selectedValue) {
    return model.isSelectable((IMutation) selectedValue);
  }

  public int getAvailableListSelectionMode() {
    return ListSelectionModel.SINGLE_SELECTION;
  }

  private String getMutationString(IMutation mutation, boolean showType) {
	  String typeString =
		  getResources().getString("Mutations.Type." + mutation.getType().getId());
  	String mutationString =
  		getResources().getString("Mutations.Mutation." //$NON-NLS-1$
  	            + mutation.getId());
      return (showType ? "(" + typeString + ") " : "") + mutationString;
  }

  public ListSelectionListener getRemoveButtonEnabledListener(final JButton button, final JList list) {
    return new ListSelectionListener() {
      public void valueChanged(ListSelectionEvent e) {
        boolean enabled = !list.isSelectionEmpty();
        /*for (Object object : list.getSelectedValues()) {
          IQualitySelection<IGift> selection = (IQualitySelection<IGift>) object;
          if (model.isCreationLearnedSelectionInExperiencedCharacter(selection)) {
            enabled = false;
            break;
          }
        }*/
        button.setEnabled(enabled);
      }
    };
  }
}