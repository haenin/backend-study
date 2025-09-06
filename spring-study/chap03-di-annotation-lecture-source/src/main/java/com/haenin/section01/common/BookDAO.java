package com.haenin.section01.common;

import java.util.List;

public interface BookDAO {
    List<BookDTO> findAllBook();

    BookDTO findBookBySequenceOf(int sequence);
}
