package com.map;

import java.io.*;
import java.util.Scanner;
import java.util.*;

public class TechTrigger {

    public static class Main {
        public static void main(String[] args) throws IOException {

            int maxtech=10;//自定义，表示该科技等级以下单位或建筑会被随机禁止
            int ceng=6;//表示关卡在第几层
            File file=new File("E:/hundun/rules_superweapon.ini");
            String filename="E:/hundun/all068umd.map";
            File outputmap=new File(filename);
            List<TriTech> trlist=new ArrayList<>();
            Scanner sc=new Scanner(file);
            int sw=0;
            List<SW> swlist=new ArrayList<>();
            while(sc.hasNextLine())
            {
                String str=sc.nextLine();
                if(str.equals("[SuperWeaponTypes]"))
                {
                    sw=1;
                }
                else if (str.contains("["))
                {
                    sw=0;
                }
                if (sw==1&&str.contains("="))
                {
                    SW swn=new SW();
                    swn.setName(str.split("=")[1]);
                    swn.setCode(Integer.parseInt(str.split("=")[0]));
                    swlist.add(swn);
                }
            }
            CommonMethod m =new CommonMethod();
            mapList list= m.readMapList(filename);
            String maxcode="0"+(Integer.parseInt(list.getMaxnormalcode().substring(1))+1);
            int code=Integer.parseInt(maxcode.substring(1));
            String tag="",trigger="",events="",actions1="",actions="";
            int tasknum=list.getMaxtaskForce()+1;
            String task="",tasklist="";
            task+="[0"+code+"]\n";
            task+="0=1,AMCV\n" +
                    "Name=1AMCV\n" +
                    "Group=-1\n";
            tasklist+=tasknum+"=0"+code+"\n";
            code++;
            task+="[0"+code+"]\n";
            task+="0=1,SMCV\n" +
                    "Name=1SMCV\n" +
                    "Group=-1\n";
            tasklist+=(tasknum+1)+"=0"+code+"\n";
            code++;
            task+="[0"+code+"]\n";
            task+="0=1,PCV\n" +
                    "Name=1PCV\n" +
                    "Group=-1\n";
            tasklist+=(tasknum+2)+"=0"+code+"\n";
            code++;
            int firstcode=code;
            trigger+="0"+code+"=Americans,<none>,科技禁用开始,0,1,1,1,0\n";
            tag+="0"+(code+1)+"=0,科技禁用开始 1,0"+code+"\n";
            events+="0"+code+"=2,27,0,6,13,0,3\n";
            actions+="0"+code+"=replacing,53,2,0"+(code+2)+",0,0,0,0,A,53,2,0"+(code+4)+",0,0,0,0,A,53,2,0"+(code+6)+",0,0,0,0,A,53,2,0"+(code+8)+",0,0,0,0,A,53,2,0"+(code+10)+",0,0,0,0,A,53,2,0"+(code+12)+",0,0,0,0,A,40005,4,m.ini@TechBan@Num"+(ceng+1)+",1,0,0,0,A";
            code=code+2;
            List<String> vlist=list.getVariableNames();
            String var0="Random_Stage1,0";
            int pyl=vlist.indexOf(var0);
            trigger+="0"+code+"=Americans,<none>,科技禁用开始_判断是否可禁盟军,1,1,1,1,0\n" +
                    "0"+(code+2)+"=Americans,<none>,科技禁用开始_判断是否可禁盟军2,1,1,1,1,0\n" +
                    "0"+(code+4)+"=Americans,<none>,科技禁用开始_判断是否可禁苏军,1,1,1,1,0\n" +
                    "0"+(code+6)+"=Americans,<none>,科技禁用开始_判断是否可禁苏军2,1,1,1,1,0\n" +
                    "0"+(code+8)+"=Americans,<none>,科技禁用开始_判断是否可禁尤里2,1,1,1,1,0\n" +
                    "0"+(code+10)+"=Americans,<none>,科技禁用开始_判断是否可禁尤里,1,1,1,1,0\n";
            tag+="0"+(code+1)+"=0,科技禁用开始_判断是否可禁盟军 1,0"+code+"\n" +
                    "0"+(code+3)+"=0,科技禁用开始_判断是否可禁盟军2 1,0"+(code+2)+"\n" +
                    "0"+(code+5)+"=0,科技禁用开始_判断是否可禁苏军 1,0"+(code+4)+"\n" +
                    "0"+(code+7)+"=0,科技禁用开始_判断是否可禁苏军2 1,0"+(code+6)+"\n" +
                    "0"+(code+9)+"=0,科技禁用开始_判断是否可禁尤里2 1,0"+(code+8)+"\n" +
                    "0"+(code+11)+"=0,科技禁用开始_判断是否可禁尤里 1,0"+(code+10)+"\n";
            events+="0"+code+"=1,27,0,8\n" +
                    "0"+(code+2)+"=1,27,0,2\n" +
                    "0"+(code+4)+"=1,27,0,9\n" +
                    "0"+(code+6)+"=1,27,0,3\n" +
                    "0"+(code+8)+"=1,27,0,4\n" +
                    "0"+(code+10)+"=1,27,0,10\n";
            actions1+="0"+code+"=1,56,0,"+(pyl+16)+",0,0,0,0,A\n" +
                    "0"+(code+2)+"=1,56,0,"+(pyl+16)+",0,0,0,0,A\n" +
                    "0"+(code+4)+"=1,56,0,"+(pyl+17)+",0,0,0,0,A\n" +
                    "0"+(code+6)+"=1,56,0,"+(pyl+17)+",0,0,0,0,A\n" +
                    "0"+(code+8)+"=1,56,0,"+(pyl+18)+",0,0,0,0,A\n" +
                    "0"+(code+10)+"=1,56,0,"+(pyl+18)+",0,0,0,0,A\n";
            code=code+12;
            int sjcode=code,sjcode2=code;
            List<Tech> tlist=getTechList();
            String owner="";
            for(Tech tech:tlist)
            {
                if(tech.getTechlevel()>maxtech) continue;
                if (tech.getBelong()==1)
                {
                    owner="盟军";
                }
                else if (tech.getBelong()==2)
                {
                    owner="苏联";
                }
                else if (tech.getBelong()==3)
                {
                    owner="尤里";
                }
                trigger+="0"+code+"=Americans,<none>,科技禁用_"+owner+tech.getCode()+",1,1,1,1,0\n";
                tag+="0"+(code+1)+"=0,科技禁用_"+owner+tech.getCode()+" 1,0"+code+"\n";
                actions1+="0"+code+"=3,40005,4,m.ini@"+tech.getCode()+"@TechLevel,11,0,0,0,A,56,0,"+(19+pyl)+",0,0,0,0,A,40005,4,m.ini@"+tech.getCode()+"@TechBan"+(ceng+1)+",1,0,0,0,A\n";
                events+="0"+code+"=3,36,0,"+(15+tech.getBelong())+",51,0,100,37,0,"+(19+pyl)+"\n";
                TriTech triTech=new TriTech();
                triTech.code=code;
                triTech.tech=tech.getCode();
                trlist.add(triTech);
                code=code+2;
            }
            int codednum=0,kk=1,ranendcode=code;
            String xingwei="";
            while(sjcode<ranendcode)
            {
                if(codednum==0)
                {
                    trigger+="0"+code+"=Americans,<none>,科技禁用清除"+kk+",0,1,1,1,0\n";
                    tag+="0"+(code+1)+"=0,科技禁用清除"+kk+" 1,0"+code+"\n";
                    actions1+="0"+code+"=";
                    events+="0"+code+"=1,36,0,"+(19+pyl)+"\n";
                    code=code+2;
                    xingwei="";
                    kk++;
                }
                if(codednum<19)
                {
                    xingwei+=",12,2,0"+sjcode+",0,0,0,0,A";
                    if(sjcode==ranendcode-2)
                    {
                        actions1+=(codednum+1)+xingwei+"\n";
                        codednum=-1;
                        xingwei="";
                    }
                }
                else if (codednum==19) {
                    xingwei += ",12,2,0" + sjcode + ",0,0,0,0,A\n";
                    codednum = -1;
                    actions1 += "20" + xingwei;
                    xingwei = "";
                }
                sjcode=sjcode+2;
                codednum++;
            }
            codednum=0;
            kk=1;
            xingwei="";
            int replacecode=code+2;
            int cxcode1=code,cxcode2;
            while(sjcode2<ranendcode)
            {
                if(codednum==0)
                {
                    code=code+2;
                    trigger+="0"+code+"=Americans,<none>,科技禁用开始随机"+kk+",1,1,1,1,0\n";
                    tag+="0"+(code+1)+"=0,科技禁用开始随机"+kk+" 1,0"+code+"\n";
                    actions1+="0"+code+"=";
                    events+="0"+code+"=1,13,0,2\n";
                    xingwei="";
                    kk++;
                }
                if(codednum<19)
                {
                    xingwei+=",53,2,0"+sjcode2+",0,0,0,0,A";
                    if(sjcode2==ranendcode-2)
                    {
                        actions1+=(codednum+1)+xingwei+"\n";
                        codednum=-1;
                        xingwei="";
                    }
                }
                else if (codednum==19) {
                    xingwei += ",53,2,0" + sjcode2 + ",0,0,0,0,A\n";
                    codednum = -1;
                    actions1 += "20" + xingwei;
                    xingwei = "";
                }
                sjcode2=sjcode2+2;
                codednum++;
            }
            int actnum=7;
            while (replacecode<=code)
            {
                actions+=",53,2,0"+replacecode+",0,0,0,0,A";
                replacecode+=2;
                actnum++;
            }
            cxcode2=code;
            actions+="\n";
            actions=actions.replaceAll("replacing",String.valueOf(actnum));
            actions=actions+actions1;
            code=code+2;
            String[] countries= list.getCountries();
            int countriescode=0;
            for(int j=0;j<countries.length;j++)
            {
                if(countries[j].equals(list.getUsingSPYPHouse()))
                {
                    countriescode=j;
                    break;
                }
            }
            for(SW w:swlist)
            {
                if (!w.getName().contains("TechCheck")) continue;
                if (w.getName().contains("_Num")) continue;
                String techcode=w.getName().split("_")[1];
                Tech tech = null;
                for(Tech t:tlist)
                {
                    if(t.getCode().equals(techcode))
                    {
                        tech=t;
                        break;
                    }
                }
                trigger+="0"+code+"="+list.getUsingSPYPHouse()+",<none>,科技禁用_已禁判断"+tech.getCode()+",0,1,1,1,0\n";
                tag+="0"+(code+1)+"=0,科技禁用_已禁判断"+tech.getCode()+" 1,0"+code+"\n";
                actions+="0"+code+"=2,40004,4,m.ini@"+tech.getCode()+"@TechLevel,"+(w.getCode()-1)+","+countriescode+",20,20,A,40004,4,m.ini@"+tech.getCode()+"@TechBan"+(ceng+1)+","+(w.getCode()-1)+","+countriescode+",20,20,A\n";
                //40004,4,m.ini@E1@TechLevel,15,8,20,20,A
                events+="0"+code+"=1,27,0,6\n";
                code=code+2;
            }
            int gcode=code;
            for(Tech tech:tlist)
            {
                int ncode=0;
                for(TriTech tri:trlist)
                {
                    if (tri.tech.equals(tech.getCode())) ncode=tri.code;
                }
                trigger+="0"+code+"=Americans,<none>,科技禁用_已禁清除"+tech.getCode()+",0,1,1,1,0\n";
                tag+="0"+(code+1)+"=0,科技禁用_已禁清除"+tech.getCode()+" 1,0"+code+"\n";
                events+="0"+code+"=2,13,0,1,60,2,1,SPYP_"+tech.getCode()+"\n";
                actions+="0"+code+"=";
                if(ncode>0) actions+="2,12,2,0"+ncode+",0,0,0,0,A,11,4,a:Ban"+tech.getCode()+",0,0,0,0,A\n";
                else actions+="1,11,4,a:Ban"+tech.getCode()+",0,0,0,0,A\n";
                code=code+2;
            }
            int lastnum=(cxcode2-cxcode1)/2;
            trigger+="0"+code+"=Americans,<none>,科技禁用超限判断,1,1,1,1,0\n" +
                    "0"+(code+2)+"=Americans,<none>,科技禁用超限判断2,1,1,1,1,0\n";
            tag+="0"+(code+1)+"=0,科技禁用超限判断 1,0"+code+"\n" +
                    "0"+(code+3)+"=0,科技禁用超限判断2 1,0"+(code+2)+"\n";
            events+="0"+code+"=1,13,0,0\n"+
                    "0"+(code+2)+"=1,60,2,1,SPYP_TECH"+ceng+"\n";
            actions+="0"+code+"=1,40004,4,m.ini@TechBan@Num"+(ceng+1)+","+(86+ceng)+","+countriescode+",20,20,A\n"+
                    "0"+(code+2)+"="+lastnum;
            for(int j=1;j<=lastnum;j++)
            {
                actions+=",12,2,0"+(cxcode1+j*2)+",0,0,0,0,A";
            }
            actions+="\n";
            code=code+4;
            int cy=code-gcode;
            for(Tech tech:tlist)
            {
                trigger+="0"+code+"=Americans,<none>,科技禁用_重开恢复科技等级"+tech.getCode()+",0,1,1,1,0\n";
                tag+="0"+(code+1)+"=0,科技禁用_重开恢复科技等级"+tech.getCode()+" 1,0"+code+"\n";
                events+="0"+code+"=1,60,2,2,SPYP_"+tech.getCode()+"\n";
                actions+="0"+code+"=";
                actions+="3,106,9,"+tech.getCode()+",0,0,0,0,"+tech.getTechlevel()+",12,2,0"+(code-cy)+",0,0,0,0,A,12,2,0"+firstcode+",0,0,0,0,A\n";
                code=code+2;
            }
              System.out.println(trigger);
              System.out.println(tag);
          System.out.println(events);
          System.out.println(actions);
            writeMap(outputmap,trigger,tag,events,actions,task,tasklist);
      /*          if(shj<3&&s.endsWith("=1,36,0,19"))
                {
                    shj=1;
                }
                else if (shj==1&&s.endsWith("=1,13,0,2"))
                {
                    if(desstart.length()==0) desstart=s.split("=")[0];
                }
                else if (shj==1&&s.endsWith("1,27,0,6"))
                {
                    if(desend.length()==0) desend=s.split("=")[0];
                    shj=3;
                }
            }
            int lastnum=(Integer.parseInt(desend.substring(1))-Integer.parseInt(desstart.substring(1)))/2;
            actions+=",40005,4,m.ini@TechBan@Num2,1,0,0,0,A\n";

            System.out.println(evnum+","+acnum);
System.out.println(actions);
            System.out.println(events);
            String event="",action="";
            code=Integer.parseInt(maxcode.substring(1));
            trigger+="0"+code+"=Americans,<none>,科技禁用超限判断,1,1,1,1,0\n" +
                    "0"+(code+2)+"=Americans,<none>,科技禁用超限判断2,1,1,1,1,0\n";
            tag+="0"+(code+1)+"=0,科技禁用超限判断 1,0"+code+"\n" +
                    "0"+(code+3)+"=0,科技禁用超限判断2 1,0"+(code+2)+"\n";
            event+="0"+code+"=1,13,0,0\n"+
                    "0"+(code+2)+"=1,60,2,1,SPYP_TECH"+ceng+"\n";
            action+="0"+code+"=1,40004,4,m.ini@TechBan@Num"+ceng+",86,"+countriescode+",20,20,A\n"+
                    "0"+(code+2)+"="+lastnum;
            for(int j=0;j<lastnum;j++)
            {
                action+=",12,2,0"+(Integer.parseInt(desstart.substring(1))+j*2)+",0,0,0,0,A";
            }
           // writeMapFor(outputmap,trigger,tag,event,action,events,actions,evnum,acnum);
            //writeMap(outputmap,trigger,tag,events,actions,task,tasklist);
//System.out.println(tlist);*/
        }

        private static void writeMap(File file2, String triggerstext, String tagstext, String eventstext, String actionstext,String task,String tasklist) throws FileNotFoundException, UnsupportedEncodingException {
            BufferedReader br=null;
            StringBuffer buff=new StringBuffer();//临时容器!
            String line=System.getProperty("line.separator");//平台换行!
            int row=0;
            br=new BufferedReader(new InputStreamReader(new FileInputStream(file2),"GB2312"));
            int used=0;
            int tagrow=0,triggerrow=0,eventrow=0,actionrow=0,taskrow=0;
            try {
                String  str=br.readLine();
                while((str=br.readLine())!=null) {
                    if (str.equals("[Events]")) eventrow=1;
                    if (str.equals("[Tags]")) tagrow=1;
                    if (str.equals("[Actions]")) actionrow=1;
                    if (str.equals("[Triggers]")) triggerrow=1;
                    if (str.equals("[TaskForces]")) taskrow=1;
                    if (triggerrow>0&&!str.equals("[Triggers]")&&!str.contains("="))
                    {
                        str = str.replaceAll(str, triggerstext);
                        triggerrow=0;
                    }
                    else if (tagrow>0&&!str.equals("[Tags]")&&!str.contains("="))  {
                        str = str.replaceAll(str, tagstext);
                        tagrow=0;
                    }
                    else if (actionrow>0&&!str.equals("[Actions]")&&!str.contains("="))  {
                        str = str.replaceAll(str, actionstext);
                        actionrow=0;
                    }
                    else if (eventrow>0&&!str.equals("[Events]")&&!str.contains("="))  {
                        str = str.replaceAll(str, eventstext);
                        eventrow=0;
                    }
                    else if (taskrow>0&&!str.equals("[TaskForces]")&&!str.contains("="))  {
                        str = str.replaceAll(str, tasklist);
                        taskrow=0;
                    }
                    buff.append(str+line);
                }
                buff.append(task+line);
                OutputStreamWriter pw= new OutputStreamWriter(new FileOutputStream(file2),"gb2312");
                if(pw!=null) {
                    pw.write(String.valueOf(buff));
                    pw.close();
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
        private static void writeMapFor(File file2, String triggerstext, String tagstext, String eventstext, String actionstext,String event,String action,int ecode,int acode) throws FileNotFoundException, UnsupportedEncodingException {
            BufferedReader br=null;
            StringBuffer buff=new StringBuffer();//临时容器!
            String line=System.getProperty("line.separator");//平台换行!
            int row=0;
            int i=1;
            br=new BufferedReader(new InputStreamReader(new FileInputStream(file2),"GB2312"));
            int used=0;
            int tagrow=0,triggerrow=0,eventrow=0,actionrow=0,taskrow=0;
            try {
                String  str;
                while((str=br.readLine())!=null) {
                    if(i==ecode)
                        str=str.replaceAll(str,event);
                    if(i==acode)
                        str=str.replaceAll(str,action);
                    if (str.equals("[Events]")) eventrow=1;
                    if (str.equals("[Tags]")) tagrow=1;
                    if (str.equals("[Actions]")) actionrow=1;
                    if (str.equals("[Triggers]")) triggerrow=1;
                    if (triggerrow>0&&!str.equals("[Triggers]")&&!str.contains("="))
                    {
                        str = str.replaceAll(str, triggerstext);
                        triggerrow=0;
                    }
                    else if (tagrow>0&&!str.equals("[Tags]")&&!str.contains("="))  {
                        str = str.replaceAll(str, tagstext);
                        tagrow=0;
                    }
                    else if (actionrow>0&&!str.equals("[Actions]")&&!str.contains("="))  {
                        str = str.replaceAll(str, actionstext);
                        actionrow=0;
                    }
                    else if (eventrow>0&&!str.equals("[Events]")&&!str.contains("="))  {
                        str = str.replaceAll(str, eventstext);
                        eventrow=0;
                    }
                    buff.append(str+line);
                }
                OutputStreamWriter pw= new OutputStreamWriter(new FileOutputStream(file2),"gb2312");
                if(pw!=null) {
                    pw.write(String.valueOf(buff));
                    pw.close();
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
        static List<Tech> getTechList() throws FileNotFoundException
        {
            List<Tech> list = new ArrayList<>();
            List<String> blist = new ArrayList<>();
            File file = new File("E:\\新文件 1.txt");
            File rules = new File("e:\\hundun\\rulesmd.ini");
            Scanner sc = new Scanner(file);
            Scanner sc2 = new Scanner(rules);
            while (sc.hasNextLine()) {
                blist.add(sc.nextLine());
            }
            int contain = 0,yaosu=0,belong=0,techlevel=-1;
            String thecode = "";
            while (sc2.hasNextLine()) {
                String str = sc2.nextLine();
                if (str.startsWith("[") && str.endsWith("]")) {
                    String s = str.split("\\[")[1].split("]")[0];
                    if (blist.contains(s)) {
                        contain = 1;
                        thecode = s;
                        yaosu=0;
                        techlevel=-1;
                        belong=0;
                    }
                } else if (contain == 1 && str.contains("TechLevel=")) {
                    techlevel=Integer.parseInt(str.split("=")[1]);
                    yaosu++;
                }else if (contain == 1 && str.contains("Prerequisite=")) {
                    String pre=str.split("=")[1];
                    if(thecode.equals("ADOG"))
                    {
                        belong=1;
                    }
                    else if (thecode.equals("DOG"))
                    {
                        belong=2;
                    }
                    else if(pre.contains("GAYARD")||pre.contains("GACNST")||pre.contains("GAPILE")||pre.contains("GATECH")||pre.contains("GAWEAP")||pre.contains("GAAIRC")||pre.contains("AMRADR"))
                    {
                        belong=1;
                    }
                    else if (pre.contains("NAYARD")||pre.contains("NACNST")||pre.contains("NAHAND")||pre.contains("NATECH")||pre.contains("NAWEAP")||pre.contains("RADAR"))
                    {
                        belong=2;
                    }
                    else if (pre.contains("YACNST")||pre.contains("YABRCK")||pre.contains("YATECH")||pre.contains("YAWEAP")||pre.contains("YAYARD")||pre.contains("NAPSIS"))
                    {
                        belong=3;
                    }
                    else belong=4;
                    yaosu++;
                }
                if(yaosu==2)
                {
                    Tech tech = new Tech();
                    tech.setCode(thecode);
                    tech.setTechlevel(techlevel);
                    tech.setBelong(belong);
                    list.add(tech);
                    thecode = "";
                    contain = 0;
                    yaosu=0;
                }
            }
            return list;
        }
    }
}
class TriTech
{
    String tech;
    int code;
}