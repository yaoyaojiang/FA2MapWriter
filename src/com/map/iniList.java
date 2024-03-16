package com.map;

import java.util.*;

public class iniList {
    List<String> TagLine;
    List<String> Vehicles;
    List<String> Infantry;
    List<String> Aircrafts;
    List<String> Buildings;
    List<String> Warheads;
    List<String> SuperWeapons;
    List<String> Variables;


    public void initialize()
    {
        this.TagLine=new ArrayList<>();
        this.Vehicles=new ArrayList<>();
        this.Infantry=new ArrayList<>();
        this.Aircrafts=new ArrayList<>();
        this.Buildings=new ArrayList<>();
        this.Warheads=new ArrayList<>();
        this.SuperWeapons=new ArrayList<>();
        this.Variables=new ArrayList<>();
    }


    public void addVehicles(String s)
    {
        this.Vehicles.add(s);
    }
    public void addInfantry(String s)
    {
        this.Infantry.add(s);
    }
    public void addAircrafts(String s)
    {
        this.Aircrafts.add(s);
    }
    public void addBuildings(String s)
    {
        this.Buildings.add(s);
    }
    public void addWarheads(String s)
    {
        this.Warheads.add(s);
    }
    public void addSuperWeapons(String s)
    {
        this.SuperWeapons.add(s);
    }
    public void addVariables(String s)
    {
        this.Variables.add(s);
    }

    public List<String> getVariables() {
        return Variables;
    }

    public void setVariables(List<String> variables) {
        Variables = variables;
    }

    public List<String> getTagLine() {
        return TagLine;
    }

    public void setTagLine(List<String> tagLine) {
        TagLine = tagLine;
    }

    public List<String> getVehicles() {
        return Vehicles;
    }

    public void setVehicles(List<String> vehicles) {
        Vehicles = vehicles;
    }

    public List<String> getInfantry() {
        return Infantry;
    }

    public void setInfantry(List<String> infantry) {
        Infantry = infantry;
    }

    public List<String> getAircrafts() {
        return Aircrafts;
    }

    public void setAircrafts(List<String> aircrafts) {
        Aircrafts = aircrafts;
    }

    public List<String> getBuildings() {
        return Buildings;
    }

    public void setBuildings(List<String> buildings) {
        Buildings = buildings;
    }

    public List<String> getWarheads() {
        return Warheads;
    }

    public void setWarheads(List<String> warheads) {
        Warheads = warheads;
    }

    public List<String> getSuperWeapons() {
        return SuperWeapons;
    }

    public void setSuperWeapons(List<String> superWeapons) {
        SuperWeapons = superWeapons;
    }

    @Override
    public String toString() {
        return "iniList{" +
                "TagLine=" + TagLine +
                ", Vehicles=" + Vehicles +
                ", Infantry=" + Infantry +
                ", Aircrafts=" + Aircrafts +
                ", Buildings=" + Buildings +
                ", Warheads=" + Warheads +
                ", SuperWeapons=" + SuperWeapons +
                ", Variables=" + Variables +
                '}';
    }
}
