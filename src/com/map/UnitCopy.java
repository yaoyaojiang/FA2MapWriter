package com.map;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class UnitCopy {
    public static void main(String[] args) throws InterruptedException, IOException {
        new NewUnits();
    }
}

class NewUnits extends JFrame {
    private JTextField fold = new JTextField("在此填写rule文件的目录地址");
    private JFileChooser chooser = new JFileChooser("D:\\project\\mapreader");
    private JButton open = new JButton("浏览");
    private JDialog dialog=new JDialog();
    private JLabel direction=new JLabel("");
    private JPanel panel = new JPanel();
    private JButton okBut = new JButton("确定");
    private JButton create = new JButton("生成");
    private JLabel success = new JLabel("修改成功");
    private JButton ok = new JButton("确定");
    private JButton cancel = new JButton("取消");
    private JLabel confirm = new JLabel("是否要新增对应属性？");
    private JTextArea tips = new JTextArea(50,40);
    private JTextField para = new JTextField("换皮的项目代码");
    private JScrollPane scroll = new JScrollPane(tips);
    private JCheckBox variable = new JCheckBox("连带新增全局变量");
    private JCheckBox useares = new JCheckBox("使用ARES继承");
    private JTextField varnums = new JTextField("全部变量范围（填写开始序号)");
    private CommonMethod c=new CommonMethod();
    private Font font = new Font("宋体", Font.PLAIN, 13);
    public NewUnits() throws InterruptedException {
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
        fold.setBounds(70, 40, 220, 30);
        fold.dispatchEvent(new FocusEvent(fold, FocusEvent.FOCUS_GAINED, true));
        fold.setName("fold");
        fold.setToolTipText("ini文件目录");
        variable.setBounds(320, 120, 180, 30);
        variable.dispatchEvent(new FocusEvent(variable, FocusEvent.FOCUS_GAINED, true));
        variable.setName("variable");
        useares.setBounds(320, 200, 120, 30);
        useares.dispatchEvent(new FocusEvent(useares, FocusEvent.FOCUS_GAINED, true));
        useares.setName("useares");
        useares.setToolTipText("使用ARES的继承写法");
        open.setBounds(320, 40, 60, 30);
        open.dispatchEvent(new FocusEvent(open, FocusEvent.FOCUS_GAINED, true));
        open.setName("open");
        varnums.setBounds(320, 160, 120, 30);
        varnums.dispatchEvent(new FocusEvent(varnums, FocusEvent.FOCUS_GAINED, true));
        varnums.setName("varnums");
        varnums.setToolTipText("全部变量范围（填写开始序号)");
        para.setBounds(70, 80, 220, 30);
        para.dispatchEvent(new FocusEvent(para, FocusEvent.FOCUS_GAINED, true));
        para.setName("para");
        create.setBounds(320, 80, 60, 30);
        create.dispatchEvent(new FocusEvent(create, FocusEvent.FOCUS_GAINED, true));
        create.setName("create");
        tips.dispatchEvent(new FocusEvent(tips, FocusEvent.FOCUS_GAINED, true));
        tips.setName("tips");
        tips.setLineWrap(true);        //激活自动换行功能
        tips.setWrapStyleWord(true);
        tips.setText("写入需要修改或增加的属性");
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setBounds(70,120,220,200);
        panel.add(create);
        panel.add(useares);
        panel.add(open);
        panel.add(fold);
        panel.add(scroll);
        panel.add(para);
        panel.add(variable);
        panel.add(varnums);
        addListener();
        this.add(panel);
        this.setBounds(300, 100, 800, 800);
        this.setPreferredSize(new Dimension(800, 600));
        this.pack();
        this.setVisible(true);
    }

    public void addListener() {
        CommonMethod m =new CommonMethod();
        okBut.addMouseListener(
                new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        dialog.setVisible(false);
                        super.mouseClicked(e);
                    }
                }
        );
        create.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String fileName = fold.getText();
                int i,mode;
                File file = new File(fileName);
                try {
                    if (!m.lastName(file).equals("ini")) {
                        throw new FileNotFoundException("后缀名必须为ini");
                    }

                    iniList list=m.readini(file);
                    WriteNewUnits(list);
                  //  WriteArmors(list);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        m.fileChoose(open, chooser, fold);
    }
//此函数用于把rules中的通用护甲全部改为专属护甲，以便AttachEffect编辑
    private void WriteArmors(iniList list) throws IOException {
        String listtext="[ArmorTypes]\n";
        File file = new File(fold.getText());
        Scanner sc=new Scanner(file);
        String nowunit="";
        StringBuffer buff=new StringBuffer();//临时容器!
        String ch=System.getProperty("line.separator");//平台换行!
        while(sc.hasNextLine())
        {
            String line=sc.nextLine();
            if (line.startsWith("[")&&line.endsWith("]"))
            {
                 nowunit=line.split("\\[")[1].split("]")[0];
                 buff.append(line+ch);
            }
            else if (line.startsWith("Armor=")&&!line.contains(",")&&!line.contains("."))
            {
                listtext+=nowunit.toLowerCase(Locale.ROOT)+"armor="+line.split("=")[1]+"\n";
                buff.append("Armor="+nowunit.toLowerCase(Locale.ROOT)+"armor"+ch);
            }
            else buff.append(line+ch);
        }
        buff.append(listtext+ch);
        OutputStreamWriter pw= new OutputStreamWriter(new FileOutputStream("E:\\RULES.TXT"),"gb2312");
        pw.write(String.valueOf(buff));
        if(pw!=null) {
            pw.close();
            dialog.setVisible(true);
        }
    }

    private void WriteNewUnits(iniList list) throws FileNotFoundException {
        String unitname=para.getText();
        String tip=tips.getText();
        String[] buffs=new String[30];
        String unittype="";
        int startxh=0;
        if (c.isInteger(varnums.getText(),true))
        {
            startxh=Integer.parseInt(varnums.getText());
        }
        int size=list.getAircrafts().size()-1;
        int listlen=0;
        for(String s:list.getAircrafts())
        {
            if (s.split(",")[1].equals(unitname))
            {
                unittype="aircraft";
            }
            if (unittype.equals("aircraft")&&list.getAircrafts().get(size).equals(s)) listlen=Integer.parseInt(s.split(",")[0]);
        }
        if (unittype.length()==0)
        {
            size=list.getVehicles().size()-1;
            for(String s:list.getVehicles())
            {
                if (s.split(",")[1].equals(unitname))
                {
                    unittype="vehicle";
                }
                if (unittype.equals("vehicle")&&list.getVehicles().get(size).equals(s)) listlen=Integer.parseInt(s.split(",")[0]);
            }
        }
        if (unittype.length()==0)
        {
            size=list.getBuildings().size()-1;
            for(String s:list.getBuildings())
            {
                if (s.split(",")[1].equals(unitname))
                {
                    unittype="building";
                }
                if (unittype.equals("building")&&list.getBuildings().get(size).equals(s)) listlen=Integer.parseInt(s.split(",")[0]);
            }
        }
        if (unittype.length()==0)
        {
            size=list.getInfantry().size()-1;
            for(String s:list.getInfantry())
            {
                if (s.split(",")[1].equals(unitname))
                {
                    unittype="infantry";
                }
                if (unittype.equals("infantry")&&list.getInfantry().get(size).equals(s)) listlen=Integer.parseInt(s.split(",")[0]);
            }
        }
        String listtext="";
        String unittext="";
        Scanner sc=new Scanner(tip);
        int blen=0;
        while (sc.hasNextLine())
        {
            buffs[blen]=sc.nextLine();
            blen++;
        }
        sc.close();
        java.util.List<String> clist=getAllCombine(blen,varnums.getText());
        Scanner scanner=new Scanner(new File(fold.getText()));
        int ar=0;
        while(scanner.hasNextLine())
        {
            String str=scanner.nextLine();
            if (str.contains("["+para.getText()+"]"))
            {
                ar++;
                unittext+=str+"\n";
            }
            else if (ar>0&&str.contains("[")&&str.contains("]"))
            {
                ar=0;
            }
            else if (ar>0)
            {
                unittext+=str+"\n";
            }
        }
        String utext="";
        listlen++;
        for(int i=0;i<clist.size();i++)
        {
            listtext+=listlen+"="+para.getText()+clist.get(i)+"\n";
            listlen++;
            String[] s=clist.get(i).split("_");
            String writetext="";
            if (useares.isSelected()) writetext+="["+para.getText()+"] : ";
            writetext+="["+para.getText()+clist.get(i)+"]\n";
            Scanner a=new Scanner(unittext);
            int[] used=new int[s.length];
            for(int k=0;k<s.length;k++) used[k]=0;
            int readline=0;
            while(a.hasNextLine())
            {
                String r=a.nextLine();
                if (readline==0)
                {
                    readline++;
                    continue;
                }
                else if (r!=null)
                {
                    int replace=0;
                    for(int j=0;j<s.length;j++)
                    {
                        int line=Integer.parseInt(s[j])-startxh;
                        if(r.split("=")[0].equals(buffs[line].split("=")[0]))
                        {
                            writetext+=buffs[line]+"\n";
                            used[j]=1;
                            replace++;
                        }
                    }
                    if(replace==0&&!useares.isSelected()) writetext+=r+"\n";
                }
            }
            for(int j=0;j<s.length;j++)
            {
                if (used[j]==0)
                {
                    writetext+=buffs[Integer.parseInt(s[j])-startxh]+"\n";
                }
            }
            utext+=writetext;
        }
        printUnitText(new File("E:/RULES.TXT"),utext,listtext,unittype);
    }

    private void printUnitText(File file,String utext, String listtext,String unittype) {
        BufferedReader br=null;
        StringBuffer buff=new StringBuffer();//临时容器!
        String line=System.getProperty("line.separator");//平台换行!
        int row=0;
        String ltype="";
        if (unittype.equals("building")) ltype="[BuildingTypes]";
        else if (unittype.equals("vehicle")) ltype="[VehicleTypes]";
        else if (unittype.equals("infantry")) ltype="[InfantryTypes]";
        else ltype="[AircraftTypes]";
        try {
            br=new BufferedReader(new InputStreamReader(new FileInputStream(file),"GB2312"));
            for(String str=br.readLine();str!=null;str=br.readLine()) {
                if (str.equals(ltype)) row=1;
                else if(row==1&&str.startsWith("[")&&str.endsWith("]"))
                {
                    buff.append(listtext+line);
                    row=0;
                }
                buff.append(str+line);
            }
            buff.append(utext+line);
            OutputStreamWriter pw= new OutputStreamWriter(new FileOutputStream(file),"gb2312");
            pw.write(String.valueOf(buff));
            if(pw!=null) {
                pw.close();
                dialog.setVisible(true);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(br!=null)
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

    }

    java.util.List<String> getAllCombine(int len,String str)
    {
        java.util.List<String> list=new ArrayList<>();
        java.util.List<Integer> numlist=new ArrayList<>();
        int startxh=0;
        if (c.isInteger(str,true))
        {
            startxh=Integer.parseInt(varnums.getText());
        }
        for(int i=startxh;i<startxh+len;i++) {
            numlist.add(i);
        }
        for(int i=1;i<=len;i++)
        {
            List<List<Integer>> combinations = combine(numlist, i);
            for(List<Integer> clist:combinations)
            {
                String s="";
                for(int j=0;j<clist.size();j++)
                {
                    if (j>0) s+="_";
                    s+=String.valueOf(clist.get(j));
                }
                list.add(s);
            }
        }
        return list;
    }
    int combinenum(int a,int b)
    {
        int up=a,down=1;
        for(int i=1;i<b;i++)
        {
            up=up*(a-i);
        }
        for(int i=1;i<b;i++)
        {
            down=down*(i+1);
        }
        return up/down;
    }
    public static java.util.List<java.util.List<Integer>> combine(java.util.List<Integer> list, int n) {
        java.util.List<java.util.List<Integer>> combinations = new ArrayList<>();
        generateCombinations(list, n, 0, new ArrayList<>(), combinations);
        return combinations;
    }

    private static void generateCombinations(java.util.List<Integer> list, int n, int startIndex, java.util.List<Integer> currentCombination, List<List<Integer>> combinations) {
        if (currentCombination.size() == n) {
            combinations.add(new ArrayList<>(currentCombination));
            return;
        }
        for (int i = startIndex; i < list.size(); i++) {
            currentCombination.add(list.get(i));
            generateCombinations(list, n, i + 1, currentCombination, combinations);
            currentCombination.remove(currentCombination.size() - 1);
        }
    }
}