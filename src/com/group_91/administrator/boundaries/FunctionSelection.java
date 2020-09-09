package com.group_91.administrator.boundaries;

import com.group_91.administrator.controls.FunctionSelectionControl;
import com.group_91.customer.entities.SuitInfo;

import javax.swing.*;
import java.awt.*;

/**
 * Interface for the function selection panel.
 *
 * @author Yingke Ding
 */
public class FunctionSelection extends JFrame {
    private final JPanel contentPane;


    /**
     * Constructs the frame.
     */
    public FunctionSelection() {
        ImageIcon img = new ImageIcon("./img/mback.png");		//Ҫ���õı���ͼƬ
        JLabel imgLabel = new JLabel(img);		//������ͼ���ڱ�ǩ�
        this.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));		//��������ǩ��ӵ�jfram��LayeredPane����
        imgLabel.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());		// ���ñ�����ǩ��λ��
        Container contain = this.getContentPane();
        ((JPanel) contain).setOpaque(false);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane = new JPanel();
        contentPane.setSize(500,575);
        contentPane.setBorder(BorderFactory.createTitledBorder(" "));
        contentPane.setLayout(null);
        contentPane.setOpaque(false);

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("./img/choose.png"));
        lblNewLabel.setBounds(200, 40, 100, 100);
        contentPane.add(lblNewLabel);


        JButton viewSuitStatsButton = new JButton(SuitInfo.getSuitName() + " Stats");
        viewSuitStatsButton.setFont(new Font("Times New Roman", Font.ITALIC, 18));
        viewSuitStatsButton.setBounds(90, 160, 300, 40);
        contentPane.add(viewSuitStatsButton);


        JButton viewGeneralOptionsStatsButton = new JButton("General Options Stat");
        viewGeneralOptionsStatsButton.setFont(new Font("Times New Roman", Font.ITALIC, 18));
        viewGeneralOptionsStatsButton.setBounds(90, 210, 300, 40);
        contentPane.add(viewGeneralOptionsStatsButton);


        JButton viewBooleanOptionsStatsButton = new JButton("Boolean Options Stat");
        viewBooleanOptionsStatsButton.setFont(new Font("Times New Roman", Font.ITALIC, 18));
        viewBooleanOptionsStatsButton.setBounds(90, 260, 300, 40);
        contentPane.add(viewBooleanOptionsStatsButton);


        JButton viewAddOnsStatsButton = new JButton("Add Ons Stat");
        viewAddOnsStatsButton.setFont(new Font("Times New Roman", Font.ITALIC, 18));
        viewAddOnsStatsButton.setBounds(90, 310, 300, 40);
        contentPane.add(viewAddOnsStatsButton);


        JButton viewSpicinessButton = new JButton("Most Popular Spiciness");
        viewSpicinessButton.setFont(new Font("Times New Roman", Font.ITALIC, 18));
        viewSpicinessButton.setBounds(90, 360, 300, 40);
        contentPane.add(viewSpicinessButton);


        JButton emailButton = new JButton("Send email");
        emailButton.setIcon(new ImageIcon("./img/mail.png"));
        emailButton.setFont(new Font("Times New Roman",Font.ITALIC,18));
        emailButton.setBounds(90, 440, 300, 40);
        contentPane.add(emailButton);


        JButton backButton = new JButton("Back to the home page");
        backButton.setFont(new Font("Times New Roman",Font.ITALIC,18));
        backButton.setBounds(90, 490, 300, 40);
        contentPane.add(backButton);
        backButton.setIcon(new ImageIcon("./img/book.png"));


        // Register control here.
        FunctionSelectionControl control = new FunctionSelectionControl(contentPane, viewSuitStatsButton,
                viewGeneralOptionsStatsButton, viewBooleanOptionsStatsButton,
                viewAddOnsStatsButton, viewSpicinessButton, emailButton, backButton);
        viewSuitStatsButton.addActionListener(control);
        viewGeneralOptionsStatsButton.addActionListener(control);
        viewBooleanOptionsStatsButton.addActionListener(control);
        viewAddOnsStatsButton.addActionListener(control);
        viewSpicinessButton.addActionListener(control);
        emailButton.addActionListener(control);
        backButton.addActionListener(control);


        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setLocation(width / 2 -300, (height / 2 - 362));

        this.setSize(500,575);
        //this.setSize(600, 725);
        this.getContentPane().add(contentPane);
        this.setResizable(false);

    }


    /**
     * Getter.
     * @return {@link FunctionSelection#contentPane}
     */
    public JPanel getQueryPane() {
        return contentPane;
    }
}
