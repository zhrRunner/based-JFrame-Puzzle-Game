package com.zhr.ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

public class GameJFrame extends JFrame implements KeyListener, ActionListener {
    // JFrame 界面窗体
    // 这个类表示游戏的主界面
    // 和游戏相关的所有逻辑接口都写在这里

    // 记录空白块的位置
    private int x = 0, y = 0;
    // 生成0-15的数组
    private int[] arr = new int[16];

    private int step = 0;

    private String path = "image/mygirl/";

    // 创建菜单选项的子选项 （ 重新游戏 重新登录 关闭游戏 联系我）这里要设置成成员变量，因为要在动作监听事件中使用

    JMenuItem replayItem = new JMenuItem("重新游戏");
    JMenuItem reloginItem = new JMenuItem("重新登录");
    JMenuItem closeItem = new JMenuItem("关闭游戏");
    JMenuItem contactItem = new JMenuItem("联系我");
    JMenuItem girlItem = new JMenuItem("美女");
    JMenuItem animalItem = new JMenuItem("动物");
    JMenuItem sportItem = new JMenuItem("运动");
    JMenuItem mygirlItem = new JMenuItem("My girl");

    public GameJFrame(){
        // 初始化窗体
        initJFrame();

        // 初始化菜单
        initJMenuBar();

        // 初始化数组
        initData();

        // 初始化图片
        initImage();

        // 显示窗体
        this.setVisible(true);
    }

    private void initData() {
        step = 0;
        for (int i = 0; i < 16; i++) arr[i] = i;
        // 随机打乱数组
        for (int i = 0; i < 16; i++) {
            int index = (int) (Math.random() * 16);  // Math.random() 生成0-1之间的随机数 乘以16就是0-15之间的随机数 然后向下取整
            int temp = arr[i];
            arr[i] = arr[index];
            arr[index] = temp;
        }
    }

    // 移动图片
    private void moveImage(int a, int b) {  // a,b表示要移动的图片的位置
        int index1 = a * 4 + b;
        int index2 = x * 4 + y;

        arr[index2] = arr[index1];
        arr[index1] = 0;

        initImage();

        // 更新空白块的位置
        x = a;
        y = b;
    }

    // 初始化图片
    private void initImage() {
        // 清空原本已经出现的所有图片
        this.getContentPane().removeAll();
        if(victory()){
            JLabel jLabel = new JLabel(new ImageIcon("image/win.png"));
            jLabel.setBounds(93 , 134, 420, 420);
            this.getContentPane().add(jLabel);
        }

        // 显示当前步数
        JLabel jLabelStep = new JLabel("步数：" + step);
        jLabelStep.setBounds(50, 30, 100, 20);
        this.getContentPane().add(jLabelStep);


        int index = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if(arr[index] == 0){
                    x = i;
                    y = j;
                }
                // 创建图片ImageIcon对象
                ImageIcon imageIcon = new ImageIcon(path + arr[index] +".jpg");
                index ++;

                // 创建JLabel对象（管理容器）
                JLabel jLabel = new JLabel(imageIcon);
                // 设置管理容器的位置和大小
                jLabel.setBounds(j * 105 + 93, i * 105 + 134, 105, 105);
                // 给管理容器添加边框
                jLabel.setBorder(new BevelBorder(1));
                // 添加管理容器到窗体
                this.getContentPane().add(jLabel);
            }
        }



        // 添加背景图片
        ImageIcon bgImage = new ImageIcon("image/background.png");
        JLabel bgLabel = new JLabel(bgImage);
        bgLabel.setBounds(50, 40, 508, 560);
        // 将背景图片添加到窗体
        this.getContentPane().add(bgLabel);

        // 刷新界面
        this.getContentPane().repaint();
    }

    private boolean victory() {
        for(int i = 0; i < 16; i ++){
            if(arr[i] != (i + 1) % 16){
                return false;
            }
        }
        return true;
    }

    private void initJMenuBar() {
        JMenuBar jMenuBar = new JMenuBar();
        // 创建菜单选项对象 （功能  关于我）
        JMenu functionJmenu = new JMenu("功能");
        JMenu aboutJmenu = new JMenu("关于我");
        JMenu changeimgItem = new JMenu("更换图片");

        // 添加子选项到菜单选项
        functionJmenu.add(changeimgItem);
        functionJmenu.add(replayItem);
        functionJmenu.add(reloginItem);
        functionJmenu.add(closeItem);
        changeimgItem.add(mygirlItem);
        changeimgItem.add(girlItem);
        changeimgItem.add(animalItem);
        changeimgItem.add(sportItem);


        aboutJmenu.add(contactItem);

        // 给条目绑定事件
        changeimgItem.addActionListener(this);
        replayItem.addActionListener(this);
        reloginItem.addActionListener(this);
        closeItem.addActionListener(this);
        contactItem.addActionListener(this);
        girlItem.addActionListener(this);
        animalItem.addActionListener(this);
        sportItem.addActionListener(this);
        mygirlItem.addActionListener(this);


        // 添加菜单选项到菜单栏
        jMenuBar.add(functionJmenu);
        jMenuBar.add(aboutJmenu);

        // 设置菜单栏
        this.setJMenuBar(jMenuBar);
    }

    private void initJFrame() {
        // 设置界面的宽和高
        this.setSize(603, 680);
        // 设置界面标题
        this.setTitle("拼图华容道 v1.0");
        // 设置界面置顶
        this.setAlwaysOnTop(true);
        // 设置界面在屏幕剧中
        this.setLocationRelativeTo(null); //null表示居中，如果传入一个组件，就是相对这个组件居中
        // 设置界面关闭的时候，程序也退出
        this.setDefaultCloseOperation(3); // 3表示退出程序, 0表示什么都不做，1表示隐藏窗体
        // 取消默认的居中放置，只有取消了才会按照XY轴的形式添加组件
        this.setLayout(null);
        // 给整个界面添加键盘监听事件
        this.addKeyListener(this);

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == 65){  // 按住键盘a键，不放，显示完整图片
            this.getContentPane().removeAll();
            ImageIcon imageIcon = new ImageIcon(path + "all.jpg");
            JLabel jLabel = new JLabel(imageIcon);
            jLabel.setBounds(93, 134, 420, 420);
            this.getContentPane().add(jLabel);
            ImageIcon bgImage = new ImageIcon("image/background.png");
            JLabel bgLabel = new JLabel(bgImage);
            bgLabel.setBounds(50, 40, 508, 560);
            this.getContentPane().add(bgLabel);
            this.getContentPane().repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // 如果游戏胜利了，就不再监听键盘事件
        if(victory()){
            return;
        }

        int code = e.getKeyCode();
        // 移动图片
        // 上: 38  下:40  左:37  右:39  进行判断
        if(code == 38 && x < 3){
            step ++;
            moveImage(x + 1, y);
        } else if(code == 40 && x > 0){
            step ++;
            moveImage(x - 1, y);
        } else if(code == 37 && y < 3){
            step ++;
            moveImage(x, y + 1);
        } else if(code == 39 && y > 0){
            step ++;
            moveImage(x, y - 1);
        } else if(code == 65){
            // 松开键盘a键，显示拼图
            initImage();
        } else if(code == 87){
            // 按了一下w键，直接胜利
            for (int i = 0; i < 16; i ++){
                arr[i] = (i + 1) % 16;
            }
            initImage();
        }


//        System.out.println(code);



    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if(obj == mygirlItem){
            changeImage("mygirl");
        } else if(obj == girlItem){
            changeImage("girl");
        } else if(obj == animalItem){
            changeImage("animal");
        } else if(obj == sportItem){
            changeImage("sport");
        } else if(obj == replayItem){
            // 重新游戏
            initData();
            initImage();
        } else if(obj == reloginItem){
            // 重新登录
            this.setVisible(false);  // 隐藏当前窗体
            new LoginJFrame();       // 创建登录窗体
        } else if(obj == closeItem){
            // 关闭游戏
            System.exit(0);
        } else if(obj == contactItem){
            // 联系我
            JDialog jDialog = new JDialog();
            JLabel jLabel = new JLabel(new ImageIcon("image/about.jpg"));
            jLabel.setBounds(0, 0, 676, 677);
            jDialog.getContentPane().add(jLabel);
            jDialog.setSize(750, 750);
            jDialog.setAlwaysOnTop(true);
            jDialog.setLocationRelativeTo(null);
            // 弹框不关闭，则不能进行下面的操作
            jDialog.setModal(true);
            jDialog.setVisible(true);
        }
    }

    private void changeImage(String obj) {
        path = "image/"+ obj + "/";
        // 查看当前路径下有多少个文件
        File file = new File(path);
        File[] files = file.listFiles();
        int cnt = files.length;
        // 随机一个1-cnt的数
        int index = (int) (Math.random() * cnt);
        path = path + obj + index + "/";
        initData();
        initImage();
    }
}
