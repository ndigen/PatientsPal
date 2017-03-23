import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;


public class GUI extends JFrame {
	private JPanel contentPane;
	JScrollPane scrollPane;
	JTextArea resultsTextArea;
	JComboBox<String> countyComboBox, statesComboBox, sortByComboBox;
	JLabel lblCounty, lblState, lblSortBy;
	JButton btnSort;

	/**
	 * Create the frame.
	 */
	public GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800,800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 70, 780, 600);

		resultsTextArea = new JTextArea();
		resultsTextArea.setFont(new Font("monospaced", Font.PLAIN, 12));
		scrollPane.setViewportView(resultsTextArea);

		String[] empty = {" "};
		countyComboBox = new JComboBox<String>(empty);
		countyComboBox.setBounds(150, 37, 247, 22);

		lblCounty = new JLabel("County");
		lblCounty.setBounds(150, 10, 56, 16);

		statesComboBox = new JComboBox<String>(Client.stateList);
		statesComboBox.addActionListener(new stateComboBoxListener());
		statesComboBox.setBounds(50, 37, 64, 22);

		lblState = new JLabel("State");
		lblState.setBounds(50, 10, 56, 16);

		String[] sortByOptions = { "Name", "Location", "Overall Rating" };
		sortByComboBox = new JComboBox<String>(sortByOptions);
		sortByComboBox.setBounds(425, 37, 137, 22);

		lblSortBy = new JLabel("Sort By:");
		lblSortBy.setBounds(425, 10, 56, 16);
		contentPane.add(lblSortBy);

		btnSort = new JButton("SORT");
		btnSort.addActionListener(new sortBtnListener());
		btnSort.setBounds(600, 37, 97, 25);

		contentPane.add(sortByComboBox);
		contentPane.add(lblState);
		contentPane.add(statesComboBox);
		contentPane.add(lblCounty);
		contentPane.add(countyComboBox);
		contentPane.add(scrollPane);
		contentPane.add(btnSort);
	}

	public class stateComboBoxListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			if(statesComboBox.getSelectedItem().toString().equals("ALL")){
				countyComboBox.removeAllItems();
				countyComboBox.addItem(" ");
				return;
			}
			
			String[] array = new String[0];
			for (int i = 0; i < Client.countyList.size(); i++) {
				if (statesComboBox.getSelectedItem().toString().equals(Client.countyList.get(i).get(0))) {
					array = new String[Client.countyList.get(i).size()];

					array[0] = "ALL";

					for (int j = 1; j < array.length; j++) {
						array[j] = Client.countyList.get(i).get(j);
					}
				}
			}

			countyComboBox.removeAllItems();

			for (int i = 0; i < array.length; i++) {
				countyComboBox.addItem(array[i]);
			}
		}
	}

	public class sortBtnListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Quicksort.sort(Client.hospitals, sortByComboBox.getSelectedItem().toString());

			String state = statesComboBox.getSelectedItem().toString();
			String county = countyComboBox.getSelectedItem().toString();

			ArrayList<Hospital> matches = new ArrayList();

			if (county.equals("ALL")) {
				for (int i = 0; i < Client.hospitals.length; i++) {
					if (Client.hospitals[i].getState().equals(state)) {
						matches.add(Client.hospitals[i]);
					}
				}
			} else if(state.equals("ALL")) {
				for (int i = 0; i < Client.hospitals.length; i++) {
					matches.add(Client.hospitals[i]);
				}
			} else {
				for (int i = 0; i < Client.hospitals.length; i++) {
					if (Client.hospitals[i].getState().equals(state)
							&& Client.hospitals[i].getCounty().equals(county)) {
						matches.add(Client.hospitals[i]);
					}
				}
			}

			Hospital[] results = new Hospital[matches.size()];

			for (int i = 0; i < matches.size(); i++) {
				results[i] = matches.get(i);
			}

			resultsTextArea.setText(Client.printHospitals(results));
		}
	}

}
