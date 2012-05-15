package net.sf.anathema.lib.gui.dialog.events;

import javax.swing.event.ChangeEvent;
import javax.swing.event.DocumentEvent;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TreeSelectionEvent;
import java.awt.datatransfer.FlavorEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.beans.PropertyChangeEvent;

public class CheckInputValidListener implements ICheckInputValidListener {

  private final IInputValidCheckable checkable;

  public CheckInputValidListener(IInputValidCheckable checkable) {
    this.checkable = checkable;
  }

  @Override
  public void checkInputValid() {
    checkable.checkInputValid();
  }

  @Override
  public void itemStateChanged(ItemEvent e) {
    checkInputValid();
  }

  @Override
  public void valueChanged(ListSelectionEvent e) {
    checkInputValid();
  }

  @Override
  public void insertUpdate(DocumentEvent e) {
    checkInputValid();
  }

  @Override
  public void removeUpdate(DocumentEvent e) {
    checkInputValid();
  }

  @Override
  public void changedUpdate(DocumentEvent e) {
    checkInputValid();
  }

  @Override
  public void changeOccurred() {
    checkInputValid();
  }

  @Override
  public void tableChanged(TableModelEvent e) {
    checkInputValid();
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    checkInputValid();
  }

  @Override
  public void valueChanged(TreeSelectionEvent e) {
    checkInputValid();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    checkInputValid();
  }

  @Override
  public void stateChanged(ChangeEvent e) {
    checkInputValid();
  }

  @Override
  public void flavorsChanged(FlavorEvent e) {
    checkInputValid();
  }

  @Override
  public void contentsChanged(ListDataEvent e) {
    checkInputValid();
  }

  @Override
  public void intervalAdded(ListDataEvent e) {
    checkInputValid();
  }

  @Override
  public void intervalRemoved(ListDataEvent e) {
    checkInputValid();
  }
}