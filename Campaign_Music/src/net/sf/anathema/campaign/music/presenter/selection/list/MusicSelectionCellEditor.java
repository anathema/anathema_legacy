package net.sf.anathema.campaign.music.presenter.selection.list;

import javax.swing.JComponent;
import javax.swing.JTextField;

import net.sf.anathema.campaign.music.model.selection.IMusicSelection;
import net.sf.anathema.campaign.music.model.selection.IMusicSelectionModel;
import net.sf.anathema.lib.gui.table.celleditors.AbstractDelegatingCellEditor;
import net.sf.anathema.lib.gui.table.celleditors.EditorDelegate;

public class MusicSelectionCellEditor extends AbstractDelegatingCellEditor {

  private final IMusicSelectionModel musicSelectionModel;

  public MusicSelectionCellEditor(IMusicSelectionModel musicSelectionModel) {
    this.musicSelectionModel = musicSelectionModel;
  }

  @Override
  protected final EditorDelegate createDelegate(JComponent editorComponent) {
    final JTextField textField = (JTextField) editorComponent;
    return new EditorDelegate(this) {
      private IMusicSelection musicSelection;

      @Override
      public void setValue(Object value) {
        this.musicSelection = (IMusicSelection) value;
        textField.setText(value != null ? value.toString() : ""); //$NON-NLS-1$
        textField.selectAll();
      }

      @Override
      public Object getCellEditorValue() {
        musicSelectionModel.updateSelectionName(musicSelection, textField.getText());
        return musicSelection;
      }
    };
  }

  @Override
  protected JComponent createEditorComponent() {
    return new JTextField();
  }
}