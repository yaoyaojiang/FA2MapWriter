package com.map;
import java.io.*;
import java.util.*;

public class tryit {

    public static class Main {


        public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
            /*ist<Tech> list=getTechList();
            String airlist="";
            String aircode="";
            String spcode="";
            String splist="";
            int airs=14,sps=16;
            for(Tech tech:list)
            {
                airlist+=airs+"=SPYP_"+tech.getCode()+"\n";
                airs++;
                aircode+="[SPYP_"+tech.getCode()+"]\n" +
                        "Image=EMPTY\n" +
                        "UIName=Name:SpyP\n" +
                        "Name=Soviet Spy Plane\n" +
                        "Strength=10\n" +
                        "Category=AirLift\n" +
                        "Armor=light\n" +
                        "TechLevel=-1\n" +
                        "Primary=SpyCameraWeapon\n" +
                        "Spawned=yes\t;" +
                        "LeadershipRating=10\n" +
                        "Selectable=no\n" +
                        "RadarInvisible=yes\n" +
                        "Sight=0\n" +
                        "Landable=no\n" +
                        "MoveToShroud=yes\n" +
                        "PitchAngle=0\n" +
                        "Speed=15\n" +
                        "Owner=British,French,Germans,Americans,Alliance,Russians,Confederation,Africans,Arabs,YuriCountry\n" +
                        "Points=30\n" +
                        "ROT=2\n" +
                        "Crewed=yes\n" +
                        "Ammo=100\n" +
                        "Explosion=TWLT070,S_BANG48,S_BRNL58,S_CLSN58,S_TUMU60\n" +
                        "MaxDebris=2\n" +
                        "Locomotor={4A582746-9839-11d1-B709-00A024DDAFD1}\n" +
                        "MovementZone=Fly\n" +
                        "ThreatPosed=0\t" +
                        "DamageParticleSystems=SparkSys,SmallGreySSys\n" +
                        "ImmuneToPsionics=yes\n" +
                        "CanPassiveAquire=no\n" +
                        "CanRetaliate=no\n" +
                        "MoveSound=SpyPlaneMoveLoop\n" +
                        "DieSound=\n" +
                        "CrashingSound=SpyPlaneDie\n" +
                        "ImpactLandSound=GenAircraftCrash\n" +
                        "DeathWeapon=BlimpBomb\n" +
                        "DeathWeaponDamageModifier=.1\n" +
                        "FlyBy=true\t" +
                        "Survivor.RookiePassengerChance=0%\n" +
                        "Survivor.VeteranPassengerChance=0%\n" +
                        "Survivor.ElitePassengerChance=0%\n" +
                        "Cloakable=yes\n" +
                        "SensorArray.Warn=no\n" +
                        "AttachEffect.Animation=EXPLOSML2\n" +
                        "AttachEffect.InitialDelay=15\n" +
                        "AttachEffect.Duration=5\n";
                spcode+="[TechCheck_"+tech.getCode()+"]\n" +
                        "UIName=Name:Chrono\n" +
                        "Name=Chrono Sphere\n" +
                        "IsPowered=true\n" +
                        "RechargeVoice=00-I156\n" +
                        "ChargingVoice=\n" +
                        "ImpatientVoice=\n" +
                        "SuspendVoice=\n" +
                        "RechargeTime=7\n" +
                        "Type=SpyPlane\n" +
                        "Action=Custom\n" +
                        "SidebarImage=SPYPICON\n" +
                        "DisableableFromShell=no\n" +
                        "Range=1.4\n" +
                        "LineMultiplier=3\n" +
                        "SW.CreateRadarEvent=no\n" +
                        "ShowTimer=no\n" +
                        "SW.RequiresTarget=all\n" +
                        "SpyPlane.Type=SPYP_"+tech.getCode()+"\n" +
                        "SpyPlane.Count=1\n" +
                        "SW.AITargeting=NoTarget\n" +
                        "SW.AutoFire=yes\n" +
                        "SW.InitialReady=yes\n" +
                        "SW.ShowCameo=no\n";
                splist+=sps+"=TechCheck_"+tech.getCode()+"\n";
                sps++;
            }
            System.out.println(splist);
            System.out.println(spcode);*/
            System.out.println(gee());
        }
        static String gee() throws FileNotFoundException, UnsupportedEncodingException {
            String s="";
            String unit="";
            for(int i=0;i<3;i++)
            {
                if(i==0) unit="MTNK";
                else if (i==1) unit="MGTK";
                else unit="BEAG";
                for(int j=1;j<=12;j++)
                {
                    String tj;
                    if (j<10) tj="0"+j;
                    else tj=String.valueOf(j);
                    s+="["+unit+tj+"]\n" +
                    "UIName=DZ:"+unit+tj+"\n"+
                    "BuildCat=Combat\n"+
                    "Strength=900\n"+
                    "Armor=steel\n"+
                    "TechLevel=12\n"+
                    "Prerequisite=GACNST\n"+
                    "Adjacent=2\n"+
                    "Sight=5\n"+
                    "Owner=British,French,Germans,Americans,Alliance,Russians,Confederation,Africans,Arabs,YuriCountry\n"+
                    "AIBasePlanningSide=1\n"+
                    "Cost=0\n"+
                    "BaseNormal=no\n"+
                    "Points=30\n"+
                    "Power=0\n"+
                    "Crewed=no\n"+
                    "Capturable=false\n"+
                    "Explosion=TWLT070,S_BANG48,S_BRNL58,S_CLSN58,S_TUMU60\n"+
                    "DebrisAnims=DBRIS4LG,DBRIS4SM,DBRIS6LG\n"+
                    "MaxDebris=3\n"+
                    "MinDebris=2\n"+
                    "ThreatPosed=0\n"+
                    "Powered=yes\n"+
                    "ROT=8\n"+
                    "ImmuneToPsionics=no\n";
                }
            }


            return s;
        }

        private static void writeMap(File file2, String triggerstext, String tagstext, String eventstext, String actionstext) throws FileNotFoundException, UnsupportedEncodingException {
            BufferedReader br=null;
            StringBuffer buff=new StringBuffer();//临时容器!
            String line=System.getProperty("line.separator");//平台换行!
            int row=0;
            br=new BufferedReader(new InputStreamReader(new FileInputStream(file2),"GB2312"));
            int used=0;
            int tagrow=0,triggerrow=0,eventrow=0,actionrow=0;
            try {
                String  str=br.readLine();
                while((str=br.readLine())!=null) {
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
            File rules = new File("D:\\rulesmd.txt");
            Scanner sc = new Scanner(file);
            Scanner sc2 = new Scanner(rules);
            while (sc.hasNextLine()) {
                blist.add(sc.nextLine());
            }
            int contain = 0;
            String thecode = "";
            while (sc2.hasNextLine()) {
                String str = sc2.nextLine();
                if (str.startsWith("[") && str.endsWith("]")) {
                    String s = str.split("\\[")[1].split("]")[0];
                    if (blist.contains(s)) {
                        contain = 1;
                        thecode = s;
                    }
                } else if (contain == 1 && str.contains("TechLevel=")) {
                    contain = 0;
                    Tech tech = new Tech();
                    tech.setCode(thecode);
                    tech.setTechlevel(Integer.parseInt(str.split("=")[1]));
                    list.add(tech);
                    thecode = "";
                }
            }
            return list;
        }
    }

    class Techtype
    {
        String name;
        String owner;
        String country;
        String prere;
        String image;
    }
    static class Techcat
    {
        String name;
        String category;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        @Override
        public String toString() {
            return "Techcat{" +
                    "name='" + name + '\'' +
                    ", category='" + category + '\'' +
                    '}';
        }
    }
}