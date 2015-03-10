/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.sensors;

import com.codename1.system.NativeInterface;

/**
 *
 * @author Chen
 */
public interface SensorsNative extends NativeInterface{
    
    public void init(int type);
    
    public void registerListener(int type);
    
    public void deregisterListener(int type);
    
}
