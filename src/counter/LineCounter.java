package counter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
 
/**
 * **********************************************
 * @description count lines of texts, Not count the blank line
 *     			get all the files of the index path
 * @author yuan
 * @date 2015-03-09   16:44:33
 ***********************************************
 */
public class LineCounter {
    List<File> list = new ArrayList<File>();
    int linenumber = 0;
     
    FileReader fr = null;
    BufferedReader br = null;
 
    
    public void counter(String projectPath) {
    	String path = projectPath;
        System.out.println(path);
        File file = new File(path);
        File files[] = null;
        files = file.listFiles();
        addFile(files);
        isDirectory(files);
        readLinePerFile();
        System.out.println("\nTotle: " + linenumber + " lines.");
    }
    
    	
    // Directory? or not?
    public void isDirectory(File[] files) {
        for (File s : files) {
            if (s.isDirectory()) {
                File file[] = s.listFiles();
                addFile(file);
                isDirectory(file);
                continue;
            }
        }
    }
 
    
    //Add all files to the list
    public void addFile(File file[]) {
        for (int index = 0; index < file.length; index++) {
            list.add(file[index]);
            // System.out.println(list.size());
        }
    }
     
    
    //Read Lines
    public void readLinePerFile() {
        try {
            for (File s : list) {
                int record = linenumber;
                if (s.isDirectory()) {
                    continue;
                }
                fr = new FileReader(s);
                br = new BufferedReader(fr);
                String i = "";
                while ((i = br.readLine()) != null) {
                    if (isBlankLine(i))
                        linenumber++;
                }
                System.out.print(s.getName());
                System.out.println("\t has " + (linenumber - record) + " lines.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                }
            }
            if (fr != null) {
                try {
                    fr.close();
                } catch (Exception e) {
                }
            }
        }
    }
 
    
    //Blank line? or not?
    public boolean isBlankLine(String i) {
        if (i.trim().length() == 0) {
            return false;
        } else {
            return true;
        }
    }
     
    
    
    public static void main(String args[]) {
        LineCounter lc = new LineCounter();
        String projectName = "E://Develop/JAVA/";     //Index
        lc.counter(projectName);
    }
}
