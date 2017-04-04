import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import edu.princeton.cs.algs4.Edge;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.DefaultListModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GUI extends JFrame {
	private JPanel contentPane;
	JScrollPane resultsScrollPane, nearestScrollPane;
	JList resultsList, nearestList;
	JComboBox<String> countyComboBox, statesComboBox, sortByComboBox;
	JLabel lblCounty, lblState, lblSortBy, lblNearest;
	JButton btnSort;
	JTextArea details;

	DefaultListModel<String> resultsListModel;
	DefaultListModel<String> nearestListModel;
	ArrayList<Hospital> matches;
	ArrayList<Hospital> nearest;

	/**
	 * Creates the GUI elements and displays them to screen
	 */
	public GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		resultsScrollPane = new JScrollPane();
		resultsScrollPane.setBounds(10, 70, 750, 450);

		resultsListModel = new DefaultListModel<String>();
		resultsList = new JList<String>(resultsListModel);
		resultsList.setFont(new Font("monospaced", Font.PLAIN, 12));
		resultsList.addMouseListener(new resultsListListener());
		resultsScrollPane.setViewportView(resultsList);

		String[] empty = { " " };
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

		details = new JTextArea();
		details.setBounds(10, 530, 375, 260);
		
		nearestListModel = new DefaultListModel<String>();
		nearestList = new JList<String>(nearestListModel);
		nearestList.setFont(new Font("monospaced", Font.PLAIN, 12));
		nearestList.addMouseListener(new nearestListListener());
		nearestScrollPane = new JScrollPane();
		nearestScrollPane.setBounds(395, 550, 365, 230);
		nearestScrollPane.setViewportView(nearestList);
		
		lblNearest = new JLabel("Hospitals within 50km");
		lblNearest.setBounds(395, 530, 300, 16);

		contentPane.add(sortByComboBox);
		contentPane.add(lblState);
		contentPane.add(statesComboBox);
		contentPane.add(lblCounty);
		contentPane.add(countyComboBox);
		contentPane.add(resultsScrollPane);
		contentPane.add(btnSort);
		contentPane.add(details);
		contentPane.add(nearestScrollPane);
		contentPane.add(lblNearest);
	}

	public class stateComboBoxListener implements ActionListener {
		/**
		 * called when the state combo box is changed. Updates the county combo box values to correspond to
		 * the state
		 */
		public void actionPerformed(ActionEvent e) {

			if (statesComboBox.getSelectedItem().toString().equals("ALL")) {
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
		/**
		 * called when the sort button is pressed, hospitals that match the criteria and display
		 * them to the results JList
		 */
		public void actionPerformed(ActionEvent e) {
			Quicksort.sort(Client.sortedHospitals, sortByComboBox.getSelectedItem().toString());

			String state = statesComboBox.getSelectedItem().toString();
			String county = countyComboBox.getSelectedItem().toString();

			matches = new ArrayList<Hospital>();

			if (county.equals("ALL")) {
				for (int i = 0; i < Client.sortedHospitals.length; i++) {
					if (Client.sortedHospitals[i].getState().equals(state)) {
						matches.add(Client.sortedHospitals[i]);
					}
				}
			} else if (state.equals("ALL")) {
				for (int i = 0; i < Client.sortedHospitals.length; i++) {
					matches.add(Client.sortedHospitals[i]);
				}
			} else {
				for (int i = 0; i < Client.sortedHospitals.length; i++) {
					if (Client.sortedHospitals[i].getState().equals(state)
							&& Client.sortedHospitals[i].getCounty().equals(county)) {
						matches.add(Client.sortedHospitals[i]);
					}
				}
			}

			resultsListModel.clear();

			for (int i = 0; i < matches.size(); i++) {
				resultsListModel.addElement(matches.get(i).toString());
			}
		}
	}

	public class resultsListListener extends MouseAdapter {
		/**
		 * called when a hospital is clicked in the results JList. Updates the details text area
		 * with further details about the hospital
		 */
		public void mouseClicked(MouseEvent event) {
			
			nearest = new ArrayList<Hospital>();
			
			Hospital selectedHospital = matches.get(resultsList.getSelectedIndex());
			for (int i = 0; i < Client.hospitals.length; i++) {
				if(selectedHospital.equals(Client.hospitals[i])) {
					for(Edge e: Client.graph.adj(i)) {
						nearest.add((Client.hospitals[e.other(i)]));
					}
				}
			}
			
			nearestListModel.clear();
			
			for (int i = 0; i < nearest.size(); i++) {
				nearestListModel.addElement(nearest.get(i).toString());
			}
			details.setText(selectedHospital.getDetails());
		}
	}

	
	public class nearestListListener extends MouseAdapter {
		/**
		 * called when a hospital is clicked in the results JList. Updates the details text area
		 * with further details about the hospital
		 */
		public void mouseClicked(MouseEvent event) {
			
			Hospital selectedHospital = nearest.get(nearestList.getSelectedIndex());
			details.setText(selectedHospital.getDetails());
		}
	}
}
