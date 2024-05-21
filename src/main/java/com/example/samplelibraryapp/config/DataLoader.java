package com.example.samplelibraryapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.samplelibraryapp.model.Book;
import com.example.samplelibraryapp.repository.BookRepository;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public void run(String... args) throws Exception {
        // データの重複を避けるためのチェック
    	addBookIfNotExists("雪国", "川端 康成", 1947, "文芸");
    	addBookIfNotExists("吾輩は猫である", "夏目 漱石", 1905, "風刺");
    	addBookIfNotExists("人間失格", "太宰 治", 1948, "文芸");
    	addBookIfNotExists("羅生門", "芥川 龍之介", 1915, "文芸");
    	addBookIfNotExists("銀河鉄道の夜", "宮沢 賢治", 1934, "ファンタジー");
    	addBookIfNotExists("ノルウェイの森", "村上 春樹", 1987, "文芸");
    	addBookIfNotExists("白夜行", "東野 圭吾", 1999, "ミステリー");
    	addBookIfNotExists("夜は短し歩けよ乙女", "森見 登美彦", 2006, "文芸");
    	addBookIfNotExists("告白", "湊 かなえ", 2008, "ミステリー");
    	addBookIfNotExists("博士の愛した数式", "小川 洋子", 2003, "文芸");
    	addBookIfNotExists("金閣寺", "三島 由紀夫", 1956, "文芸");
    	addBookIfNotExists("今夜、すべてのバーで", "中島 らも", 1989, "文芸");
    	addBookIfNotExists("死神の精度", "伊坂 幸太郎", 2005, "文芸");
    	addBookIfNotExists("半沢直樹シリーズ", "池井戸 潤", 2010, "ビジネス");
    	addBookIfNotExists("キッチン", "吉本 ばなな", 1988, "文芸");
    	addBookIfNotExists("巷説百物語", "京極 夏彦", 1999, "ホラー");
    	addBookIfNotExists("OUT", "桐野 夏生", 1997, "ミステリー");
    	addBookIfNotExists("敦煌", "井上 靖", 1959, "歴史");
    	addBookIfNotExists("砂の器", "松本 清張", 1961, "ミステリー");
    	addBookIfNotExists("竜馬がゆく", "司馬 遼太郎", 1962, "歴史");
    	addBookIfNotExists("図書館戦争", "有川 浩", 2006, "SF");
    	addBookIfNotExists("風味絶佳", "山田 詠美", 2002, "文芸");
    	addBookIfNotExists("ビタミンF", "重松 清", 2000, "文芸");
    	addBookIfNotExists("カラスの親指", "道尾 秀介", 2008, "ミステリー");
    	addBookIfNotExists("かがみの孤城", "辻村 深月", 2017, "文芸");
    	addBookIfNotExists("十二国記シリーズ", "小野 不由美", 1992, "ファンタジー");
    	addBookIfNotExists("雷桜", "宇江佐 真理", 2002, "歴史");
    	addBookIfNotExists("千里眼シリーズ", "松岡 圭祐", 1997, "サスペンス");
    	addBookIfNotExists("私の男", "桜庭 一樹", 2007, "文芸");
    	addBookIfNotExists("ソードアート・オンライン", "川原 礫", 2009, "ライトノベル");
    	addBookIfNotExists("とある魔術の禁書目録", "鎌池 和馬", 2004, "ライトノベル");
    	addBookIfNotExists("ログ・ホライズン", "橙乃 ままれ", 2011, "ライトノベル");
    	addBookIfNotExists("Re:ゼロから始める異世界生活", "長月 達平", 2014, "ライトノベル");
    	addBookIfNotExists("この素晴らしい世界に祝福を！", "暁 なつめ", 2013, "ライトノベル");
    	addBookIfNotExists("ノーゲーム・ノーライフ", "榎宮祐", 2012, "ライトノベル");
    	addBookIfNotExists("電波女と青春男", "入間人間", 2009, "ライトノベル");
    	addBookIfNotExists("物語シリーズ", "西尾維新", 2006, "ライトノベル");
    	addBookIfNotExists("俺の妹がこんなに可愛いわけがない", "伏見つかさ", 2008, "ライトノベル");

    }

    private void addBookIfNotExists(String title, String author, int publicYear, String category) {
        if (!bookRepository.existsByTitle(title)) {
            bookRepository.save(new Book(title, author, publicYear, category));
        }
    }
}

