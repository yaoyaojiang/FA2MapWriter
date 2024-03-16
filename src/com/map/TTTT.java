package com.map;

public class TTTT
{
    public void  main(String args[])
{

}

    String Waypoint_toAl(int k)
    {
        String s = null;
        try {
            if (k < 26) {
                char ch = (char) (k + 65);
                s = String.valueOf(ch);
            } else if (k >= 26&&k<702) {
                int fi = k / 26;
                int en = k % 26;
                char ch1 = (char) (fi + 64);
                char ch2 = (char) (en + 65);
                s = String.valueOf(ch1) + String.valueOf(ch2);
            } else if (k > 701) throw new Exception("路径点不能大于701");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }
}
