package nio;

import java.nio.file.*;
import java.util.List;

public class FileStudy {
    public static void studyPath() throws Exception {
        Path path = Paths.get("/Users/jinstonlee/Documents/");

        Path dirPath = Paths.get("/Users/jinstonlee/Documents/study/nio/", "midir");
        Path filePath = Paths.get("/Users/jinstonlee/Documents/study/nio/", "midir", "myFile.txt");
        WatchService watchService = FileSystems.getDefault().newWatchService();
        path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY);
        while (true){
            WatchKey watchKey= watchService.take();
            List<WatchEvent<?>> events=watchKey.pollEvents();
            for (WatchEvent<?> event : events) {
                Path eventPath = (Path)event.context();
                WatchEvent.Kind<?> kind = event.kind();

                if (kind == StandardWatchEventKinds.ENTRY_CREATE) {
                    System.out.println(
                            String.format("파일 %s 가 생성되었습니다.",
                                    eventPath.getFileName()));
                }
                else if (kind == StandardWatchEventKinds.ENTRY_MODIFY) {
                    System.out.println(
                            String.format("파일 %s 가 수정되었습니다.",
                                    eventPath.getFileName()));
                }
                else if (kind == StandardWatchEventKinds.ENTRY_DELETE) {
                    System.out.println(
                            String.format("파일 %s 가 삭제되었습니다.",
                                    eventPath.getFileName()));
                }
            }

            boolean valid = watchKey.reset();

            if (!valid) {
                break;
            }
        }
        watchService.close();


        if (Files.notExists(dirPath)) {
            Files.createDirectories(dirPath);
        }
        if (Files.notExists(filePath)) {
            Files.createFile(filePath);
        }

        DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path);
        for (Path p : directoryStream) {

        }
        Files.deleteIfExists(filePath);
        Files.deleteIfExists(dirPath);


        System.out.println(path.getFileName());
        System.out.println(path.getParent());
        System.out.println(path.getNameCount());

        System.out.println(String.format("디렉토리 여부 : %s", Files.isDirectory(path)));
        System.out.println(String.format("파일 여부 : %s", Files.isRegularFile(path)));
        System.out.println(String.format("마지막 수정 시간 : %s", Files.getLastModifiedTime(path)));
        System.out.println(String.format("파일 크기 : %s", Files.size(path)));
        System.out.println(String.format("소유자 : %s", Files.getOwner(path)));
        System.out.println(String.format("숨김 여부 : %s", Files.isHidden(path)));
        System.out.println(String.format("읽기 가능 여부 : %s", Files.isReadable(path)));
        System.out.println(String.format("쓰기 여부 : %s", Files.isWritable(path)));


    }

    public static void main(String[] args) {
        try {
            studyPath();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
