package hum.util;

import hum.graph.unweighted.Graph;
import hum.graph.weighted.WGraph;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

/**
 * @author hum
 */
public class ReadGraph {

    public static boolean readGraph(String filename, Graph graph) {
        if (filename == null) {
            System.out.println("filename is null or words is null");
            return false;
        }
        // 文件读取
        Scanner scanner;
        try {
            File file = new File(filename);
            if (file.exists()) {
                scanner = new Scanner(new BufferedInputStream(new FileInputStream(file)), "UTF-8");
                scanner.useLocale(Locale.ENGLISH);
            } else {
                System.out.println("file not exists");
                return false;
            }
        } catch (IOException ioe) {
            System.out.println("Cannot open " + filename);
            return false;
        }
        if (scanner.hasNextLine()) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            if (n != graph.getV()) {
                System.out.println("doesn't match V");
                return false;
            }
        }
        while (scanner.hasNext()) {
            int v = scanner.nextInt();
            int w = scanner.nextInt();
            graph.addEdge(v, w);
        }
        return true;
    }

    public static boolean readWGraph(String filename, WGraph graph) {
        if (filename == null) {
            System.out.println("filename is null or words is null");
            return false;
        }
        // 文件读取
        Scanner scanner;
        try {
            File file = new File(filename);
            if (file.exists()) {
                scanner = new Scanner(new BufferedInputStream(new FileInputStream(file)), "UTF-8");
                scanner.useLocale(Locale.ENGLISH);
            } else {
                System.out.println("file not exists");
                return false;
            }
        } catch (IOException ioe) {
            System.out.println("Cannot open " + filename);
            return false;
        }
        if (scanner.hasNextLine()) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            if (n != graph.getV()) {
                System.out.println("doesn't match V");
                return false;
            }
        }
        while (scanner.hasNext()) {
            int v = scanner.nextInt();
            int w = scanner.nextInt();
            double weight = scanner.nextDouble();
            graph.addEdge(v, w, weight);
        }
        return true;
    }

}
