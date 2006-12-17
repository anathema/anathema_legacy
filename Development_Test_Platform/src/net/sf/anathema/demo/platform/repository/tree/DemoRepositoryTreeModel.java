package net.sf.anathema.demo.platform.repository.tree;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.repository.ItemType;
import net.sf.anathema.framework.repository.RepositoryConfiguration;
import net.sf.anathema.framework.repository.tree.IRepositoryTreeModel;
import net.sf.anathema.framework.repository.tree.IRepositoryTreeModelListener;
import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.lib.control.GenericControl;
import net.sf.anathema.lib.control.IClosure;

public class DemoRepositoryTreeModel implements IRepositoryTreeModel {

  public static final ItemType NOTE = new ItemType("Note", new RepositoryConfiguration(".not", "Note/")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
  public static final ItemType CHARACTER = new ItemType(
      "ExaltedCharacter", new RepositoryConfiguration(".ecg", "ExaltedCharacter/")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
  private final List<IItemType> types = new ArrayList<IItemType>();
  private final List<PrintNameFile> printNameFiles = new ArrayList<PrintNameFile>();
  private final GenericControl<IRepositoryTreeModelListener> listeners = new GenericControl<IRepositoryTreeModelListener>();

  public DemoRepositoryTreeModel() {
    types.add(CHARACTER);
    types.add(NOTE);
    printNameFiles.add(new PrintNameFile(null, "Amber Quaint", "Amber Quaint", CHARACTER)); //$NON-NLS-1$ //$NON-NLS-2$
    printNameFiles.add(new PrintNameFile(null, "Vedia Telperion", "Vedia Telperion", CHARACTER)); //$NON-NLS-1$ //$NON-NLS-2$
    printNameFiles.add(new PrintNameFile(null, "Der Lessor ist scheiﬂe.", "Note1", NOTE)); //$NON-NLS-1$ //$NON-NLS-2$
  }

  public IItemType[] getAllItemTypes() {
    return types.toArray(new IItemType[0]);
  }

  public PrintNameFile[] getPrintNameFiles(IItemType itemType) {
    List<PrintNameFile> typeFiles = new ArrayList<PrintNameFile>();
    for (PrintNameFile file : printNameFiles) {
      if (file.getItemType() == itemType) {
        typeFiles.add(file);
      }
    }
    return typeFiles.toArray(new PrintNameFile[0]);
  }

  public void addPrintNameFile(PrintNameFile file) {
    printNameFiles.add(file);
    firePrintNameFileAdded(file);
  }

  private void firePrintNameFileAdded(final PrintNameFile file) {
    listeners.forAllDo(new IClosure<IRepositoryTreeModelListener>() {
      public void execute(IRepositoryTreeModelListener input) {
        input.printNameFileAdded(file);
      }
    });
  }

  public void addRepositoryTreeModelListener(IRepositoryTreeModelListener listener) {
    listeners.addListener(listener);
  }

  public boolean canBeDeleted(Object userObject) {
    return false;
  }

  public void deleteItem(Object currentlySelectedObject) {
    // TODO Auto-generated method stub
  }

  public String getRepositoryPath() {
    return "DEMO"; //$NON-NLS-1$
  }
}