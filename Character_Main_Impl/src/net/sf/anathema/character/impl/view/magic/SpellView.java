package net.sf.anathema.character.impl.view.magic;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionListener;

import net.disy.commons.swing.layout.grid.GridAlignment;
import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.sf.anathema.character.generic.framework.magic.view.IMagicViewListener;
import net.sf.anathema.character.generic.framework.magic.view.MagicLearnView;
import net.sf.anathema.character.view.magic.ISpellView;
import net.sf.anathema.character.view.magic.ISpellViewProperties;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.control.objectvalue.ObjectValueControl;
import net.sf.anathema.lib.util.IIdentificate;

public class SpellView implements ISpellView {

  private MagicLearnView magicLearnView;

  private JPanel content = new JPanel(new GridDialogLayout(1, false));
  private JComboBox circleComboBox;
  private final ObjectValueControl circleControl = new ObjectValueControl();
  private JLabel nameLabel;
  private JLabel circleLabel;
  private JLabel costLabel;
  private JLabel sourceLabel;

  public JComponent getComponent() {
    return content;
  }

  public void initGui(IIdentificate[] circles, final ISpellViewProperties properties) {
    JComponent selectionPanel = createSelectionPanel(circles, properties);
    JComponent detailsPanel = createDetailsPanel(properties);
    GridDialogLayoutData data = new GridDialogLayoutData();
    data.setHorizontalAlignment(GridAlignment.FILL);
    content.add(selectionPanel, data);
    content.add(detailsPanel, data);
  }

  private JPanel createSelectionPanel(IIdentificate[] circles, final ISpellViewProperties properties) {
    this.magicLearnView = new MagicLearnView() {
      @Override
      protected ListSelectionListener createLearnedListListener(final JButton button, final JList list) {
        return properties.getRemoveButtonEnabledListener(button, list);
      }
    };
    JComboBox box = magicLearnView.addFilterBox(
        properties.getCircleString(),
        circles,
        properties.getCircleSelectionRenderer());
    box.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        circleControl.fireValueChangedEvent(circleComboBox.getSelectedItem());
      }
    });
    magicLearnView.init(properties);
    JPanel selectionPanel = new JPanel(new GridDialogLayout(4, false));
    magicLearnView.addTo(selectionPanel);
    selectionPanel.setBorder(new TitledBorder(properties.getSelectionTitle()));
    return selectionPanel;
  }

  public void setSpellDetails(String name, String circle, String costString, String sourceString) {
    nameLabel.setText(name);
    circleLabel.setText(circle);
    costLabel.setText(costString);
    sourceLabel.setText(sourceString);
  }

  private JComponent createDetailsPanel(ISpellViewProperties properties) {
    JPanel detailsPanel = new JPanel(new GridDialogLayout(2, false));
    detailsPanel.setBorder(new TitledBorder(properties.getDetailTitle()));
    nameLabel = new JLabel();
    nameLabel.setFont(nameLabel.getFont().deriveFont(Font.BOLD));
    GridDialogLayoutData nameData = new GridDialogLayoutData();
    nameData.setHorizontalSpan(2);
    detailsPanel.add(nameLabel, nameData);
    detailsPanel.add(new JLabel(properties.getCircleString() + ":")); //$NON-NLS-1$
    circleLabel = new JLabel();
    detailsPanel.add(circleLabel);
    detailsPanel.add(new JLabel(properties.getCostString() + ":")); //$NON-NLS-1$
    costLabel = new JLabel();
    detailsPanel.add(costLabel);
    detailsPanel.add(new JLabel(properties.getSourceString() + ":")); //$NON-NLS-1$
    sourceLabel = new JLabel();
    detailsPanel.add(sourceLabel);
    return detailsPanel;
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

  public void addCircleSelectionListener(IObjectValueChangedListener listener) {
    circleControl.addObjectValueChangeListener(listener);
  }

  public void setLearnedMagic(Object[] spells) {
    magicLearnView.setLearnedMagic(spells);
  }

  public void setMagicOptions(Object[] spells) {
    magicLearnView.setMagicOptions(spells);
  }

  public void clearSelection() {
    magicLearnView.clearSelection();
  }

  public boolean needsScrollbar() {
    return false;
  }
}