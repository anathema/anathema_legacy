package net.sf.anathema.charmentry.view;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;

import net.disy.commons.swing.layout.grid.EndOfLineMarkerComponent;
import net.disy.commons.swing.layout.grid.GridAlignment;
import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.sf.anathema.character.library.intvalue.IIntValueDisplayFactory;
import net.sf.anathema.character.library.intvalue.IntValueDisplayFactory;
import net.sf.anathema.charmentry.presenter.ISelectableTraitView;
import net.sf.anathema.framework.value.IIntValueDisplay;
import net.sf.anathema.framework.value.IIntValueView;
import net.sf.anathema.lib.gui.widgets.ChangeableJComboBox;
import net.sf.anathema.lib.gui.widgets.IChangeableJComboBox;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.workflow.container.ISelectionContainerView;
import net.sf.anathema.lib.workflow.container.view.SelectionContainerListView;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.lib.workflow.textualdescription.view.LineTextView;

public class BasicDataView {

  private final JPanel prerequisitePanel = new JPanel(new GridDialogLayout(1, false));
  private final JPanel dataPanel = new JPanel(new GridDialogLayout(5, false));
  private final JPanel controlPanel = new JPanel(new GridDialogLayout(1, false));

  public JComponent getContent() {
    dataPanel.setBorder(new TitledBorder("Basic Data"));
    JPanel contentPanel = new JPanel(new GridDialogLayout(2, false));
    GridDialogLayoutData gridDialogLayoutData = new GridDialogLayoutData();
    gridDialogLayoutData.setVerticalAlignment(GridAlignment.BEGINNING);
    gridDialogLayoutData.setHorizontalAlignment(GridAlignment.FILL);
    contentPanel.add(dataPanel, gridDialogLayoutData);
    prerequisitePanel.setBorder(new TitledBorder("Prerequisite Charms"));
    contentPanel.add(prerequisitePanel, GridDialogLayoutData.FILL_BOTH);
    GridDialogLayoutData data = createHorizontallySpanningData(2);
    data.setHorizontalAlignment(GridAlignment.END);
    contentPanel.add(controlPanel, data);
    return contentPanel;
  }

  public ITextView addSingleLineTextView(String labelString) {
    addLabel(labelString);
    LineTextView textView = new LineTextView(30);
    GridDialogLayoutData data = createHorizontallySpanningData(3);
    dataPanel.add(textView.getComponent(), data);
    dataPanel.add(new EndOfLineMarkerComponent());
    return textView;
  }

  public IChangeableJComboBox addObjectSelectionView(String label, boolean editable, ListCellRenderer renderer) {
    addLabel(label);
    ChangeableJComboBox<Object> box = new ChangeableJComboBox<Object>(null, editable);
    box.setRenderer(renderer);
    GridDialogLayoutData data = createHorizontallySpanningData(2);
    dataPanel.add(box.getComponent(), data);
    dataPanel.add(new EndOfLineMarkerComponent());
    return box;
  }

  public ISelectableTraitView addPrimaryPrerequisiteSelectionView(String string, IIntValueDisplayFactory factory) {
    addLabel(string);
    SelectableTraitView view = new SelectableTraitView(factory);
    GridDialogLayoutData data = createHorizontallySpanningData(2);
    dataPanel.add(view.getSelectionComponent(), data);
    dataPanel.add(view.getValueComponent(), data);
    return view;
  }

  public IIntValueView addEssencePrerequisiteView(
      String categoryString,
      String valueDisplayString,
      IntValueDisplayFactory factory,
      int value,
      int maxValue) {
    addLabel(categoryString);
    GridDialogLayoutData data = createHorizontallySpanningData(2);
    dataPanel.add(new JLabel(valueDisplayString), data);
    IIntValueDisplay display = factory.createIntValueDisplay(maxValue, value);
    dataPanel.add(display.getComponent(), data);
    return display;
  }

  public ISourceSelectionView addSourceView(
      String categoryString,
      String bookString,
      String pageString,
      IIdentificate[] sources) {
    addLabel(categoryString);
    SourceSelectionView view = new SourceSelectionView(bookString, pageString, sources);
    view.addTo(dataPanel);
    return view;
  }

  public ICostEntryView addCostView(String categoryString, String valueString, String textString) {
    addLabel(categoryString);
    CostEntryView view = new CostEntryView(valueString, textString);
    view.addTo(dataPanel);
    return view;
  }

  public ISelectionContainerView addPrerequisiteCharmView(ListCellRenderer renderer) {
    SelectionContainerListView view = new SelectionContainerListView();
    view.setRenderer(renderer);
    prerequisitePanel.add(new JScrollPane(
        view.getContent(),
        ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED), GridDialogLayoutData.FILL_VERTICAL);
    return view;
  }

  private GridDialogLayoutData createHorizontallySpanningData(int span) {
    GridDialogLayoutData data = new GridDialogLayoutData();
    data.setHorizontalSpan(span);
    return data;
  }

  private void addLabel(String labelString) {
    dataPanel.add(new JLabel(labelString));
  }

  public JButton addSaveButton(String text) {
    JButton button = new JButton(text);
    GridDialogLayoutData data = new GridDialogLayoutData();
    data.setHorizontalAlignment(GridAlignment.END);
    controlPanel.add(button, data);
    return button;
  }
}