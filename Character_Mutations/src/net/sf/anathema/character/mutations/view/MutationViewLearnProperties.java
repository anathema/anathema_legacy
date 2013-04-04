package net.sf.anathema.character.mutations.view;

import net.sf.anathema.character.generic.framework.magic.view.AbstractMagicLearnProperties;
import net.sf.anathema.character.generic.rules.IExaltedSourceBook;
import net.sf.anathema.character.library.quality.presenter.IQualitySelection;
import net.sf.anathema.character.mutations.model.IMutation;
import net.sf.anathema.character.mutations.model.IMutationsModel;
import net.sf.anathema.lib.gui.TooltipBuilder;
import net.sf.anathema.lib.gui.list.LegalityCheckListCellRenderer;
import net.sf.anathema.lib.resources.Resources;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.Component;

public class MutationViewLearnProperties extends AbstractMagicLearnProperties implements IMutationLearnViewProperties {

  private final IMutationsModel model;

  public MutationViewLearnProperties(Resources resources, IMutationsModel model) {
    super(resources);
    this.model = model;
  }

  @Override
  public String getAddButtonToolTip() {
    return getResources().getString("Mutations.Tooltips.AddTooltip");
  }

  @Override
  public String getRemoveButtonToolTip() {
    return getResources().getString("Mutations.Tooltips.RemoveTooltip");
  }

  @Override
  public ListCellRenderer getAvailableMagicRenderer() {
    return new LegalityCheckListCellRenderer(getResources()) {

      @Override
      public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        JComponent component = (JComponent) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        component.setToolTipText(MutationViewLearnProperties.this.getToolTipText(value));
        return component;
      }

      @Override
      protected boolean isLegal(Object object) {
        return model.isSelectable((IMutation) object);
      }

      @Override
      protected String getPrintName(Resources res, Object value) {
        return getMutationString((IMutation) value, true);
      }
    };
  }

  private String getToolTipText(Object obj) {
    IMutation mutation = (IMutation) obj;
    IExaltedSourceBook source = mutation.getSource();

    if (source != null) {
      StringBuilder builder = new StringBuilder();
      builder.append(getResources().getString(createSourceBookKey(source)));
      Integer page = mutation.getPage();
      if (page != null) {
        builder.append(TooltipBuilder.CommaSpace);
        builder.append(getResources().getString("CharmTreeView.ToolTip.Page"));
        builder.append(TooltipBuilder.Space);
        builder.append(page);
      }
      return builder.toString();
    }
    return null;
  }

  private String createSourceBookKey(IExaltedSourceBook source) {
    return "ExaltedSourceBook." + source.getId();
  }

  @Override
  public ListCellRenderer getLearnedMagicRenderer() {
    return new DefaultListCellRenderer() {

      @SuppressWarnings("unchecked")
      @Override
      public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        IQualitySelection<IMutation> selection = (IQualitySelection<IMutation>) value;
        IMutation gift = selection.getQuality();
        String printName = getMutationString(gift, false);
        Component renderComponent = super.getListCellRendererComponent(list, printName, index, isSelected, cellHasFocus);
        renderComponent.setEnabled(model.isActive(selection));
        return renderComponent;
      }
    };
  }

  @Override
  public boolean isMagicSelectionAvailable(Object selectedValue) {
    return model.isSelectable((IMutation) selectedValue);
  }

  @Override
  public int getAvailableListSelectionMode() {
    return ListSelectionModel.SINGLE_SELECTION;
  }

  private String getMutationString(IMutation mutation, boolean showType) {
    String typeString = getResources().getString("Mutations.Type." + mutation.getType().getId());
    String mutationString = getResources().getString("Mutations.Mutation."
            + mutation.getId());
    return (showType ? "(" + typeString + ") " : "") + mutationString;
  }

  @Override
  public ListSelectionListener getRemoveButtonEnabledListener(final JButton button, final JList list) {
    return new ListSelectionListener() {
      @Override
      public void valueChanged(ListSelectionEvent e) {
        boolean enabled = !list.isSelectionEmpty();
        button.setEnabled(enabled);
      }
    };
  }
}
