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