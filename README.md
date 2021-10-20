# java

## Thread

자바에서 쓰레드를 만드는 방법은 두 가지가 있다.

1. **Thread Class를 상속받기**

Thread를 상속받는 클래스를 만들어 run()을 오버라이드 하여 재정의한다.

run()에서는 쓰레드가 실행되는 동안 작업할 내용을 정의한다.

main에서 생성한 Thread class의 인스턴스를 생성하여 start()로 실행한다.

(run()을 통해 실행하면 쓰레드의 함수를 다 수행한 뒤 main의 함수를 실행함)

2. **Runnable 인터페이스를 implements**

Thread Class를 상속받을 경우 자바의 단일 상속 때문에 다른 클래스를 상속받을 수 없다.

이를 해결하기 위해서 implements 방식으로 Thread를 구현하기도 한다.

Runnable 인터페이스를 implements 한 뒤 run()을 구현한다.

main에서 생성한 Runnable class의 인스턴스를 생성하여 Runnable을 파라미터로 받는 쓰레드를 생성하여 start()로 실행할 수 있다.

---

```java
Thread.sleep(mills)
```

쓰레드의 동작을 일시정지할 수 있다.

```java
Thread.setPriority(int)
```

쓰레드의 우선 순위를 설정할 수 있다.

```java
Thread.isAlive()
```

쓰레드 작동 여부를 Boolean 값으로 받아올 수 있다.

``` java
Thread.setDaemon(Boolean)
```

Daemon Thread로 설정할 수 있다. Daemon Thread는 부모 쓰레드가 동작 중일 때만 동작한다.

- 함수에 synchronized를 붙여 쓰레드의 접근을 제어하여 자원을 관리할 수 있다. (하지만 많이 사용하면 성능이 떨어진다.)

- 성능 향상을 위해 함수의 특정 부분만 synchronized (this){}를 사용할 수 있다.

```java
Thread.join()
```

쓰레드의 작업 종료를 기다릴 수 있다 .

```java
Thread.stop()
Thread.inturreupt()
```

Thread.stop()을 사용하면 쓰레드를 중단할 수 있으나 Deprecated되어 Thread.interrupt()를 사용한다.

(하지만 자식 쓰레드의 sleep상태를 깨우는 것 뿐, 자식 쓰레드가 실행 중이면 강제로 중단할 수는 없다.)

```java
Object.wait(mills)
Object.notify()
```

Object.wait(mills)을 사용하여 쓰레드를 멈출 수 있다. 

mills 시간이 지나거나 Object.notify를 통해 멈춤을 해제할 수 있다. (임의의 하나 쓰레드)

Object.notifyAll()을 사용하면 해당 Object를 사용하는 모든 쓰레드를 꺠운다.

## Socket

```java
Socket socket = new Socket(SERVER_ADDRESS, PORT);
```

Socet(ADDRESS, PORT) 인스턴스를 만들어 소켓을 연결할 수 있다.

```java
ServerSocket serverSocket = new ServerSocket(PORT);
```

ServerSocket(PORT) 인스턴스를 만들어 서버 소켓을 열 수 있다.

```java
Socket socket = serverSocket.accept();
```

ServerSocket.accept()를 사용하여 클라이언트 소켓 접속을 기다릴 수 있다.

```java
Socket.close()
```

소켓 연결을 해제할 수 있다.

```java
InputStream is = socket.getInputStream();
OutputStream os = socket.getOutputStream();
```

socket.getInputStream, socket.getOutputStream으로 InputStream, OutputStream을 만들 수 있다.

```java
os.write(bytes);
```

OutputStream.write(byte[])로 서버에 데이터를 보낼 수 있다.

```java
is.read(bytes);
```

InputStream.write(byte[])로 데이터를 받을 수 있다.

## java.nio

java.io의 성능향상을 위해 만들어진 new io

```java
Path path= Paths.get(Uri)
```

해당 Uri의 파일의 경로(Path)를 생성한다.

```java
path.getFileName()
```

해당 경로의 파일의 이름을 반환한다.

```java
path.getParent()
```

해당 경로의 부모 경로를 반환한다.

```java
System.out.println(String.format("디렉토리 여부 : %s", Files.isDirectory(path)));
System.out.println(String.format("파일 여부 : %s", Files.isRegularFile(path)));
System.out.println(String.format("마지막 수정 시간 : %s", Files.getLastModifiedTime(path)));
System.out.println(String.format("파일 크기 : %s", Files.size(path)));
System.out.println(String.format("소유자 : %s", Files.getOwner(path)));
System.out.println(String.format("숨김 여부 : %s", Files.isHidden(path)));
System.out.println(String.format("읽기 가능 여부 : %s", Files.isReadable(path)));
System.out.println(String.format("쓰기 여부 : %s", Files.isWritable(path)));
```

Files.method(path)를 통해 다양한 값들을 받아올 수 있다.

```java
Files.createDirectories(path);
```

디렉토리나 파일을 만들 수 있다.

```java
DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path);
```

해당 경로의 자식들을 소유한 콜랙션을 생성할 수 있다.

```java
for (Path p : directoryStream){}
```

반복문을 통해 순회도 가능하다.

```java
Files.deleteIfExists(path);
```

해당 경로의 파일(디렉토리)을 삭제할 수 있다.

```java
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
```

파일의 변경을 감지할 수 있다.

### Buffer

```java
//1
ByteBuffer byteBuffer = ByteBuffer.allocate(10);
//2
byte[] bytes=new byte[]{1,2,3,4,5};
ByteBuffer byteBuffer2=ByteBuffer.wrap(bytes);
```

두 가지 방법으로 ByteBuffer를 생성할 수 있다.

```java
buffer.limit();
buffer.capacity();
buffer.position();
```

버퍼의 limit, capacity, position을 반환한다.

```java
byteBuffer.put(byte);
```

버퍼에 값을 추가한다.

```java
byteBuffer2.get();
```

버퍼 값을 반환한다.

```java
byteBuffer.flip();
```

버퍼의 포지션을 맨 앞으로 옮긴다. (limit이 값이 있는 버퍼의 크기로 줄어든다)

```java
byteBuffer.rewind();
```

버퍼의 포지션을 맨 앞으로 옮긴다.

```java
byteBuffer.clear();
```

버퍼의 limit을 초기화한다.

```java
byteBuffer.mark();
byteBuffer.reset();
```

버퍼의 position을 mark한 곳으로 reset한다.

## Web

### 서블렛

#### 쿠키 & 세션

http 프로토콜이 아닐 경우 소켓을 통해 클라이언트를 인식,

http 프로토콜은 서버에 요청을 보내고 바로 소캣을 끊어버리기 때문에 소캣으로 클라이언트 인식 불가

이를 위해 나온 것이 쿠키, 세션

서버가 클라이언트에 쿠키를 세팅하면 클라이언트 이를 브라우저에 저장하고 서버에 요청할 떄 쿠키를 같이 보냄. 서버 측에선 쿠키로 클라리언트 구분 - web 초창기

노출의 위험성이 있으므로 안 쓰는 게 좋음 (간단한 값들만 쓰는 게 좋음)

수명을 지정하지 않으면 -1, -1은 사용할 시 수명 업데이트

이를 보완하기 위해 세션이 등장

자바 표준에서는 HttpSession 인터페이스가 있으나 구현체는 서버에 따라 다름

쿠키는 String 타입만 전달 가능하지만 세션은 모든 객체가 가능

### JSP



