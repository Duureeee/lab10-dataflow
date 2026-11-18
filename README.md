# Lab 10 — Data Flow Testing (DU Pairs)

**Оюутан:** Б.Дүүрэнбилэг  
**Код:** b232270032  
**Хичээл:** F.CSA313 — Программ хангамжийн чанарын баталгаа ба тест

## Environment

### java -version

```text
openjdk version "17.0.21.0.3" 2026-10-24
OpenJDK Runtime Environment (build 17.0.21.0.3+1-1-24.04.1-Ubuntu)
OpenJDK 64-Bit Server VM (build 17.0.21.0.3+1-1-24.04.1-Ubuntu, mixed mode, sharing)
```

### mvn -version

```text
Apache Maven 3.8.7
Maven home: /usr/share/maven
Java version: 17.0.21.0.3, vendor: Ubuntu, runtime: /usr/lib/jvm/java-17-openjdk-amd64
Default locale: en, platform encoding: UTF-8
OS name: "linux", version: "5.15.167.4-microsoft-standard-wsl2", arch: "amd64", family: "unix"
```

## A. gcd() — DU Analysis

Definition (def) нь хувьсагчид утга оноох үйлдэл.
c-use нь хувьсагчийн утгыг тооцоололд ашиглах,
p-use нь нөхцөл шалгахад ашиглах үйлдэл юм.

Def-clear зам гэдэг нь definition-ээс use хүртэл
тухайн хувьсагч дахин тодорхойлогдоогүй зам юм.

### A1. DU хосууд

| Хувьсагч | Def | Use | Төрөл | Def-clear зам | Тест | Хамрагдсан |
|---|---|---|---|---|---|---|
| x | 13 | 16 | c-use | 13→15(T)→16 | gcdWithTwoIterations | Тийм |
| x | 13 | 20 | c-use | 13→15(F)→20 | gcdWithoutLoop | Тийм |
| x | 17 | 16 | c-use | 17→18→15(T)→16 | gcdWithTwoIterations | Тийм |
| x | 17 | 20 | c-use | 17→18→15(F)→20 | gcdWithTwoIterations | Тийм |
| y | 13 | 15(T) | p-use | 13→15(T) | gcdWithTwoIterations | Тийм |
| y | 13 | 15(F) | p-use | 13→15(F) | gcdWithoutLoop | Тийм |
| y | 13 | 16 | c-use | 13→15(T)→16 | gcdWithTwoIterations | Тийм |
| y | 13 | 17 | c-use | 13→15(T)→16→17 | gcdWithTwoIterations | Тийм |
| y | 18 | 15(T) | p-use | 18→15(T) | gcdWithTwoIterations | Тийм |
| y | 18 | 15(F) | p-use | 18→15(F) | gcdWithTwoIterations | Тийм |
| y | 18 | 16 | c-use | 18→15(T)→16 | gcdWithTwoIterations | Тийм |
| y | 18 | 17 | c-use | 18→15(T)→16→17 | gcdWithTwoIterations | Тийм |
| tmp | 16 | 18 | c-use | 16→17→18 | gcdWithTwoIterations | Тийм |

Нийт: 13 DU хос, бүгд хамрагдсан.

### A2. Тестийн тайлбар

**gcdWithoutLoop**

- Оролт: gcd(7, 0)
- Хүлээгдсэн үр дүн: 7
- Давталт: 0
- Зөвхөн энэ тестээр хамрах хосууд:
  x(13→20), y(13→15F).

**gcdWithTwoIterations**

- Оролт: gcd(12, 8)
- Хүлээгдсэн үр дүн: 4
- Давталт: 2
- Эхний давталт: x=8, y=4.
- Хоёр дахь давталт: x=4, y=0.
- x(17→16), y(18→15T), y(18→16),
  y(18→17) зэрэг хосуудад дараагийн
  давталт шаардлагатай.

### A3. Дүгнэлт

gcd() функцэд x, y, tmp хувьсагчдын definition
болон use цэгүүдийг тодорхойлсон.
Нийт 13 DU хосыг олж, 2 JUnit тестээр
бүгдийг хамруулсан.

Давталтгүй болон олон давталттай замуудыг
хамруулснаар дахин тодорхойлогдсон
хувьсагчдын хэрэглээг шалгасан.

## AI Assistance

Лабораторийн ажлын явцад DU хосуудын анализ,
JUnit тест болон тайлан боловсруулахад AI
туслахаас зөвлөгөө авч, үр дүнг нягтлуулахад
ашигласан.

Кодын ажиллагааг Maven болон JUnit тестээр
шалгаж баталгаажуулсан.
