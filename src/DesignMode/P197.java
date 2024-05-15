package DesignMode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author climb.xu
 * @date 2022/5/9 14:59
 */
public class P197 {
    public static void main(String[] args) {
        Folder folder = new Folder("folder1");
        AbstractFile textFile = new TextFile("1.txt");
        ImageFile imageFile = new ImageFile("1.png");
        VideoFile videoFile = new VideoFile("1.mp4");
        folder.add(textFile);
        folder.add(imageFile);
        folder.add(videoFile);
        folder.display();
        VideoFile videoFile2 = new VideoFile("2.mp4");
        textFile.add(videoFile2);//报错,叶子构件不支持访问及管理子构件
    }
}

//抽象构件
abstract class AbstractFile {
    public abstract void add(AbstractFile file);

    public abstract void remove(AbstractFile file);

    public abstract void display();
}

//构件适配器(因为叶子构件只需要实现display方法)
abstract class FileAdapter extends AbstractFile {
    @Override
    public void add(AbstractFile file) {
        throw new RuntimeException("不支持此操作");
    }

    @Override
    public void remove(AbstractFile file) {
        throw new RuntimeException("不支持此操作");
    }

    @Override
    public void display() {
    }
}

//叶子构件
class ImageFile extends FileAdapter {
    private final String fileName;

    public ImageFile(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void display() {
        System.out.println(fileName);
    }
}

//叶子构件
class TextFile extends FileAdapter {
    private final String fileName;

    public TextFile(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void display() {
        System.out.println(fileName);
    }
}

class VideoFile extends FileAdapter {
    private final String fileName;

    public VideoFile(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void display() {
        System.out.println(fileName);
    }
}

//容器构件
class Folder extends AbstractFile {
    private final String fileName;
    private final List<AbstractFile> fileList;

    public Folder(String fileName) {
        fileList = new ArrayList<>();
        this.fileName = fileName;
    }

    @Override
    public void add(AbstractFile file) {
        fileList.add(file);
    }

    @Override
    public void remove(AbstractFile file) {
        fileList.remove(file);
    }

    @Override
    public void display() {
        System.out.println(fileName + ":");
        for (AbstractFile abstractFile : fileList) {
            System.out.print("\t");
            abstractFile.display();
        }
    }
}