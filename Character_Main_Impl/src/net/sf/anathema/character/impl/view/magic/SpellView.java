package net.sf.anathema.character.impl.view.magic;

import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.disy.commons.swing.layout.grid.GridDialogLayoutDataFactory;
import net.disy.commons.swing.layout.grid.IGridDialogLayoutData;
import net.sf.anathema.character.generic.framework.magic.view.IMagicViewListener;
import net.sf.anathema.character.generic.framework.magic.view.MagicLearnView;
import net.sf.anathema.character.generic.magic.spells.CircleType;
import net.sf.anathema.character.presenter.magic.spells.SpellViewProperties;
import net.sf.anathema.character.view.magic.ISpellView;
import net.sf.anathema.lib.control.IObjectValueChangedListener;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.workflow.labelledvalue.IValueView;
import net.sf.anathema.lib.workflow.labelledvalue.view.LabelledStringValueView;
import org.jmock.example.announcer.Announcer;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionListener;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;

public class SpellView implements ISpellView {

  private MagicLearnView magicLearnView;

  private final JPanel content = new JPanel(new GridDialogLayout(1, false));
  private final Announcer<IObjectValueChangedListener> circleControl = Announcer.to(IObjectValueChangedListener.class);
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

  @Override
  public JComponent getComponent() {
    return content;
  }

  @Override
  public IValueView<String> addDetailValueView(String label) {
    LabelledStringValueView view = new LabelledStringValueView(label, new GridDialogLayoutData());
    view.addToStandardPanel(detailPanel);
    return view;
  }

  @Override
  public JLabel addDetailTitleView() {
    JLabel label = new JLabel();
    label.setFont(label.getFont().deriveFont(Font.BOLD));
    detailPanel.add(label, GridDialogLayoutDataFactory.createHorizontalSpanData(2));
    return label;
  }

  @Override
  public void initGui(IIdentificate[] circles) {
    JComponent selectionPanel = createSelectionPanel(circles);
    IGridDialogLayoutData data = GridDialogLayoutData.FILL_BOTH;
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
      @Override
      public void actionPerformed(ActionEvent e) {
        circleControl.announce().valueChanged(box.getSelectedItem());
      }
    });
    return panel;
  }

  @Override
  public void addMagicViewListener(IMagicViewListener listener) {
    magicLearnView.addMagicViewListener(listener);
  }

  @Override
  public void addOptionListListener(ListSelectionListener listener) {
    magicLearnView.addOptionListListener(listener);
  }

  @Override
  public void addSelectionListListener(ListSelectionListener listener) {
    magicLearnView.addSelectionListListener(listener);
  }

  @Override
  public void addCircleSelectionListener(IObjectValueChangedListener<CircleType> listener) {
    circleControl.addListener(listener);
  }

  @Override
  public void setLearnedMagic(Object[] spells) {
    magicLearnView.setLearnedMagic(spells);
  }

  @Override
  public void setMagicOptions(Object[] spells) {
    magicLearnView.setMagicOptions(spells);
  }

  @Override
  public void addLearnedMagic(Object[] magics) {
    magicLearnView.addLearnedMagic(magics);
  }

  @Override
  public void addMagicOptions(IIdentificate[] magics, Comparator<IIdentificate> comparator) {
    magicLearnView.addMagicOptions(magics, comparator);
  }

  @Override
  public void removeLearnedMagic(Object[] magics) {
    magicLearnView.removeLearnedMagic(magics);
  }

  @Override
  public void removeMagicOptions(Object[] magics) {
    magicLearnView.removeMagicOptions(magics);
  }

  @Override
  public void clearSelection() {
    magicLearnView.clearSelection();
  }
}
