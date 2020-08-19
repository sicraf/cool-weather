package com.sicraf.coolweather.util;

import android.text.TextUtils;

import com.sicraf.coolweather.db.City;
import com.sicraf.coolweather.db.County;
import com.sicraf.coolweather.db.Province;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Utility {
    /*
     * 解析和处理服务器返回的省级数据
     */
    public static boolean handleProvincesResponse(String response) {
        if(!TextUtils.isEmpty(response)) {
            try {
                JSONArray allProvinces = new JSONArray(response);
                for(int i = 0; i < allProvinces.length(); i++) {
                    JSONObject provinceObj = allProvinces.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceName(provinceObj.getString("name"));
                    province.setProvinceCode(provinceObj.getInt("id"));
                    province.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /*
     * 解析和处理服务器返回的市级数据
     */
    public static boolean handleCitiesResponse(String response, int provinceId) {
        if(!TextUtils.isEmpty(response)) {
            try {
                JSONArray allCities = new JSONArray(response);
                for(int i = 0; i < allCities.length(); i++) {
                    JSONObject cityObj = allCities.getJSONObject(i);
                    City city = new City();
                    city.setCityName(cityObj.getString("name"));
                    city.setCityCode(cityObj.getInt("id"));
                    city.setProvinceId(provinceId);
                    city.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /*
     * 解析和处理服务器返回的县级数据
     */
    public static boolean handleCountiesResponse(String response, int cityId) {
        if(!TextUtils.isEmpty(response)) {
            try {
                JSONArray allCounties = new JSONArray(response);
                for(int i = 0; i < allCounties.length(); i++) {
                    JSONObject countyObj = allCounties.getJSONObject(i);
                    County county = new County();
                    county.setCountyName(countyObj.getString("name"));
                    county.setWeatherId(countyObj.getString("weather_id"));
                    county.setCityId(cityId);
                    county.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
