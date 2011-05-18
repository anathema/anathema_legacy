package net.sf.anathema.character.thaumaturgy.view;

import java.awt.Dimension;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;

import net.disy.commons.swing.layout.GridDialogLayoutDataUtilities;
import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.sf.anathema.character.library.intvalue.IIntValueDisplayFactory;
import net.sf.anathema.character.library.trait.presenter.TraitPresenter;
import net.sf.anathema.character.library.trait.view.SimpleTraitView;
import net.sf.anathema.character.library.trait.visitor.IDefaultTrait;
import net.sf.anathema.character.thaumaturgy.model.ThaumaturgyMagic;
import net.sf.anathema.character.thaumaturgy.model.ThaumaturgyMagicType;
import net.sf.anathema.framework.presenter.view.ButtonControlledObjectSelectionView;
import net.sf.anathema.framework.presenter.view.IButtonControlledObjectSelectionView;
import net.sf.anathema.framework.presenter.view.ITextFieldComboBoxEditor;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.gui.IView;
import net.sf.anathema.lib.gui.widgets.ChangeableJComboBox;

public class ThaumaturgyView implements IThaumaturgyView, IView {

  private final IIntValueDisplayFactory factory;
  private final JPanel mainPanel = new JPanel(new GridDialogLayout(1, false));
  private final JPanel magicPanel = new JPanel(new GridDialogLayout(5, false));
  ButtonControlledObjectSelectionView<String> artSelectionView;
  ProcedureEditView<String> procedureSelectionView;
  private IDefaultTrait procedureControl;
  private JPanel selectionPanel = new JPanel();
  private ChangeableJComboBox<String> comboBox;
  private ThaumaturgyMagicType mode;

  public ThaumaturgyView(IIntValueDisplayFactory factory) {
    this.factory = factory;
  }
  
  public void setCurrentMode(ThaumaturgyMagicType type)
  {
	  if (type == mode)
		  return;
	  mode = type;
	  
	  selectionPanel.removeAll();
	  if (type == ThaumaturgyMagicType.Degree)
	  {
		  selectionPanel.setLayout(new GridDialogLayout(2, false));
		  artSelectionView.addComponents(selectionPanel);
	  }
	  if (type == ThaumaturgyMagicType.Procedure)
	  {
		  selectionPanel.setLayout(new GridDialogLayout(4, false));
		  procedureSelectionView.addComponents(selectionPanel);
	  }
	  
	  //the width of the procedure selection view
	  //should probably be determined automagically, but after
	  //wasting four hours trying to figure out why the damned
	  //artSelection wouldn't fill properly, I can't be
	  //bothered...
	  selectionPanel.setPreferredSize(new Dimension(431, 20));
	 
	  selectionPanel.revalidate();
	  selectionPanel.repaint();
  }
  
  public void addModeListener(final String[] values, final String value, final IObjectValueChangedListener<String> listener)
  {
	  this.comboBox = new ChangeableJComboBox<String>(false);
	  comboBox.setObjects(values);
	  comboBox.addObjectSelectionChangedListener(listener);
	  comboBox.setSelectedObject(value);
  }
  
  public IThaumaturgyMagicView addMagicView(
      String artName,
      String procedureName,
      Icon deleteIcon,
      int value,
      int maxValue) {
    ThaumaturgyMagicView magicView = new ThaumaturgyMagicView(factory, artName, deleteIcon, procedureName, value, maxValue);
    magicView.addComponents(magicPanel);
    mainPanel.revalidate();
    return magicView;
  }
  
  public IButtonControlledObjectSelectionView<String> addArtSelectionView(
	      String labelText,
	      ITextFieldComboBoxEditor editor,
	      ListCellRenderer renderer,
	      Icon addIcon) {
	    artSelectionView = new ArtSelectionView(
	        renderer,
	        addIcon,
	        null,
	        editor);
	    return artSelectionView;
	  }

  public IProcedureEditView<String> addProcedureSelectionView(
	  IDefaultTrait trait,
      ListCellRenderer renderer,
      ITextFieldComboBoxEditor artEditor,
      ITextFieldComboBoxEditor procedureEditor,
      Icon addIcon) {
	procedureControl = trait;
	SimpleTraitView procedureControl = new SimpleTraitView(factory, null, 0, ThaumaturgyMagic.ALLOWED_DOT_COUNT);
	new TraitPresenter(trait, procedureControl).initPresentation();
    procedureSelectionView = new ProcedureEditView<String>(
        addIcon,
        procedureControl,
        renderer,
        artEditor,
        procedureEditor);
    return procedureSelectionView;
  }

  public JComponent getComponent() {
    GridDialogLayoutData data = GridDialogLayoutDataUtilities.createFillNoGrab();
    data.setGrabExcessVerticalSpace(true);
    mainPanel.add(comboBox.getComponent());
    mainPanel.add(selectionPanel);
    mainPanel.add(new JScrollPane(magicPanel), data);
    return mainPanel;
  }
  
  public void clear()
  {
	  artSelectionView.setSelectedObject(null);
	  procedureSelectionView.clear();
	  procedureControl.setCurrentValue(0);
  }
}