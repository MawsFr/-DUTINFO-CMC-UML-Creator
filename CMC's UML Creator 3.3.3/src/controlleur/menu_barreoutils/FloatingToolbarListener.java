package controlleur.menu_barreoutils;

import java.awt.Window;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;

import javax.swing.JDialog;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.plaf.basic.BasicToolBarUI;

public class FloatingToolbarListener implements HierarchyListener {

	@Override
	public void hierarchyChanged(HierarchyEvent e) {

		if ((e.getChangeFlags() & HierarchyEvent.PARENT_CHANGED) == 0) {
			return;
		}
		JToolBar barre = (JToolBar) e.getComponent();
		if (!((BasicToolBarUI) barre.getUI()).isFloating()) {
			return;
		}

		Window window = SwingUtilities.windowForComponent(barre);
		if(window == null) {
			return;
		}

		window.dispose();
		((JDialog) window).setUndecorated(true);
		window.setVisible(true);

	}

}
