package net.sf.anathema.campaign.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.sf.anathema.campaign.presenter.ICampaignContentViewProperties;
import net.sf.anathema.campaign.presenter.view.ISeriesContentView;
import net.sf.anathema.framework.presenter.view.AbstractTabView;
import net.sf.anathema.framework.repository.tree.IRepositoryTreeView;
import net.sf.anathema.framework.repository.tree.RepositoryTreeView;

public class CampaignContentView extends AbstractTabView<ICampaignContentViewProperties> implements ISeriesContentView {

  private static final Dimension PANEL_SIZE = new Dimension(600, 500);
  private IRepositoryTreeView contentTreeView;
  private IRepositoryTreeView repositoryTreeView;
  private final List<IContentChangeListener> listeners = new ArrayList<IContentChangeListener>();

  public CampaignContentView(String title) {
    super(title, false);
  }

  @Override
  protected void createContent(JPanel panel, ICampaignContentViewProperties properties) {
    panel.setLayout(new GridDialogLayout(3, false, 20, 4));
    panel.setPreferredSize(PANEL_SIZE);
    final JTree contentTree = (JTree) (contentTreeView.getComponent());
    final JButton addButton = createAddButton(properties.getAddButtonIcon());
    addButton.setToolTipText(properties.getAddToolTip());
    final JButton removeButton = createRemoveButton(properties.getRemoveButtonIcon());
    removeButton.setToolTipText(properties.getRemoveToolTip());
    final JTree repositoryTree = (JTree) (repositoryTreeView.getComponent());
    repositoryTree.addTreeSelectionListener(new TreeSelectionListener() {
      public void valueChanged(TreeSelectionEvent e) {
        fireSelectionChanged(repositoryTree);
        addButton.setEnabled(repositoryTree.getSelectionCount() > 0);
      }
    });
    repositoryTree.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(java.awt.event.MouseEvent e) {
        if (e.getClickCount() == 2) {
          fireAddRequest();
        }
      }
    });
    contentTree.addTreeSelectionListener(new TreeSelectionListener() {
      public void valueChanged(TreeSelectionEvent e) {
        fireSelectionChanged(contentTree);
        removeButton.setEnabled(contentTree.getSelectionCount() > 0);
      }
    });
    contentTree.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(java.awt.event.MouseEvent e) {
        if (e.getClickCount() == 2) {
          fireRemoveRequest();
        }
      }
    });
    addButton.setEnabled(repositoryTree.getSelectionCount() > 0);
    removeButton.setEnabled(contentTree.getSelectionCount() > 0);
    JPanel contentPanel = new JPanel(new BorderLayout());
    contentPanel.add(new JScrollPane(contentTree), BorderLayout.CENTER);
    contentPanel.add(removeButton, BorderLayout.SOUTH);
    panel.add(contentPanel, GridDialogLayoutData.FILL_BOTH);
    panel.add(addButton);
    panel.add(new JScrollPane(repositoryTree), GridDialogLayoutData.FILL_BOTH);
  }

  private void fireSelectionChanged(JTree tree) {
    for (IContentChangeListener listener : listeners) {
      listener.selectionChanged(tree);
    }
  }

  public IRepositoryTreeView addContentTree() {
    contentTreeView = new RepositoryTreeView();
    return contentTreeView;
  }

  public IRepositoryTreeView addRepositoryTree() {
    repositoryTreeView = new RepositoryTreeView();
    return repositoryTreeView;
  }

  private JButton createAddButton(Icon icon) {
    JButton button = new JButton(icon);
    button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        fireAddRequest();
      }
    });
    return button;
  }

  private JButton createRemoveButton(Icon icon) {
    JButton button = new JButton(icon);
    button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        fireRemoveRequest();
      }
    });
    return button;
  }

  public void addContentChangeListener(IContentChangeListener listener) {
    listeners.add(listener);
  }

  private void fireAddRequest() {
    for (IContentChangeListener listener : listeners) {
      listener.addRequested();
    }
  }

  private void fireRemoveRequest() {
    for (IContentChangeListener listener : listeners) {
      listener.removeRequested();
    }
  }
}