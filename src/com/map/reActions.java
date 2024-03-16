package com.map;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;

public class reActions {
    public static void main(String[] args) throws InterruptedException, IOException {
        new CreateActions();
    }
}
class CreateActions extends JFrame {
    private JTextField fold = new JTextField("在此填写map文件的目录地址");
    private JFileChooser chooser = new JFileChooser("D:\\project\\mapreader");
    private JButton open = new JButton("浏览");
    private JDialog dialog=new JDialog();
    private JButton okBut = new JButton("确定");
    private JButton create = new JButton("生成");
    private JLabel success = new JLabel("修改成功");
    private JPanel panel = new JPanel();
    private Font font = new Font("宋体", Font.PLAIN, 13);
    private CommonMethod m=new CommonMethod();
    private JTextField trigger1 = new JTextField("起始触发号");
    private JTextField trigger2 = new JTextField("结束触发号");
    private JTextField action1 = new JTextField("在此填写行为号");
    private JTextField identity1 = new JTextField("内部循环次数");
    private JTextField enums = new JTextField("在此填写枚举类内容");
    private JTextField parameter1 = new JTextField("在此填写参数内容");
    private JCheckBox replace = new JCheckBox("替换");
    private JButton ok = new JButton("确定");
    private JButton cancel = new JButton("取消");
    private JLabel confirm = new JLabel("是否要新增行为？");
    private JLabel direction=new JLabel("");
    private JButton newaction = new JButton("新增");
    int num = 1;
    public CreateActions(){
        panel.setLayout(null);
        dialog.setBounds(400, 200, 350, 150);//设置弹出对话框的位置和大小
        dialog.setLayout(new FlowLayout());//设置弹出对话框的布局为流式布局
        dialog.add(confirm);
        dialog.add(success);
        dialog.add(ok);
        dialog.add(okBut);
        okBut.setVisible(false);
        success.setVisible(false);
        dialog.add(cancel);
        fold.setBounds(70, 80, 220, 30);
        fold.dispatchEvent(new FocusEvent(fold, FocusEvent.FOCUS_GAINED, true));
        fold.setName("fold");
        fold.setToolTipText("map文件目录");
        open.setBounds(320, 80, 60, 30);
        open.dispatchEvent(new FocusEvent(open, FocusEvent.FOCUS_GAINED, true));
        open.setName("open");
        trigger1.setBounds(70, 120, 110, 30);
        trigger1.dispatchEvent(new FocusEvent(trigger1, FocusEvent.FOCUS_GAINED, true));
        trigger1.setName("trigger1");
        trigger2.setBounds(190, 120, 110, 30);
        trigger2.dispatchEvent(new FocusEvent(trigger2, FocusEvent.FOCUS_GAINED, true));
        trigger2.setName("trigger2");
        replace.setBounds(320, 120, 70, 30);
        replace.dispatchEvent(new FocusEvent(replace, FocusEvent.FOCUS_GAINED, true));
        replace.setName("replace");
        action1.setBounds(70, 160, 100, 30);
        action1.dispatchEvent(new FocusEvent(action1, FocusEvent.FOCUS_GAINED, true));
        action1.setName("action1");
        parameter1.setBounds(180, 160, 250, 30);
        parameter1.dispatchEvent(new FocusEvent(parameter1, FocusEvent.FOCUS_GAINED, true));
        parameter1.setName("parameter1");
        newaction.setBounds(560, 160, 80, 30);
        newaction.dispatchEvent(new FocusEvent(newaction, FocusEvent.FOCUS_GAINED, true));
        newaction.setName("newaction");
        identity1.setBounds(440, 160, 90, 30);
        identity1.dispatchEvent(new FocusEvent(identity1, FocusEvent.FOCUS_GAINED, true));
        identity1.setName("identity");
        enums.setBounds(400, 80, 200, 30);
        enums.dispatchEvent(new FocusEvent(enums, FocusEvent.FOCUS_GAINED, true));
        enums.setName("enums");
        create.setBounds(560, 200, 80, 30);
        create.dispatchEvent(new FocusEvent(create, FocusEvent.FOCUS_GAINED, true));
        create.setName("create");
        direction.setBounds(930, 70, 200, 460);
        direction.setFont(font);
        direction.setForeground(Color.RED);
        try {
            m.JlabelSetText(direction,"新建行为的递增定义与事件类似。在定义新建行为的递增中，直接使用{初始数字（INI编号），递增额度}来定义递增。注意，本程序同样可以批量新增ARES3.0的行为。新建行为的参数中，只有参数的数字和INI编号可递增。当出现有非数字的参数时，一律填在参数1之中；需要填写路径点的参数一律填在参数2中，且路径点必须填写数字而不是map文本中的26进制字母。出现没有填写参数2的情况下，参数2会按照默认情况被定义为A或0。参数1可以用%random%{最小值，最大值}表示生成指定范围内INI序号或数字的随机数。其中，最大值和最小值可以在随机数递增值框来表示随机数范围的变化。随机数递增值一般仅填写数字，但是也可以用如12,7这种格式表示递增值为12，且递增7次后恢复为原取值范围继续循环。");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        panel.add(direction);
        panel.add(fold);
        panel.add(trigger1);
        panel.add(trigger2);
        panel.add(action1);
        panel.add(parameter1);
        panel.add(open);
        panel.add(create);
        panel.add(replace);
        panel.add(newaction);
        panel.add(enums);
        panel.add(identity1);
        addListener();
        this.add(panel);
        this.setBounds(300, 100, 1280, 800);
        this.setPreferredSize(new Dimension(1280, 800));
        this.pack();
        this.setVisible(true);

    }

    private void writeActions(mapList list) throws IOException {
        File file = new File(fold.getText());
        String starttrigger=trigger1.getText(),endtrigger=trigger2.getText();
        java.util.List<String> triggerlist=list.getTriggers();
        java.util.List<String> actionlist=list.getActions();
        Collections.sort(triggerlist);
        Collections.sort(actionlist);
        String text="",lasttrigger="",firsttrigger="";
        String[] e=enums.getText().split(",");
    }

    private void addListener() {
        m.fileChoose(open, chooser, fold);
        m.dialogExit(okBut, dialog);
        m.dialogExit(cancel, dialog);
        ok.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String fileName = fold.getText();
                int i,mode;
                File file = new File(fileName);
                try {
                    CommonMethod m=new CommonMethod();
                    mapList list=m.readMapList(fold.getText());
                    writeActions(list);
                    okBut.setVisible(true);
                    success.setVisible(true);
                    ok.setVisible(false);
                    cancel.setVisible(false);
                    confirm.setVisible(false);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
        create.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String fileName = fold.getText();
                File file = new File(fileName);
                try {
                    if (!m.lastName(file).equals("map")) {
                        throw new FileNotFoundException("后缀名必须为map");
                    }
                    okBut.setVisible(false);
                    success.setVisible(false);
                    ok.setVisible(true);
                    cancel.setVisible(true);
                    confirm.setVisible(true);
                    dialog.setVisible(true);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
        newaction.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JTextField action = new JTextField();
                JTextField p1 = new JTextField();
                JTextField p3 = new JTextField();
                action.setBounds(70, action1.getY() + num * 40, 100, 30);
                p1.setBounds(180, action1.getY() + num * 40, 250, 30);
                p3.setBounds(440, action1.getY() + num * 40, 90, 30);
                Color color = panel.getBackground();
                panel.setBackground(Color.white);
                panel.setBackground(color);
                action.dispatchEvent(new FocusEvent(action, FocusEvent.FOCUS_GAINED, true));
                action.setName("action" + (num + 1));
                p1.dispatchEvent(new FocusEvent(action, FocusEvent.FOCUS_GAINED, true));
                p1.setName("parameter" + (num + 1));
                p3.dispatchEvent(new FocusEvent(action, FocusEvent.FOCUS_GAINED, true));
                p3.setName("identity" + (num + 1));
                p3.setText("1");
                panel.add(action);
                panel.add(p1);
                panel.add(p3);
                num++;
            }
        });
    }
}

