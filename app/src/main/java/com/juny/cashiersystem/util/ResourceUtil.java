package com.juny.cashiersystem.util;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;


/**
 * 获取系统资源的工具类
 * @author ls
 *
 */
public class ResourceUtil {

    // com.bumptech.glide.load.model.AssetUriParser.ASSET_PREFIX
    public static final String ASSET_PREFIX = "file:///android_asset/";
    // android.resource://package_name/id_number
    public static final String RES_PREFIX = "android.resource://" + Env.getContext().getPackageName() + "/";
    // android.resource://package_name/type/name
    public static final String RAW_PREFIX = "android.resource://" + Env.getContext().getPackageName() + "/raw/";

    /**
     * 根据资源ID返回字符串
     * 
     * @param resid
     * @return
     */
    public static String getString(int resid){
        return Env.getContext().getString(resid);
    }
    
    /**
     * 根据资源ID返回字符串
     * @param resid
     * @param formatArgs
     * @return
     */
    public static String getString(int resid, Object... formatArgs){
        return Env.getContext().getString(resid, formatArgs);
    }
    
    /**
     * 获取颜色
     */
    public static int getColor(int resid){
        return Env.getContext().getResources().getColor(resid);
    }   
    
    /**
     * 获取图片
     */
    public static Drawable getDrawable(int resid){
        return Env.getContext().getResources().getDrawable(resid);
    }

    /**
     * 获取图片
     */
    public static Bitmap getBitmap(int resid){
        Resources res = Env.getContext().getResources();
        return BitmapFactory.decodeResource(res, resid);
    }

    /**
     * 获取dimems大小（单位为像素pix）
     * @param resid
     * @return
     */
    public static int getDimens(int resid){
        return (int)Env.getContext().getResources().getDimension(resid);
    }
    
    /**
     * 根据资源id返回字符串数组
     * @param resid
     * @return
     */
    public static String[] getStringArray(int resid){
        return Env.getContext().getResources().getStringArray(resid);
    }
    
    /**
     * 获取资源中的整数数据
     * @param resid
     * @return
     */
    public static int getInteger(int resid){
        return Env.getContext().getResources().getInteger(resid);
    }
    
    /**
     * 根据资源名获取资源id
     * @param name 资源名
     * @return
     */
    public static int getDrawableResId(String name){
    	return Env.getContext().getResources().getIdentifier(name, "drawable",Env.getContext().getPackageName());
    }
    
}
