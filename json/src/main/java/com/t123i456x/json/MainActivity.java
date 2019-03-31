package com.t123i456x.json;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;
import org.json.JSONTokener;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private String TAG = "MainActivity";
    private String VersionInfo = "[{\"outputType\":{\"type\":\"APK\"}," +
            "\"apkInfo\":{\"type\":\"MAIN\",\"splits\":[],\"versionCode\":1," +
            "\"versionName\":\"1.0\",\"enabled\":true," +
            "\"outputFile\":\"app-release.apk\",\"fullName\":\"release\",\"baseName\":\"release\"}," +
            "\"path\":\"app-release.apk\"," +
            "\"properties\":{}}]";
    private TextView tv0;
    private int VersionCode = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv0 = (TextView) findViewById(R.id.tv0);

    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.bn0:
                try{
                    objectToJson();
                }catch (Exception e){
                    Log.d(TAG,"error");
                }
                break;
            case R.id.bn1:
                try{
                    arrayToJson();
                }catch (Exception e){
                    Log.d(TAG,"error");
                }
                break;
            case R.id.bn2:
                try{
                    jsonStringerToJson();
                }catch (Exception e){
                    Log.d(TAG,"error");
                }
                break;
            case R.id.bn3:
                try{
                    fromJson();
                }catch (Exception e){
                    Log.d(TAG,"error");
                }
                break;
            case R.id.bn4:
                objectToJson1();
                break;
            case R.id.bn5:
                objectsToJson();
                break;
            case R.id.bn6:
                fromJsonString();
                break;
            case R.id.bn7:
                tv0.setText("版本信息："+"\n"+VersionInfo);
                break;
            case R.id.bn8:
                try{
                    versionCode();
                }catch (Exception e){
                    Log.d(TAG,"error");
                }
                break;
        }
    }

    //构建JSON对象
    public void objectToJson() throws Exception {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", "zhaokaiqiang");
            jsonObject.put("age", 22);

            Log.d(TAG, jsonObject.toString());
            tv0.setText(jsonObject.toString());
    }

    //构建JSON数组
    public void arrayToJson() throws Exception {

        JSONArray jsonArray = new JSONArray();
        JSONArray p = new JSONArray();
        p.put("zhaokaiqiang");
        p.put(22);
        p.put(Birthday(1992, 1, 19));
        jsonArray.put("toJson");
        jsonArray.put(100);
        jsonArray.put(true);
        jsonArray.put(p);

        Log.d(TAG, jsonArray.toString());
        tv0.setText(jsonArray.toString());

    }

    //构建JSON字符串
    public void jsonStringerToJson() throws Exception {

        JSONStringer jsonStringer = new JSONStringer();

        jsonStringer.object();
        jsonStringer.key("name");
        jsonStringer.value("zhaokaiqiang");
        jsonStringer.key("age");
        jsonStringer.value(22);
        jsonStringer.key("birthday");
        jsonStringer.value(Birthday(1992, 1, 19));
        jsonStringer.endObject();

        Log.d(TAG, jsonStringer.toString());
        tv0.setText(jsonStringer.toString());
    }

    //解析JSON
    public void fromJson() throws Exception {

        JSONStringer jsonStringer = new JSONStringer();

        jsonStringer.object();
        jsonStringer.key("name");
        jsonStringer.value("zhaokaiqiang");
        jsonStringer.key("age");
        jsonStringer.value(22);
        jsonStringer.endObject();

        String jsonString = jsonStringer.toString();
        Log.d(TAG, "生成的json----------" + jsonString);

        JSONTokener jsonTokener = new JSONTokener(jsonString);
        JSONObject jsonObject = (JSONObject) jsonTokener.nextValue();
        String name = jsonObject.getString("name");
        int age = jsonObject.getInt("age");
        Log.d(TAG, "name=" + name);
        Log.d(TAG, "age=" + age);
        tv0.setText("name=" + name+"\t"+"age=" + age);

    }

    ////构建JSON
    public JSONArray Birthday(int a, int b, int c) {
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(a);
        jsonArray.put(b);
        jsonArray.put(c);
        return jsonArray;
    }

    //GSON
    public void objectToJson1() {

        Person p = new Person("zhaokaiqiang", 22, new Birthday(1992, 1, 19));
        Gson gson = new Gson();

        String jsonString = gson.toJson(p);

        Log.d(TAG, "---------单一对象生成--------");
        Log.d(TAG, jsonString);

        Person person = gson.fromJson(jsonString, Person.class);

        Log.d(TAG, "---------单一对象解析--------");
        Log.d(TAG, person.toString());

        tv0.setText("---------单一对象生成--------"+"\n"+jsonString+"\n"+"---------单一对象解析--------"+"\n"+person.toString());

    }

    //GSON
    public void objectsToJson() {

        Gson gson = new Gson();
        Person person = new Person("zhaokaiqiang", 22,
                new Birthday(1992, 1, 19));
        ArrayList<Person> arrayList = new ArrayList<Person>();
        arrayList.add(person);
        arrayList.add(person);
        arrayList.add(person);

        String jsonString = gson.toJson(arrayList);

        Log.d(TAG, "---------集合对象生成--------");
        Log.d(TAG, jsonString);

        Type type = new TypeToken<ArrayList<Person>>() {
        }.getType();

        ArrayList<Person> persons = gson.fromJson(jsonString, type);

        Log.d(TAG, "---------集合对象解析--------");
        Log.d(TAG, persons.toString());
        tv0.setText("---------集合对象生成--------"+"\n"+jsonString+"\n"+"---------集合对象解析--------"+"\n"+persons.toString());
    }


    public String getJsonString() {
        Person p = new Person("zhaokaiqiang", 22, new Birthday(1992, 1, 19));
        JsonElement jsonElement = new JsonParser().parse(new Gson().toJson(p));

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", "zhao");
        jsonObject.addProperty("age", 22);
        jsonObject.add("person", jsonElement);
        Log.d(TAG, "getJsonString------" + jsonObject.toString());
        return jsonObject.toString();
    }

    public void fromJsonString() {

        Gson gson = new Gson();
        JsonElement jsonElement = new JsonParser().parse(getJsonString());

        JsonObject jsonObject = jsonElement.getAsJsonObject();

        JsonElement personElement = jsonObject.get("person");
        Person person = gson.fromJson(personElement, Person.class);

        JsonElement nameElement = jsonObject.get("name");
        String name = nameElement.getAsString();

        JsonElement ageElement = jsonObject.get("age");
        int age = ageElement.getAsInt();

        Log.d(TAG, "person-----" + person);
        Log.d(TAG, "name-----" + name);
        Log.d(TAG, "age-----" + age);
        tv0.setText("person-----" + person+"\n"+"name-----" + name+"\n"+"age-----" + age);
    }

    public void versionCode() {
        try {
            JSONArray array = new JSONArray(VersionInfo);
            JSONObject obj = array.getJSONObject(0);
            JSONObject apkInfo = obj.getJSONObject("apkInfo");

            String versionCode =apkInfo.getString("versionCode");
            String versionName =apkInfo.getString("versionName");
            boolean enabled =apkInfo.getBoolean("enabled");
            tv0.setText("versionCode:"+"\t"+versionCode+"\n"+"versionName:"+"\t"+versionName+"\n"+"enabled:"+enabled);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
