package net.sf.anathema.lib.gui.dialog.events;

import net.sf.anathema.lib.control.IChangeListener;

import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelListener;
import javax.swing.event.TreeSelectionListener;
import java.awt.datatransfer.FlavorListener;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeListener;

public interface ICheckInputValidListener
    extends
    ActionListener,
    ItemListener,
    ListSelectionListener,
    DocumentListener,
    IChangeListener,
    ChangeListener,
    TableModelListener,
    TreeSelectionListener,
    PropertyChangeListener,
    FlavorListener,
    ListDataListener {

  void checkInputValid();
}
