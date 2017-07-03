/**
 * Created by e440 on 2017/6/8.
 */
import java.io.*;
import java.util.*;


public class Main {
    static List<File> files = new LinkedList<File>();
    static Map<String,Integer> imports = new HashMap<String,Integer>();
    public static void main(String[] args) {
        if(args.length != 1){
            System.out.println("命令参数不匹配。");
        }

        String dirPath = args[0];
        System.out.println(dirPath);
        File dir = new File(dirPath);
        getFile(dir);
//        System.out.println("开始遍历");
        for(File f:files){
//            System.out.println(f.getPath());
            countImport(f);
        }
        List<Map.Entry<String,Integer>> list =
                new ArrayList<Map.Entry<String,Integer>>(imports.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                return (o2.getValue() - o1.getValue());
            }
        });
        System.out.println("前十个被import最多的类为：");
        if(list.size()<10){
            for(Map.Entry<String,Integer> entry:list){
                String key = entry.getKey();
                Integer val = entry.getValue();
                System.out.println(key+":"+val);
            }
        }else{
            int i=0;
            for(Map.Entry<String,Integer> entry:list){
                String key = entry.getKey();
                Integer val = entry.getValue();
                System.out.println(key+":"+val);
                i++;
                if(i >= 10){
                    break;
                }
            }
        }
//        Iterator iter = imports.entrySet().iterator();
//        while (iter.hasNext()) {
//            Map.Entry entry = (Map.Entry) iter.next();
//            String key = (String)entry.getKey();
//            Integer val = (Integer)entry.getValue();
//            System.out.println(key+":"+val);
//
//        }


    }
    public static void getFile(File f){
        if(f!=null){
            if(f.isDirectory()) {
                File[] fileArray = f.listFiles();
                if (fileArray != null) {
                    for (int i = 0; i < fileArray.length; i++) {
                        //递归调用
                        File file = fileArray[i];
                        if (!file.isDirectory()) {
                            String name = file.getName();
                            String[] pStrs = name.split("\\.");
                            if (!pStrs[pStrs.length - 1].equals("java")) {
//                                System.out.println(name+"不是java文件。");
                            } else {
                                files.add(file);
                            }
                        }
                        getFile(file);
                    }
                }
            }
        }
    }
    public static void countImport(File f){
        try{
            BufferedReader reader = new BufferedReader(new FileReader(f));
            String str = null;
            while ((str = reader.readLine()) != null) {
                String[] pStrs = str.split(" ");
                if((pStrs.length == 2 || pStrs.length == 3) && pStrs[0].equals("import")){
                    String str2 = pStrs[pStrs.length-1];
                    String importName = str2.substring(0,str2.length()-1);
                    if(!imports.containsKey(importName)){
                        imports.put(importName,1);
                    }else{
                        imports.put(importName,imports.get(importName)+1);
                    }
                }

            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}

