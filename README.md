## short description of this small project

The Analyzer does a simple overview of your chatting behavior with someone on WhatsApp. This project still may have some messy code so I am really sorry for this.

The file of the chat can be exported in WhatsApp (Android) or directly in WhatsApp Web (right click on chat -> archive chat).

The exported file will have the following layout:

```
18.10.17, 17:10 - Person A: I love programming in Java
18.10.17, 17:37 - Person B: Yeah, it is fun
...
```

The analysis can be done by creating a WamAnalyzer object:

```
WamAnalyzerImpl analyzer = new WamAnalyzerImpl();
analyzer.analyzeWhatsApp("C:\\Users\\Foo\\Desktop\\ChatWithBar.txt");
```

The program will display graphs as result of the analysis:

![1st graph](https://i.gyazo.com/e319691970b9c5ec8fc46718e5e226b4.png)

![2nd graph](https://i.gyazo.com/07d051ed2fe484ad37ab0ab672902561.png)
