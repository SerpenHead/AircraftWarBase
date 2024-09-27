package edu.hitsz;

import edu.hitsz.DAO.PlayerDaoImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DaoList {
    private JTable RankTable;
    private JPanel panel1;
    private JScrollPane RankScrolPanel;
    private JButton delete;
    private JTextArea RankList;

    public DaoList(String[][] tableData, PlayerDaoImpl playerDaoImpl){
        String[] columnName = {"name", "score", "date", "time", "level"};

        //表格模型
        DefaultTableModel model = new DefaultTableModel(tableData, columnName){
            @Override
            public boolean isCellEditable(int row, int col){
                return false;
            }
        };
        RankTable.setModel(model);
        RankScrolPanel.setViewportView(RankTable);
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = RankTable.getSelectedRow();
                System.out.println(row);
                playerDaoImpl.deleteFromTxt(row);
                if(row != -1){
                    model.removeRow(row);
            }
        }
        });
    }
    public JPanel getMenuMainPanel() {
        return panel1;
    }
}
