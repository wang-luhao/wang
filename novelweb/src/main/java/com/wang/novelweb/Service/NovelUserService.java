package com.wang.novelweb.Service;

import com.wang.novelweb.Entity.NovelUser;

public interface NovelUserService {
    public NovelUser LoginCheck(String phone, String password);
}
