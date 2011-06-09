package net.sf.anathema.campaign.music.presenter;

import javax.swing.JComponent;
import javax.swing.JTextField;

import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.campaign.music.model.libary.ILibrary;
import net.sf.anathema.campaign.music.model.libary.ILibraryControl;
import net.sf.anathema.lib.gui.table.celleditors.AbstractDelegatingCellEditor;
import net.sf.anathema.lib.gui.table.celleditors.EditorDelegate;

public class MusicLibraryCellEditor extends AbstractDelegatingCellEditor {

  private final ILibraryControl control;

  public MusicLibraryCellEditor(ILibraryControl control) {
    this.control = control;
  }

  @Override
  protected EditorDelegate createDelegate(JComponent editor) {
    final JTextField textField = (JTextField) editor;
    return new EditorDelegate(this) {
      private static final long serialVersionUID = 11707497538628072L;
      private ILibrary library;

      @Override
      public void setValue(Object value) {
        this.library = (ILibrary) value;
        textField.setText(value != null ? value.toString() : ""); //$NON-NLS-1$
        textField.selectAll();
      }

      @Override
      public Object getCellEditorValue() {
        String name = textField.getText().trim();
        boolean changeName = !StringUtilities.isNullOrEmpty(name) || !control.containsLibraryName(name);
        if (changeName) {
          control.updateLibrary(library, name);
        }
        return library;
      }
    };
  }

  @Override
  protected JComponent createEditorComponent() {
    return new JTextField();
  }
}