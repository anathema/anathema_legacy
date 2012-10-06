package net.sf.anathema.lib.gui.list.veto;

import net.sf.anathema.lib.gui.list.ListSelectionMode;

import javax.swing.DefaultListSelectionModel;
import java.util.ArrayList;
import java.util.List;

public class VetoableListSelectionModel extends DefaultListSelectionModel {

	private final List<IVetor> vetors = new ArrayList<IVetor>();
	private boolean alreadyAsked;
	private final ListSelectionMode mode;

	public VetoableListSelectionModel() {
		this(ListSelectionMode.SingleSelection);
	}

	public VetoableListSelectionModel(ListSelectionMode mode) {
		this.mode = mode;
		setSelectionMode(mode.getMode());
	}

	@Override
	public void addSelectionInterval(final int index0, final int index1) {
		executeVetoable(new Runnable() {
			@Override
            public void run() {
				VetoableListSelectionModel.super.addSelectionInterval(index0,
						index1);
			}
		});
	}

	private synchronized void executeVetoable(Runnable block) {
		if (alreadyAsked) {
			block.run();
			return;
		}
		if (vetos()) {
			return;
		}
		alreadyAsked = true;
		block.run();
		alreadyAsked = false;
	}

	public synchronized void addVetor(IVetor vetor) {
		vetors.add(vetor);
	}

	@Override
	public void removeSelectionInterval(final int index0, final int index1) {
		if (getMaxSelectionIndex() == -1) {
			return;
		}
		executeVetoable(new Runnable() {
			@Override
            public void run() {
				VetoableListSelectionModel.super.removeSelectionInterval(
						index0, index1);
			}
		});
	}

	public synchronized void removeVetor(IVetor vetor) {
		vetors.remove(vetor);
	}

	@Override
	public void setSelectionInterval(final int index0, final int index1) {
		executeVetoable(new Runnable() {
			@Override
            public void run() {
				VetoableListSelectionModel.super.setSelectionInterval(index0,
						index1);
			}
		});
	}

	@Override
	public void setSelectionMode(int selectionMode) {
		super.setSelectionMode(mode.getMode());
	}

	private synchronized boolean vetos() {
		List<IVetor> cloneList = new ArrayList<IVetor>(vetors);
        for (IVetor vetor : cloneList) {
            if (vetor.vetos()) {
                return true;
            }
        }
		return false;
	}
}