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
        addBookIfNotExists("吾輩は猫である", "夏目漱石", 1905, "小説");
        addBookIfNotExists("雪国", "川端康成", 1947, "小説");
        addBookIfNotExists("羅生門", "芥川龍之介", 1915, "小説");
        addBookIfNotExists("人間失格", "太宰治", 1948, "小説");
        addBookIfNotExists("こころ", "夏目漱石", 1914, "小説");
        addBookIfNotExists("おくのほそ道", "松尾芭蕉", 1702, "紀行文");
        addBookIfNotExists("源氏物語", "紫式部", 1008, "古典文学");
        addBookIfNotExists("坊っちゃん", "夏目漱石", 1906, "小説");
        addBookIfNotExists("平家物語", "作者不詳", 1300, "古典文学");
        addBookIfNotExists("万葉集", "大伴家持", 750, "詩集");
    }

    private void addBookIfNotExists(String title, String author, int publicYear, String category) {
        if (!bookRepository.existsByTitle(title)) {
            bookRepository.save(new Book(title, author, publicYear, category));
        }
    }
}

