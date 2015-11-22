package vue.entites.propriete;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ErreurDialogue extends JFrame {
	JButton ok;
	JLabel txtErreur;
	public ErreurDialogue(String message){
		this.setSize(100,100);
		this.setLocationRelativeTo(null);
		JPanel pnl = new JPanel();
		pnl.setLayout(new BoxLayout(pnl, BoxLayout.Y_AXIS));
		txtErreur = new JLabel(message);
		ok = new JButton("OK");
		ok.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}

		});
		pnl.add(txtErreur);
		pnl.add(ok);
		this.getContentPane().add(pnl);
		pack();
		this.setVisible(true);
	}
}