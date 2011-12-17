package net.sf.anathema.character.impl.view.magic;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionListener;

import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.disy.commons.swing.layout.grid.GridDialogLayoutDataFactory;
import net.sf.anathema.character.generic.framework.magic.view.IMagicViewListener;
import net.sf.anathema.character.generic.framework.magic.view.MagicLearnView;
import net.sf.anathema.character.generic.magic.spells.CircleType;
import net.sf.anathema.character.presenter.charm.SpellViewProperties;
import net.sf.anathema.character.view.magic.ISpellView;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.control.objectvalue.ObjectValueControl;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.workflow.labelledvalue.IValueView;
import net.sf.anathema.lib.workflow.labelledvalue.view.LabelledStringValueView;

public class SpellView implements ISpellView {

  private MagicLearnView magicLearnView;

  private final JPanel content = new JPanel(new GridDialogLayout(1, false));
  private final ObjectValueControl<CircleType> circleControl = new ObjectValueControl<CircleType>();
  private final JPanel detailPanel = new JPanel(new GridDialogLayout(2, false));

  private final SpellViewProperties properties;

  public SpellView(final SpellViewProperties properties) {
    this.properties = properties;
    this.magicLearnView = new MagicLearnView() {
      @Override
      protected ListSelectionListener createLearnedListListener(final JButton button, final JList list) {
        return properties.getRemoveButtonEnabledListener(button, list);
      }
    };
  }

  public JComponent getComponent() {
    return content;
  }

  public IValueView<String> addDetailValueView(String label) {
    LabelledStringValueView view = new LabelledStringValueView(label, new GridDialogLayoutData());
    view.addToStandardPanel(detailPanel);
    return view;
  }

  public JLabel addDetailTitleView() {
    JLabel label = new JLabel();
    label.setFont(label.getFont().deriveFont(Font.BOLD));
    detailPanel.add(label, GridDialogLayoutDataFactory.createHorizontalSpanData(2));
    return label;
  }

  public void initGui(IIdentificate[] circles) {
    JComponent selectionPanel = createSelectionPanel(circles);
    GridDialogLayoutData data = GridDialogLayoutDataFactory.createHorizontalFillNoGrab();
    content.add(selectionPanel, data);
    detailPanel.setBorder(new TitledBorder(properties.getDetailTitle()));
    content.add(detailPanel, data);
  }

  private JPanel createSelectionPanel(IIdentificate[] circles) {
    JPanel panel = createFilterBox(properties.getCircleString(), circles, properties.getCircleSelectionRenderer());
    magicLearnView.addAdditionalOptionsPanel(panel);
    magicLearnView.init(properties);
    JPanel selectionPanel = new JPanel(new GridDialogLayout(4, false));
    magicLearnView.addTo(selectionPanel);
    selectionPanel.setBorder(new TitledBorder(properties.getSelectionTitle()));
    return selectionPanel;
  }

  public JPanel createFilterBox(String label, Object[] objects, ListCellRenderer renderer) {
    JPanel panel = new JPanel(new GridDialogLayout(2, false));
    panel.add(new JLabel(label));
    final JComboBox box = new JComboBox(objects);
    box.setRenderer(renderer);
    panel.add(box, GridDialogLayoutData.FILL_HORIZONTAL);
    box.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        circleControl.fireValueChangedEvent((CircleType) box.getSelectedItem());
      }
    });
    return panel;
  }

  public void addMagicViewListener(IMagicViewListener listener) {
    magicLearnView.addMagicViewListener(listener);
  }

  public void addOptionListListener(ListSelectionListener listener) {
    magicLearnView.addOptionListListener(listener);
  }

  public void addSelectionListListener(ListSelectionListener listener) {
    magicLearnView.addSelectionListListener(listener);
  }

  public void addCircleSelectionListener(IObjectValueChangedListener<CircleType> listener) {
    circleControl.addObjectValueChangeListener(listener);
  }

  public void setLearnedMagic(Object[] spells) {
    magicLearnView.setLearnedMagic(spells);
  }

  public void setMagicOptions(Object[] spells) {
    magicLearnView.setMagicOptions(spells);
  }

  public void addLearnedMagic(Object[] magics) {
    magicLearnView.addLearnedMagic(magics);
  }

  public void addMagicOptions(IIdentificate[] magics, Comparator<IIdentificate> comparator) {
    magicLearnView.addMagicOptions(magics, comparator);
  }

  public void removeLearnedMagic(Object[] magics) {
    magicLearnView.removeLearnedMagic(magics);
  }

  public void removeMagicOptions(Object[] magics) {
    magicLearnView.removeMagicOptions(magics);
  }

  public void clearSelection() {
    magicLearnView.clearSelection();
  }
}