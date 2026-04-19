package com.yjb.business.app.service;

import com.yjb.business.app.dto.RequestUserDto;

public interface BooksService {
    public String getBooksList();
    public String getBookById(RequestUserDto id);
    public String insteBook(String title,String author);
    public String getBookInfoByName(String title);
}
